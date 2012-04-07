package PLMS.server.handlers;

import PLMS.Enums.*;
import PLMS.frames.*;
import PLMS.Utils.MessageOperation;
import PLMS.Utils.SessionUtil;
import org.apache.mina.core.session.IoSession;

/**
 * 对服务器收到的上行消息的处理类
 * 服务器收到的上行请求消息只能是以下几种
 * （1）链路检查（登录，退出登录，心跳）消息 ，此时服务器应确认
 * （2）对服务器复位消息、参数设置、控制命令的确认消息
 * （3）查参数、请求1类，2类，3类数据的回复数据，将数据保存到数据库中
 */
public class UpMessageHandler extends AbstractMessageHandler<UpMessage> {


    @Override
    public void handleMessage(IoSession session, UpMessage upMessage) throws Exception {
        super.handleMessage(session, upMessage);
        System.out.println("服务器上传消息事件处理:" + UpMessage.class);
        System.out.println("会话:" + session.getId());


        SessionUtil util=(SessionUtil) session.getAttribute("UTIL");
        //----------------------------------------------------

            //---------按功能处理 ------------------
            switch (upMessage.getAfnCode()) {
                case CONFIRM_DENY:
                    afn00_handler();
                    break;
                case LINK_DETECT:
                    afn02_handler(session,upMessage);
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
        if(!upMessage.isRequest())
        {
            util.setPFC(upMessage.getSequence());
        }
        session.setAttribute("UTIL",util);     //重新更新
 }



    /**
     * 对回复确认消息的处理
     * 从当前会话的发送消息队列中，查找消息的SEQ，如果和收到的消息的SEQ一样
     */
    public void afn00_handler()
    {
         // 对哪个消息的确认
    }
    /**
     * 对链路检查消息的处理
     *
     */
    public void afn02_handler(IoSession session, UpMessage msg)
    {
        UserDataFragment udf=msg.getBody().dataUnits.get(0);
        SessionUtil util=(SessionUtil)session.getAttribute("UTIL");
        System.out.println( "当前会话序列号:"+util.getPFC());
        // --------------(1)登录---------------------
        if(udf.containFn(EnumFn.F01))
          {
              MessageOperation.sendMessage(session, MessageOperation.answer(msg,true));
              session.setAttribute("STATUS","登录成功");
              System.out.println("登录!");
          }
        // --------------(2)退出登录---------------------
        if(udf.containFn(EnumFn.F02))
        {
            MessageOperation.sendMessage(session, MessageOperation.answer(msg,true));
            session.setAttribute("STATUS","退出登录");
            System.out.println("退出登录!");
        }
        // --------------(3)心跳 ------------------------
        if(udf.containFn(EnumFn.F03))
        {
            MessageOperation.sendMessage(session, MessageOperation.answer(msg,true));
            System.out.println("心跳!");
        }
    }
    public void   updateSession(IoSession session)
    {

    }
    public void save()
    {
         System.out.println("处理处理");
    }
}

