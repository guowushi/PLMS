package plms.client.handler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 所有消息都要进行的处理代码
 */
public abstract class AbstractMessageHandler<T> implements MessageHandler<T> {
    // -------------------------- log ----------------------------------------------------
    public  static Logger log= LoggerFactory.getLogger(AbstractMessageHandler.class);
    @Override
    public void handleMessage(IoSession session, T t) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        //System.out.println("接收到的消息类:"+ t.getClass());
    }
}
