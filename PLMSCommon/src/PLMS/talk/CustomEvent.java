package PLMS.talk;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 */
public class CustomEvent  extends AWTEvent{

    Object module;
    public Object getModule() {
        return module;
    }

    public Object getUserDate() {
        return userDate;
    }

    public void setUserDate(Object userDate) {
        this.userDate = userDate;
    }

    Object userDate;
    public CustomEvent(Object module ,Object source, int id) {
        super(source, id);
        this.module=module;
    }

}
