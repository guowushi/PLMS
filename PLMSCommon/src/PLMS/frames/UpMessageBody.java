package PLMS.frames;

/**

 */
public class UpMessageBody  extends  MessageBody{
    public UpMessageBody() {
         this.setCtrl(new ControlDomainUpMessage());
    }
}
