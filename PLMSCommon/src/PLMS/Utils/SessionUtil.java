package PLMS.Utils;

import PLMS.Enums.*;
import PLMS.frames.*;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.ExpiringMap;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 会话工具
 */
public class SessionUtil  {
    private IoSession currentSession;  // 当前会话
    private int PFC;                    // 会话计数器
    private LinkedBlockingQueue<AbstractMessage> recieved;        // 当前会话收到的消息队列
    private LinkedBlockingQueue<AbstractMessage> sendWaited;      //当前会话等待发送的消息队列
    private LinkedBlockingQueue<AbstractMessage> waitingAnswer;    //当前会话发送成功,等待回复的消息队列
    private ExpiringMap<String,Object> expiringMap=new ExpiringMap<String, Object>(5);
    private int status;
    private Date timeStamp;                                        // 上次消息的发送时间

    // --------------------------------------------------------------------------------------------

    public int getPFC() {
        return PFC;


    }

    public void setPFC(int PFC) {
        this.PFC = PFC;
    }

    public LinkedBlockingQueue<AbstractMessage> getRecieved() {
        return recieved;
    }

    public void setRecieved(LinkedBlockingQueue<AbstractMessage> recieved) {
        this.recieved = recieved;
    }

    public LinkedBlockingQueue<AbstractMessage> getWaitingAnswer() {
        return waitingAnswer;
    }

    public void setWaitingAnswer(LinkedBlockingQueue<AbstractMessage> waitingAnswer) {
        this.waitingAnswer = waitingAnswer;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    // ------------------------构造函数------------------------------------------
    public SessionUtil(IoSession session)
    {
        this.currentSession=session;
        recieved=new LinkedBlockingQueue<AbstractMessage>(1);
        waitingAnswer =new LinkedBlockingQueue<AbstractMessage>(1);
    }
    // ------------------------------------------------------------------------------
     /**
     * 发送一个消息
     * @param msg
     * @param <T>
     */
    public <T extends AbstractMessage>   void sendMessage(T msg){

        try {
           
            if (msg.getPrimary() == EnumPRM.FROM_PRIM_STATION) {
                // （1） 启动站每发送一个消息，PFC+1
                msg.getBody().getSeqDomain().setSEQNumber(PFC);
                WriteFuture future= currentSession.write(msg);
                future.awaitUninterruptibly();
                if(future.isWritten())
                {
                    PFC++;
                    // 计时器开始，当超出该时间后，如果还没有收到则应重发请求消息
                    Calendar c = Calendar.getInstance();
                    timeStamp= c.getTime();
                    waitingAnswer.put(msg);
                }else{
                    //消息发送失败
                }
            } else {
                //（2）回复消息.回复消息的序列号为被回复消息的序列号
                // msg.body.seq.SEQNumber=RecievedQuene.GetLast().;

            }
        } catch (Exception e) {
            //发送失败
        }
 
        
    }

    /**
     * 根据当前会话情况，创建一个消息
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends AbstractMessage> T createMessage(Class<T> clazz) throws IllegalAccessException, InstantiationException {
      T msg=null;
      msg=(T)clazz.newInstance();
      return msg;
    }

    /**
     *
     * @param msg
     * @param yn
     * @param <T>
     * @return
     */
    public <T extends AbstractMessage> AbstractMessage answer(T msg,boolean yn)
    {
        AbstractMessage ans;
        MSG_DIRECTION dir = msg.getDirection();
        // -------回复消息的方向为反向 --------------------
        if (dir == MSG_DIRECTION.DownStream) {
            ans = new UpResponseMessage();

        } else {
            ans = new DownResponseMessage();
        }
        ans.getBody().getCtrl().setFUNCODE(0);
        // ----------回复消息的地址域一样--------
        ans.body.setAddr(msg.getBody().getAddr());
        // ----------确认、否则------------- ---
        ans.setAFN(AFN.CONFIRM_DENY);
        //  回复消息的序列号为原消息序列号
        int msg_seq = msg.getSequence();
        ans.getBody().getSeqDomain().setTpV(0);
        ans.getBody().getSeqDomain().setFIN(1);
        ans.getBody().getSeqDomain().setFIR(1);
        ans.getBody().getSeqDomain().setSEQNumber(msg_seq);
        // ----------确认为F0，否认为F1---------------------
        UserDataFragment u = new UserDataFragment();
        u.addMeasuredPoint(EnumInfoPoint.P0);
        if (yn) {
            u.addFn(msg.getAfnCode(), EnumFn.F01);
        } else {
            u.addFn(msg.getAfnCode(), EnumFn.F02);
        }
        ans.getBody().addDataUnit(u);
        return ans; 
    }

}
