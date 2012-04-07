package PLMS.GUI;

import javax.swing.table.AbstractTableModel;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 */
public class myRowModel<T>  extends AbstractTableModel{
    private HashMap<Object,T>  rowsMap;                  //存放关键字key -row对象映射
    private ArrayList<T>       rows=new ArrayList<T>();  //所有的数据行对象
    //private ArrayList<Method>  cols=new ArrayList<Method>(); //列集合,对应一个getter方法,返回列值
    private TreeMap<Integer,Method>  cols=new TreeMap<Integer, Method>();
    private Class<T>  clazz;
    public  myRowModel(Class<T> t){
        clazz=t; init();
    }

    public void init(){
        
        Method []methods=clazz.getMethods();   //获取该列对象所有的方法
        for(Method method:methods){
            TableColumnDefine annotation=(TableColumnDefine)method.getAnnotation(TableColumnDefine.class);    //找到列对象所表示的列
            if(annotation!=null){
                //annotation.order();
                cols.put(annotation.order(),method);        //添加到列集合

            }
        }


    }
    public void getPrimary(){
        Method []methods=clazz.getMethods();   //获取该列对象所有的方法
        int cnt=0;
        for(Method method:methods){
            Annotation annotation=method.getAnnotation(PrimaryKeyDefine.class);    //找到列对象所表示的列
            if(annotation!=null){

            }
        }

    }
    //----------------------------------------------------------------------------
    /**
     * 返回行数
     * @return
     */
    @Override
    public int getRowCount() {
        return rows.size();
    }

    /**
     * 返回列数
     * @return
     */
    @Override
    public int getColumnCount() {
            return cols.size();
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T t=rows.get(rowIndex);       //获取第rowIndex行数据
        Object ret=null;
        try {
           ret= cols.get(columnIndex).invoke(t);    //获取该行的列数据
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);

        T t=rows.get(rowIndex);       //获取第rowIndex行数据
        Object ret=null;
        try {
            ret= cols.get(columnIndex).invoke(t,aValue);    //获取该行的列数据
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    
    public void  addRow(T row)
    {
        rows.add(row);
        this.fireTableStructureChanged();
    }

    public void findRowByKey()
    {

    }
}
