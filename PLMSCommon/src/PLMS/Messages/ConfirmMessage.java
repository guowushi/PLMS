package PLMS.Messages;

import PLMS.Enums.*;
import PLMS.frames.DownResponseMessage;
import PLMS.frames.UserDataFragment;

/**
 * 确认和否认消息 （AFN00）
 */
public class ConfirmMessage  extends DownResponseMessage{
    public ConfirmMessage(){
    /** ------------------------------------------------------------------------
     *     每个消息都需要设置以下的参数
     */
    // -------------控制域部分-------------
    // this.setDirection()
    // this.setPrimary(true)
    this.setFCB(0);                     //
    this.setFCV(0);
    this.setFunCode(ResponseFunctionCode.CONFIRM);   //   功能码
    //-------------地址域部分------------
    /*
   * 需要设置以下参数
   this.setRegionAddress(0x3030);
   this.setTermialAddress(0x0100);
   this.setMainStationAddress(1);
    */
    //--------------应用层功能码--------------
    this.setAFN(AFN.CONFIRM_DENY);
    //---------------序列域-------------
    this.setHasTimeStamp(false);        // Tpv=0
    this.setFirst(true);                // FIR=1
    this.setLast(true);                 // FIN=1
    this.setConfirm(false);              // CON=0
    // this.setSequcense()               // 序列号
    // -------------------数据单元标识---------------------
    UserDataFragment u=new UserDataFragment();
    u.addMeasuredPoint(EnumInfoPoint.P0);
    u.addFn(this.getAfnCode(), EnumFn.F01);
    // -----------------具体数据单元的数据-----------------
    // F21 fn=(F21)(u.fnObjects.get(EnumFn.F21));
    // fn.fees[0]=4;
    this.addDataUnit(u);
    // ---------------------------------------------------------------------
    }
}
