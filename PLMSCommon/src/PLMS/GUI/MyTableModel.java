package PLMS.GUI;

import PLMS.DataUnits.AFN02.F1;
import quick.dbtable.Column;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 自定义的表模型
 */
public class MyTableModel<T> extends AbstractTableModel {
    private String[] titleNames;                        // 列标题集合
    private ArrayList<T> rows = new ArrayList<T>();     //行集合
    private TableColumnModel  columns;                  //列集合
    private  int keyIndex;
    //private Vector<Object[]> rows=null;
    public MyTableModel() {

    }

    public MyTableModel(String[] titles) {
        titleNames = titles;
    }

    // --------------------------------------------------------------------------------
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
      return  rows.get(0).getClass().getFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T t=rows.get(rowIndex);
      Object obj= null;
        try {
            obj = t.getClass().getFields()[columnIndex].get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //super.setValueAt(aValue, rowIndex, columnIndex);
        T t=rows.get(rowIndex);

        try {
             t.getClass().getFields()[columnIndex].set(t,aValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }

    // -------------------------------------------------------------------------

    /**
     * 添加一行数据
     * @param row
     */
    public void addRow(T row) {
        rows.add(row);
        this.fireTableStructureChanged();
    }
    public  T ceateT(Class<T> clazz) {
          T instance=null;
        try {
            instance=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
    public void addRow(Class<T> clazz){
            T t=(T)ceateT(clazz);
            rows.add(t);
            fireTableStructureChanged();

    }

    /**
     * 删除一行
     * @param row
     */
    public void deleteRow(int row) {
        rows.remove(row);
    }
    public T getRow(int row){        
        return rows.get(row);
    }
    public T getRowByKey(Object key)
    {
         for(T r:rows){
             try {
                 if(r.getClass().getFields()[keyIndex].get(r).equals(key))
                     return r;
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             }
         }
        return null;
    }
    public void setPrimaryKey(String key){
   
            T t=rows.get(0);
            Field []fields=t.getClass().getFields();
            for(int i=0;i<fields.length;i++){
                if(fields[i].getName().equals(key)){
                    keyIndex=i;
                }
            }
      
    }

    public String[] getTitleNames() {
        return titleNames;
    }

    public void setTitleNames(String[] titleNames) {
        this.titleNames = titleNames;
        this.fireTableStructureChanged();
    }
}
