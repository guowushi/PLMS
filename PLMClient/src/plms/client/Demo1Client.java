package plms.client;

import PLMS.frames.KeepAliveMessageImpl;
import PLMS.codec.MessagesProtocolCodecFactory;
import plms.client.handler.MultiClientHandler;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 客户端
 */
public class Demo1Client {
    public static Logger log = LoggerFactory.getLogger(Demo1Client.class);
    private String HOST = "127.0.0.1";
    private int PORT = 3005;
    public static String RUNAT="终端";
    public int TermailAddr=0x1010;  // 终端地址
    public IoSession currentSession=null;
    private int clientID;   //  客户端的编号
    public Demo1Client(){}
    public Demo1Client(int clientID){
        this.clientID=clientID;
    }
    /**
     * 载入客户端的配置信息
     * @param xmlconfig
     */
    public void loadConfig(String xmlconfig)
    {
        try {
            XMLConfiguration conf=new XMLConfiguration(xmlconfig) ;
            HOST=conf.getString("PLMS.server.ip",HOST);
            PORT= conf.getInt("PLMS.server.port",PORT);
        } catch (ConfigurationException e) {
            System.out.println("读取客户端配置文件失败！");
        }
    }

    /**
     * 连接服务器
     */
    public void connectServer(String ip,int port)
    {
        //-----------------创建非阻塞的客户端 -------------------------------
        IoConnector connector = new NioSocketConnector();
        // ---------------------设置连接超时时间--------------------------------
        //connector.setConnectTimeout(3000);
        connector.setConnectTimeoutMillis(30000);
        // ---------------------创建keepAliveFilter--------------------------------
        KeepAliveFilter keepAliveFilter = new KeepAliveFilter(new KeepAliveMessageImpl());
        //设置参数
        keepAliveFilter.setForwardEvent(false);
        keepAliveFilter.setRequestInterval(30);  //每个30秒发一个心跳
        keepAliveFilter.setRequestTimeout(10);  // 10秒没有回复则执行handler
        // 规定时间后没有返回数据包，添加Handler
        keepAliveFilter.setRequestTimeoutHandler(
                //对KeepAlive的处理代码
                new KeepAliveRequestTimeoutHandler() {
                    public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
                        System.out.println("连接已无响应...关闭session。");
                        session.close(true);
                    }

                });
        //connector.getFilterChain().addLast("KeepAlive", keepAliveFilter);
        // ----------------------------------------------------------------------
        connector.getFilterChain().addLast("logger",new LoggingFilter());

        // （1）创建编解码工厂对象
        //  (2) 利用工厂对象创建创建ProtocolCodecFilter对象
        //  (3) 将Filter对象添加到FilterChain中
        MessagesProtocolCodecFactory mf=new MessagesProtocolCodecFactory(false);
        ProtocolCodecFilter fl=new ProtocolCodecFilter(mf);
        connector.getFilterChain().addLast("PLMS", fl);
       // connector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
        //connector.getFilterChain().addLast("executor2", new ExecutorFilter(IoEventType.WRITE));
        // -------------------------设置消息处理--------------------------------------
        MultiClientHandler clientHandler =new MultiClientHandler();
        connector.setHandler(clientHandler);
        // ----------------------------------------------------------------------------
         
        try {
            ConnectFuture futute = connector.connect(new InetSocketAddress(ip, port));
            futute.awaitUninterruptibly();
            currentSession = futute.getSession();
        } catch (Exception e) {
            log.error("连接服务器错误！");
        }
   
    }

    /**
     * 关闭连接
     */
    public void closeConnection()
    {
        this.currentSession.close(true);                    //关闭当前客户端会话
        this.currentSession.getCloseFuture().awaitUninterruptibly(); //一直等待，直到获取Future对象，
        this.currentSession.getService().dispose();        // 真正关闭
    }
}
