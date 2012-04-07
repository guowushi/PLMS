package PLMS.Base.Event;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-2-24
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
public class submitEvent extends AWTEvent{
    public submitEvent(Object source, int id) {
        super(source, id);
    }
}
