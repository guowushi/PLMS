package PLMS.Exceptions;

/**
 * 自定义的异常类.PLMS系统中所有异常的父类
 */
public class PLMSException extends Exception{
    //private String errorInfo="";
    private String systemInfo="电力管理系统";
    protected String exceptionMessage="";
    //--------------------------------------------
    public PLMSException(String message) {
        super(message);
    }

    public PLMSException() {
    }
    //----------------------------------------------
    @Override
    public String getMessage() {
        String ret= super.getMessage();
        ret=ret+systemInfo;
        ret=ret+exceptionMessage;
        return ret ;
    }
}
