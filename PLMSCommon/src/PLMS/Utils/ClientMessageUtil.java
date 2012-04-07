package PLMS.Utils;

import PLMS.Enums.AFN;
import PLMS.frames.UpRequestMessage;
import PLMS.Messages.LoginMessage;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端的消息处理工具
 * User: Administrator
 */
public class ClientMessageUtil extends MessageOperation{


    public ClientMessageUtil(IoSession session) {
        super(session);
    }

    /**
     * 创建一个消息
     * @param afn
     */
    public static void createMessage(AFN afn)
    {
        UpRequestMessage msg=new UpRequestMessage();
        msg.setAFN(afn);
        msg.setRegionAddress(0x3030);
        msg.setTermialAddress(0x11);
    }
    public static  void sendMessage()
    {

    }
    public static void login()
    {

        LoginMessage msg=new LoginMessage();

        msg.setMainStationAddress(1);
        msg.setRegionAddress(0x3030);
        msg.setTermialAddress(4);


    }

    public void sendKeepAlive()
    {

    }

}
