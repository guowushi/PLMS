package PLMS.server.handlers;

import PLMS.Exceptions.PLMSException;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.ExceptionHandler;

/**
 * 对某个异常处理
 */
public class ErrorHandler implements ExceptionHandler<PLMSException> {
    @Override
    public void exceptionCaught(IoSession session, PLMSException e) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("PLMS发生错误！");
    }
}
