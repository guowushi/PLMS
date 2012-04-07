package plms.client.handler;

import PLMS.DataUnits.AbstractFn;
import PLMS.frames.*;
import PLMS.Enums.AFN;
import PLMS.Enums.EnumFn;
import PLMS.Enums.EnumInfoPoint;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端处理
 */
public class Demo1ClientHandler extends IoHandlerAdapter {
    private static Logger log = LoggerFactory.getLogger(Demo1ClientHandler.class);

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.info("消息发送成功：" +message.toString());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("已连接到服务器,会话ID：" +session.getId());

        session.setAttributeIfAbsent("PFC", new PFC());


        //创建LoginMessage消息
       UpMessage msg=new UpMessage();


        //LoginMessage login_message=new LoginMessage();
        msg.setPRM(1);     // (1)启动站标识
        msg.setFunCode(9); //   控制域功能码（必须手工）
        msg.setACD(0);  //   ACD=0 请求访问位


        //
        AddressDomain addr=new AddressDomain();
        addr.regionAddress=0x3030;          //手工指定参数
        addr.termialAddress=0xea03;         //手工指定参数
        addr.mainStationAddress=0;         //手工指定参数
         msg.body.setAddr(addr); ;
       // msg.addressDomain=new AddressDomain(0x3030,0xea03,0);

        msg.body.setAfn(AFN.LINK_DETECT) ;   //手工指定参数


        SeqDomain seqDomain =new SeqDomain();
        seqDomain.setCON(1);                                    //手工指定参数
        seqDomain.setFIR(1);
        seqDomain.setFIN(1);
        seqDomain.setTpV(0);
        seqDomain.setSEQNumber(0x0);
        msg.body.setSeqDomain(seqDomain);
        UserDataFragment u1=  new UserDataFragment();
        u1.addMeasuredPoint(EnumInfoPoint.P0);      //手工指定参数
        AbstractFn fn=u1.addFn(msg.getAfnCode(),EnumFn.F01);   //手工指定参数
        msg.body.dataUnits.add(u1);

        // 填写自定计算的参数
        msg.header.setUserDataLength(msg.body.length());
        msg.footer.setCheckSum(msg.body.getCheckSum());
      //  MessageOperation.sendMessage(session,login_message);   //发送消息对象
        MessageOperation.sendMessage(session,msg);   //发送消息对象
        MessageOperation.sendMessage(session,msg);   //发送消息对象
    }

    public void autoComplete()
    {

    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        log.info("客户端收到的信息为：" + msg);

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        log.info("连接断开！");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("客户端异常：", cause);
    }
}
