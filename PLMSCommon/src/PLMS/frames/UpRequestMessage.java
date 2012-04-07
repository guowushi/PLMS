package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.RequestFunctionCode;

/**
 * 终端发送给主站的上行请求消息
 */
public class UpRequestMessage extends  UpMessage {
    // --------------------------------------------
    public UpRequestMessage() {
       super();
       body.getCtrl().PRM=1;
    }
    public UpRequestMessage(AFN afn){
        super(afn);
    }
    // -----------------------------------------------
    public void setFunCode(RequestFunctionCode code) {
         this.setFunCode(code.valueOf());
    }

}
