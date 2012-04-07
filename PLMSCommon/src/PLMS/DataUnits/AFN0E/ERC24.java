package PLMS.DataUnits.AFN0E;

import PLMS.DataForm.A07;
import PLMS.DataForm.A15;
import PLMS.Enums.EVENT;

import java.nio.ByteBuffer;

/**
 * 电压越限记录
 * D0~D2 按顺序对位表示 U 相、V 相、W 相，置“1”：对应相发生越限，若多相同时发生越限可同时置“1”；置“0”：对应相未发生越限。
 * D6~D7 编码表示电压越限类型，取值 1~2 分别表示越上上限、越下下限，值 0、3 为备用。
 */
public class ERC24 extends  AbstactERC {

    // ------------------------------------------------------------
    private A15 eventTime;          //发生时间：分时日月年  数据格式 15  5
    private  int startFlag;          //D7：起/止标志       
    private  int measuringPoint;    //  D5~D0：pn（测量点号）  BIN  1
    private  int outOfLimit;        //越限标志  BS8  1
    private A07 Uuv;                //发生时的 Uu/Uuv  数据格式 07  2
    private  A07 Uv;                 //发生时的 Uv  数据格式 07  2
    private A07 Uwv;                //发生时的 Uw/Uwv  数据格式 07  2
    // ------------------------------------------------------------
    public ERC24(){
        this.length=15;
        this.erc= EVENT.ERC24;
    }
    public ERC24(byte[] in){
        this.length=15;
        this.erc= EVENT.ERC24;
            init(in);
    }
    // ------------------------------------------------------------
    @Override
    public int length() {
        return this.length;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        return new byte[0];
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

        bytes=new byte[Uuv.length()];
        buf.get(bytes);
        Uuv.init(bytes);

        bytes=new byte[Uv.length()];
        buf.get(bytes);
        Uv.init(bytes);

        bytes=new byte[Uwv.length()];
        buf.get(bytes);
        Uwv.init(bytes);
    }
}
