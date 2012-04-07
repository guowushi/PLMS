package plms.client.handler;

import PLMS.Messages.LoginMessage;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;

/**
 * 服务器收到一个登录消息后的处理
 */
public class LoginMessageHandler implements MessageHandler<LoginMessage> {
    @Override
    public void handleMessage(IoSession session, LoginMessage loginMessage) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.print("aaaaaa");
    }
}
