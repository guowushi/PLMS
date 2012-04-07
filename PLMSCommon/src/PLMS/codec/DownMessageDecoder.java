package PLMS.codec;

import PLMS.frames.*;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：下行消息的解码器
 */
public class DownMessageDecoder implements MessageDecoder{
    private static Logger log = LoggerFactory.getLogger(DownMessageDecoder.class);
    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer ioBuffer) {
        return MessageOperation.CheckMessageDecodable(session, ioBuffer);

    }

    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
       DownMessage msg=new DownMessage();
        MessageDecoderResult ret;
        ret=MessageOperation.decodeMessage(ioBuffer,msg);
        protocolDecoderOutput.write(msg);
        return   ret;
      
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }



    

}
