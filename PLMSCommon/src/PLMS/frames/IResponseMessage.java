package PLMS.frames;


import PLMS.Enums.ResponseFunctionCode;

/**
 * 请求消息（PRM=0）消息来自于从动站
 */
public interface IResponseMessage extends IMessage {
    public void setFunCode(ResponseFunctionCode funCode);
}
