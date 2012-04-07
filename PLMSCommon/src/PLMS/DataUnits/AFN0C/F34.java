package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A11;
import PLMS.DataForm.A14;
import PLMS.DataForm.A15;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求1类数据：当前反向有/无功电能示值、二/三象限无功电能示值（总、费率 1~M）
 */
public class F34 extends AbstractFn{
    //-------------------------------------------------------------------
    public A15 readingTime;         // 终端抄表时间  数据格式 15  分时日月年  5
    public int feeCount;            // 费率数 M  BIN  个  1
    // (1)当前反向有功总电能示值  数据格式 14  kWh  5
    //当前费率 1 反向有功总电能示值  数据格式 14  kWh  5
    //        „„  „„  „„  „„
    //当前费率 M 反向有功总电能示值  数据格式 14  kWh  5
    public A14 RAP_R;
    public A14[] RAP_R_FEES;
    // (2)当前反向无功总电能示值  数据格式 11  kvarh  4
    //当前费率 1 反向无功总电能示值  数据格式 11  kvarh  4
    //        „„  „„  „„  „„
    //当前费率 M 反向无功总电能示值  数据格式 11  kvarh  4
    public A11 RRP_R;
    public A11[] RRP_R_FEES;
    //(3)当前2象限无功总电能示值  数据格式 11  kvarh  4
    //当前2象限费率 1 无功电能示值  数据格式 11  kvarh  4
    //       „„  „„  „„  „„
    // 当前2象限费率 M 无功电能示值  数据格式 11  kvarh  4
    public A11 RP_R2;
    public A11[] RP_R2_FEES;
    //(4)当前3象限无功总电能示值  数据格式 11  kvarh  4
    //当前3象限费率 1 无功电能示值  数据格式 11  kvarh  4
    //        „„  „„  „„  „„
    //当前3象限费率 M 无功电能示值  数据格式 11  kvarh  4
    public A11 RP_R3;
    public A11[] RP_R3_FEES;
    // -------------------------------------------------------------------------
    public F34(){}
    public F34(byte[] in){
        init(in);
    }
    // --------------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=5+1;
        len=len+RAP_R.length()+RAP_R_FEES.length*A14.LENGTH;
        len=len+RRP_R.length()+RRP_R_FEES.length*A11.LENGTH;
        len=len+RP_R2.length()+RP_R2_FEES.length*A11.LENGTH;
        len=len+RP_R3.length()+RP_R3_FEES.length*A11.LENGTH;
        return len;
    }

    @Override
    public byte[] valueOf() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        byte[] tmp;
        tmp=new byte[readingTime.length()];
        buf.get(tmp);
        readingTime.init(tmp);
        feeCount=buf.get();
        //--------------------------------------
        tmp=new byte[RAP_R.length()];
        buf.get(tmp);
        RAP_R.init(tmp);
        for(int i=0;i<RAP_R_FEES.length;i++)
        {
            buf.get(tmp);
            RAP_R_FEES[i].init(tmp);
        }
        //--------------------------------------
        tmp=new byte[RRP_R.length()];
        buf.get(tmp);
        RRP_R.init(tmp);
        for(int i=0;i<RRP_R_FEES.length;i++)
        {
            buf.get(tmp);
            RRP_R_FEES[i].init(tmp);
        }
        //--------------------------------------
        tmp=new byte[RP_R2.length()];
        buf.get(tmp);
        RP_R2.init(tmp);
        for(int i=0;i<RP_R2_FEES.length;i++)
        {
            buf.get(tmp);
            RP_R2_FEES[i].init(tmp);
        }
        // ----------------------------------------
        tmp=new byte[RP_R3.length()];
        buf.get(tmp);
        RP_R3.init(tmp);
        for(int i=0;i<RP_R3_FEES.length;i++)
        {
            buf.get(tmp);
            RP_R3_FEES[i].init(tmp);
        }
    }
}
