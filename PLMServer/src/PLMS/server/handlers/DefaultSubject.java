package PLMS.server.handlers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 */
public class DefaultSubject<T> implements Subject<T>{
    protected T data;


    public void setData(T data){this.data=data;}
    // ---------------------------------------------------------
    protected List<Observer<T>> observers=new LinkedList<Observer<T>>();
    @Override
    public void Attach(Observer<T> tObserver) {
        if(!observers.contains(tObserver))
            observers.add(tObserver);
    }

    @Override
    public void Detach(Observer<T> tObserver) {
        observers.remove(tObserver);
    }

    @Override
    public void Notify() {
        for(int i=0;i<observers.size();i++)
            observers.get(i).Update(data);
    }


}
