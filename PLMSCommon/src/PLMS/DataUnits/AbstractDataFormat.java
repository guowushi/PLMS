package PLMS.DataUnits;

import PLMS.Exceptions.DataFormException;

/**
 * 抽象的数据格式，所有的A？？都必须从此类派生
 */
public abstract  class AbstractDataFormat {
    private String tips;
    public abstract int length();
    public abstract byte[] valueOf();
    public abstract void init(byte[] in) throws DataFormException;
    public abstract <T>   T getValue();
    
    public String getTips()
    {
      return tips;
    }
    
}
