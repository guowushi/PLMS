package PLMS.DataUnits.AFN0E;

import PLMS.DataForm.A15;
import PLMS.Enums.EVENT;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * 计量芯片数据读出错误
 */
public class ERC70 extends  AbstactERC {
    public A15 powerOff;
    public A15 powerOn;
    public ERC70() {
        this.length=12;           //长度
        this.erc= EVENT.ERC14;    //事件编号
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public byte[] valueOf() {
        IoBuffer buf=IoBuffer.allocate(this.length());
        buf.put(this.erc.valueOf());
        buf.put((byte)this.length());
        buf.put(this.powerOff.valueOf());
        buf.put(this.powerOn.valueOf());
        return buf.array();
    }
}
