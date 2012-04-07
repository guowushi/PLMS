package PLMS.frames;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 消息头
 */
public class MessageHeader implements IMessagePart {
    public static final int LENGTH = 6;
    private byte startChar1;
    private int length1;
    private int length2;
    private byte startChar2;
    private int specificationMark = 1;
    //------------------------------------------------------
    public MessageHeader() {
        startChar1 = MessageDefinition.DEFAULT_START_CHAR;
        startChar2 = MessageDefinition.DEFAULT_START_CHAR;
    }
    /**
     * @param in 输入的字节数组
     */
    public MessageHeader(byte[] in) {
        if (in.length == LENGTH) {
            startChar1 = in[0];
            int temp1 = in[1] << 24 >>> 24;
            int temp2 = in[2] << 24 >>> 24;
            length1 = (temp1 + (temp2 << 8)) >>> 2;
            int temp3 = in[3] << 24 >>> 24;
            int temp4 = in[4] << 24 >>> 24;
            length2 = (temp3 + (temp4 << 8)) >>> 2;
            startChar2 = in[5];
        } else {

        }
    }
    //-----------------------------------------------------
    public  int length() {
        // 返回用户数据长度
        return LENGTH;
    }

    public byte[] valueOf() {
        int l1 = length1 << 2 | specificationMark;
        IoBuffer buf = IoBuffer.allocate(LENGTH);
        buf.put(startChar1);
        buf.put((byte) l1);
        buf.put((byte) (l1 >>> 8));
        buf.put((byte) l1);
        buf.put((byte) (l1 >>> 8));
        buf.put(startChar2);
        buf.flip();
        return buf.array();
    }
    /**
     * 消息头是否有效
     * 有效条件：2个起始字符一样，2个长度一样
     * @return
     */
    public boolean isValid() {
        if ((startChar1 == startChar2) && (length1 == length2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取用户数据单元的长度（
     * @return
     */
    public int getAPDULength() {
        return length1;
    }
    public void setUserDataLength(int v) {
        length1 = v;
        length2 = v;
    }

}
