package PLMS.frames;

import PLMS.Enums.EnumPRM;
import PLMS.Enums.MSG_DIRECTION;

/**
 * 消息工厂(用于创建不同的消息)
 */
public class MessageFactory {

    public static <T extends AbstractMessage> T create(T msg)
    {
         MSG_DIRECTION dir =MSG_DIRECTION.DownStream;
        EnumPRM prm=EnumPRM.FROM_PRIM_STATION;
        // ------下行，主动发送给终端----
        if(dir==MSG_DIRECTION.DownStream && prm==EnumPRM.FROM_PRIM_STATION)
        {
           //DownRequestMessage msg=new DownRequestMessage();
        }
        // ------下行，终端发送给主站后，主站做出的回应
        if(dir==MSG_DIRECTION.DownStream && prm==EnumPRM.FROM_SLAVE_STATION)
        {

        }
        // -----上行，终端发送给主站-----------------
        if(dir==MSG_DIRECTION.UPStream && prm==EnumPRM.FROM_PRIM_STATION)
        {

        }
        // -----上行，主站发送给终端一个消息后，终端对主站的回应-----------------
        if(dir==MSG_DIRECTION.UPStream && prm==EnumPRM.FROM_SLAVE_STATION)
        {

        }
        return  msg;
    }
    public static void main(String[] args)
    {
        DownRequestMessage m=new DownRequestMessage();
        DownRequestMessage b= MessageFactory.create(m);
        System.out.print("aaa");
    }
}
