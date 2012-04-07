package PLMS.Messages;

import PLMS.Enums.AFN;
import PLMS.frames.DownRequestMessage;

/**
 * 参数设置消息
 */
public class ParamSetting extends DownRequestMessage implements IParamSetting{

    public ParamSetting() {
        this.setAFN(AFN.PARAMETERS_SETTING);

    }

    @Override
    public void setFrameCountBit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFrameCountValid() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
