package PLMS.frames;

import PLMS.Messages.KeepAliveMessage;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 客户端每隔一段时间就发送一个KeepAlive消息给主站.
 */
public class KeepAliveMessageImpl implements KeepAliveMessageFactory{
    KeepAliveMessage request;
    KeepAliveMessage response;
    @Override
    public boolean isRequest(IoSession session, Object o) {
        //----------判断是否是Keep-live请求消息-----------------------
        if(o.getClass()==DownMessage.class)
        {
            return true;
        }
        else{return false;}
    }

    @Override
    public boolean isResponse(IoSession session, Object o) {

        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        return request;
    }

    @Override
    public Object getResponse(IoSession session, Object o) {
        return response;
    }
}
