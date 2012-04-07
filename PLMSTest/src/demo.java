import com.adamtaft.eb.EventBusService;
import com.adamtaft.eb.EventHandler;
import com.adamtaft.eb.VetoException;

/**
 * 测试
 */


public class demo {
    @EventHandler
    public void handleStringEvent(Integer event) {
        System.out.print(event);
       // throw new AssertionError("Should not happen, event was vetoed.");
    }

    @EventHandler(canVeto =true)
    public void vetoStringEvent(String event) {
        throw new VetoException();
    }

    public static void main(String[] args) throws InterruptedException {
        demo meh = new demo();
        EventBusService.subscribe(meh);

        Thread.sleep(3000);
        // 这次，不会显示，因为事件被否决了，不会传送给regular handlers
        EventBusService.publish("Some String Event");
        EventBusService.publish(new Integer(1));
        Thread.sleep(13000);
    }
}


          


    


