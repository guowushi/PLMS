package PLMS.codec;
 import PLMS.frames.*;
 import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * 消息编码解码工厂类
 * 添加对某个特定消息的编码类和解码类
 */
public class MessagesProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    public MessagesProtocolCodecFactory(boolean isServer) {
         if(isServer)
         {
            // 服务器 需要 上行消息的解码器1个,下行消息的编码器N个
             super.addMessageEncoder(DownMessage.class,DownMessageEncoder.class);
             super.addMessageDecoder(UpMessageDecoder.class);
             //super.addMessageDecoder(LoginMessageDecoder.class);
         }else
         {
             // 客户端 需要 上行消息的编码器N个,下行消息的解码器1个
             super.addMessageEncoder(UpMessage.class,UpMessageEncoder.class);
            // super.addMessageEncoder(LoginMessage.class,LoginMessageEncoder.class);
             super.addMessageDecoder(DownMessageDecoder.class);
         }
    }
}
