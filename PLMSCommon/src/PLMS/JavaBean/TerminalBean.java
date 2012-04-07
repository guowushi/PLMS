package PLMS.JavaBean;

import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;

/**
 * JavaBean的属性类型应为对象，这样才能传递个BeanTableModel
 */
public class TerminalBean {
    
    private Long sessionId;
    private String termailIP;
    private Integer terminalAddress;
    private String terminalStatus;



    private String    rwMessage;
    private IoSession session;  //当前会话
    // -------------------------------------------------

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getTerminalStatus() {
        return terminalStatus;
    }

    public void setTerminalStatus(String terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

    public String getTermailIP() {
        return termailIP;
    }

    public void setTermailIP(String termailIP) {
        this.termailIP = termailIP;
    }

    public Integer getTerminalAddress() {
        return terminalAddress;
    }

    public void setTerminalAddress(Integer terminalAddress) {
        this.terminalAddress = terminalAddress;
    }
    public String getRwMessage() {
        return rwMessage;
    }

    public void setRwMessage(String rwMessage) {
        this.rwMessage = rwMessage;
    }
    //---------------------------------------------------
    public TerminalBean(IoSession session) {
        this.session = session;
        getSessionInfo(session);
    }
    public  void getSessionInfo(IoSession session)
    {
        InetSocketAddress clientAddr=(InetSocketAddress)session.getRemoteAddress();
        this.setTermailIP(clientAddr.getAddress().toString());   // 客户端IP
        this.setSessionId(session.getId());                     // session编号
        this.setTerminalAddress(123);                           //终端地址
        this.setTerminalStatus(session.getAttribute("STATUS").toString());                       //状态
        this.setRwMessage(""+session.getReadMessages()+"/"+session.getWrittenMessages());
    }

    @Override
    public boolean equals(Object obj) {
        boolean ret=false;
        if(this.getSessionId()==((TerminalBean)obj).getSessionId())
        {
            ret=true;
        }
        return  ret;
    }
}
