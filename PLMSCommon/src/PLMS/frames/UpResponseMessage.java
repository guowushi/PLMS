package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.ResponseFunctionCode;

/**
 * 终端返回给主站的响应消息
 */
public class UpResponseMessage  extends  UpMessage{

    // ----------------------------------------------
    public UpResponseMessage() {
        super();
        //body.ctrl.PRM=0;

    }
    public UpResponseMessage(AFN afn){
        super(afn);

    }
   //------------------------------------------------------------
    @Override
    protected void setDefaultValue() {
        super.setDefaultValue();
        this.setPRM(0);
    }

    // ---------------------------------------
    public void setFuncode(ResponseFunctionCode code)
    {
        this.setFunCode(code.valueOf());
       // this.body.ctrl.FUNCODE=code.valueOf();
    }
}
