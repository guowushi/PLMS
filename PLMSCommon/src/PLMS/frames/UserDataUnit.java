package PLMS.frames;

import PLMS.Enums.AFN;
import PLMS.Enums.EnumInfoPoint;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * 用户数据区域，包括
 * (1)应用层功能码AFN
 * (2)帧序列号
 * (3)一个或多个用户数据分区
 */
public class UserDataUnit {
    public AFN afnCode;   // 应用功能码
    public SeqDomain sequence;          // 帧序列号
    public ArrayList<UserDataFragment> dataUnits; //多个数据分区
    // public  AUX  aux;
    public UserDataUnit() {
        dataUnits=new ArrayList<UserDataFragment>(0);
    }
    /**
     * 根据DA返回所有信息点
     *
     * @param da2
     * @param da1
     * @return
     */
    public BitSet getDA(byte da2, byte da1) {
        int[] da = new int[64];
        BitSet bits = new BitSet(64);
        for (int i = 0; i <= 7; i++) {
            int v2 = (da2 >> i) & 1;
            for (int j = 0; j <= 7; j++) {
                int v1 = (da1 >> j) & 1;

                int offset = i * 8 + j;
                bits.set(offset, v1);
            }
        }
        return bits;
    }

    /**
     * 返回信息元组
     *
     * @param dt2
     * @param dt1
     * @return
     */
    public BitSet getDT(byte dt2, byte dt1) {
        BitSet bits = new BitSet(248);
        for (int i = 0; i <= 7; i++) {
            int v = dt1 >> i & 1;
            int offset = dt2 * 8 + v;

        }

        return bits;
    }

    /**
     * 添加一个信息点的用户数据
     *
     * @return
     */
    public UserDataFragment addUserDataFragment(EnumInfoPoint p) {
        return null;
    }
    public int length()
    {
        int len=0;
        for (UserDataFragment e : dataUnits) {
           len=len+e.length();
        }
       return len+2;
    }
    public byte[] valueOf() {
        IoBuffer buf = IoBuffer.allocate(1).setAutoExpand(true).setAutoShrink(true);

       buf.put( afnCode.valueOf());
       buf.put( sequence.valueOf() );
        //将所有单元输出
        for (UserDataFragment e : dataUnits) {
         buf.put(e.valueOf());

        }
        buf.flip();
        buf.shrink();
        byte[] bs=new byte[buf.limit()];
        buf.get(bs);
        return bs;

    }
}
