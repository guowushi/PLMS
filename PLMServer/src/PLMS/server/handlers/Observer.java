package PLMS.server.handlers;

/**
 * 观察者接口，要成为观察者必须实现此接口才行
 */
public interface Observer<T> {
    public void Update(T data);
}
