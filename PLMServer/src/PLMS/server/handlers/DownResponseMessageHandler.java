package PLMS.server.handlers;
import PLMS.frames.DownResponseMessage;
import org.apache.mina.core.session.IoSession;

/**
 * 对下行回复消息的处理
 */
public class DownResponseMessageHandler extends AbstractMessageHandler<DownResponseMessage> {
    @Override
    public void handleMessage(IoSession session, DownResponseMessage downResponseMessage) throws Exception {
        super.handleMessage(session, downResponseMessage);
        log.info("服务器对已发送的下行回复消息处理.......");
    }
}
