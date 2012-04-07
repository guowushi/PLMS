package PLMS.Checker;
import java.util.*;
import javax.swing.text.*;
public class InvalidCounter{
	private Map<JTextComponent,CheckerSet<IChecker>> InvalidMap=new HashMap();
	private static final InvalidCounter oneObject=new InvalidCounter();
	private InvalidCounter(){}
	public static InvalidCounter getInvalidCounter(){
		//return oneObject;
        return  new InvalidCounter();
	}
	void add(JTextComponent com,IChecker checker){
		 
			CheckerSet<IChecker> cs=InvalidMap.get(com);
			if(cs!=null)
				cs.add(checker);
			cs=new CheckerSet<IChecker>();
			cs.add(checker);
			InvalidMap.put(com,cs);
		
	}
	void remove(JTextComponent com,IChecker checker){
			CheckerSet<IChecker> cs=InvalidMap.get(com);
			if(cs!=null){
				Iterator<IChecker> it=cs.iterator();
				IChecker ch1;
				while(it.hasNext()){
					ch1=it.next();
					if(ch1.equals(checker))
						it.remove();
				}
				if(cs.size()==0)
					InvalidMap.remove(com);
			}
	}
	public boolean validAll(){
		return InvalidMap.size()==0;
	}
	
}