package plms.client.handler;

import PLMS.frames.DownMessage;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端对下行消息的处理
 */
public class DownMessageHandler extends AbstractMessageHandler<DownMessage> {
    @Override
    public void handleMessage(IoSession session, DownMessage downMessage) throws Exception {
        super.handleMessage(session, downMessage);
        log.info("客户端收到下行消息后的事件处理");

        if (downMessage.needConfirm()) {
            //发送确认消息
            MessageOperation.sendMessage(session,  MessageOperation.answer(downMessage, true));

        } else {
            //按功能处理
            switch (downMessage.getAfnCode()) {
                case CONFIRM_DENY:
                    break;
                case LINK_DETECT:
                    break;
                case Repeater_Station_Commad:
                    break;
                case PARAMETERS_SETTING:
                    break;
                case CONTROL_CMD:
                    break;
                case AUTHENTICATION:
                    break;
                case QUERY_PARAMETERS:
                    break;
                case REQUEST_CLASS_1:
                    break;
                case REQUEST_CLASS_2:
                    break;
                case REQUEST_CLASS_3:
                    break;

            }
        }
    }
}
