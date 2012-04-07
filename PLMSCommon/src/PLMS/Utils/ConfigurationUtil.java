package PLMS.Utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * 配置文件读取的工具类
 */
public class ConfigurationUtil {
    private Configuration conf;

    //--------------------------------------------
    public ConfigurationUtil(String file) {
        loadConfig(file);
    }

    // -------------------------------------------------------------
    public Configuration getConfig() {
        return conf;
    }

    public void loadConfig(String file) {
        String prefix = file.substring(file.lastIndexOf(".") + 1);
        if (prefix.toLowerCase() == "xml") {
            loadXmlFile(file);
        }
        if (prefix.toLowerCase() == "properties") {
            loadPropertyFile(file);
        }
    }

    public void loadPropertyFile(String file) {
        try {
             conf = new PropertiesConfiguration(file);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void loadXmlFile(String file) {
        try {
            conf = new XMLConfiguration(file);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key, String def) {
        String ret;
        if (conf.getString(key) != "") {
            ret = def;
        } else {
            ret = (String) conf.getString(key, def);
        }
        return ret;
    }
}
