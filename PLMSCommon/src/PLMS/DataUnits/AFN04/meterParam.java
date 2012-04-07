package PLMS.DataUnits.AFN04;

import PLMS.DataForm.A12;

import java.nio.ByteBuffer;

/**
 * 一个电能表的参数设置 (仅F10使用）
 */

class meterParam
{
    int meterNO;                //电能表/交流采样装置序号  BIN    1
    int measuringPoint;        //所属测量点号  BIN    1
    int serialPort;              //通信速率及端口号  BIN    1
    int baudrate;
    int specification;          //通信规约类型  BIN    1
    A12 communicationAddr;      //通信地址  数据格式 12    6
    long communicationPassword;  //通信密码  BIN    6
    int decimalDigits;          //电能费率个数、有功电能示值整数位及小数位个数  BIN    1
    int IntegerDigits;
    int fees;
    final int  LENGTH=17;
    // --------------------------------------------------------------------
    public meterParam(){}
    public meterParam(byte[] in){ init(in);}
    // ----------------------------------------------------------------------

    public int length()
    {
        return this.LENGTH;
    }
    public byte[] valueOf()
    {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put((byte)meterNO);
        buf.put((byte)measuringPoint);
        int communicate=(serialPort &0x0f) + ((baudrate<<4) &0xF0);
        buf.put((byte)communicate);
        buf.put((byte)specification);
        buf.put(communicationAddr.valueOf());

        byte[] cc=new byte[6];
        int c1=(int) (communicationPassword & 0xFFFFFFFF);
        short c2=  (short)(communicationPassword  >>32);
        buf.putShort(c2);
        buf.putInt(c1);

        int    fees_digital=(decimalDigits &0x03) +((IntegerDigits<<2) &0xC) +((fees<<4) &0xf0) ;
        buf.put((byte)fees_digital);
        return buf.array();
    }

    public void init(byte[] in )
    {
        ByteBuffer buf= ByteBuffer.wrap(in);
        this.meterNO=buf.get();
        this.measuringPoint=buf.get();
        int tmp=buf.get();
        this.serialPort= tmp &0x0f;              //通信速率及端口号  BIN    1
        this.baudrate=(tmp &0xf0) >>4;
        this.specification =buf.get();
        
        byte[] a=new byte[communicationAddr.length()];
        buf.get(a);
        this.communicationAddr=new A12(a);

        int t1=buf.getInt();
        short t2=buf.getShort();
        this.communicationPassword=t1<<16 + t2;

        tmp=buf.get();
        this.fees=(tmp &0xf0)>>4;
        this.IntegerDigits=(tmp&0x0c)>>2;
        this.decimalDigits=tmp &0x3;

    }
}