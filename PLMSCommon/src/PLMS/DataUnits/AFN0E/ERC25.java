package PLMS.DataUnits.AFN0E;

import PLMS.DataForm.A07;
import PLMS.DataForm.A15;
import PLMS.Enums.EVENT;

import java.nio.ByteBuffer;

/**
 * 电流越限记录
 */
public class ERC25 extends  AbstactERC {
   // ------------------------------------------------------------------------
    private A15 eventTime;          //发生时间：分时日月年  数据格式 15  5
    private  int startFlag;          //D7：起/止标志
    private  int measuringPoint;    //  D5~D0：pn（测量点号）  BIN  1
    private  int outOfLimit;        //越限标志  BS8  1
    private A07 Iu;                //发生时的 Uu/Uuv  数据格式 07  2
    private  A07 Iv;                 //发生时的 Uv  数据格式 07  2
    private A07 Iw;                //发生时的 Uw/Uwv  数据格式 07  2
    // -----------------------------------------------------------------------
    public ERC25(){
        this.length=15;
        this.erc= EVENT.ERC25;
    }
    public ERC25(byte[] in){
        this.length=15;
        this.erc= EVENT.ERC25;
        init(in);
    }
    // -------------------------------------------------------------------------
    @Override
    public int length() {
        return this.length;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        this.erc= EVENT.get(buf.get());
        this.length=buf.get();
        //---------------------------------------------------------
        byte[] bytes=new byte[eventTime.length()];
        buf.get(bytes);
        this.eventTime.init(bytes);

        byte tmp=buf.get();
        measuringPoint=tmp & 0x3f;
        startFlag=(tmp & 0x80) >>7;

        outOfLimit=buf.get();

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
