package PLMS.codec;
import PLMS.frames.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：对上传消息的编码(客户端调用)
 * 说明：因为编码是在客户端上进行，所有不存在数据包分片等问题；编码就是将对象转成字节数组
 */
public class UpMessageEncoder  implements MessageEncoder<UpMessage> {
    private static Logger log = LoggerFactory.getLogger(UpMessageEncoder.class);
    @Override
    public void encode(IoSession session, UpMessage upMessage, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        int msgLength=upMessage.length();
        System.out.println("upMessage客户端编码开始！消息长度:"+msgLength);
        IoBuffer buf=IoBuffer.allocate(msgLength).setAutoExpand(true).setAutoShrink(true);
        buf.put(upMessage.valueOf());
        buf.flip();
        buf.shrink();
        //buf.putObject(loginMessage);
        System.out.println("客户端编码完成！长度:"+buf.limit());
        log.info("客户端编码后的数据"+buf.getHexDump());
        protocolEncoderOutput.write(buf);
    }
}
