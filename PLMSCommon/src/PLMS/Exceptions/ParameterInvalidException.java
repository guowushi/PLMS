package PLMS.Exceptions;

/**
 * PLMS--参数无效的异常
 */
public class ParameterInvalidException extends Exception{
    /**
     * @param msg:异常消息
     * */
    public ParameterInvalidException(String msg){
        super(msg);
    }
}
