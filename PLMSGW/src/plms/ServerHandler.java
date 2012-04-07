package plms;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by IntelliJ IDEA.
 */
public class ServerHandler extends IoHandlerAdapter{
    private MessageStack stackUp;
    private MessageStack stackDown;
    public ServerHandler(MessageStack stack1,MessageStack stack2) {
        this.stackUp=stack1;
        this.stackDown=stack2;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        //Thread.currentThread().notify();
        // 接收到客户端连接
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        //PLMS.server <----------------gateway<-----------------------plms.client
        stackUp.saveMessage(message);
      // session.write( stackDown.getMessage());     ????




    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
