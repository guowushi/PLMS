package PLMS.frames;

/**
 * 消息尾部
 */
public class MessageFooter implements IMessagePart {
    private byte checkSum;
    private byte endChar;
    public static final int LENGTH = 2;
    // ------------------------------------------
    public MessageFooter() {
        endChar = MessageDefinition.DEFAULT_END_CHAR;
    }
    public MessageFooter(byte[] in) {
            init(in);
    }
    // -------------------------------------------
    public byte getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(byte checkSum) {
        this.checkSum = checkSum;
    }

    public byte getEndChar() {
        return endChar;
    }

    public void setEndChar(byte endChar) {
        this.endChar = endChar;
    }

    public void init(byte[] in )
    {
        if (in.length == LENGTH) {
            checkSum = in[0];
            endChar = in[1];
        }
    }
    public  int length() {
        return LENGTH;
    }
    public boolean isValid() {
        if (endChar == MessageDefinition.DEFAULT_END_CHAR) {
            return true;
        } else {
            return false;
        }
    }

    public byte[] valueOf() {
        byte[] v = new byte[LENGTH];
        v[0] = this.checkSum;
        v[1] = this.endChar;
        return v;
    }

}