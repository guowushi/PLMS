package PLMS.Base;

import PLMS.talk.ITalk;
import PLMS.talk.LoudSpeaker;
import PLMS.talk.TalkArguments;

import javax.swing.*;

/**
 * 需要交自己的某些事件公布出来的界面模块从此处继承
 */
public class Module extends JPanel{
    /**
     * 用于通知监听器（监听器感兴趣的）模块发生了（监听器感兴趣的）事件
     * */
    protected static LoudSpeaker loudSpeaker=new LoudSpeaker();
    public static LoudSpeaker getLoudSpeaker(){return  loudSpeaker;}

}
