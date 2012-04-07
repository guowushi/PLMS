package PLMS.server.handlers;
import PLMS.frames.UpRequestMessage;
import org.apache.mina.core.session.IoSession;

/**
 * 服务器 对 收到的 请求消息 进行处理
 */
public class UpRequestMessageHandler extends AbstractMessageHandler<UpRequestMessage> {
    @Override
    public void handleMessage(IoSession session, UpRequestMessage upRequestMessage) throws Exception {
        super.handleMessage(session, upRequestMessage);
        System.out.println("对客户端发送的上传请求消息进行处理");
    }
}
