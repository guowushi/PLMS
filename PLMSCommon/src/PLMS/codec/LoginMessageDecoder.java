package PLMS.codec;

import PLMS.Messages.LoginMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * loginMessage 解码类
 */
public class LoginMessageDecoder implements MessageDecoder {
    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        // buf.fill(20);
        //buf.flip();
        // 将接收到的Iobuffer字节转化成对象
        System.out.println(" LoginMessage解码 :");
        LoginMessage msg = new LoginMessage();
        //msg.setEnd_char(ioBuffer.get());
        protocolDecoderOutput.write(msg);
        return MessageDecoderResult.OK;
    }

    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer ioBuffer) {
        System.out.println("LoginMessage decodable:"+ioBuffer.getHexDump());
        if (ioBuffer.remaining() >= 20) {
            return MessageDecoderResult.OK;
        } else {
            return MessageDecoderResult.NEED_DATA;
        }

    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
