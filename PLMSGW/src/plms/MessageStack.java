package plms;

import javax.tools.JavaCompiler;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-2-26
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class MessageStack {

       Stack<Object> stacks;
       private int MAX_LENGTH;
       public synchronized Object getMessage( )
       {
           // 如果仓库空了
           while(stacks.size()==0)
           {
               try {
                  // 等待，并且从这里退出push()
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
          Object o=stacks.pop();
          notifyAll();
          return o;

       }
    public synchronized void saveMessage(Object o)
    {
        // ------------如果仓库满了--------------------
        while (stacks.size()==MAX_LENGTH)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stacks.push(o);
       // 就唤醒有可能等待的消费者，让他们醒来，准备消费
        notifyAll();


    }

}

