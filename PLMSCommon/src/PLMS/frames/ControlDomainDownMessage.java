package PLMS.frames;

/**
 * 下行消息的控制域
 */
public class ControlDomainDownMessage extends AbstractControlDomain {
    private   int FCB=0;
    private   int FCV=0;
    // -------------------------------------------------------------
    public ControlDomainDownMessage() {
        DIR=0;
    }
    public ControlDomainDownMessage(byte in) {
           init(in);
    }
    //------------------------------------------------------------
    public void init(byte in)
    {
        DIR=0;
        PRM=(in & 64)>>6 ;
        FCB=(in & 32)>>5;
        FCV=(in & 16)>>4;
        FUNCODE= in & 15;
    }
    // ---------------------------------------------------------------
    public void setFCB(int FCB) {
        this.FCB = FCB;
    }
    public void setFCV(int FCV) {
        this.FCV = FCV;
    }

    public int getFCB() {
        return FCB;
    }

    public int getFCV() {
        return FCV;
    }

    //-------------------------------------------------------------
    public byte valueOf()
    {
        int v=(DIR<<7) | (PRM<<6) | (FCB<<5) | (FCV<<4) | FUNCODE;
        return (byte)v;
    }
    public boolean  isValid()
    {
        return true;
    }
}
