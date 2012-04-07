package PLMS.GUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 基于Bean的TableModel
 */
public class BeanTableModel<M> extends AbstractTableModel {
    private Vector<M> rows = new Vector<M>();
    //DefaultTableModel
    private Vector<BeanColumn> columns = new Vector<BeanColumn>();
    private Class<?> beanClass;

    public BeanTableModel(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
    //-----------------------------------------------------------------------------------
    /**
     * 添加新的一列
     * @param columnGUIName
     * @param beanAttribute
     * @param editable
     */
    public void addColumn(String columnGUIName, String beanAttribute,  EditMode editable) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(beanAttribute, beanClass);
            columns.add(new BeanColumn(columnGUIName, editable, descriptor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String columnGUIName, String beanAttribute) {
        addColumn(columnGUIName, beanAttribute, EditMode.NON_EDITABLE);
    }

    /**
     * 添加新的一行
     * @param row
     */
    public void addRow(M row) {
        rows.add(row);
       this.fireTableDataChanged();
    }

    public void addRows(List<M> rows) {
        for (M row : rows) {
            addRow(row);
        }
    }
    //-------------------------------------------------------------------
    public int getColumnCount() {
        return columns.size();
    }

    public int getRowCount() {
        return rows.size();
    }

    /**
     *  获取指定行和列的值
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        BeanColumn column = columns.get(columnIndex);
        M row = rows.get(rowIndex);

        Object result = null;
        try {
            result = column.descriptor.getReadMethod().invoke(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  设置指定行列的值
     * @param value
     * @param rowIndex
     * @param columnIndex
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        M row = rows.get(rowIndex);
        BeanColumn column = columns.get(columnIndex);

        try {
            column.descriptor.getWriteMethod().invoke(row, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        BeanColumn column = columns.get(columnIndex);
        Class<?> returnType = column.descriptor.getReadMethod().getReturnType();
        return returnType;
    }

    /**
     * 获取列名
     * @param column
     * @return
     */
    public String getColumnName(int column) {
        return columns.get(column).columnGUIName;
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).editable == EditMode.EDITABLE;
    }

    public List<M> getRows() {
        return rows;
    }

    public enum EditMode {
        NON_EDITABLE,
        EDITABLE;
    }

    /** 表格的一列 */
    private static class BeanColumn {
        private String columnGUIName;    //列名
        private EditMode editable;        //该列是否可编辑
        private PropertyDescriptor descriptor; //JavaBean的属性

        public BeanColumn(String columnGUIName, EditMode editable, PropertyDescriptor descriptor) {
            this.columnGUIName = columnGUIName;
            this.editable = editable;
            this.descriptor = descriptor;
        }
    }
    public M getRow(int i){
        
       return  rows.get(i);
   
    }
    public boolean  findRow(M m)
    {
        boolean ret=false;
        for(M r:rows){
            if(r.equals(m)){
                return true;
            }
        }
        return ret;
    }
    public int getRowIndex(M m)
    {
        int i=0;
        for(M r:rows){
        
            if(r.equals(m)){
               return i;
            }
            i++;
        }
        return i; 
    }

    public void removeRow(int i){
          rows.remove(i);
    }
    public void updateRow(M row)
    {
        if(findRow(row)){
            rows.set(getRowIndex(row),row);
        }
        this.fireTableDataChanged();
    }
   
    public void invokeRefreshTable(final BeanTableModel model) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 model.fireTableDataChanged();
            }
        });
    }
}
