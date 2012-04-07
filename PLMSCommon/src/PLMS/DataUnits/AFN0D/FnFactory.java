package PLMS.DataUnits.AFN0D;

import PLMS.DataUnits.AFN05.F31;
import PLMS.DataUnits.AbstractFn;
import PLMS.Enums.AFN;
import com.jidesoft.swing.MarqueePane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述AFN=OD下，可用的所有Fn   ；工具类
 */
public class FnFactory {
    public ArrayList<Object> UsedFn;
    public Map<String, Class> MapFn;
    //------------------------------------------------------
    public FnFactory(AFN afn) {
        MapFn = new HashMap<String, Class>();
        switch (afn) {
            case CONFIRM_DENY:
                break;
            case CONTROL_CMD:
                break;
            case PARAMETERS_SETTING:
                break;
            case LINK_DETECT:
                break;
            case REQUEST_CLASS_1:
                break;
            case REQUEST_CLASS_2:
                initAFN0D();
                break;
            case REQUEST_CLASS_3:
                break;
        }

    }

    public void initAFN00() {
        MapFn.put("F1", F1.class);       //全部确认：对收到报文中的全部数据单元标识进行确认
        MapFn.put("F2", F81.class);      //全部否认：对收到报文中的全部数据单元标识进行否认
        MapFn.put("F3", F81.class);      //按数据单元标识确认和否认：对收到报文中的全部数据单元标识进行逐个确认/否认
    }

    public void initAFN01() {
        MapFn.put("F1", F1.class);
        MapFn.put("F2", F81.class);
        MapFn.put("F3", F81.class);
    }

    public void initAFN02() {
        MapFn.put("F1", F1.class);
        MapFn.put("F2", F81.class);
        MapFn.put("F3", F81.class);
    }

    public void initAFN04() {
        MapFn.put("F1", F1.class);
        MapFn.put("F2", F81.class);
        MapFn.put("F3", F81.class);
    }

    public void initAFN05() {
        MapFn.put("F31", PLMS.DataUnits.AFN05.F31.class);  //对时命令

    }

    public void initAFN0A() {

        MapFn.put("F1", F1.class);//Fn=1  通讯参数(只用心跳周期)  一帧
        MapFn.put("F3", F1.class);//    Fn=3  IP 地址和端口  一帧
        MapFn.put("F4", F1.class); //    Fn=4  主站电话和短信中心号码  一帧
        MapFn.put("F6", F1.class); //Fn=6  终端组地址  一帧
        MapFn.put("F7", F1.class);// Fn=7  终端抄表日设置  一帧
        MapFn.put("F9", F1.class);// Fn=9  配置数量表  一帧
        MapFn.put("F10", F1.class); //Fn=10  暂无  一帧
        MapFn.put("F16", F1.class); //Fn=16  虚拟专网用户名和密码  一帧
        MapFn.put("F21", F1.class);//Fn=21  费率时段和费率数  一帧
        MapFn.put("F24", F1.class); //Fn=24  终端抄表间隔(min)  一帧
        MapFn.put("F26", F1.class);//    Fn=26  测量点限值参数(阀值参数)  一帧

    }

    public void initAFN0C() {
        MapFn.put("F1", F1.class);//Fn=1  终端版本信息  一帧
        MapFn.put("F2", F1.class);// Fn=2  终端日历时钟  一帧
        MapFn.put("F25", F1.class);//Fn=25  当前三相及总有/无功功率、功率因数，  三相电压、电流、零序电流             一帧
        MapFn.put("F27", F1.class);// Fn=27  电能表日历时钟及电能表状态信息  一帧
        MapFn.put("F33", F1.class);// Fn=33  当前正向有/无功电能示值、一/四象限无         功电能示值（总、费率 1~M）  一帧
        MapFn.put("F34", F1.class);//Fn=34  当前反向有/无功电能示值、二/三象限无        功电能示值（总、费率 1~M）
        MapFn.put("F129", F1.class);// Fn=129      (扩展)     分相正向有功电能示值
        MapFn.put("F130", F1.class);//  Fn=130      (扩展)     分相正向无功电能示值
        MapFn.put("F131", F1.class);// Fn=131      (扩展)     分相反向有功电能示值
        MapFn.put("F132", F1.class);// Fn=132      (扩展)     分相反向无功电能示值

    }

    public void initAFN0D() {
        MapFn.put("F1", F1.class);
        MapFn.put("F81", F81.class);       //合相有功功率
        MapFn.put("F82", F81.class);       // A 相有功功率
        MapFn.put("F83", F81.class);       //B 相有功功率
        MapFn.put("F84", F81.class);        //C 相有功功率
        MapFn.put("F85", F81.class);       // 合相无功功率
        MapFn.put("F86", F81.class);        //A 相无功功率
        MapFn.put("F87", F81.class);        //B 相无功功率
        MapFn.put("F88", F81.class);        // C 相无功功率
        MapFn.put("F89", F81.class);       //A 相电压
        MapFn.put("F90", F81.class);       //B 相电压
        MapFn.put("F91", F81.class);      // C 相电压
        MapFn.put("F92", F81.class);       //A 相电流
        MapFn.put("F93", F81.class);       //B 相电流
        MapFn.put("F94", F81.class);      // C 相电流
        MapFn.put("F95", F81.class);       //合相电流
        MapFn.put("F105", F81.class);     // 合相功率因素
        MapFn.put("F106", F81.class);     //A 相功率因素
        MapFn.put("F107", F81.class);     //B 相功率因素
        MapFn.put("F108", F81.class);     //C 相功率因素
        MapFn.put("F145", F81.class);      //A 相电压谐波含量
        MapFn.put("F146", F81.class);      //B 相电压谐波含量
        MapFn.put("F147", F81.class);      //C 相电压谐波含量
        MapFn.put("F149", F81.class);      //A 相电流谐波含量
        MapFn.put("F150", F81.class);      //B 相电流谐波含量
        MapFn.put("F151", F81.class);      //C 相电流谐波含量
        MapFn.put("F185", F185.class);      //A 相电压合格率
        MapFn.put("F186", F185.class);      //B 相电压合格率
        MapFn.put("F187", F187.class);      //C 相电压合格率
        MapFn.put("F193", F81.class);      //正向有/无功电能示值（总、费率 1~M）
        MapFn.put("F194", F81.class);      // 反向有/无功电能示值（总、费率 1~M）
        MapFn.put("F197", F81.class);      // 正向有功分相电能示值
        MapFn.put("F198", F81.class);     //正向无功分相电能示值
        MapFn.put("F199", F81.class);     //反向有功分相电能示值
        MapFn.put("F200", F81.class);     // 反向无功分相电能示值
    }

    public void initAFN0E() {
        MapFn.put("F1", F1.class);   //Erc=14  终端停、上电事件  一帧
        MapFn.put("F1", F1.class);    //Erc=61  表盖报警  一帧
        MapFn.put("F1", F1.class);    //Erc=62  端钮盖报警  一帧
        MapFn.put("F1", F1.class);   // Erc=63  变压器防盗报警  一帧
        MapFn.put("F1", F1.class);    //Erc=64  终端停电事件  一帧
        MapFn.put("F1", F1.class);
        MapFn.put("F1", F1.class);   // Erc=70    (扩展码)    计量芯片数据读出错误  一帧
        MapFn.put("F1", F1.class);    //Erc=71    (扩展码)    计量芯片未校准错误  一帧
        MapFn.put("F1", F1.class);    //Erc=72    (扩展码)    计量芯片校验和错  一帧
        MapFn.put("F1", F1.class);    //Erc=73    (扩展码)    缓冲芯片存储错误  一帧
        MapFn.put("F1", F1.class);   //Erc=74    (扩展码)    缓冲芯片参数区 CRC 错误  一帧
        MapFn.put("F1", F1.class);    //Erc=75  暂无  一帧
        MapFn.put("F1", F1.class);   //Erc=76  2 芯片数据传输错误  一帧
        MapFn.put("F1", F1.class);    //Erc=77  FLASH 初始化错误  一帧
    }

    /**
     * 创建一个Fn对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends AbstractFn> T create(Class<T> clazz) {
        boolean hasFn = false;
        for (Class c : MapFn.values()) {
            if (c == clazz) {
                hasFn = true;
                break;
            }
        }
        T obj = null;
        if (hasFn) {
            try {
                obj = (T) clazz.newInstance();
            } catch (Exception e) {

            }
        } else {

        }
        return obj;
    }

    public static void main(String[] args) {
        FnFactory fac = new FnFactory(AFN.REQUEST_CLASS_2);
        F1 f1 = fac.create(F1.class);
        System.out.print(f1.length());
    }
}
