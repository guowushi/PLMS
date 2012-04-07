package PLMS.DataUnits;

/**
 * 所有Fn的抽象类
 */
public abstract class AbstractFn {
    protected String description="";          //
    //-------------------------------------------------------------------
    public  abstract  int length();            // 获取Fn数据单元的长度
    public  abstract  byte[] valueOf();        //获取Fn所表示的字节数组
    public  abstract  void init(byte[] in );   // 初始化的字节数组
    public  String getDescription()           //获取Fn的描述信息
    {
       return description;
    }
}
