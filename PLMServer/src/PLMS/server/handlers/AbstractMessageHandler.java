package PLMS.server.handlers;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有消息都要进行的处理代码
 */
public abstract class AbstractMessageHandler<T> implements MessageHandler<T> {
    public  static Logger log= LoggerFactory.getLogger(AbstractMessageHandler.class);
    @Override
    public void handleMessage(IoSession session, T t) throws Exception {
        //发送事件
      // Module.getLoudSpeaker().CustomEvent(Server.getInstance(),new CustomEvent(Server.getInstance(),this,Server.CUSTOM_RECV));
        DefaultSubject<Object> subject=new DefaultSubject<Object>();
        //subject.Attach( );

        //To change body of implemented methods use File | Settings | File Templates.
        //System.out.println("接收到的消息类:"+ t.getClass());
    }
}
