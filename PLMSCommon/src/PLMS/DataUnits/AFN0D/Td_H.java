package PLMS.DataUnits.AFN0D;

/**
 *  小时冻结类数据时标 Td_h
 */
public class Td_H {
    private final int LENGTH=1;
    //D0~D5：表示上一整点的小时时间，数值范围 0~23。
    //D6~D7：表示冻结密度 m（见附录 C）。
    private int freezeDensity;
    private int freezeHour;
    // ----------------------------------------------------
    public Td_H(){}
    public Td_H(byte[] in){
        
    }
    // -----------------------------------------------------
    public int length()
    {
        return this.LENGTH;
    }
    public byte[] valueOf()
    {
        byte[] ret=new byte[this.length()];
        int decade= freezeHour / 10 ;
        int single= freezeHour % 10;
        ret[0]=(byte) ((freezeDensity<<6) + (decade<<4) + single) ;
        return ret;
    }
    public void init(byte[] in)
    {
         freezeDensity= (in[0] &0xA0) >>6 ;
        int decade=(in[0] &0x30) >>4 ;
        int single=(in[0] &0x0F);
        freezeHour= decade*10+single;
    }
}
