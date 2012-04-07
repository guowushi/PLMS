package PLMS.DataUnits.AFN04;

import PLMS.DataForm.A05;
import PLMS.DataForm.A06;
import PLMS.DataForm.A07;
import PLMS.DataForm.A23;
import PLMS.DataUnits.*;

import java.nio.ByteBuffer;

/**
 * 参数设置--测量点限值参数
 */
public class F26 extends AbstractFn{
    public static  final  int LENGTH=27;
    public A07 U_UP; //电压合格上限  数据格式 07  V  2
    public A07 U_LOW;//电压合格下限  数据格式 07  V  2
    public A07 U_BREAK; //电压断相门限  数据格式 07  V  2
    public A07 U_OVER;//电压上上限（过压门限）  数据格式 07  V  2
    public A07 U_OWE; //电压下下限（欠压门限）  数据格式 07  V  2
    public A06 I_OVER; //相电流上上限（过流门限）  数据格式 06  A  2
    public A06 I_UP;  //相电流上限（额定电流门限）  数据格式 06  A  2
    public A06 I0_UP;  //零序电流上限  数据格式 06  A  2
    public A23 VIEW_OVER; //视在功率上上限  数据格式 23  kVA  3
    public A23 VIEW_UP;  //视在功率上限  数据格式 23  kVA  3
    public A05 U_UNBALANCE;   //三相电压不平衡限值  数据格式 05  %  2
    public A05 I_UNBALANCE;//三相电流不平衡限值  数据格式 05  %  2
    public int LOST_U_TIME;  //连续失压时间限值  BIN  min  1
    //------------------------------------------------------------------
    public F26(){}
    public F26(byte[] in){
             init(in);
    }
    // -----------------------------------------------------------------
    @Override
    public int length() {
        return LENGTH;   
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf= ByteBuffer.allocate(this.length());
        buf.put(U_UP.valueOf());
        buf.put(U_LOW.valueOf());
        buf.put(U_BREAK.valueOf());
        buf.put(U_OVER.valueOf());
        buf.put(U_OWE.valueOf());
        buf.put(I_OVER.valueOf());
        buf.put(I_UP.valueOf());
        buf.put(I0_UP.valueOf());
        buf.put(VIEW_OVER.valueOf());
        buf.put(VIEW_UP.valueOf());
        buf.put(U_UNBALANCE.valueOf());
        buf.put(I_UNBALANCE.valueOf());
        buf.put((byte)LOST_U_TIME);
        return buf.array();  
        
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf= ByteBuffer.wrap(in);
        byte[] tmp;
        // -------------------------------
        tmp=new byte[U_UP.length()];
        buf.get(tmp);
        U_UP.init(tmp);
        tmp=new byte[U_LOW.length()];
        buf.get(tmp);
        U_LOW.init(tmp);
        tmp=new byte[U_BREAK.length()];
        buf.get(tmp);
        U_BREAK.init(tmp);
        tmp=new byte[U_OVER.length()];
        buf.get(tmp);
        U_OVER.init(tmp);
        tmp=new byte[U_OWE.length()];
        buf.get(tmp);
        U_OWE.init(tmp);
        tmp=new byte[I_OVER.length()];
        buf.get(tmp);
        I_OVER.init(tmp);
        tmp=new byte[I0_UP.length()];
        buf.get(tmp);
        I0_UP.init(tmp);

        tmp=new byte[VIEW_OVER.length()];
        buf.get(tmp);
        VIEW_OVER.init(tmp);

        tmp=new byte[VIEW_UP.length()];
        buf.get(tmp);
        VIEW_UP.init(tmp);

        tmp=new byte[U_UNBALANCE.length()];
        buf.get(tmp);
        U_UNBALANCE.init(tmp);

        tmp=new byte[I_UNBALANCE.length()];
        buf.get(tmp);
        I_UNBALANCE.init(tmp);

        LOST_U_TIME=buf.get();
    }     
}
