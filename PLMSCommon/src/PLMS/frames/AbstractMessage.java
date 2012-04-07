package PLMS.frames;

import PLMS.Enums.*;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *  抽象消息类（所有消息的父类）
 *  定义所有消息都需要的操作，在子类中拓展
 */
public abstract class AbstractMessage implements IMessage   {
    public MessageHeader header;
    public MessageBody body;
    public MessageFooter footer;
    //-------------------------------------------------------------------
    //public abstract void setFuncode(Enum v);
    // -------------------------构造函数---------------------------------
    public AbstractMessage() {
        header=new MessageHeader();
        footer=new MessageFooter();
        body=new MessageBody();
        setDefaultValue();
    }
    public AbstractMessage(AFN afn) {
        this();  //调用无参数构造函数
        this.setAFN(afn);
        setDefaultValue();
    }
    public AbstractMessage(MessageBody b) {
        body=b;     // 其余部分根据body自动产生
        setDefaultValue();
    }
    public AbstractMessage(MessageHeader h,MessageBody b,MessageFooter f) {
        // 所有组成部分手工设定，不自动产生
        header=h;
        body=b;
        footer=f;
        setDefaultValue();
    }
    protected abstract void setDefaultValue();     // 设置消息的默认值
    // --------------------------实现接口-----------------------------------
    /**
     * 返回消息的字节数组
     * @return byte[]
     * */
    public  byte[] valueOf()
    {
        IoBuffer buf=IoBuffer.allocate(this.length());
        buf.put(header.valueOf());
        buf.put(body.valueOf());
        buf.put(footer.valueOf());
        buf.flip();
        byte[] bs=new byte[buf.limit()];
        buf.get(bs);
        return bs;
    }

    /**
     *  判断一个消息是否是一个正确的消息
     *  (1)消息的每个部分正确，且消息体的验证和正确
     * @return
     */
    public  boolean  isValid()
    {
        if(header.isValid() && body.isValid() && footer.isValid())
        {
             if(MessageOperation.computeCheckSum(body.valueOf())== footer.getCheckSum() ){
                 return true;
             }
        }
        return false;
    }
    /**
     * 返回消息的长度
     * @return
     */
    public   int length()
    {
        return  HEAD_LENGTH + body.length()+FOOT_LENGTH;
    }

    // -----------------------下面是所有消息都需要手工设置的参数---------------------------------

    /**
     * 设置功能码
     * @param afn
     */
    public void setAFN(AFN afn) {
        body.setAfn(afn);
    }
    /**
     * 设置区域编码
     * @param v
     */
    public void setRegionAddress(int v)
    {
        body.getAddr().setRegionAddress((short) v);
    }

    /**
     * 设置终端地址
     * @param v
     */
    public void setTermialAddress(int v)
    {
        body.getAddr().setTermialAddress((short) v);
    }

    /**
     * 设置主站地址
     * @param v
     */
    public void setMainStationAddress(int v)
    {
        body.getAddr().setMainStationAddress(v);
    }

    /**
     * 设置该消息是否需要确认
     * @param v
     */
    public void setConfirm(int v)
    {
       body.getSeqDomain().setCON(v);
    }
    public void setConfirm(boolean v)
    {
        if(v){
            body.getSeqDomain().setCON(1);
        }else{
            body.getSeqDomain().setCON(0);
        }
    }
    // -----------------------------------------------------------------------------
    public MessageHeader getHeader() {
        return header;
    }

    public MessageBody getBody() {
        return body;
    }

    public MessageFooter getFooter() {
        return footer;
    }
    public AFN getAfnCode()
    {
        return  body.getAfn();
    }
    public MSG_DIRECTION getDirection()
    {
        if(body.getCtrl().DIR==1)
        {
            return MSG_DIRECTION.UPStream;
        }else{
            return MSG_DIRECTION.DownStream;
        }
    }
    public EnumPRM getPrimary()
    {
        if(body.getCtrl().PRM==1)
        {
              return EnumPRM.FROM_PRIM_STATION;
        }else{
              return EnumPRM.FROM_SLAVE_STATION;
        }
    }

    /**
     * 消息是否需要确认
     * @return
     */
    public boolean  needConfirm()
    {
        boolean ret;
        if( body.getSeqDomain().getCON()==1){
            ret=true;
        }else{
            ret=false;
        }
        return ret;
    }

    /**
     * 消息是否是第一个
     * @return
     */
    public boolean  isFirst()
    {
        boolean ret;
        if( body.getSeqDomain().getFIR()==1){
            ret=true;
        }else{
            ret=false;
        }
        return ret;
    }

    /**
     * 消息是否是最后一个
     * @return
     */
    public boolean isLast()
    {
        boolean ret;
        if( body.getSeqDomain().getFIN()==1){
            ret=true;
        }else{
            ret=false;
        }
        return ret;
    }

    /**
     * 消息是否为请求消息
     * @return
     */
    public  boolean  isRequest()
    {
        if( body.getCtrl().PRM== 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 用一个MessageBody组装一个消息
     * @param b  消息主体部分，其他部分自动产生
     */
    public void assembleWith(MessageBody b)
    {
        this.body=b;
        this.header.setUserDataLength(b.length());
        this.footer.setCheckSum(b.getCheckSum());
    }
    /**
     * 添加一个用户数据片（一个消息可以有多个）
     */
    public void addDataUnit(UserDataFragment u)
    {
        this.getBody().addDataUnit(u);
    }
    /**
     * 创建一个数据单元
     * @param points
     * @param fns
     */
    public void addUataUnit(EnumInfoPoint[] points,EnumFn[] fns )   throws Exception
    {
        UserDataFragment u=new UserDataFragment();
        for(int i=0;i<points.length;i++)
        {
            u.addMeasuredPoint(points[i]);
        }
        for(int i=0;i<fns.length;i++)
        {
            u.addFn(this.getAfnCode(), fns[i]);
        }
        this.addDataUnit(u);
    }
    /**
     * 设置消息是否有时间标签
     * @param b
     */
    public void setHasTimeStamp(boolean b) {
         
        if(b)
        {
            this.body.getSeqDomain().setTpV(1);

        }else{
            this.body.getSeqDomain().setTpV(0);
        }
    }

    /**
     * 设置是否为第一个消息
     * @param
     * b */
    public void setFirst(boolean b) {
        if(b)
        {
            this.body.getSeqDomain().setFIR(1);

        }else{
            this.body.getSeqDomain().setFIR(0);
        }
    }

    /**
     * 设置是否为最后一个消息
     * @param b
     * */
    public void setLast(boolean b) {
        if(b)
        {
            this.body.getSeqDomain().setFIN(1);

        }else{
            this.body.getSeqDomain().setFIN(0);
        }
    }

    /**
     * 获取消息的序列号
     * @return  int
     */
    public int getSequence()
    {
         int v=this.getBody().getSeqDomain().getSEQNumber();
        return v;
    }

    @Override
    public void setPrimary(EnumPRM prm) {
        body.getCtrl().setPRM(prm.valueOf());
    }

    @Override
    public void setDirection(MSG_DIRECTION dir) {
        body.getCtrl().setDirection(dir);
    }
}
