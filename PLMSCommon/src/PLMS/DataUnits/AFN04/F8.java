package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 参数设置--终端事件记录配置设置
 * (1)事件记录有效标志位：
 * D0~D63 按顺序对位表示事件代码 ERC1~ERC64 所定义的事件，
 *  置“1”：需要对该位所对应的告警事件进行记录；置“0”：不需要记录。
 *  (2)事件重要性等级标志位：
 *  D0~D63 按顺序对位表示事件代码 ERC1~ERC64 所定义的事件，
 *  置“1”：该位所对应的告警事件为重要事件，该事件发生后，如通道具备主动上报条件，应主动上报事件记录，如不具备主动上报条件，通过 ACD 位上报；
 *  置“0”：该位所对应的告警事件为一般事件，该事件发生后，只需要进行事件记录。
 */
public class F8  extends AbstractFn{
    private int[] eventRecordFlag=new int[64];
    private int[] eventImportantFlag=new int[64];
    private static int LENGTH=16;
    //-------------------------------------------------------
    public F8(){}
    public F8(byte[] in ){
        init(in);
    }
    // -----------------------------------------------------------
    public  void setEventRecordFlag(int evt,boolean yn)
    {
        if (yn)
        { eventRecordFlag[evt-1] = 1; }
        else
        { eventRecordFlag[evt-1] = 0;    }
    }
    public void setEventImportantFlag(int evt,boolean yn)
    {
        if (yn)
        { eventImportantFlag[evt-1] = 1; }
        else
        { eventImportantFlag[evt-1] = 0;    }
    }
    // -----------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        byte b=0;
        int bitcount=1;
        for(int i=0;i<eventRecordFlag.length;i++)
        {

            b=(byte)(b+ (eventRecordFlag[i]<<(bitcount-1)));
            if((bitcount)%8==0)
            {
                buf.put(b);
                b=0;
                bitcount=1;
            }
            bitcount++;
        }
        b=0;
        bitcount=1;
        for(int i=0;i<eventImportantFlag.length;i++)
        {

            b=(byte)(b+ (eventImportantFlag[i]<<(bitcount-1)));
            if((bitcount)%8==0)
            {
                buf.put(b);
                b=0;
                bitcount=1;
            }
            bitcount++;
        }
        return buf.array();
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        long tmp=buf.getLong();

        for (int i = 0; i < 64; i++) {
            int bit=   (int) ((tmp >>i  ) &0x1);
            if(bit==1){
                setEventRecordFlag(i+1,true);
            }else{
                setEventRecordFlag(i+1,false);
            }
        }

         tmp=buf.getLong();

        for (int i = 0; i < 64; i++) {
            int bit=   (int) ((tmp >>i  ) &0x1);
            if(bit==1){
                setEventImportantFlag(i+1,true);
            }else{
                setEventImportantFlag(i+1,false);
            }
        }
    }
}
