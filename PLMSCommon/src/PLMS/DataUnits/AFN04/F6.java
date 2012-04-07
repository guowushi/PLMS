package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 参数设置--终端组地址
 */
public class F6 extends AbstractFn{

    private static  int LENGTH=16;
    private int[] groupAddrs=new int[8]; //一个终端总共8个组地址
    // -------------------------------------------------------------------
    public  F6(){
    }
    public F6(byte[] in ){
           init(in);
    }
    //-----------------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        for(int i=0;i<groupAddrs.length;i++)
        {
             buf.putShort((short)groupAddrs[i]);
        }
        return buf.array();
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        for(int i=0;i<groupAddrs.length;i++)
        {
            groupAddrs[i]=   buf.getShort();
        }

    }
}
