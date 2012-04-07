package PLMS.DataUnits;

/**
 * Fn对象
 */
public class FnObject<T extends AbstractFn> {
    private String name="";                  //名称
    private String tip="";                     //说明
    private boolean enabled=false;      // 是否启用
   private Class<T>    clazz=null;               // 对应的类
    //---------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
    // ---------------------------------------------
    public  static <T extends AbstractFn> FnObject createFnObject(String name,Class<T>  clazz)
    {
        FnObject fnObject=new FnObject();
        fnObject.setName(name);
        fnObject.setClazz(clazz);
        return fnObject;
    }
}
