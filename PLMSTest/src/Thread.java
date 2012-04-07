import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-2-25
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
import static java.util.concurrent.TimeUnit.*;



class BeeperControl {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper = new Runnable() {
            public void run() { System.out.println("beep"); }
        };
       // final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 1000, 1000, TimeUnit.MILLISECONDS);

       /* scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, TimeUnit.MILLISECONDS);*/
    }


    public static void main(String[] args)
    {
        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);
        final Runnable beeper = new Runnable() {
            public void run() { System.out.println("beep"); }
        };
        // final ScheduledFuture<?> beeperHandle =
        scheduler.scheduleAtFixedRate(beeper, 1000, 1000, TimeUnit.MILLISECONDS);
          //  new BeeperControl().beepForAnHour();
    }
}
