package PLMS.frames;

/**
 * 下行消息接口
 */
public interface IDownMessage extends IMessage{
    public void setFrameCountBit();
    public void setFrameCountValid();
}
