package PLMS.server.handlers;

import PLMS.frames.*;
import PLMS.JavaBean.TerminalBean;
import PLMS.Messages.LoginMessage;
import PLMS.Utils.SessionUtil;
import com.adamtaft.eb.EventBusService;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 对Message的处理程序(转发器)
 * 该类接收各种解码后的消息对象，通过判读后转发给特定的消息事件处理类处理；
 * 对于所有消息都要进行的处理，则可以在本类中进行
 */
public class ServerHandlerTransponder extends DemuxingIoHandler {
    // ----------------------------------------------------------------------------------
    public static Logger log = LoggerFactory.getLogger(ServerHandlerTransponder.class);

    public ServerHandlerTransponder() {
        /*
        *  注册服务器的事件处理程序
        *  当服务器收到一个上行消息后，
        * （1）对上行请求消息处理（如做出回复）
        * （2）对上行回复消息处理 (如获取数据，保存到数据库）
        */
        this.addReceivedMessageHandler(UpRequestMessage.class, new UpRequestMessageHandler());
        this.addReceivedMessageHandler(UpResponseMessage.class, new UpResponseMessageHandler());
        //  也可以对特殊的上行消息特殊处理（如登录消息进行回复）
        this.addReceivedMessageHandler(LoginMessage.class, new LoginMessageHandler());
        this.addReceivedMessageHandler(UpMessage.class, new UpMessageHandler());



        /**
         * 当服务器发送一个下行消息后
         * （1）下行请求消息的处理（如计时器开始，防止超时）
         * （2）下行回复消息的处理
         */
        this.addSentMessageHandler(DownMessage.class, new DownMessageHandler());
        this.addSentMessageHandler(DownResponseMessage.class, new DownResponseMessageHandler());
        this.addSentMessageHandler(DownRequestMessage.class, new DownRequestMessageHandler());
        /**
         *  错误处理
         *  根据不通的错误类型进行不同的处理
         */
        //this.addExceptionHandler(PLMSException.class, new ErrorHandler());
    }
    // ---------------------------------------------------------------------------------------------
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("---------------对接收的消息进行预处理-------------");
        System.out.println(((UpMessage)message).getSequence());
        super.messageReceived(session, message);
        //session.setAttributeIfAbsent("STATUS", "创建连接...");
        updateInfo(session);

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
       // updateInfo(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        // super.exceptionCaught(session, cause);
    }
    // ----------------------------------------------------------------------------------------------
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        session.setAttribute("STATUS", "创建连接...");
        updateInfo(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        log.info("创建新会话.......");
        log.info("    当前服务端连接总数："+session.getService().getManagedSessionCount());
        log.info("    当前会话创建时间："+session.getCreationTime());
        InetSocketAddress s=(InetSocketAddress)session.getRemoteAddress();
        log.info("    当前会话远端地址："+s.getAddress().toString());
        log.info("    当前会话服务器发送消息："+session.getWrittenMessages());
        //连接成功后，设置会话信息，并保存
        //session.setAttributeIfAbsent("PFC", new PFC());
         session.setAttribute("STATUS", "连接成功!");
         session.setAttributeIfAbsent("UTIL",new SessionUtil(session));

        // 如果当前连接的终端已经登录
        //if(session.getAttribute("STATUS")==){

        //}
        updateInfo(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        log.info("关闭会话，服务端当前连接："+session.getService().getManagedSessionCount());
        session.setAttribute("STATUS", "连接关闭!");
        updateInfo(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        log.info("服务端进入空闲状态..."+session.getLastBothIdleTime());
        session.setAttribute("STATUS", "空闲...");
        if(session.getBothIdleCount()>5){
          //  session.close(false);
        }
      //  updateInfo(session);

    }
    public void logSession(IoSession session)
    {
        log.info("服务端连接总数："+session.getService().getManagedSessionCount());
        log.info("当前会话");
    }

    public void updateInfo(IoSession session)
    {
        //log.info("服务端连接总数："+session.getService().getManagedSessionCount());
        InetSocketAddress clientAddr=(InetSocketAddress)session.getRemoteAddress();
        TerminalBean termianlBean=new TerminalBean(session);
      //termianlBean.setTerminalAddress(123);
      // termianlBean.setTermailIP(clientAddr.getAddress().toString());
      // termianlBean.setTerminalStatus("LOGIN");
     //   termianlBean.setSessionId(1);
        //a.setTerminalID(123);
        EventBusService.publish(termianlBean);
    }

}
