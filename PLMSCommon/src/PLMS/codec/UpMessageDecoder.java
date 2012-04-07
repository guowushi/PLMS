package PLMS.codec;

import PLMS.Enums.EnumPRM;
import PLMS.frames.*;
import PLMS.frames.UpMessage;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能:服务器接受到的字节数组,转换成消息对象
 * 说明:接受到的数组放在缓存中,可能会有数据分片或粘包问题
 * (1)数据分片的处理
 * (2)粘包问题的处理
 */
public class UpMessageDecoder implements MessageDecoder {
    private static Logger log = LoggerFactory.getLogger(UpMessageDecoder.class);

    /**
     * 判断数据能被解码
     * （1）是否够长,至少20字节
     * （2）缓存长度>=消息头6字节+消息体+消息尾2字节
     * （3）起始字符和结束字符是否正确
     *
     * @param session
     * @param ioBuffer
     * @return
     */
    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer ioBuffer) {
        return MessageOperation.CheckMessageDecodable(session, ioBuffer);

    }

    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        UpMessage msg = new UpMessage();
        // UpRequestMessage msg=new UpRequestMessage();
        MessageDecoderResult ret;
        ret=MessageOperation.decodeMessage(ioBuffer,msg);
       if(msg.getPrimary()== EnumPRM.FROM_PRIM_STATION)
        {
          UpRequestMessage  msg1=new UpRequestMessage();
          //  msg1=(UpRequestMessage)msg;
           // protocolDecoderOutput.write(msg1);
        }else{
          UpResponseMessage  msg2=new UpResponseMessage();
        //  msg2=(UpResponseMessage)msg;
          //  protocolDecoderOutput.write(msg2);
        }

       protocolDecoderOutput.write(msg);
        return   ret;


    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }




}
