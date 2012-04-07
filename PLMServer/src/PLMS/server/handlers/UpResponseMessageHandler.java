package PLMS.server.handlers;

import PLMS.frames.UpResponseMessage;
import org.apache.mina.core.session.IoSession;

/**
 * Created by IntelliJ IDEA.
 */
public class UpResponseMessageHandler extends AbstractMessageHandler<UpResponseMessage> {
    @Override
    public void handleMessage(IoSession session, UpResponseMessage upResponseMessage) throws Exception {
        super.handleMessage(session, upResponseMessage);
        System.out.println("对客户端发送的上传回复消息进行处理");
    }
}
