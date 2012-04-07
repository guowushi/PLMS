package PLMS.Enums;

/**
    消息状态
 */
public enum MSG_STATUS {

        MSG_IN_MIDDLE,      //多帧，中间帧
        MSG_LAST,            //多帧，最后一帧
        MSG_FIRST,          // 多帧,第一帧
        MSG_SINGLE;         // 单帧

}
