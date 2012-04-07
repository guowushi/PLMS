package PLMS.Enums;

/**
 * 消息方向
 */
public enum MSG_DIRECTION {
    DownStream(0),
    UPStream(1);
    private int direction;
    private MSG_DIRECTION(int dir) {
        direction=dir;
    }
    public int valueOf()
    {
        return this.direction;
    }
}
