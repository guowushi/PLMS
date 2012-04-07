package PLMS.frames;

import PLMS.DataUnits.AbstractFn;
import PLMS.Enums.AFN;
import PLMS.Enums.MSG_DIRECTION;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;

/**
 * 消息主体.
 */
public class  MessageBody implements IMessagePart  {
    // --------------------------------------------------------------------
    private AbstractControlDomain ctrl;                 //控制域
    private AddressDomain  addr;                        //地址域
    private AFN afn;                                    // 应用层功能码
    private SeqDomain seqDomain;                            // 帧序列域
    public ArrayList<UserDataFragment> dataUnits;       //用户数据
    private AbstractAUX aux;                            // 附加信息域
    // -------------------------构造函数-------------------------------------
      public MessageBody() {
        addr=new AddressDomain();
        seqDomain =new SeqDomain();
        dataUnits=new ArrayList<UserDataFragment>();
    }
    public  MessageBody(byte[] in ){
            init(in);
    }
    // ------------------------------------------------------------------------
    public void  init(byte[]  in){
        IoBuffer buf = IoBuffer.wrap(in);
        // ---------------(1)读取控制域(1字节) ----------------------------
        byte ctrlbyte = buf.get();
        this.getCtrl().init(ctrlbyte);
        // ---------------(2)读取地址域（5字节）-----------------------------
        byte[] addr_bytes = new byte[AddressDomain.LENGTH];
        buf.get(addr_bytes);
        this.getAddr().init(addr_bytes);
        // ---------------(3)功能码(1字节）-----------------------------------
        this.setAfn(buf.getEnum(AFN.class));
        // ---------------（4）帧序列域(1字节）---------------------------------
        byte seq_byte = buf.get();
        SeqDomain seqDomain = MessageOperation.decodeSEQ(seq_byte);
        // ----------------(5)读取用户数据----------------------
        while (buf.remaining() > MessageFooter.LENGTH + UserDataFragment.getDataIndentifyLength()) {
            //
            UserDataFragment udf = new UserDataFragment();
            byte da1 = buf.get();
            byte da2 = buf.get();
            byte dt1 = buf.get();
            byte dt2 = buf.get();
            //udf.setPoints(da1,da2);
            //udf.setFn(dt1,dt2);
           for(AbstractFn fn:udf.fnObjects.values()){
               byte[] tmp =new byte[fn.length()];
               buf.get(tmp);
               fn.init(in);
           }
           this.dataUnits.add(udf);
        }


    }
    public  UserDataFragment createUserDataFragment(){
        UserDataFragment udf = new UserDataFragment();
        return     udf;
    }
    // -----------------------------------------------------------------------
    public AbstractControlDomain getCtrl() {
        if(ctrl.DIR==1)
        {
            return (ControlDomainUpMessage) ctrl;
        }
        else
        {
            return (ControlDomainDownMessage) ctrl;
        }
    }
    public void setCtrl(AbstractControlDomain ctrl) {
        this.ctrl = ctrl;
    }

    public AddressDomain getAddr() {
        return addr;
    }

    public void setAddr(AddressDomain addr) {
        this.addr = addr;
    }

    public AFN getAfn() {
        return afn;
    }

    public void setAfn(AFN afn) {
        this.afn = afn;
    }

    public SeqDomain getSeqDomain() {
        return seqDomain;
    }

    public void setSeqDomain(SeqDomain seqDomain) {
        this.seqDomain = seqDomain;
    }

    public ArrayList<UserDataFragment> getDataUnits() {
        return dataUnits;
    }

    public void setDataUnits(ArrayList<UserDataFragment> dataUnits) {
        this.dataUnits = dataUnits;
    }

    public AbstractAUX getAux() {
        return aux;
    }

    public void setAux(AbstractAUX aux) {
        this.aux = aux;
    }
    // -------------------------------------------------------------------------
    /**
     * 返回消息主体的长度
     * @return
     */
    public int length()
    {
        int len=0;
        for (UserDataFragment e : dataUnits) {
            len=len+e.length();
        }
        len=8+len;
        if(seqDomain.getTpV()==1)
        {
           // len=len+aux.length();
        }
        if(hasMark())
        {
           len=len+2;
        }
        return len;
    }

    /**
     *  返回消息体的字节数组
     * @return byte[]
     */
    public byte[] valueOf() {
        IoBuffer buf = IoBuffer.allocate(this.length()).setAutoExpand(true).setAutoShrink(true);
        buf.put(this.getCtrl().valueOf());
        buf.put(this.getAddr().valueOf());
        buf.put(this.getAfn().valueOf());
        buf.put( this.getSeqDomain().valueOf() );
        for (UserDataFragment e : dataUnits) {
            buf.put(e.valueOf());//将所有用户数据单元输出
        }
        //   现在还没有考虑AUX
        //buf.put(this.getAux().valueOf());
        // ----下行的设置参数消息需要追加0x6789-------
        if(hasMark())
        {
            byte add1=0x67;
            byte add2=(byte)0x89;
             buf.put(add1);
             buf.put(add2);
        }
        buf.flip();
        buf.shrink();
        byte[] bs=new byte[buf.limit()];
        buf.get(bs);
        return bs;
    }

    /**
     * 消息体是否正确
     * @return
     */
    public boolean  isValid()
    {
        boolean yn=true;
        //yn=ctrl.isValid() && addr.isValid() && seqDomain.isValid() && aux.isValid();
        yn= ctrl.isValid() && addr.isValid() && seqDomain.isValid();
        if(hasMark()){

        }
        // yn =yn &&
        //  控制域的DIR=1,ACD=1,
        return yn;
    }

    /**
     * 根据数据的字节数组产生校验和
     * @return
     */
    public byte  getCheckSum()
    {
       return   MessageOperation.computeCheckSum(this.valueOf());
    }

    public void addDataUnit(UserDataFragment u)
    {
            this.dataUnits.add(u);
    }
    /**
     * 是否包含特征码（只有下行的设置参数才有）
     * @return
     */
    public boolean hasMark()
    {
        boolean ret=false;
        if(this.getCtrl().getDirection()==MSG_DIRECTION.DownStream  && this.getAfn()==AFN.PARAMETERS_SETTING)
        {
            ret=true;
        }
        return ret;
    }

    /**
     * 克隆一个消息体
     * @return
     */
    public MessageBody clone(){
        MessageBody body=new MessageBody();
        body.setCtrl(this.getCtrl());
        body.setAddr(this.getAddr());
        body.setAfn(this.getAfn());
        body.setSeqDomain(this.getSeqDomain());
        body.setDataUnits(this.getDataUnits());
        return  body;
    }
}
