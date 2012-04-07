package PLMS.Checker;
import PLMS.MyToolTip.ToolTip;

import java.util.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.Color;
public class CheckerExecutor{

    protected ToolTip myToolTip=new ToolTip();
	protected Map<JTextComponent,CheckerSet<IChecker>> checkerMap=new HashMap();
	public void add(final JTextComponent component,IChecker []checker){
		 
		if(checkerMap.containsKey(component)){
			CheckerSet<IChecker> cs= checkerMap.get(component);
			for(int i=0;i<checker.length;i++)
				cs.add(checker[i]);
			return;
		}
		CheckerSet<IChecker> checkers=new CheckerSet<IChecker>();
		for(int i=0;i<checker.length;i++)
			checkers.add(checker[i]);
		bind(component,checkers);
	}
	public void add(final JTextComponent component,IChecker checker){
		if(checkerMap.containsKey(component)){
			CheckerSet<IChecker> cs=checkerMap.get(component);
			cs.add(checker);
			return ;
		}
		CheckerSet<IChecker> cs=new CheckerSet<IChecker>();
		cs.add(checker);
		
		bind(component,cs);
		
	}
	
	public void remove(final JTextComponent component,IChecker checker){
		CheckerSet<IChecker> cs=checkerMap.get(component);
		if(cs==null)
			return ;
		Iterator<IChecker> it=cs.iterator();
		while(it.hasNext()){
			IChecker checker1=it.next();
			if(checker1.equals(checker)){
				it.remove();
			}
		}
	}
	public void removeAll(final JTextComponent component){
		if(checkerMap.containsKey(component)){
			checkerMap.remove(component);
		}
	}
	protected void bind(final JTextComponent component,CheckerSet<IChecker> cs)
	{
		checkerMap.put(component,cs);
		cs.setOldBackground(component.getBackground());
		cs.setPromptBackground(Color.red);

        FocusListener fl=	new FocusListener(){
            public void focusGained(FocusEvent e){
                CheckerSet<IChecker> cs= checkerMap.get(component);
                component.setBackground(cs.getOldBackground());
            }
            public void focusLost(FocusEvent e){

            }

        };
        component.addFocusListener(fl);
	}
    public void setToolTip(ToolTip tt){
        myToolTip=tt;
    }
}