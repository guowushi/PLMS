package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.EnumPRM;
import PLMS.Enums.MSG_DIRECTION;

/**
 * 下行消息
 */
public class DownMessage extends  AbstractMessage{
   public byte[] characteristic;
    //--------------------------------------------------------
    public DownMessage() {
        super();
    }
    public DownMessage(AFN afn)
    {
        super(afn);

    }
    public DownMessage(MessageBody b){
        super(b);
    }
    public DownMessage(MessageHeader h,MessageBody b,MessageFooter f) {
         super(h,b,f);

    }
   // ------------------------------------------------------
    @Override
    protected void setDefaultValue() {

        body.setCtrl(new ControlDomainDownMessage());
        body.getCtrl().DIR=0;
        body.setAux(new DownAUX());

        characteristic=new byte[2];
        characteristic[0]=0x67;
        characteristic[1]=(byte)0x89;
    }

    // -------------------------------------------------
    public ControlDomainDownMessage getCtrl()
    {
        return (ControlDomainDownMessage)body.getCtrl();
    }
    public  void setFCB(int v)
    {
        getCtrl().setFCB(v);
    }
    public  void setFCB(boolean v)
    {
        if(v){
            getCtrl().setFCB(1);
        }else {
            getCtrl().setFCB(0);
        }
    }
    public void setFCV(int v)
    {
         getCtrl().setFCV(v);
    }
    public void setFCV(boolean v)
    {
        if(v){
            getCtrl().setFCV(1);
        }else{
            getCtrl().setFCV(1);
        }
    }
    @Override
    public boolean isRequest() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }



}
