package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A20;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求1类数据-- 终端版本信息
 */
public class F1 extends AbstractFn{
    public final int LENGTH=30;
    public String HerstellerCode;           //厂商代号  ASCII  4
    public String EquipmentNumber;          //设备编号  ASCII  8
    public String firmwareVersion;          //终端软件版本号  ASCII  4
    public A20 firewareDate=new A20();                //终端软件发布日期：日月年  数据格式 20  3
    public String capacity;                 //终端配置容量信息码  ASCII  11
    // ----------------------------------------------------------------
    public F1() {
        
    }
    public F1(byte[] in)
    {
        init(in);
    }
    // ------------------------------------------------------
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
         ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] b=new byte[4];
         buf.get(b);
        HerstellerCode=b.toString();
        b=new byte[8];
        buf.get(b);
        EquipmentNumber=b.toString();
        b=new byte[4];
        buf.get(b);
        firmwareVersion=b.toString();
        
        b=new byte[firewareDate.length()];
        buf.get(b);
        firewareDate.init(b);
        b=new byte[11];
        buf.get(b);
        capacity=b.toString();
    }
}
