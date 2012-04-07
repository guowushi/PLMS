package PLMS.GUI;

import java.util.EventListener;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-16
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
public interface MessageReceivedListener extends EventListener {
    public void doEvent(MessageReceived dm);
}
