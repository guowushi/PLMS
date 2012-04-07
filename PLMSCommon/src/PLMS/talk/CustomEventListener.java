package PLMS.talk;

import java.util.EventListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-16
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public interface CustomEventListener extends EventListener {
    public void todo(CustomEvent event);
}
