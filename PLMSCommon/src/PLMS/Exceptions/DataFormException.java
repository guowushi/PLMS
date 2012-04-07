package PLMS.Exceptions;

import PLMS.DataUnits.AbstractDataFormat;

/**
 * PLMS -数据格式错误
 */
public class DataFormException  extends PLMSException{
    private AbstractDataFormat whichFormat;
    //----------------------------------------------------------------
    public DataFormException(String message, AbstractDataFormat whichFormat) {
        super(message);
        this.whichFormat = whichFormat;
    }

    public DataFormException(AbstractDataFormat whichFormat) {
        super(whichFormat.getTips());
        this.whichFormat = whichFormat;

    }
    public DataFormException() {

    }
    // --------------------------------------------------------------

}
