package PLMS.DataUnits.AFN0E;

import PLMS.Enums.EVENT;

/**
 *  事件记录格式
 */
public abstract class AbstactERC {

    public EVENT erc;               //事件类型，1字节
    public int length;              //该事件记录的长度，1字节
    public abstract int length();
    public abstract byte[] valueOf();
    public abstract void init(byte[] in);

}
