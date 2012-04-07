package PLMS.server.handlers;


import PLMS.Messages.LoginMessage;
import org.apache.mina.core.session.IoSession;

/**
 * 对登录消息的处理类
 */
public class LoginMessageHandler extends AbstractMessageHandler<LoginMessage> {
    @Override
    public void handleMessage(IoSession session, LoginMessage loginMessage) throws Exception {
        super.handleMessage(session, loginMessage);

        System.out.println("登录上传消息处理:"+loginMessage.getClass());
       // System.out.println("登录上传消息处理:"+ loginMessage.addressDomain.regionAddress);


}
}
