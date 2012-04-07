package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;

/**
 * 参数设置--终端抄表间隔设置
 * 终端抄表间隔为终端实时抄表的时间间隔，取值范围为 1Min~60Min。
 */
public class F24  extends AbstractFn{
    public static  final  int LENGTH=1;
    public int readingTimeInterval;
    public F24(){}
    public F24(byte[] in)
    {
        init(in);
    }
    @Override
    public int length() {
        return LENGTH;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] valueOf() {
        byte[] ret=new byte[this.length()];
        ret[0]=(byte)readingTimeInterval;
        return ret;
    }

    @Override
    public void init(byte[] in) {
       this.readingTimeInterval=in[0];
    }
}
