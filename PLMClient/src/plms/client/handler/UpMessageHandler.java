package plms.client.handler;

import PLMS.frames.UpMessage;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端对 上行消息的处理
 * （1）如果需要回复，则开始计时
 */
public class UpMessageHandler extends AbstractMessageHandler<UpMessage> {
    @Override
    public void handleMessage(IoSession session, UpMessage upMessage) throws Exception {
        super.handleMessage(session, upMessage);
        log.info("客户端对已发送的上传消息处理...");


    }
}
