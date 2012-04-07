package PLMS.frames;

/**
 * 上行消息的控制域
 */
public class ControlDomainUpMessage extends AbstractControlDomain {
    
    //----------------------------------
    /**
     * 要求访问位
     * ACD=1，终端有重要事件等待访问；附加信息域带有事件计数器
     * ACD=0，终端无事件数据
     */
    private   int ACD;
    /**
     * 保留位
     */
    private  int RESERVE=0;
    //--------------getter & setter ---------------------
    public int getACD() {
        return ACD;
    }

    public void setACD(int ACD) {
        this.ACD = ACD;
    }


    //----------------------------------------------
    public ControlDomainUpMessage() {
        DIR=1;
        RESERVE=0;
    }
    public ControlDomainUpMessage(byte in) {
               init(in);
    }

    //--------------------------------------------------------
    public  byte valueOf()
    {
        int v=(DIR<<7) | (PRM<<6) | (ACD<<5) | (RESERVE<<4) | FUNCODE;
        return (byte)v;
    }
    public  boolean  isValid()
    {
        return true;
    }
    public void init(byte in)
    {
        DIR=1;
        PRM=(in & 0x40)>>6 ;
        ACD=(in& 0x20)>>5;
        RESERVE=(in & 16)>>4;
        FUNCODE= in & 15;
    }

}
