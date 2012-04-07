package PLMS.frames;

import PLMS.DataForm.A16;

/**
 * 附加信息域
 */                                           
public abstract class AbstractAUX {
    
    protected byte[] tp; //时间标签
    protected int pfc;   // 启动帧序号计数器
    protected A16 timeStamp;   //启动帧发送时标
    protected int maxDelay;         // 允许延时时间
    protected int len;
    public  int length() {
        return len;
    }
    //--------------------------------------------
    public abstract byte[] valueOf();
    public abstract boolean  isValid();
    public abstract void init(byte[] in);
    
}
