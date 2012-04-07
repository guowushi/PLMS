package PLMS.frames;

import PLMS.Enums.RequestFunctionCode;

/**
 * 回复消息(PRM=1),消息来自于启动站
 */
public interface IRequestMessage extends IMessage{

        public void setFunCode(RequestFunctionCode funCode);
}
