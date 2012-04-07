package PLMS.GUI;

import java.util.EventObject;

/**
 自定义的收到消息事件
 */
public class MessageReceived  extends EventObject {
    private Object obj;
    public MessageReceived(Object source) {
        super(source);
        obj=source;
    }
}
