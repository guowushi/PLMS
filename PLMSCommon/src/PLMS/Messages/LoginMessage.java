package PLMS.Messages;


import PLMS.Enums.EnumFn;
import PLMS.Enums.EnumInfoPoint;
import PLMS.Enums.RequestFunctionCode;
import PLMS.frames.UpRequestMessage;
import PLMS.Enums.AFN;
import PLMS.frames.UserDataFragment;

/**
 * 终端发出的上行登录消息
 */
public final class LoginMessage extends UpRequestMessage {
    public LoginMessage() {
           init();

    }
    
    public LoginMessage(int reg,int tem,int msa)
    {
        init();
        this.setRegionAddress(reg);
        this.setTermialAddress(tem);
        this.setMainStationAddress(msa);
    }
    public void init()
    {

        /** ------------------------------------------------------------------------
         *     每个消息都需要设置以下的参数
         */
        // -------------控制域部分-------------
        // this.setDirection()
        // this.setPrimary(true)
        this.setACD(0);                                     //  请求访问位
        this.setFunCode(RequestFunctionCode.LINK_TEST);   //   功能码
        //-------------地址域部分------------
        /*
        * 需要设置以下参数
        this.setRegionAddress(0x3030);
        this.setTermialAddress(0x0100);
        this.setMainStationAddress(1);
         */
        //--------------应用层功能码--------------
        this.setAFN(AFN.LINK_DETECT);
        //---------------序列域-------------
        this.setHasTimeStamp(false);        // Tpv=0
        this.setFirst(true);                // FIR=1
        this.setLast(true);                 // FIN=1
        this.setConfirm(true);              // CON=1
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
