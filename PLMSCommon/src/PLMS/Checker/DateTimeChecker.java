package PLMS.Checker;
import java.util.regex.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
public class DateTimeChecker implements IChecker
{
    protected String tips;
	public DateTimeChecker(){
		
	}
	public boolean valid(String text){
		try{
			Date d=DateFormat.getDateInstance().parse(text);
			return true;
		}catch(ParseException e){
			return false;
		}
	}

    @Override
    public String getTips() {
        return tips;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTips(String tips) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}