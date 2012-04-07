package PLMS.Base;

import PLMS.Base.Event.submitEvent;
import PLMS.Checker.ManualCheckerExecutor;
import org.apache.commons.configuration.Configuration;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;

/**
 * 设置参数的界面需要从这里继承
 */
public abstract class WidgetBase {


    protected  ManualCheckerExecutor executor = new ManualCheckerExecutor();
    EventListenerList listenerList=new EventListenerList();
    {
        addSubmitListener(new submitListener() {
            @Override
            public boolean CanSubmit(submitEvent e) {
                return executor.validAll();
            }
        });
    }

    /**
     * @param  sl submitEvent监听器
     * @see PLMS.Base.Event.submitEvent
     * @see submitListener
     * */
    public final void addSubmitListener(submitListener sl){
        listenerList.add(submitListener.class,sl);
        JMenuItem it;

    }
    /**
     * @param sl submitEvent 监听器
     * @see PLMS.Base.Event.submitEvent
     * @see submitListener
     * */
    public final void removeSubmitListener(submitListener sl){
        listenerList.remove(submitListener.class, sl);
    }
    /**
     * @return  返回表示设置参数的对象，如果某字段无效，那么返回null
     * */
    public final Object getValue(){
        submitListener[]sls=listenerList.getListeners(submitListener.class);
        if(sls!=null && sls.length!=0){
            submitEvent e=new submitEvent(this,0);
            for(int i=0;i<sls.length;i++)
            {
                  if(!sls[0].CanSubmit(e))
                      return null;
            }
        }
       return  collectValues();
    }
    /**
     *采集数据
     * */
    protected abstract Object collectValues();
    /**
     * 返回最外层容器
     * */
    public abstract Container getContentPane();
}


