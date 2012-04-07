package PLMS.frames;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 上行消息的AUX
 */
public class UpAUX extends AbstractAUX{
    private byte[] EC;   //事件计数器 ， 2字节

    public UpAUX() {
      // this.len=8;
        EC=new byte[2];
        
    }

    /**
     * 返回数据长度
     * 
     * @return
     */
    public  int length(int ACD,int Tpv)
    {
        int len=0;
        if(ACD==1)
        {
            len=len+EC.length;  
        }
        if(Tpv==1){
            len=len+tp.length;
        }
        return len;
    }
    @Override
    public byte[] valueOf() {
        IoBuffer buf = IoBuffer.allocate(this.length());
        buf.put(this.EC);
        buf.put(this.tp);
        return buf.array();
    }
    public boolean  isValid()
    {
        return true;
    }

    @Override
    public void init(byte[] in) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
