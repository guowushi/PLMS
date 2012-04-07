package PLMS.Exceptions;

import PLMS.DataUnits.AbstractDataFormat;

/**
 * PLMS - Fn还未实现异常
 */
public class FnNotImplementationException extends PLMSException{
    private AbstractDataFormat whichFormat;
    public FnNotImplementationException(String message, AbstractDataFormat whichFormat) {
        super(message);
        this.whichFormat = whichFormat;
    }

    public FnNotImplementationException(AbstractDataFormat whichFormat) {
        super(whichFormat.getTips());
        this.whichFormat = whichFormat;

    }
    public FnNotImplementationException(String message) {
       super(message);
    }
}
