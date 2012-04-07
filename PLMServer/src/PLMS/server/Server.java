package PLMS.server;

import PLMS.codec.MessagesProtocolCodecFactory;
import com.adamtaft.eb.EventBus;
import com.adamtaft.eb.EventBusService;
import PLMS.server.handlers.ServerHandlerTransponder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.reqres.ResponseInspector;
import org.apache.mina.filter.reqres.ResponseType;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import javax.swing.*;

/**
 * 服务器 主线程程序
 */
public class Server {
    private int PORT = 3005;                            // 默认端口
    private String IP = "0.0.0.0";                     // 默认IP
    public static Logger log = LoggerFactory.getLogger(Server.class);
    private static IoAcceptor acceptor = null;
    private static Server server = new Server();

    private EventBus eventBus=EventBusService.getInstance();
    public int status;
    public final static int CUSTOM_RECV = 0;
    /**
     * 私有的构造函数（使用单例模式，保证系统只有一个服务器实例）
     */
    private Server() {
    }
    //----------------------------------------------------------

    public EventBus getEventBus() {
        return eventBus;
    }

    public static Server getInstance() {
        return server;
    }
    public IoAcceptor getIoAcceptor() {

        return acceptor;
    }

    /**
     * 载入服务器的配置文件
     */
    public void loadConf(String xmlconfig) {
        try {
            XMLConfiguration conf = new XMLConfiguration(xmlconfig);
            IP = conf.getString("PLMS.server.ip", IP);
            PORT = conf.getInt("PLMS.server.port", PORT);
            //System.out.print(PORT);
        } catch (ConfigurationException e) {
            System.out.println("读取配置文件(func.xml)失败！");
        }

    }

    /**
     * 服务器端初始化（只进行一次）
     */
    public void init() {
        loadConf("funcs.xml");
        try {
            // ---------------------创建TCP Socket -----------------------------------------
            acceptor = new NioSocketAcceptor();
            // ---------------------设置log Filter----------------------------------------------
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
           // ScheduledThreadPoolExecutor schedPool= new ScheduledThreadPoolExecutor(5);
            //RequestResponseFilter reqFilter=new RequestResponseFilter(new MyResponseInspector(),schedPool);


            //MyCoderFactory mf=new MyCoderFactory();
            MessagesProtocolCodecFactory mf = new MessagesProtocolCodecFactory(true);
            ProtocolCodecFilter fl = new ProtocolCodecFilter(mf);
            acceptor.getFilterChain().addLast("PLMS", fl);

            acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
            // ----------------------服务器端参数设置------------------------------------------
            //acceptor.getSessionConfig().setReadBufferSize(20);// 设置读缓冲
            // 设置如果10秒后没有上下数据传输，触发
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            // -----------------------服务器事件处理--------------------------------------------
            ServerHandlerTransponder ht = new ServerHandlerTransponder();
            acceptor.setHandler(ht);
            log.info("服务器初始化成功！");
            status = 1;
        } catch (Exception e) {
            log.error("服务器初始化异常...");
            e.printStackTrace();
        }
    }

    /**
     * 启动服务器
     */
    public void start() {
        try {
            // ------------------------绑定IP和端口 ---------------------------------------------
            acceptor.bind(new InetSocketAddress(IP, PORT));
            log.info("服务器端口监听成功！" + IP + ":" + PORT);
            status = 2;
        } catch (Exception e) {
            log.error("服务器端口监听异常...");
            JOptionPane.showMessageDialog(null,"端口被使用！");

            e.printStackTrace();
        }
    }

    /**
     * 服务器端暂时停止服务
     */
    public void pause() {
        acceptor.unbind();
    }

    /**
     * 继续运行
     */
    public void resume() {
        this.start();
    }

    /**
     * 关闭服务器
     */
    public void stop() {
        acceptor.unbind();
        log.info("服务器停止！");
        if (acceptor.isDisposed() != true) {
            //  acceptor.dispose();
        }
    }


   class MyResponseInspector implements ResponseInspector{

       @Override
       public Object getRequestId(Object message) {
           return null;  //To change body of implemented methods use File | Settings | File Templates.
       }

       @Override
       public ResponseType getResponseType(Object message) {
           return null;  //To change body of implemented methods use File | Settings | File Templates.
       }
   }



}
