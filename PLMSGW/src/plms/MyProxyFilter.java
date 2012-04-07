package plms;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.filter.ProxyFilter;

/**
 * Created by IntelliJ IDEA.
 */
public class MyProxyFilter extends ProxyFilter{
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws ProxyAuthException {
        super.messageReceived(nextFilter, session, message);

    }

    @Override
    public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) {
        super.filterWrite(nextFilter, session, writeRequest);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void writeData(NextFilter nextFilter, IoSession session, WriteRequest writeRequest, boolean isHandshakeData) {
        super.writeData(nextFilter, session, writeRequest, isHandshakeData);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        super.messageSent(nextFilter, session, writeRequest);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        super.sessionCreated(nextFilter, session);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
        super.sessionOpened(nextFilter, session);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(nextFilter, session, status);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        super.sessionClosed(nextFilter, session);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
