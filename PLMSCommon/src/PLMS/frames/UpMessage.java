package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.EnumPRM;
import PLMS.Enums.MSG_DIRECTION;

/**
 * 上行消息 (DIR=1)
 */
public class UpMessage   extends AbstractMessage {

    // -----------------------------------------------
    public UpMessage() {
        super();
    }
    public UpMessage(AFN afn)
    {
        super(afn);
    }
    public UpMessage(MessageBody b){
        super(b);
    }
    public UpMessage(MessageHeader h,MessageBody b,MessageFooter f) {
        super(h,b,f);
    }
    // ------------------------------------------------------
    protected void setDefaultValue(){
        body.setCtrl(new ControlDomainUpMessage());
        body.getCtrl().DIR=1;
        body.setAux(new UpAUX());
    }
    // ------------------------------------------------
    public ControlDomainUpMessage getCtrl()
    {
        return (ControlDomainUpMessage)body.getCtrl();
    }

    @Override
    public boolean isRequest() {
        return false;
    }
    // --------------------------------------------------
    public void setACD(int v)
    {
        this.getCtrl().setACD(v);
    }

    public void setPRM( int v ) {
        this.getCtrl().setPRM(v);
    }

    public void setFunCode(int v) {
         this.getCtrl().FUNCODE=v;

    }

    @Override
    public void setPrimary(EnumPRM prm) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDirection(MSG_DIRECTION dir) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
