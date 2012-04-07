package PLMS.server.handlers;

import PLMS.frames.DownMessage;
import PLMS.Utils.SessionUtil;
import org.apache.mina.core.session.IoSession;

/**
 * 下行
 */
public class DownMessageHandler extends AbstractMessageHandler<DownMessage> {
    @Override
    public void handleMessage(IoSession session, DownMessage downMessage) throws Exception {
        super.handleMessage(session, downMessage);
        System.out.println("服务器下行消息处理");
        System.out.println("会话:" + session.getId());
        SessionUtil util=(SessionUtil) session.getAttribute("UTIL");
        //----------------------------------------------------

        //---------按功能处理 ------------------
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
        // 如果不是回复消息
        if(!downMessage.isRequest())
        {
            util.setPFC(downMessage.getSequence());
        }
        session.setAttribute("UTIL",util);     //重新更新
    }
}
