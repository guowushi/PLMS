package plms;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 */
public class Gateway {
    IoSession clientSession;
    IoSession serverSession;
    // 使用map(key,value)保存每个会话的 MessageStack和 stackDown
    public MessageStack stackUp;
    public MessageStack stackDown;
    public Gateway(){
      stackUp=new MessageStack();
      ExecutorService pool= Executors.newCachedThreadPool();
       Runnable s=new GWServer(stackUp,stackDown);
        Runnable c=new GWClient(stackUp,stackDown);
      pool.execute(s);
      pool.execute(c);
    }
    public static void main(String[] args) {
           new Gateway();
   }

}
