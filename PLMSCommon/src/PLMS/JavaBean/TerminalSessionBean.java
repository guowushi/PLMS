package PLMS.JavaBean;

import PLMS.GUI.TableColumnDefine;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;

/**
 * 终端的会话状态
 */
public class TerminalSessionBean {
    IoSession   currentSession;
    private int terminalID;

    public int getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(int terminalID) {
        this.terminalID = terminalID;
    }

    public TerminalSessionBean(IoSession   s){
            this.currentSession =s;
    }
    public IoSession getCurrentSession()
    {
        return this.currentSession;
    }
    // --------------------------------------------------------------------------
    @TableColumnDefine(order=0)
    public String getTerminalIP()
    {
        InetSocketAddress ip=(InetSocketAddress)currentSession.getRemoteAddress();
        return  ip.toString();
    }
    @TableColumnDefine(order=0)
    public int getTerminalAddr()
    {
        return 1234;
    }
    @TableColumnDefine(order=2)
    public int getTerminalAddr2()
    {
        return 1234;
    }
    @TableColumnDefine(order=3)
    public int getTerminalAddr4()
    {
        return 1234;
    }
    @TableColumnDefine(order=4)
    public int getTerminalAddr5()
    {
        return 1234;
    }
    @TableColumnDefine(order=5)
    public int getTerminalAddr6()
    {
        return 1234;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
