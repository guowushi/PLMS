package PLMS.DataUnits;

import PLMS.DataUnits.AFN0D.*;
import PLMS.Enums.AFN;
import PLMS.Enums.MSG_DIRECTION;
import PLMS.Exceptions.FnNotImplementationException;
import PLMS.frames.DownMessage;

import java.util.*;

/**
 * 描述AFN可用的所有Fn   ；工具类
 */
public class FnFactory implements IFnFactory {

    public Map<String, Class> DownFn = new HashMap<String, Class>();  //下行Fn
    private Map<String,String> FnDescriptions=new HashMap<String, String>(); //Fn描述信息
    public Map<String, Class> UpFn = new HashMap<String, Class>();    //上行Fn
    private AFN afn;                                                //当前的AFN
    private Map<String,Object>  fnUsed=new HashMap<String, Object>();

    //--------------------构造函数----------------------------------
    public FnFactory()
    {

    }
    public FnFactory(AFN afn)  throws FnNotImplementationException{
        this.afn = afn;
        switch (afn) {
            case CONFIRM_DENY:
                initAFN00();
                break;
            case RESET:
                initAFN01();
                break;
            case LINK_DETECT:
                initAFN02();
                break;
            case PARAMETERS_SETTING:
                initAFN04();
                break;
            case CONTROL_CMD:
                initAFN05();
                break;
            case QUERY_PARAMETERS:
                initAFN0A();
                break;
            case REQUEST_CLASS_1:
                initAFN0C();
                break;
            case REQUEST_CLASS_2:
                initAFN0D();
                break;
            case REQUEST_CLASS_3:
                initAFN0E();
                break;
        }

    }
    // ----------------------------------------------------------------------

    /**
     * 注册一个Fn
     * @param clazz
     * @param <T>
     */
    public <T extends AbstractFn> void registerFn(MSG_DIRECTION dir, String str, Class<T> clazz,String description)   throws FnNotImplementationException{
        try{
        if (Class.forName(str) != null) {
            if (dir == MSG_DIRECTION.DownStream) {
                DownFn.put(str, clazz);
                FnDescriptions.put(str,description);
            } else {
                UpFn.put(str, clazz);
                FnDescriptions.put(str,description);
            }
        } else {

        }
        }catch (ClassNotFoundException e)
        {
           throw  new FnNotImplementationException("asa");
        }
    }
    /**
     * 注册一个Fn
     * @param clazz
     * @param <T>
     */
    public <T extends AbstractFn> void registerFn(MSG_DIRECTION dir, String str, Class<T> clazz)   throws FnNotImplementationException{
        try{
            if (Class.forName(str) != null) {
                if (dir == MSG_DIRECTION.DownStream) {
                    DownFn.put(str, clazz);

                } else {
                    UpFn.put(str, clazz);

                }
            } else {

            }
        }catch (ClassNotFoundException e)
        {
            throw  new FnNotImplementationException("asa");
        }
    }
    /**
     * 判断是否有可用的Fn
     *
     * @param fnStr
     * @return
     */
    public boolean isFnAvailable(String fnStr) {
        return DownFn.containsKey(fnStr);
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * AFN=00
     * 确认否认消息
     */
    public void initAFN00()  throws FnNotImplementationException{

            registerFn(MSG_DIRECTION.DownStream,"F1", PLMS.DataUnits.AFN00.F1.class);       //全部确认：对收到报文中的全部数据单元标识进行确认
            registerFn(MSG_DIRECTION.DownStream,"F2", PLMS.DataUnits.AFN00.F2.class);      //全部否认：对收到报文中的全部数据单元标识进行否认
            registerFn(MSG_DIRECTION.DownStream,"F3", PLMS.DataUnits.AFN00.F3.class);      //按数据单元标识确认和否认：对收到报文中的全部数据单元标识进行逐个确认/否认

    }

    /**
     * 复位
     */
    public void initAFN01()  throws FnNotImplementationException  {

            registerFn(MSG_DIRECTION.DownStream,"F1", PLMS.DataUnits.AFN01.F1.class);      // 硬件初始化
            registerFn(MSG_DIRECTION.DownStream,"F2", PLMS.DataUnits.AFN01.F2.class);       // 数据区初始化  p0
            registerFn(MSG_DIRECTION.DownStream,"F3", PLMS.DataUnits.AFN01.F3.class);      //参数及全体数据区初始化    p0


    }

    /**
     * 链路检查
     */
    public void initAFN02() throws FnNotImplementationException {
        UpFn.put("F1", PLMS.DataUnits.AFN02.F1.class);      //登录
        UpFn.put("F2", PLMS.DataUnits.AFN02.F2.class);      //退出登录
        UpFn.put("F3", PLMS.DataUnits.AFN02.F3.class);      //心跳
    }

    /**
     * 设置参数-
     */
    public void initAFN04() throws FnNotImplementationException {

            registerFn(MSG_DIRECTION.DownStream,"F1", PLMS.DataUnits.AFN04.F1.class);    // 终端通信参数设置
            DownFn.put("F3", PLMS.DataUnits.AFN04.F3.class);    //    Fn=3  IP 地址和端口  一帧
            DownFn.put("F4", PLMS.DataUnits.AFN04.F1.class);    //    Fn=4  主站电话和短信中心号码  一帧
            DownFn.put("F6", PLMS.DataUnits.AFN04.F1.class);    //Fn=6  终端组地址  一帧
            DownFn.put("F7", PLMS.DataUnits.AFN04.F1.class);    // Fn=7  终端抄表日设置  一帧
            DownFn.put("F9", PLMS.DataUnits.AFN04.F1.class);    // Fn=9  配置数量表  一帧
            DownFn.put("F10", PLMS.DataUnits.AFN04.F1.class);   //Fn=10  暂无  一帧
            DownFn.put("F16", PLMS.DataUnits.AFN04.F1.class);   //Fn=16  虚拟专网用户名和密码  一帧
            DownFn.put("F21", PLMS.DataUnits.AFN04.F1.class);   //Fn=21  费率时段和费率数  一帧
            DownFn.put("F24", PLMS.DataUnits.AFN04.F1.class);   //Fn=24  终端抄表间隔(min)  一帧
            DownFn.put("F26", PLMS.DataUnits.AFN04.F1.class);   // 测量点限值参数(阀值参数)  一帧



    }

    // --------------------------控制命令--------------------------------
    public void initAFN05()  throws FnNotImplementationException{
        DownFn.put("F31", PLMS.DataUnits.AFN05.F31.class);  //对时命令
        //(1)
        FnObject fnObject=new FnObject();
        fnObject.setName("对时命令");
        fnObject.setClazz(PLMS.DataUnits.AFN05.F31.class);
        fnUsed.put("F31",fnObject);

    }

    // --------------------------查询参数----------------------------------
    public void initAFN0A()  throws FnNotImplementationException{

        UpFn.put("F1", PLMS.DataUnits.AFN04.F1.class);//Fn=1  通讯参数(只用心跳周期)  一帧
        UpFn.put("F3", PLMS.DataUnits.AFN04.F3.class);//    Fn=3  IP 地址和端口  一帧
        UpFn.put("F4", PLMS.DataUnits.AFN04.F1.class); //    Fn=4  主站电话和短信中心号码  一帧

        DownFn.put("F6", PLMS.DataUnits.AFN04.F1.class); //Fn=6  终端组地址  一帧
        DownFn.put("F7", PLMS.DataUnits.AFN04.F1.class);// Fn=7  终端抄表日设置  一帧
        DownFn.put("F9", PLMS.DataUnits.AFN04.F1.class);// Fn=9  配置数量表  一帧
        DownFn.put("F10", PLMS.DataUnits.AFN04.F1.class); //Fn=10  暂无  一帧
        DownFn.put("F16", PLMS.DataUnits.AFN04.F1.class); //Fn=16  虚拟专网用户名和密码  一帧
        DownFn.put("F21", PLMS.DataUnits.AFN04.F1.class);//Fn=21  费率时段和费率数  一帧
        DownFn.put("F24", PLMS.DataUnits.AFN04.F1.class); //Fn=24  终端抄表间隔(min)  一帧
        DownFn.put("F26", PLMS.DataUnits.AFN04.F1.class);//    Fn=26  测量点限值参数(阀值参数)  一帧

    }

    // ---------------------------请求1类参数---------------------------------------
    public void initAFN0C()  throws FnNotImplementationException{
        DownFn.put("F1", F1.class);//Fn=1  终端版本信息  一帧
        DownFn.put("F2", F1.class);// Fn=2  终端日历时钟  一帧
        DownFn.put("F25", F1.class);//Fn=25  当前三相及总有/无功功率、功率因数，  三相电压、电流、零序电流             一帧
        DownFn.put("F27", F1.class);// Fn=27  电能表日历时钟及电能表状态信息  一帧
        DownFn.put("F33", F1.class);// Fn=33  当前正向有/无功电能示值、一/四象限无         功电能示值（总、费率 1~M）  一帧
        DownFn.put("F34", F1.class);//Fn=34  当前反向有/无功电能示值、二/三象限无        功电能示值（总、费率 1~M）
        DownFn.put("F129", F1.class);// Fn=129      (扩展)     分相正向有功电能示值
        DownFn.put("F130", F1.class);//  Fn=130      (扩展)     分相正向无功电能示值
        DownFn.put("F131", F1.class);// Fn=131      (扩展)     分相反向有功电能示值
        DownFn.put("F132", F1.class);// Fn=132      (扩展)     分相反向无功电能示值



    }

    // -------------------------请求2类参数-----------------------------------
    public void initAFN0D()  throws FnNotImplementationException{
        DownFn.put("F1", F1.class);
        DownFn.put("F81", F81.class);       //合相有功功率
        DownFn.put("F82", F81.class);       // A 相有功功率
        DownFn.put("F83", F81.class);       //B 相有功功率
        DownFn.put("F84", F81.class);        //C 相有功功率

        DownFn.put("F85", F85.class);       // 合相无功功率
        DownFn.put("F86", F85.class);        //A 相无功功率
        DownFn.put("F87", F85.class);        //B 相无功功率
        DownFn.put("F88", F85.class);        // C 相无功功率

        DownFn.put("F89", F89.class);       //A 相电压
        DownFn.put("F90", F89.class);       //B 相电压
        DownFn.put("F91", F89.class);      // C 相电压

        DownFn.put("F92", F92.class);       //A 相电流
        DownFn.put("F93", F92.class);       //B 相电流
        DownFn.put("F94", F92.class);      // C 相电流
        DownFn.put("F95", F92.class);       //合相电流

        DownFn.put("F105", F105.class);     // 合相功率因素
        DownFn.put("F106", F105.class);     //A 相功率因素
        DownFn.put("F107", F105.class);     //B 相功率因素
        DownFn.put("F108", F105.class);     //C 相功率因素

        DownFn.put("F145", F145.class);      //A 相电压谐波含量
        DownFn.put("F146", F145.class);      //B 相电压谐波含量
        DownFn.put("F147", F145.class);      //C 相电压谐波含量
        DownFn.put("F149", F149.class);      //A 相电流谐波含量
        DownFn.put("F150", F149.class);      //B 相电流谐波含量
        DownFn.put("F151", F149.class);      //C 相电流谐波含量

        DownFn.put("F185", F185.class);      //A 相电压合格率
        DownFn.put("F186", F186.class);      //B 相电压合格率
        DownFn.put("F187", F187.class);      //C 相电压合格率

        DownFn.put("F193", F193.class);      //正向有/无功电能示值（总、费率 1~M）
        DownFn.put("F194", F193.class);      // 反向有/无功电能示值（总、费率 1~M）

        DownFn.put("F197", F197.class);      // 正向有功分相电能示值
        DownFn.put("F198", F198.class);     //正向无功分相电能示值
        DownFn.put("F199", F197.class);     //反向有功分相电能示值
        DownFn.put("F200", F198.class);     // 反向无功分相电能示值
    }

    // --------------------请求3类参数-------------------------------------
    public void initAFN0E()  throws FnNotImplementationException{
        DownFn.put("F1", F1.class);   //Erc=14  终端停、上电事件  一帧
        DownFn.put("F1", F1.class);    //Erc=61  表盖报警  一帧
        DownFn.put("F1", F1.class);    //Erc=62  端钮盖报警  一帧
        DownFn.put("F1", F1.class);   // Erc=63  变压器防盗报警  一帧
        DownFn.put("F1", F1.class);    //Erc=64  终端停电事件  一帧
        DownFn.put("F1", F1.class);
        DownFn.put("F1", F1.class);   // Erc=70    (扩展码)    计量芯片数据读出错误  一帧
        DownFn.put("F1", F1.class);    //Erc=71    (扩展码)    计量芯片未校准错误  一帧
        DownFn.put("F1", F1.class);    //Erc=72    (扩展码)    计量芯片校验和错  一帧
        DownFn.put("F1", F1.class);    //Erc=73    (扩展码)    缓冲芯片存储错误  一帧
        DownFn.put("F1", F1.class);   //Erc=74    (扩展码)    缓冲芯片参数区 CRC 错误  一帧
        DownFn.put("F1", F1.class);    //Erc=75  暂无  一帧
        DownFn.put("F1", F1.class);   //Erc=76  2 芯片数据传输错误  一帧
        DownFn.put("F1", F1.class);    //Erc=77  FLASH 初始化错误  一帧
    }
    // -----------------------------------------------------------------------------------------------

    /**
     * 创建一个Fn对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends AbstractFn> T create(Class<T> clazz) {
        boolean hasFn = false;
        for (Class c : DownFn.values()) {
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
                e.getMessage();
            } finally {

            }

        } else {

        }
        return obj;
    }

    public void getAvailableFn() {


    }
    //---------------------------------------------------
    public static void main(String[] args)  throws FnNotImplementationException{
        FnFactory fac = new FnFactory(AFN.REQUEST_CLASS_2);
        DownMessage mg = new DownMessage();

        F1 f1 = fac.create(F1.class);
        System.out.print(f1.length());
    }
}
