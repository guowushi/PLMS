package PLMS.DataUnits.AFN04;

import PLMS.DataUnits.AbstractFn;
import PLMS.GUI.PLMSJFrame;
import org.apache.commons.beanutils.Converter;

import java.nio.ByteBuffer;

/**
 * 设置参数-主站IP地址和端口
 */
public class F3 extends AbstractFn{
    public final static int LENGTH=40;
    //------------------------------------------
    public String mainIP;
    public int mainPort;
    public String backIP;
    public int backPort;
    public String gatewayIP;
    public  int gatewayPort;
    public  String proxyIP;
    public  int proxyPort;
    public  String APN;
    // --------------------------------------------------
    public  F3(){}
    public F3(byte[] in){init(in);}
    // ------------------------------------------------
    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public byte[] valueOf() {
        ByteBuffer buf=ByteBuffer.allocate(this.length());
        String[] ip_part= this.mainIP.split("\\.");
        for(int i=0;i<ip_part.length;i++)
        {
              int tmp=  Integer.getInteger(ip_part[i]);
              buf.put((byte)tmp);
        }
        buf.putShort((short)this.mainPort);
        //----------------------------------
        ip_part= this.backIP.split("\\.");
        for(int i=0;i<ip_part.length;i++)
        {
            int tmp=  Integer.getInteger(ip_part[i]);
            buf.put((byte)tmp);
        }
        buf.putShort((short)this.backPort);
        //----------------------------------
        ip_part= this.gatewayIP.split("\\.");
        for(int i=0;i<ip_part.length;i++)
        {
            int tmp=  Integer.getInteger(ip_part[i]);
            buf.put((byte)tmp);
        }
        buf.putShort((short)this.gatewayPort);
        //----------------------------------
        ip_part= this.proxyIP.split("\\.");
        for(int i=0;i<ip_part.length;i++)
        {
            int tmp=  Integer.getInteger(ip_part[i]);
            buf.put((byte)tmp);
        }
        buf.putShort((short)this.proxyPort);
        //-------------------------------------------
        buf.put(APN.getBytes());
        return buf.array();
    }

    @Override
    public void init(byte[] in) {
        ByteBuffer buf=ByteBuffer.wrap(in);
       this.mainIP= Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get());
        
        this.mainPort= buf.getShort();
        this.backIP=Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get());
        this.backPort=buf.getShort();
        this.gatewayIP=Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get());
        this.gatewayPort=buf.getShort();
        this.proxyIP=Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get())+"."+Integer.toString(buf.get());
        this.proxyPort=buf.getShort();
        byte[] tmp=new byte[16];
        buf.get(tmp);
        this.APN=String.valueOf(tmp);
    }
    // ------------------------------------------------
    public String getIP(byte[] in)
    {
       return Integer.toString(in[0])+"."+Integer.toString(in[1])+"."+Integer.toString(in[2])+"."+Integer.toString(in[3]);
    }

    
}
