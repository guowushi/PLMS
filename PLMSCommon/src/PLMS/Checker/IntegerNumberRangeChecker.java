package PLMS.Checker;
public class  IntegerNumberRangeChecker extends RangeChecker{
	private long max;
	private long min;

	public IntegerNumberRangeChecker(long num1,long num2){
		if(num1>num2){
			max=num1;
			min=num2;
		}else{
			max=num2;
			min=num1;
		}
        tips="需要"+min+"到"+max+"之间的整数";
	}
	public boolean valid(String text){
		try{
			long num=Long.parseLong(text);
			if(num>min && num<max)
				return true;
		}catch(Exception e){
			return false;
		}
		return false;
	}

	public boolean equals(Object obj){
		if(obj instanceof IntegerNumberRangeChecker){
			IntegerNumberRangeChecker c=(IntegerNumberRangeChecker)obj;
			if(max==c.max && min==c.min)
				return true;
		}
		return false;
	}
}