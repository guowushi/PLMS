package PLMS.Base;

import PLMS.Base.Event.submitEvent;

import java.util.EventListener;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-2-24
 * Time: 下午10:38
 * To change this template use File | Settings | File Templates.
 */
public interface submitListener extends EventListener {
    public boolean CanSubmit(submitEvent e);
}