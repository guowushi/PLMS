package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.RequestFunctionCode;

/**
 * 主站发送给终端的下行请求消息（如设置终端参数，获取终端数据等）
 */
public class DownRequestMessage  extends  DownMessage{
    //----------------------------------------------
    public DownRequestMessage() {
        super();

    }
    public DownRequestMessage(AFN afn){
        super(afn);
    }

    @Override
    protected void setDefaultValue() {
        super.setDefaultValue();
        body.getCtrl().PRM= 1;
    }

    //-------------------------------------------------
    public boolean isRequest()
    {
        return true;
    }
    public void setFuncode(RequestFunctionCode code)
    {
        this.body.getCtrl().FUNCODE=code.valueOf();
    }
}
