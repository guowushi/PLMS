package PLMS.Exceptions;

/**
 * PLMS-发送消息异常
 */
public class SendException extends PLMSException{
    private String errorInfo;
    public SendException(String message) {
        super(message);
        errorInfo=message;
    }

    public SendException() {

    }

}
