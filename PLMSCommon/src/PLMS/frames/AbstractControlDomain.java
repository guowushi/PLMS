package PLMS.frames;

import PLMS.Enums.MSG_DIRECTION;

/**
 * 控制域的抽像类（定义下行消息和上下消息的控制域所共有的东西）
 */
public abstract class AbstractControlDomain {

    public static final int LENGTH = 1;
    // ------------------------------------------------
    /**
     * 消息方向（1-上行;0-下行）
     */
    protected int DIR;
    /**
     * 启动标志，谁是通信的发起者（1-启动站；0-从动站）
     */
    protected int PRM;
    /**
     * 功能码
     */
    public int FUNCODE;

    // -------------- getter & setter ------------------
    public void setDIR(int DIR) {
        this.DIR = DIR;
    }

    public void setPRM(int PRM) {
        this.PRM = PRM;
    }

    public void setFUNCODE(int FUNCODE) {
        this.FUNCODE = FUNCODE;
    }
    public MSG_DIRECTION getDirection() {
        if (DIR == 0) {
            return MSG_DIRECTION.DownStream;
        } else {
            return MSG_DIRECTION.UPStream;
        }
    }
    public void setDirection(MSG_DIRECTION dir) {
        if (dir==MSG_DIRECTION.DownStream) {
            DIR=0 ;
        } else {
            DIR=1 ;
        }
    }
    //---------------------------------------------------

    public int length() {
        return this.LENGTH;
    }



    // ------------------抽象方法---------------------------
    public abstract boolean isValid();
    public abstract void  init(byte in );
    public abstract byte valueOf();

    //public abstract void setFCB(int v);
    //public abstract void setFCV(int v);
    //public abstract void setACD(int v);
}
