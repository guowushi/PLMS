package PLMS.frames;

/**
 * 帧序列域
 */
public class SeqDomain {
    public static final int LENGTH=1;
    //-----------------------------------------------
    private int TpV=0;
    private int FIR;
    private   int FIN;
    private   int CON;
    private int SEQNumber;


    //-------------------------------------------------
    public SeqDomain() {
    }

    public SeqDomain(int tpV, int FIR, int FIN, int CON, int SEQNumber) {
        TpV = tpV;
        this.FIR = FIR;
        this.FIN = FIN;
        this.CON = CON;
        this.SEQNumber = SEQNumber;
    }
    public SeqDomain(byte in)
    {
        init(in);
    }
    public void init(byte in)
    {
        TpV= (in & 128) >>7;
        FIR=(in&64)>>6;
        FIN=(in &32)>>5;
        CON=(in &16)>>4;
        SEQNumber=(in & 0x0F);
    }
    //-------------------------------------------------
    public int length()
    {
        return LENGTH;
    }
    public boolean  isValid()
    {
        return true;
    }

    public byte valueOf()
    {
          int v=(TpV<<7) | (FIR<<6) | (FIN<<5)  | (CON<<4) | SEQNumber;
          return (byte)v;
    }
    //-----------------------------------------
    public int getCON()
    {
        return this.CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getTpV() {
        return TpV;
    }

    public void setTpV(int tpV) {
        TpV = tpV;
    }

    public int getFIR() {
        return FIR;
    }

    public void setFIR(int FIR) {
        this.FIR = FIR;
    }

    public int getFIN() {
        return FIN;
    }

    public void setFIN(int FIN) {
        this.FIN = FIN;
    }

    public int getSEQNumber() {
        return SEQNumber;
    }

    public void setSEQNumber(int SEQNumber) {
        this.SEQNumber = SEQNumber;
    }
}
