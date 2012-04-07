package PLMS.Checker;
import java.util.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.Color;
public class AutoCheckerExecutor extends CheckerExecutor{
	private InvalidCounter counter=InvalidCounter.getInvalidCounter();
 
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
					CheckerSet<IChecker> cs= checkerMap.get(component);
					if(cs!=null){
						 Iterator<IChecker> it=cs.iterator();
						IChecker ch1;
						while(it.hasNext()){
							ch1=it.next();
							if(!ch1.valid(component.getText())){
								component.setBackground(cs.getPromptBackground());
                                myToolTip.setComponet(component);
                                myToolTip.setText(ch1.getTips());
                                myToolTip.Show();
								 counter.add(component,ch1);
							}
							 else
							 	counter.remove(component,ch1);
						}
					}
				}
				
			};
		component.addFocusListener(fl);
		fl.focusLost(null);
	}
}