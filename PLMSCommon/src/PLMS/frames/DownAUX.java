package PLMS.frames;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 下行消息的AUX
 */
public class DownAUX extends  AbstractAUX{
    private byte[] PW; // 消息验证码，16字节
    //-------------------------------------------------
    public DownAUX() {
        super();
        PW=new byte[16];

        //this.len=22;
    }
    public  int length(int ACD,int Tpv)
    {
        int len=0;
        if(ACD==1)
        {
            len=len+PW.length;
        }
        if(Tpv==1){
            len=len+tp.length;
        }
        return len;
    }

     @Override
    public byte[] valueOf() {
        IoBuffer buf = IoBuffer.allocate(this.length());
        buf.put(this.PW);
        buf.put(this.tp);
        return buf.array();
    }
    @Override
    public boolean  isValid()
    {
        return true;
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
