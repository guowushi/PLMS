package PLMS.Checker;
import java.util.regex.*;
public class RegexChecker implements IChecker{
	protected Pattern p;
    protected String tips;
	public RegexChecker(String reg){
		p=Pattern.compile(reg);
	}
	public void setRegexText(String reg){
		p=Pattern.compile(reg);
	}
	public boolean valid(String text){
		Matcher m=p.matcher(text);
		return m.matches();
	}
	public boolean equals(Object obj){
		if(obj instanceof RegexChecker){
			RegexChecker tmp=(RegexChecker)obj;
			if(tmp.p.toString().equals(p.toString()));
				return true;
		}
		return false;
	}
    public String getTips(){
        return tips;
    }
    public void setTips(String tips){
        this.tips=tips;
    }
}