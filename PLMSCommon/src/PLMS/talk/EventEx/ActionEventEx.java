package PLMS.talk.EventEx;

import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-10
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class ActionEventEx extends ActionEvent{
    /**
     * 用于区分发生事件的具体对象，例如是Button1 还是Button2 是JMenuItem1 还是JMenuItem2
     * */
    protected int talkId;
    public int getTalkId(){return  talkId;}
    public void setTalkId(int id){talkId=id;}
    public ActionEventEx(Object source, int id, String command) {
        super(source, id, command);
    }
    public ActionEventEx(ActionEvent e){
        super(e.getSource(),e.getID(),e.getActionCommand(),e.getWhen(),e.getModifiers());
    }
    public ActionEventEx(ActionEvent e,int talkId){
       this(e);
       this.talkId=talkId;
    }
}
