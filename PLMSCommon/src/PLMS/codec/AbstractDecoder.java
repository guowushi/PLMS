package PLMS.codec;

import PLMS.frames.IMessage;
import PLMS.frames.MessageFooter;
import PLMS.frames.MessageHeader;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解码器
 */
public class AbstractDecoder {
    private static Logger log = LoggerFactory.getLogger(AbstractDecoder.class);
    /**
     * 获取某字节的第i位
     * @return
     */
    public int getBit(byte in,int i)
    {
        return   (in>>(i-1)) & 1;
    }
    public boolean   hasFullMessageInBuffer(IoBuffer ioBuffer)
    {
        boolean ret=false;
        if (ioBuffer.remaining() < IMessage.MINI_LENGTH) {
            ret=false;
        }else{
            byte[] header = new byte[MessageHeader.LENGTH];
            byte[] footer = new byte[MessageFooter.LENGTH];
            ioBuffer.get(header);
            MessageHeader messageHeader = new MessageHeader(header);
        }

        return ret;
    }
    public boolean isValidMessage()
    {
        boolean ret=false;
        return ret;
    }

    /**
     * 通用的解码方法
     * @param session
     * @param ioBuffer
     * @return
     */
    public MessageDecoderResult CheckDecode(IoSession session, IoBuffer ioBuffer)
    {
        // -----------------判断解码前的操作-------------------------
        log.info("判断是否能解码。当前收到字节"+ioBuffer.getHexDump());
        // -----------------判断能够解码---------------------------------
        MessageDecoderResult ret;
        if (ioBuffer.remaining() >= IMessage.MINI_LENGTH) {
            byte[] header = new byte[MessageHeader.LENGTH];
            byte[] footer = new byte[MessageFooter.LENGTH];
            ioBuffer.get(header);
            MessageHeader messageHeader = new MessageHeader(header);
            if (messageHeader.isValid()) {
                if (ioBuffer.remaining() >= messageHeader.getAPDULength() + MessageFooter.LENGTH) {
                    byte[] userData = new byte[messageHeader.getAPDULength()];
                    ioBuffer.get(userData);

                    ioBuffer.get(footer);
                    MessageFooter messageFooter = new MessageFooter(footer);
                    if(messageFooter.getCheckSum()== MessageOperation.computeCheckSum(userData))
                    {
                        ret= MessageDecoderResult.OK;
                    }else{
                        ret=MessageDecoderResult.NOT_OK;
                    }
                } else {
                    ret= MessageDecoderResult.NEED_DATA;
                }

            } else {
                ret = MessageDecoderResult.NOT_OK;
            }
        } else {
            ret= MessageDecoderResult.NEED_DATA;
        }
        // -----------------结束当前解码判断-------------------------
        if(ret==MessageDecoderResult.NEED_DATA){
            log.info("数据长度不够");
        }
        if(ret==MessageDecoderResult.NOT_OK){
            log.info("数据不对");
        }
        if(ret==MessageDecoderResult.OK){
            log.info("数据正确，准备解码。。。");
        }
        return ret;
    }
}
