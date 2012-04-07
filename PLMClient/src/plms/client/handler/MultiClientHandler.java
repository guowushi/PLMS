package plms.client.handler;

import PLMS.Enums.AFN;
import PLMS.Enums.RequestFunctionCode;
import PLMS.frames.*;
import PLMS.Utils.SessionUtil;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端事件处理
 */
public class MultiClientHandler extends DemuxingIoHandler
 {
     private static Logger log = LoggerFactory.getLogger(MultiClientHandler.class);
     public MultiClientHandler() {
             /**
              * 注册客户端的事件处理程序
              * 当客户端收到一个消息后
              */
             this.addReceivedMessageHandler(DownMessage.class,new DownMessageHandler());
             /**
              * 当客户端发送一个消息后
              */
             this.addSentMessageHandler(UpMessage.class, new UpMessageHandler());
     }
     // ------------------------------------------------------------------------------------
     @Override
     public void messageReceived(IoSession session, Object message) throws Exception {
         super.messageReceived(session, message);
         log.info("客户端收到消息,消息类：" +message.getClass());
     }

     @Override
     public void messageSent(IoSession session, Object message) throws Exception {
         super.messageSent(session, message);
         log.info("客户端消息发送成功,消息类：" +message.getClass());
     }

     @Override
     public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
         // super.exceptionCaught(session, cause);
     }
     // --------------------------------------------------------------------------------------
     @Override
     public void sessionCreated(IoSession session) throws Exception {
         super.sessionCreated(session);
     }

     @Override
     public void sessionOpened(IoSession session) throws Exception {
         super.sessionOpened(session);
         log.info("客户端已连接到服务器,会话ID：" +session.getId());
          session.setAttributeIfAbsent("PFC", new PFC());
         session.setAttributeIfAbsent("UTIL",new SessionUtil(session));
     }

     @Override
     public void sessionClosed(IoSession session) throws Exception {
         super.sessionClosed(session);
         log.info("客户端连接断开！");
     }

     @Override
     public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
         super.sessionIdle(session, status);
         log.info("客户端闲置状态中！");
         SessionUtil util = (SessionUtil) session.getAttribute("UTIL");

     }

     public void  sendmsg()
     {

         DownRequestMessage msg=new DownRequestMessage();

         msg.setFCV(1);
         msg.setFCB(0);
         msg.setFuncode(RequestFunctionCode.LINK_TEST);
         msg.setAFN(AFN.PARAMETERS_SETTING);
         msg.setMainStationAddress(1);
         msg.setRegionAddress(0x3030);
         msg.setTermialAddress(4);

     }
 }
