package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.*;
import PLMS.DataUnits.*;

import java.nio.ByteBuffer;

/**
 * 当前三相及总有/无功功率、功率因数，三相电压、电流、零序电流
 */
public class F25 extends AbstractFn {
    public final int LENGTH = 51;
    public A15 readingTime=new A15();  //终端抄表时间  数据格式 15  分时日月年  5
    public A09 P=new A09();        //当前总有功功率  数据格式 09  kW  3
    public A09 P_A;      //当前 U 相有功功率  数据格式 09  kW  3
    public A09 P_B;      //当前 V 相有功功率  数据格式 09  kW  3
    public A09 P_C;      //当前 W 相有功功率  数据格式 09  kW  3
    public A09 Q;         //当前总无功功率  数据格式 09  kW  3
    public A09 Q_A;      //当前 U 相无功功率  数据格式 09  kW  3
    public A09 Q_B;         //当前 V 相无功功率  数据格式 09  kW  3
    public A09 Q_C;         //当前 W 相无功功率  数据格式 09  kW  3
    public A05 PF;          //当前总功率因数  数据格式 05  %  2
    public A05 PF_A;        //当前 U 相功率因数  数据格式 05  %  2
    public A05 PF_B;        //当前 V 相功率因数  数据格式 05  %  2
    public A05 PF_C;        //当前 W 相功率因数  数据格式 05  %  2
    public A07 U_A;         //当前 U 相电压  数据格式 07  V  2
    public A07 U_B;         //当前 V 相电压  数据格式 07  V  2
    public A07 U_C;         //当前 W 相电压  数据格式 07  V  2
    public A06 I_A;         //当前 U 相电流  数据格式 06  A  2
    public A06 I_B;         //当前 V 相电流  数据格式 06  A  2
    public A06 I_C;         //当前 W 相电流  数据格式 06  A  2
    public A06 I0;          //当前零序电流  数据格式 06  A  2

    // --------------------------------------------------------------------------
    public F25() {

    }

    public F25(byte[] in) {
        init(in);
    }

    // --------------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf = ByteBuffer.wrap(in);
        byte[] tmp;
        //----------------------------------
        tmp = new byte[readingTime.length()];
        buf.get(tmp);
        readingTime.init(tmp);
        //----------------------------------
        tmp = new byte[P.length()];
        buf.get(tmp);
        P.init(tmp);
        tmp = new byte[P_A.length()];
        buf.get(tmp);
        P_A.init(tmp);
        tmp = new byte[P_B.length()];
        buf.get(tmp);
        P_B.init(tmp);
        tmp = new byte[P_C.length()];
        buf.get(tmp);
        P_C.init(tmp);
        //-------------------------------
        tmp = new byte[Q.length()];
        buf.get(tmp);
        Q.init(tmp);
        tmp = new byte[Q_A.length()];
        buf.get(tmp);
        Q_A.init(tmp);
        tmp = new byte[Q_B.length()];
        buf.get(tmp);
        Q_B.init(tmp);
        tmp = new byte[Q_C.length()];
        buf.get(tmp);
        Q_C.init(tmp);
        //-------------------------------
        tmp = new byte[PF.length()];
        buf.get(tmp);
        PF.init(tmp);
        tmp = new byte[PF_A.length()];
        buf.get(tmp);
        PF_A.init(tmp);
        tmp = new byte[PF_B.length()];
        buf.get(tmp);
        PF_B.init(tmp);
        tmp = new byte[PF_C.length()];
        buf.get(tmp);
        PF_C.init(tmp);
        //--------------------------------
        tmp = new byte[U_A.length()];
        buf.get(tmp);
        U_A.init(tmp);
        tmp = new byte[U_B.length()];
        buf.get(tmp);
        U_B.init(tmp);
        tmp = new byte[U_C.length()];
        buf.get(tmp);
        U_C.init(tmp);
        //-------------------------------
        tmp = new byte[I_A.length()];
        buf.get(tmp);
        I_A.init(tmp);
        tmp = new byte[I_B.length()];
        buf.get(tmp);
        I_B.init(tmp);
        tmp = new byte[I_C.length()];
        buf.get(tmp);
        I_C.init(tmp);
        // -----------------------------
        tmp = new byte[I0.length()];
        buf.get(tmp);
        I0.init(tmp);
    }
}
