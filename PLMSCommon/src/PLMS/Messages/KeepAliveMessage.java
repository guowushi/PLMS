package PLMS.Messages;

import PLMS.Enums.AFN;
import PLMS.Enums.EnumFn;
import PLMS.Enums.EnumInfoPoint;
import PLMS.Enums.RequestFunctionCode;
import PLMS.frames.UpRequestMessage;
import PLMS.frames.UserDataFragment;

/**
 * KeepAlive
 */
public class KeepAliveMessage extends UpRequestMessage implements IKeepAliveMessage {

    // ----------------------------------
    public KeepAliveMessage() {

       this.setACD(0);
       this.setFunCode(RequestFunctionCode.LINK_TEST);
        //-------------------------------
        /* 需要设置以下参数
        this.setRegionAddress(0x3030);
        this.setTermialAddress(0x0100);
        this.setMainStationAddress(1);
        */
        this.setAFN(AFN.LINK_DETECT);
        //-------------------------------------
        this.setHasTimeStamp(false);
        this.setFirst(true);
        this.setLast(true);
        this.setConfirm(true);
        //------------------------------------
        UserDataFragment u=new UserDataFragment();
        u.addMeasuredPoint(EnumInfoPoint.P0);
        u.addFn(this.getAfnCode(), EnumFn.F03);
        // -----------------具体数据单元的数据-----------------
        // F21 fn=(F21)(u.fnObjects.get(EnumFn.F21));
        // fn.fees[0]=4;
        this.addDataUnit(u);
    }

    public KeepAliveMessage(int reg,int tem,int msa){

        this.setRegionAddress(reg);
        this.setTermialAddress(tem);
        this.setMainStationAddress(msa);
    }
    // ------------------------------------------------------------

}
