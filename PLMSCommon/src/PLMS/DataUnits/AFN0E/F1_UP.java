package PLMS.DataUnits.AFN0E;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 下行消息
 */
public class F1_UP extends AbstractFn{
    public int EC1;         //当前重要事件计数器 EC1
    public int EC2;         // 当前一般事件计数器 EC2
    public int Pm;          //本帧报文传送的事件记录起始指针 Pm
    public int Pn;          //本帧报文传送的事件记录结束指针 Pn
    public ArrayList<AbstactERC> Events;  // 事件列表
    // -----------------------------------------------------------------------

    public F1_UP(){}
    public F1_UP(byte[] in ){
        init(in);
    }
    // -------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        for(AbstactERC erc:Events)
        {
               len=len+erc.length();
        }
        return len+4;  
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf=ByteBuffer.allocate(this.length());
        buf.put((byte)EC1);
        buf.put((byte)EC2);
        buf.put((byte)Pm);
        buf.put((byte)Pn);
        for(AbstactERC erc:Events)
        {
            buf.put(erc.valueOf());
        }
        return buf.array();
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
