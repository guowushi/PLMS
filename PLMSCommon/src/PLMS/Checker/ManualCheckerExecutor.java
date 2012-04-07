package PLMS.Checker;
import PLMS.MyToolTip.ToolTip;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class ManualCheckerExecutor extends CheckerExecutor{
	public boolean validAll(){
		Set<JTextComponent> keys=checkerMap.keySet();
		Iterator<JTextComponent> it=keys.iterator();
		JTextComponent com;
		CheckerSet<IChecker> cs;
        //遍历<文本组件，检查器集>图
		while(it.hasNext()){
			com=it.next();//被检查的对象
			cs=checkerMap.get(com); //检查器集
			if(cs!=null){
				Iterator<IChecker> it1=cs.iterator();
                IChecker ch;
				while(it1.hasNext()){ //遍历检查器集
                    ch= it1.next();   //获取检查器
					if(!ch.valid(com.getText())){ //文本组件值无效
						com.setBackground(cs.getPromptBackground());
                        myToolTip.setComponet(com);//将myTooltip和com组件相关联
                        myToolTip.setText(ch.getTips());
                        myToolTip.Show();

						return false;
					}else{
						com.setBackground(cs.getOldBackground());
					}
				}
			}
		}
		return true;
	}
    public void setTooltip(ToolTip tt){
        super.setToolTip(tt);
    }

}