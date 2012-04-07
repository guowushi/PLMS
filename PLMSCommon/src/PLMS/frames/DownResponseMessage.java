package PLMS.frames;
import PLMS.Enums.AFN;
import PLMS.Enums.ResponseFunctionCode;

/**
 * 主站返回给终端的回应消息
 */
public class DownResponseMessage extends DownMessage{
    public DownResponseMessage() {
        this.body.getCtrl().PRM=0;
    }
    public DownResponseMessage(AFN afn){
        super(afn);
    }
    //-------------------------------------------------
    public void setFunCode(ResponseFunctionCode code)
    {
        this.body.getCtrl().FUNCODE=code.valueOf();
    }
}
