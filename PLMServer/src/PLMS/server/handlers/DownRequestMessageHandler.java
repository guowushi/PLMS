package PLMS.server.handlers;

import PLMS.frames.DownRequestMessage;
import org.apache.mina.core.session.IoSession;

/**
 * Created by IntelliJ IDEA.
 */
public class DownRequestMessageHandler extends AbstractMessageHandler<DownRequestMessage> {
    @Override
    public void handleMessage(IoSession session, DownRequestMessage downRequestMessage) throws Exception {
        super.handleMessage(session, downRequestMessage);
        log.info("服务器对下行请求消息处理");
        if(downRequestMessage.needConfirm())
        {
            // 当前消息，计时器开始
            //session.
        }
    }
}
