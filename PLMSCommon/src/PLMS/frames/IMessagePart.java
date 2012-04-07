package PLMS.frames;

/**
 * 消息的部分(一个消息由多个IMessagePart组成)
 * 用于规范消息组成的
 */
public interface IMessagePart {
    public int length();                        // 获取这部分的字节长度
    public byte[] valueOf();                    // 获取这部分的字节数组
    public boolean isValid();                  // 这部分数据是否有效
}
