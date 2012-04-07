package PLMS.Utils;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSession;

/**
 * 某一个会话状态对象
 */
public class SessionInfo  {
    public int MSA;         //主站地址
    public int TERMADDR;    //终端地址
    public STATUS status;   //状态
   // public
    public int sno=0;
    private  int  count;
    {
          count=0;
          status=STATUS.CONNECTED;
    }
    //----------------------------------------------------------------------
    /**
     *  每个会话都有一个字节的计数器（0-255）表示启动帧的序列号
     *  启动站每发送一个消息，FPC加1， 重发帧不加1
     * @return
     */
    public int getPFC()
    {
        int currentvalue=count;
        //currentvalue=currentvalue %255;
        count=(count) %255;
        return currentvalue;
    }

    // ------------------------------------------
    public enum  STATUS{
        CONNECTED,
        LOGINED,
        Alive;
    }
    
}
