package PLMS.frames;

/**
 * 每个会话都有一个字节的计数器（0-255）表示启动帧的序列号
 * 启动站每发送一个消息，FPC加1， 重发帧不加1
 */
public class PFC {
    private int value;
    public PFC() {
        value=0;
    }
    public int valueOf()
    {
        return value;
    }    
    public int nextSeq()
    {
       int currentvalue=value;
       //currentvalue=currentvalue %255;

        value=(value+1) %255;

        return currentvalue;
    }
}
