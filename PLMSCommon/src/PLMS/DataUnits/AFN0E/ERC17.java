package PLMS.DataUnits.AFN0E;

import PLMS.DataForm.A05;
import PLMS.DataForm.A06;
import PLMS.DataForm.A07;
import PLMS.DataForm.A15;
import PLMS.Enums.EVENT;

import java.nio.ByteBuffer;

/**
 * ERC17：电压/电流不平衡度越限记录
 */
public class ERC17 extends AbstactERC {
    // ------------------------------------------------------------------------
    public A15 eventTime;        // 发生时间：分时日月年  数据格式 15  5
    //  D7：起/止标志    D6=0      D5~D0：pn（测量点号）  BIN  1
    // 起/止标志：置“1”：发生，置“0”：恢复。
    public int startFlag;
    public int measuringPoint;
    public int abnormal;         // 异常标志,D0~D1 按顺序对位表示电压不平衡度越限、电流不平衡度越限，置“1”有效，置0”无效，
    public A05 U_Unbalance;     //发生时的电压不平衡度（%）  数据格式 05  2
    public A05 I_Unbalance;      //发生时的电流不平衡度（%）  数据格式 05  2
    public A07 Uuv;    //发生时的 Uu/Uuv  数据格式 07  2
    public A07 Uv;  //发生时的 Uv  数据格式 07  2
    public A07 Uwv; //发生时的 Uw/Uwv  数据格式 07  2
    public A06 Iu;  //发生时的 Iu  数据格式 06  2
    public A06 Iv;  //发生时的 Iv  数据格式 06  2
    public A06 Iw;  //发生时的 Iw  数据格式 06  2
    // -------------------------------------------------------------------------
    public ERC17() {
        this.length=25;
        this.erc= EVENT.ERC17;
    }
    public ERC17(byte[] in) {
        this.length=25;
        this.erc= EVENT.ERC17;
        init(in);
    }
    // -------------------------------------------------------------------------

    // --------------------------------------------------------------------------
    @Override
    public int length() {
        return this.length;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put(this.erc.valueOf());
        buf.put((byte)length);
        buf.put(eventTime.valueOf());
        int tmp= measuringPoint +  (startFlag<<7) ;
        buf.put((byte)tmp);
        buf.put((byte)abnormal);
        buf.put(U_Unbalance.valueOf());
        buf.put(I_Unbalance.valueOf());
        buf.put(Uuv.valueOf());
        buf.put(Uv.valueOf());
        buf.put(Uwv.valueOf());
        buf.put(Iu.valueOf());
        buf.put(Iv.valueOf());
        buf.put(Iw.valueOf());
        return buf.array();
    }

    @Override
    public void init(byte[] in ) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        this.erc=EVENT.get(buf.get());
        this.length=buf.get();

        byte[] bytes=new byte[eventTime.length()];
        buf.get(bytes);
        this.eventTime.init(bytes);

        byte tmp=buf.get();
        measuringPoint=tmp & 0x3f;
        startFlag=(tmp & 0x80) >>7;

        abnormal=buf.get();

        bytes=new byte[U_Unbalance.length()];
        buf.get(bytes);
        U_Unbalance.init(bytes);

        bytes=new byte[I_Unbalance.length()];
        buf.get(bytes);
        I_Unbalance.init(bytes);

        bytes=new byte[Uuv.length()];
        buf.get(bytes);
        Uuv.init(bytes);

        bytes=new byte[Uv.length()];
        buf.get(bytes);
        Uv.init(bytes);

        bytes=new byte[Uwv.length()];
        buf.get(bytes);
        Uwv.init(bytes);

        bytes=new byte[Iu.length()];
        buf.get(bytes);
        Iu.init(bytes);

        bytes=new byte[Iv.length()];
        buf.get(bytes);
        Iv.init(bytes);

        bytes=new byte[Iw.length()];
        buf.get(bytes);
        Iw.init(bytes);
    }
}
