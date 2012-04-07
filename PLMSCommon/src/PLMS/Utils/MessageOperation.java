package PLMS.Utils;

import PLMS.DataUnits.AbstractFn;
import PLMS.Enums.*;
import PLMS.frames.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * 消息处理工具（不区分客户端和服务器）
 */
public class MessageOperation {

    IoSession session;  //当前会话
    // ----------------------------------------------------------------------------
    public MessageOperation(IoSession session) {
        this.session = session;
    }
    //------------------------------------------------------------------------------
    public <T extends AbstractMessage> boolean sendMessage(T msg) {
        // 计算消息的参数
        msg.header.setUserDataLength(msg.body.length());
        msg.footer.setCheckSum(msg.body.getCheckSum());
        if (msg.getPrimary() == EnumPRM.FROM_PRIM_STATION) {
            // （1）请求消息
            // 启动站每发送一个消息，PFC+1
            PFC pfc = (PFC) session.getAttribute("PFC");
            SessionInfo info = (SessionInfo) session.getAttribute("STATUS");
            msg.getBody().getSeqDomain().setSEQNumber(pfc.nextSeq());
            // 计时器开始，当超出该时间后，如果还没有收到则应重发请求消息
        } else {
            //（2）回复消息
            // 获取需要的消息序列号，回复消息的序列号为该号码

            // msg.body.seq.SEQNumber=RecievedQuene.GetLast().;
        }
        try {
            session.write(msg);
            //发送成功后，计数器+1

        } catch (Exception e) {
            //发送失败
        }

        return true;
    }


    /**
     * 发送一个消息
     *
     * @param session：当前会话
     * @param msg
     * @param <T>
     * @return
     */

    public static <T extends AbstractMessage> boolean sendMessage(IoSession session, T msg) {
        // 计算消息的参数
        msg.header.setUserDataLength(msg.body.length());
        msg.footer.setCheckSum(msg.body.getCheckSum());

        if (msg.getPrimary() == EnumPRM.FROM_PRIM_STATION) {
            // （1）请求消息
            // 启动站每发送一个消息，PFC+1
            PFC pfc = (PFC) session.getAttribute("PFC");
            SessionInfo info = (SessionInfo) session.getAttribute("STATUS");

            msg.getBody().getSeqDomain().setSEQNumber(pfc.nextSeq());
            // 计时器开始，当超出该时间后，如果还没有收到则应重发请求消息
        } else {
            //（2）回复消息
            // 获取需要的消息序列号，回复消息的序列号为该号码

            // msg.body.seq.SEQNumber=RecievedQuene.GetLast().;
        }
        try {
            session.write(msg);
            //发送成功后，计数器+1

        } catch (Exception e) {
            //发送失败
        }

        return true;
    }

    /**
     * 计算某一个字节数组的校验码
     *
     * @param in
     * @return
     */
    public static byte computeCheckSum(byte[] in) {
        byte retValue = (byte) 0;
        for (int i = 0; i < in.length; i++) {
            byte tmp = in[i];
            retValue = (byte) (retValue + tmp);
        }
        return retValue;
    }

    // --------------------------------------------------------------------------
    public static MessageHeader decodeMessageHeader(byte[] in) {
        MessageHeader header = new MessageHeader(in);
        return header;
    }

    public static MessageFooter decodeMessageFooter(byte[] in) {
        MessageFooter footer = new MessageFooter(in);
        return footer;
    }

    public static SeqDomain decodeSEQ(byte in) {
        SeqDomain seqDomain = new SeqDomain(in);
        return seqDomain;
    }

    public static AddressDomain decodeAddressDomain(byte[] in) {
        int a1 = (in[0] << 8) + in[1]; //区域地址
        int a2 = (in[2] << 8) + in[3]; // 终端地址
        int a3 = in[4] >>> 1;      // 主站地址
        AddressDomain addressDomain = new AddressDomain(a1, a2, a3);
        return addressDomain;
    }

    /**
     * 获取某字节的第i位
     *
     * @return
     */
    public static int getBit(byte in, int i) {
        return (in >> (i - 1)) & 1;
    }

    /**
     * 消息解码
     *
     * @param buf
     * @param msg
     * @param <T> 解码后的一个消息
     * @return
     */
    public static <T extends AbstractMessage> MessageDecoderResult decodeMessage(IoBuffer buf, T msg) {
        MessageDecoderResult ret;
        // ---------------------------------------------------------------------
        System.out.println("符合解码条件， 解码开始...");
        System.out.println("当前缓冲数据：" + buf.getHexDump());
        // ----------------------------这里创建一个具体的消息----------------------
        // DownMessage msg = new DownMessage();
        //---------------------头部解码（6字节）------------------------------
        byte[] header_bytes = new byte[MessageHeader.LENGTH];
        buf.get(header_bytes);
        msg.header = MessageOperation.decodeMessageHeader(header_bytes);
        // -------------------消息体解码 -------------------------------------
        //byte[] body_bytes = new byte[msg.header.getAPDULength()];
        // ---------------(1)读取控制域(1字节) ----------------------------
        byte ctrl_byte = buf.get();
        msg.body.getCtrl().init(ctrl_byte);
        // ---------------(2)读取地址域（5字节）-----------------------------
        byte[] addr_bytes = new byte[AddressDomain.LENGTH];
        buf.get(addr_bytes);
        msg.body.setAddr(MessageOperation.decodeAddressDomain(addr_bytes));
        // ---------------（3）消息功能码（1字节）-----------------------------
        AFN afn = buf.getEnum(AFN.class);
        msg.body.setAfn(afn);
        // ----------------（4）帧序列域（1字节）------------------------------
        byte seq_byte = buf.get();
        msg.body.setSeqDomain(MessageOperation.decodeSEQ(seq_byte));
        // ----------------（5）用户数据解码----------------------------------
        while (buf.remaining() >= MessageFooter.LENGTH + UserDataFragment.getDataIndentifyLength()) {
            //
            UserDataFragment udf = new UserDataFragment();
            //读2字节的DA  meters.setDA1
            byte da1 = buf.get();
            byte da2 = buf.get();
            //udf.setPoints(da1,da2);
            for (int i = 0; i <= 7; i++) {
                int da2_bit;
                da2_bit = (da2 >> i) & 1;
                for (int j = 0; j < 7; j++) {
                    int da1_bit;
                    da1_bit = (da1 >> j) & 1;
                    int v = da2_bit * (8 * i + j + 1);
                    //
                    udf.addMeasuredPoint(v);
                }
            }
            //读2字节的DT  meters.setDT1
            byte dt1 = buf.get();
            byte dt2 = buf.get();
            //udf.setFn(dt1,dt2);
            for (int i = 1; i <= 8; i++) {
                int da1_bit = getBit(dt1, i); //da1的第位
                //
                if (da1_bit == 1) {
                    int f = dt2 * 8 + i;     //fn对应的数字
                    AbstractFn fn = udf.addFn(afn, EnumFn.get(f));     //创建fn
                    if (fn != null) {
                        if (fn.length() > 0 && buf.remaining() >= fn.length()) {
                            //读数据到Fn中
                            byte[] fnByteArray = new byte[fn.length()];
                            buf.get(fnByteArray);
                            fn.init(fnByteArray);
                            //-----
                        } else {

                        }
                    }
                }
            }
            msg.body.dataUnits.add(udf);
        }
        //---------------------------下行的设置参数功能有特征吗--------------------------------------
        if (buf.remaining() >= 2) {
            if (msg.getBody().hasMark()) {
                byte[] add = new byte[2];
                buf.get(add);
                if (add[0] == 0x67 && add[1] == 0x89) {
                }
            }
        }
        if (buf.remaining() == 2) {
            //数据正确
            // -------------------------读取footer部分-------------------------
            byte[] footer_bytes = new byte[MessageFooter.LENGTH];
            buf.get(footer_bytes);
            msg.footer = MessageOperation.decodeMessageFooter(footer_bytes);

        } else {
            // 要求重发
            return MessageDecoderResult.NOT_OK;
        }

        // --------------------------------------------------------------------------------
        System.out.println("解码结束");
        System.out.println("当前缓冲数据：" + buf.getHexDump());
        // 解码后的消息是否有效
        if (msg.isValid()) {
            ret = MessageDecoderResult.OK;
        } else {
            ret = MessageDecoderResult.NOT_OK;
        }
        return ret;
    }

    /**
     * 判断缓冲区的数据是否包含一个完整的消息
     *
     * @param ioBuffer
     * @return
     */
    public boolean hasFullMessageInBuffer(IoBuffer ioBuffer) {
        boolean ret = false;
        if (ioBuffer.remaining() < IMessage.MINI_LENGTH) {
            ret = false;
        } else {
            byte[] header = new byte[MessageHeader.LENGTH];
            byte[] footer = new byte[MessageFooter.LENGTH];
            ioBuffer.get(header);
            MessageHeader messageHeader = new MessageHeader(header);
        }

        return ret;
    }

    /**
     * 消息是否有效
     *
     * @return
     */
    public boolean isValidMessage() {
        boolean ret = false;
        return ret;
    }

    /**
     * 检查消息能够解码
     *
     * @param session
     * @param ioBuffer
     * @return
     */
    public static MessageDecoderResult CheckMessageDecodable(IoSession session, IoBuffer ioBuffer) {
        Logger log = LoggerFactory.getLogger(MessageOperation.class);
        //-----------------判断解码前的操作---------------------------
        log.info("判断是否能解码。当前收到字节" + ioBuffer.getHexDump());
        // -----------------判断能够解码---------------------------------
        MessageDecoderResult ret;
        byte[] header = new byte[MessageHeader.LENGTH];
        byte[] footer = new byte[MessageFooter.LENGTH];
        // 长度至少要达到最小长度（最小20字节）
        if (ioBuffer.remaining() >= IMessage.MINI_LENGTH) {
            // 读取消息头部
            ioBuffer.get(header);
            MessageHeader messageHeader = new MessageHeader(header);
            if (messageHeader.isValid()) {
                if (ioBuffer.remaining() >= messageHeader.getAPDULength() + MessageFooter.LENGTH) {
                    // 读取用户消息
                    byte[] userData = new byte[messageHeader.getAPDULength()];
                    ioBuffer.get(userData);
                    // 读取消息尾
                    ioBuffer.get(footer);
                    MessageFooter messageFooter = new MessageFooter(footer);
                    ret = MessageDecoderResult.OK;
                } else {
                    ret = MessageDecoderResult.NEED_DATA;
                }
            } else {
                ret = MessageDecoderResult.NOT_OK;
            }
        } else {
            ret = MessageDecoderResult.NEED_DATA;
        }
        // -----------------结束当前解码判断-------------------------
        if (ret == MessageDecoderResult.NEED_DATA) {
            log.info("数据长度不够");
        }
        if (ret == MessageDecoderResult.NOT_OK) {
            log.info("数据不对");
        }
        if (ret == MessageDecoderResult.OK) {
            log.info("数据正确，准备解码。。。");
        }
        return ret;
    }

    /**
     * 创建一个回复消息
     * @param msg
     * @param <T>
     * @return
     */
    public static  <T extends AbstractMessage> AbstractMessage ceateReply(T msg) {
        AbstractMessage ans=null;
        return  ans;
        
    }

    /**
     * 对某个消息的回复
     * @param msg
     * @param yn
     * @param <T>
     */
    public static <T extends AbstractMessage> AbstractMessage answer(T msg, boolean yn) {
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
        ans.getBody().getSeqDomain().setFIN(1) ;
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

    /**
     * 创建一个特定类型的消息
     *
     * @param clazz 消息类
     * @param <T>
     * @return
     */
    public <T extends AbstractMessage> IMessage createMessage(Class<? extends AbstractMessage> clazz) {
        // KeepAliveMessage msg=new KeepAliveMessage();
        T b = null;
        try {
            b = (T) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return b;
    }


    //------------------------
    /**
     * 根据afn和f，创建Fn实例对象(例如创建PLMS.DataUnits.AFN00.F1_UP)
     *
     * @param afn :功能码
     * @param f   : EnumFn枚举值
     * @return ：fn的实例对象
     */
    public static AbstractFn createFnInstance(AFN afn, EnumFn f) {
        //
        String strAfn = String.format("%02x", afn.valueOf()).toUpperCase();
        String strFn = String.format("%d", f.valueOf()).toUpperCase();
        AbstractFn fn = null;
        String class_url = "PLMS.DataUnits.AFN" + strAfn + ".F" + strFn;
        try {
            // 获取类的字节码
            Class fnclass = Class.forName(class_url);
            // 查看类的LENGTH静态常量 ，获取Fn长度
            Field fldLength = fnclass.getField("LENGTH");
            // fn长度为0,说明没有数据部分，没有必要创建实例
            //if (fldLength.getInt(fnclass) > 0) {
            fn = (AbstractFn) (fnclass.newInstance()); //创建实例
            //fnObjList.add(fn);
            // System.out.println(fn.length());
            //}
        } catch (Exception e) {
            System.out.println("类创建错误");
        }
        return fn;
    }

}
