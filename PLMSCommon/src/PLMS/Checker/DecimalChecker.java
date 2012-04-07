package PLMS.Checker;
import java.util.regex.*;
public class DecimalChecker extends RegexChecker
{ 
	private int leftLen;
	private int rightLen;
	public DecimalChecker(int lLen,int rLen){
		super("(0*)[0-9]{0,"+lLen+"}(\\.[0-9]{1,"+rLen+"}(0*))?");
		leftLen=lLen;
		rightLen=rLen;
		tips="需要小数（小数点左边对多"+lLen+"位，最少0位；小数点右边最多"+rLen+"为，最少0为）";
	}
	public void setRegexText(String reg){}
	public boolean valid(String text){
		if(text.length()==0)
			return false;
		return super.valid(text);
	}
	 
}