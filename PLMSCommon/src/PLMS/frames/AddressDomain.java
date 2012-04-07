package PLMS.frames;
import PLMS.Messages.LoginMessage;
import PLMS.Utils.*;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.ByteBuffer;

/**
 * 地址域
 */
public final class AddressDomain implements IMessagePart{
    public static final  int LENGTH=5;

    protected int isMultiAddress= MessageDefinition.MULTICAST_NO;
    public  int regionAddress;      //BCD格式，2字节，如1-2-3-4
    //public String regionAddress;     //
    public  int termialAddress;     // 终端地址，2字节
    public  int  mainStationAddress;  //主站地址 1字节
  
    // -------------------------------------------------------------------------------
    /**
     * 创建地址域
     * @param regionAddress
     * @param mainStationAddress
     * @param termialAddress
     */
    public AddressDomain(int regionAddress,  int termialAddress,int mainStationAddress) {
        this.regionAddress = regionAddress &0xFFFF;
        this.termialAddress = termialAddress&0xFFFF;
        this.mainStationAddress = mainStationAddress&0xFF;
    }
    public AddressDomain(byte[] in) {
        init(in);
    }
    public AddressDomain(boolean multicast) {
        setMultiCast(multicast);

    }
    public AddressDomain() {
    }
    // -----------------------------------------------------------------
    /**
     * 设置多播
     * @param yn
     */
    public void setMultiCast(boolean yn){
        if(yn){
            isMultiAddress=MessageDefinition.MULTICAST_YES;
            termialAddress=0xffff;
        }else{
            isMultiAddress= MessageDefinition.MULTICAST_NO;
        }
    }

    /**
     * 设置地址域
     * @param a1
     */
    public  void setRegionAddress(short a1)
    {
        regionAddress=a1 & 0xFFFF;
    }
    /**
     *  获取行政区域编码A1
     */
    public  int getRegionAddress(){
        return this.regionAddress ;
        
    }
    /**
     * 获取终端地址A2
     * @return
     */
    public int  getTerminalAddress(){
        
        return this.termialAddress;

    }

    public void setMainStationAddress(int mainStationAddress) {
        this.mainStationAddress = mainStationAddress &0xFF;
    }

    /**
     * 设置终端地址
     * @param a2
     */
    public void setTermialAddress(short  a2)
    {
        //A2=ByteUtil.digitalToByte(a2);
        if(a2==0xffff)
        {
            isMultiAddress=MessageDefinition.MULTICAST_YES;
            termialAddress=0xffff;
        }
       else{
            isMultiAddress= MessageDefinition.MULTICAST_NO;
            termialAddress=a2;
        }
    }


    /**
     * 是否为组播地址
     * @return
     */
    public boolean isMultiAddressFlag() {
        if ( isMultiAddress==MessageDefinition.MULTICAST_YES && termialAddress==0xFFFF ) {
            return false;
        } else {
            return true;
        }
    }
    public byte[] valueOf()
    {
        IoBuffer buf= IoBuffer.allocate(LENGTH);
        try{
        byte[] regs;
        //regs= ByteUtil.str2Bcd(String.valueOf(regionAddress));  //有问题！！
        //regs= ByteUtil.str2Bcd(String.valueOf(regionAddress));  //有问题！！
        buf.putShort((short)regionAddress);
        buf.putShort((short)termialAddress);
        int a3=(mainStationAddress<<1) | isMultiAddress   ;
        buf.put((byte)a3);
        buf.flip();
        }catch (Exception e)
        {

        }
        return buf.array();
    }
    public boolean  isValid()
    {
        return true;
    }
    public  int length()
    {
        return  LENGTH;
    }
    
    public void init(byte[] in ){
        ByteBuffer buf=ByteBuffer.wrap(in);
        byte[] reg=new byte[2];
        buf.get(reg);
        this.regionAddress=(Integer.parseInt(ByteUtil.bcd2Str(reg)));  //有问题!
        this.regionAddress=buf.getShort();
        this.termialAddress=buf.getShort();
        this.mainStationAddress=buf.get()>>>1;
    }
    public static void main(String[] args){
        byte[] reg=new byte[2];
        LoginMessage msg = new LoginMessage(0x3030, 0xea03,0);

        reg[0]=0x30;
        reg[1]=0x30;
        // 68 01 00 01 00 68  --C9 --00 30 30 06 00 02 70 00 00 01 00 00 16
        //68 31 00 31 00 68 --00 --48 30 06 00 00 00 60 00 00 01 00 DF 16
        System.out.print(ByteUtil.bcd2Str(reg));

         
    }
}
