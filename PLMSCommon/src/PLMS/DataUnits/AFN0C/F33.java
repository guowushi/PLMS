package PLMS.DataUnits.AFN0C;

import PLMS.DataForm.A11;
import PLMS.DataForm.A14;
import PLMS.DataForm.A15;
import PLMS.DataUnits.AbstractFn;

import java.nio.ByteBuffer;

/**
 * 请求1类数据：当前正向有/无功电能示值、一/四象限无功电能示值（总、费率 1~M）
 */
public class F33 extends AbstractFn{
    //public final  int LENGTH=10;
    //-------------------------------------------------------------------
    public A15 readingTime;         // 终端抄表时间  数据格式 15  分时日月年  5
    public int feeCount;            // 费率数 M  BIN  个  1
    // (1)当前正向有功总电能示值  数据格式 14  kWh  5
    //当前费率 1 正向有功总电能示值  数据格式 14  kWh  5
    //        „„  „„  „„  „„
    //当前费率 M 正向有功总电能示值  数据格式 14  kWh  5
    public A14 PAP_R;
    public A14[] PAP_R_FEES;
    // (2)当前正向无功总电能示值  数据格式 11  kvarh  4
    //当前费率 1 正向无功总电能示值  数据格式 11  kvarh  4
    //        „„  „„  „„  „„
    //当前费率 M 正向无功总电能示值  数据格式 11  kvarh  4
    public A11 PRP_R;
    public A11[] PRP_R_FEES;
    //(3)当前一象限无功总电能示值  数据格式 11  kvarh  4
    //当前一象限费率 1 无功电能示值  数据格式 11  kvarh  4
     //       „„  „„  „„  „„
   // 当前一象限费率 M 无功电能示值  数据格式 11  kvarh  4
    public A11 RP_R1;
    public A11[] RP_R1_FEES;
    //(4)当前四象限无功总电能示值  数据格式 11  kvarh  4
    //当前四象限费率 1 无功电能示值  数据格式 11  kvarh  4
    //        „„  „„  „„  „„
    //当前四象限费率 M 无功电能示值  数据格式 11  kvarh  4
    public A11 RP_R4;
    public A11[] RP_R4_FEES;
    // --------------------------------------------------------------------

    public F33(){}
    public F33(byte[] in ){
        init(in);
    }
    // ---------------------------------------------------------------------
    @Override
    public int length() {
        int len=0;
        len=5+1;
        len=len+PAP_R.length()+PAP_R_FEES.length*A14.LENGTH;
        len=len+PRP_R.length()+PRP_R_FEES.length*A11.LENGTH;
        len=len+RP_R1.length()+RP_R1_FEES.length*A11.LENGTH;
        len=len+RP_R4.length()+RP_R4_FEES.length*A11.LENGTH;
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
        tmp=new byte[PAP_R.length()];
        buf.get(tmp);
        PAP_R.init(tmp);
        for(int i=0;i<PAP_R_FEES.length;i++)
        {
            buf.get(tmp);
            PAP_R_FEES[i].init(tmp);
        }
        //--------------------------------------
        tmp=new byte[PRP_R.length()];
        buf.get(tmp);
        PRP_R.init(tmp);
        for(int i=0;i<PRP_R_FEES.length;i++)
        {
            buf.get(tmp);
            PRP_R_FEES[i].init(tmp);
        }
        //--------------------------------------
        tmp=new byte[RP_R1.length()];
        buf.get(tmp);
        RP_R1.init(tmp);
        for(int i=0;i<RP_R1_FEES.length;i++)
        {
            buf.get(tmp);
            RP_R1_FEES[i].init(tmp);
        }
        // ----------------------------------------
        tmp=new byte[RP_R4.length()];
        buf.get(tmp);
        RP_R4.init(tmp);
        for(int i=0;i<RP_R4_FEES.length;i++)
        {
            buf.get(tmp);
            RP_R4_FEES[i].init(tmp);
        }
    }
}
