package PLMS.Checker;
public class DecimalRangeChecker extends RangeChecker{
	private double max;
	private double min;
	public DecimalRangeChecker(double num1,double num2){
		if(num1>num2){
			max=num1;
			min=num2;
		}else{
			max=num2;
			min=num1;
		}
	}
	public boolean valid(String text){
		try{
			double num=Double.parseDouble(text);
			if(num>min && num<max)
				return true;
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
}