package PLMS.Checker;
import java.util.regex.*;
import java.lang.Exception;
public class IntegerNumberChecker extends RegexChecker{
	private int length;
	
	public IntegerNumberChecker(int len){
		super("(0*)[0-9]{1,"+len+"}");	
		length=len;
        tips="需要整数（最大长度为"+len+"）" ;
	}
	public void setRegexText(String text){
		
	}
	public void setLength(int len){
		length=len;
		super.setRegexText("(0*)[0-9]{1,"+length+"}");
	}
	public int getLength(){return length;}
}