package PLMS.codec;

import PLMS.frames.AbstractMessage;
import PLMS.frames.DownMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：对下行消息的编码器
 * 将下行消息对象编码成二进制流
 */
public class DownMessageEncoder implements MessageEncoder<DownMessage>{
    private static Logger log = LoggerFactory.getLogger(DownMessageEncoder.class);
    @Override
    public void encode(IoSession session, DownMessage downMessage, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

        protocolEncoderOutput.write(encodeMessage(downMessage));

    }
    public IoBuffer encodeMessage(AbstractMessage msg)
    {
        // ----------------编码前的操作------------------------
        int msgLength=msg.length();
        log.info("编码开始： 消息类型-"+msg.toString()+" 消息长度-"+msgLength);
       // ----------------编码----------------------------
        IoBuffer buf=IoBuffer.allocate(msgLength).setAutoExpand(true).setAutoShrink(true);
        buf.put(msg.valueOf());
        buf.flip();
        buf.shrink();
        // -----------------编码后的操作-------------
        log.info("编码结束！数据("+buf.limit()+"字节)："+buf.getHexDump());
        return buf;
    }
}
