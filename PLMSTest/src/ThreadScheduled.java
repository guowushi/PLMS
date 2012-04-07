import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-2-25
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
public class ThreadScheduled {
    public static void main(String[] args)
    {

        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);
       // Sechder job = new Sechder();
        //--------------任务1------
      Runnable task = new Runnable() {
            public void run() {
                //就算这个任务异常也不会影响其他任务
                System.out.print("SlightBevelBorder");
              //  throw new RuntimeException();
            }
        };

        scheduExec.scheduleAtFixedRate(
                task,    //要执行的任务
                1000, 1000,
                TimeUnit.MILLISECONDS);

    }

}
