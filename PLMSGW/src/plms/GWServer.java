package plms;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.ProxyConnector;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * 监听外网请求
 */
public class GWServer implements Runnable{
    private int GWPORT=3333;
    private String GWIP="127.0.0.1";
    NioSocketAcceptor acceptor ;
    ProxyConnector proxyConnector;
    MessageStack stackUp,stackDown;
    public GWServer(MessageStack stack1,MessageStack stack2) {
        this.stackUp = stack1;
        this.stackDown=stack2;
    }

    @Override
    public void run()  {

        try {
            // ---------------------创建TCP Socket -----------------------------------------
            acceptor = new NioSocketAcceptor();
            acceptor.setHandler(new ServerHandler(stackUp,stackDown) );
            acceptor.bind(new InetSocketAddress(GWIP,GWPORT));
            // --------------获取所有会话--------------------
            Map<Long,IoSession> map= acceptor.getManagedSessions();
            for(Long key:map.keySet())
            {
                   map.get(key).write(stackDown.getMessage());
            }
        } catch (Exception e) {
            //log.error("服务器初始化异常...");
            e.printStackTrace();
        } 
    }
}
