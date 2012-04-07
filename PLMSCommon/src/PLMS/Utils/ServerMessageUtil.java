package PLMS.Utils;

import PLMS.DataUnits.AFN04.F21;
import PLMS.Enums.AFN;
import PLMS.Enums.EnumFn;
import PLMS.Enums.EnumInfoPoint;
import PLMS.Enums.RequestFunctionCode;
import PLMS.frames.DownRequestMessage;
import PLMS.frames.UpRequestMessage;
import PLMS.frames.UserDataFragment;
import org.apache.mina.core.session.IoSession;

/**
 * 服务器端用的消息辅助类
 */
public class ServerMessageUtil  extends MessageOperation{

    public ServerMessageUtil(IoSession session) {
        super(session);
    }

    /**
     * 
     * @param session
     */
    public static void setting(IoSession session)
    {
        DownRequestMessage msg=new DownRequestMessage();
        // ---------------
        msg.setFCB(0);
        msg.setFCV(1);
        msg.setFuncode(RequestFunctionCode.REQUEST_CLASS1);

        msg.setRegionAddress(0x3030);
        msg.setTermialAddress(0x0100);
        msg.setMainStationAddress(1);

        msg.setAFN(AFN.PARAMETERS_SETTING);

        //msg.getBody().getSeqDomain().TpV=0;
        msg.setConfirm(0);
        msg.setFirst(true);
        msg.setLast(true);
        msg.setHasTimeStamp(false);

        UserDataFragment u=new UserDataFragment();
        u.addMeasuredPoint(EnumInfoPoint.P0);
        u.addFn(msg.getAfnCode(), EnumFn.F21);

        F21 fn=(F21)(u.fnObjects.get(EnumFn.F21));
        fn.fees[0]=4;

        msg.addDataUnit(u);

        MessageOperation.sendMessage(session,msg);

    }

    /**
     * 对消息的回复
     */
    public <T extends UpRequestMessage,S  extends DownRequestMessage> void answer(T req,S ans )
    {
        
        
    }
    public  DownRequestMessage answer(UpRequestMessage req )
    {

         return null;
    }
    
}
