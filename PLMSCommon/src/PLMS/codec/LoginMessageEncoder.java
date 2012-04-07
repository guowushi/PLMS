package PLMS.codec;

import PLMS.Messages.LoginMessage;
import PLMS.frames.UpMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将LoginMessage消息编码成字节流.
 */
public class LoginMessageEncoder implements MessageEncoder<LoginMessage>{
    private static Logger log = LoggerFactory.getLogger(LoginMessageEncoder.class);
    @Override
    public void encode(IoSession session, LoginMessage loginMessage, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        int msgLength=loginMessage.length();
        System.out.println("客户端编码开始！消息长度:"+msgLength);
        IoBuffer buf=IoBuffer.allocate(msgLength).setAutoExpand(true).setAutoShrink(true);
        buf.put(loginMessage.valueOf());
        buf.flip();
        buf.shrink();
        //     buf.putObject(loginMessage);
        System.out.println("客户端编码完成！长度:"+buf.limit());
       log.info(buf.getHexDump());
        protocolEncoderOutput.write(buf);


    }


    /**
     *   将Byte[]转换成消息对象
     * @param buf
     * @return
     */
    public UpMessage Byte2Message(IoBuffer buf)
    {
         UpMessage msg=new UpMessage();
        //msg.check_sum
         return msg;
    }

}
