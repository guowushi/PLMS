package PLMS.frames;

import PLMS.Enums.EnumPRM;
import PLMS.Enums.MSG_DIRECTION;

/**
 * 所有消息的父类
 */
public class Message extends AbstractMessage{
    public int a;

    @Override
    protected void setDefaultValue() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void setPrimary(EnumPRM prm) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDirection(MSG_DIRECTION dir) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public boolean isRequest() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
