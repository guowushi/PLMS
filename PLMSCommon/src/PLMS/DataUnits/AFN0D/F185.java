package PLMS.DataUnits.AFN0D;

import PLMS.DataForm.A05;
import PLMS.DataForm.A07;
import PLMS.DataForm.A18;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求2类数据-----  A 相电压合格率统计数据
 */
public class F185 extends AbstractFn {
    //public static int LENGTH;
    public Td_D timeMark;                            // 日冻结类数据时标 Td_d  见 2.2  日月年  3
    public short monitorTime;                    // A 相电压监测时间  BIN  min  2
    public short qualifiedAccumulateTime;        // A 相电压合格累计时间  BIN  min  2
    public short qualifiedPercent;              // A 相电压合格率  数据格式 05  %  2
    public short unQualifiedAccumulateTime;     // A 相电压不合格累计时间  BIN  min  2
    public A07 maxValue;                           // A 相电压最大值  数据格式 07  V  2
    public A18 maxOccurrenceTime;                 // A 相电压最大值发生时间  数据格式 18  分时日  3
    public A07 minValue;                           // A 相电压最小值  数据格式 07  V  2
    public A18 minOccurrenceTime;               // A 相电压最小值发生时间  数据格式 18  分时日  3
    public short exceedToplimitTime;            // A 相电压超上限时间  BIN  min  2
    public short exceedLowerlimitTime;          // A 相电压超下限时间  BIN  min  2
    public A05 exceedLimitPercent;              // A 相电压超限率%  数据格式 05  %  2

    // -----------------------------------------------------------------------------------
    public F185(){}
    public F185(byte[] in ){
           init(in);
    }
    // ------------------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=len+timeMark.length();
        len=len+2+2+2+2;
        len=len+maxValue.length();
        len=len+maxOccurrenceTime.length();
        len=len+minValue.length();
        len=len+minOccurrenceTime.length();
        len=len+2+2;
        len=len+exceedLimitPercent.length();
        return 0;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] tmp;
        tmp=new byte[timeMark.length()];
        buf.get(tmp);
        timeMark.init(tmp);
        monitorTime=buf.getShort();
        qualifiedAccumulateTime=buf.getShort();
        qualifiedPercent=buf.getShort();
        unQualifiedAccumulateTime=buf.getShort();
        tmp=new byte[maxValue.length()];
        buf.get(tmp);
        maxValue.init(tmp);
        tmp=new byte[maxOccurrenceTime.length()];
        buf.get(tmp);
        maxOccurrenceTime.init(tmp);
        tmp=new byte[minValue.length()];
        buf.get(tmp);
        minValue.init(tmp);
        tmp=new byte[minOccurrenceTime.length()];
        buf.get(tmp);
        minOccurrenceTime.init(tmp);
        exceedToplimitTime=buf.getShort();
        exceedLowerlimitTime=buf.getShort();
        tmp=new byte[exceedLimitPercent.length()];
        buf.get(tmp);
        exceedLimitPercent.init(tmp);
    }
}
