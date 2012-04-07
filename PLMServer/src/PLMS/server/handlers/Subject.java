package PLMS.server.handlers;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-20
 * Time: 下午9:12
 * To change this template use File | Settings | File Templates.
 */
public interface Subject<T> {
    public void Attach(Observer<T> observer);
    public void Detach(Observer<T> observer);
    void Notify();
}
