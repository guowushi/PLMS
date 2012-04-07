package PLMS.talk;

import PLMS.talk.EventEx.ActionEventEx;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-10
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class LoudSpeaker {

   
    protected Map<Object,LinkedList<ActionListener>> actionlist=new HashMap<Object, LinkedList<ActionListener>>();
    protected Map<Object,LinkedList<LI>> customList=new HashMap<Object, LinkedList<LI>>();
    protected Map<Object,LinkedList<TM>> methods=new HashMap<Object, LinkedList<TM>>();
    

    public void registerActionListener(Object module, ActionListener listener){
        LinkedList<ActionListener>l=actionlist.get(module);
        if(l!=null){
            if(!l.contains(listener))
                l.add(listener);
        } else {
            l=new LinkedList<ActionListener>();
            l.add(listener);
            actionlist.put(module,l);
        }
    }
    /**
     * 注册模块module的类型为EventID的事件监听器
     * */
    public void registerCustomEventListener(Object module,CustomEventListener listener,int EventID){
        LinkedList<LI> list=customList.get(module);
        LI li=new LI();
        li.listener=listener;
        li.eventId=EventID;
        if(list!=null){
            if(!list.contains(li))
                list.add(li);
        }else {
            list=new LinkedList<LI>();
            list.add(li);
            customList.put(module,list);
        }
    }


    
  /*  public void registerMethod(Object module,Object listener,int type,String methodName) throws NoSuchMethodException {
          LinkedList<TM>l=methods.get(module);
          TM tm=new TM();
         tm.obj=listener;
          tm.type=type;
          Class c=listener.getClass();
          Method  m=c.getMethod(methodName);
        tm.method=m;
          if(l!=null){
              if(!l.contains(tm))
                  l.add(tm);
          }else
          {
              l=new LinkedList<TM>();
              l.add(tm);
              methods.put(module,l);
          }
        
    }
*/

    public void ActionEvent(Object module,ActionEventEx e){
        LinkedList<ActionListener> l=actionlist.get(module);
        if(l!=null){
            for(int i=0;i<l.size();i++){
                l.get(i).actionPerformed(e);
            }
        }   
 /*       LinkedList<TM> l1=methods.get(module);
        if(l1!=null){
            TM tm=null;
            for(int i=0;i<l1.size();i++){
                tm=l1.get(i);
                if(tm.type== ACTION_EVENT){
                    try {
                        tm.method.invoke(tm.obj);
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }*/
    }

    public void CustomEvent(Object module,CustomEvent event){
        LinkedList<LI> l=customList.get(module);
        if(l!=null){
            for(int i=0;i<l.size();i++){
                if(event.getID()==l.get(i).eventId){
                    l.get(i).listener.todo(event);
                }
            }
        }
    }
}
class LI{
    CustomEventListener listener;
    int eventId;
}
class TM{
    public Object obj;
    public int type;
    public Method method;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TM tm = (TM) o;

        if (type != tm.type) return false;
        if (!method.equals(tm.method)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + method.hashCode();
        return result;
    }
}
