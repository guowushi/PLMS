package PLMS.Checker;
import java.lang.reflect.*;
public class NumberRangeChecker<T extends Number> extends RangeChecker
{
	private T max;
	private T min;
	public NumberRangeChecker(T num1,T num2){
		 
			min=num2 ;
			max=num1 ;
		 
		
	}
	public boolean valid(String text){
		Class<?> c=max.getClass();
		try{
		Constructor<?> constructor=c.getConstructor(String.class);
//<?> newObj=constructor.newInstance(text);
		}catch(Exception e){
			return false;
		}
	/*
		Class<?> c=T.class;
		Class<String> []param=new Class<String>[1];//{String.class};
		Constructor<?> constructor=c.getConstructors(param);
	 

		try{
			T newObj=constructor.newInstance(text);
			Comparable<T> value=(Comparable<T>)newObj;
			if(value.comparaTo(max)<0 && value.comparaTo(min)>0)
				return true;
		}catch(Exception e){
			return false;
		}*/
		return false;
	}

    @Override
    public String getTips() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTips(String tips) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}