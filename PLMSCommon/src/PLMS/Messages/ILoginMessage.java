package PLMS.Messages;

import PLMS.frames.IMessage;

/**
 * 定义LoginMessage接口，
 * 只定义了登录消息需要的几个方法，这样屏蔽从LoginMessage父类继承的其他方法（以免设置错误）.
 */
public interface ILoginMessage  extends IMessage{

}
