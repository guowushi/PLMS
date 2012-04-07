package plms;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * 用于连接服务器
 */
public class GWClient implements Runnable{
   
    private int PORT=3005;
    private String IP="127.0.0.1";
  

    private MessageStack stackUp,stackDown;

    public GWClient(MessageStack stack1,MessageStack stack2) {
        this.stackUp = stack1;
        this.stackDown=stack2;
    }

    @Override
    public void run() {
            // 创建客户端，连接服务器

        //-----------------创建非阻塞的客户端 -------------------------------
        IoConnector connector = new NioSocketConnector();
        // ---------------------设置连接超时时间--------------------------------
        //connector.setConnectTimeout(3000);

        //connector.setConnectTimeoutMillis(30000);
       connector.setHandler(new ClientHandler(stackUp,stackDown));
        try {
             ConnectFuture futute = connector.connect(new InetSocketAddress(IP, PORT));
            futute.awaitUninterruptibly();
             futute.getSession().write(stackUp.getMessage());    //取消息转发

        } catch (Exception e) {
           // log.error("连接服务器错误！");
        }
    }

}
