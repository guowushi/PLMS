package PLMS.GUI;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-13
 * Time: 下午6:18
 * To change this template use File | Settings | File Templates.
 */
public class MyDbTableModel  extends AbstractTableModel {
        String[] columnNames = new String[0] ;// 列名
        Vector<String[]> rows_data = new Vector<String[]>() ;// 行
        public  MyDbTableModel(ResultSet results){
            setResultSet(results) ; //传入一个记录集
        }

        public void setResultSet(ResultSet results){
            try{
                ResultSetMetaData mdata = results.getMetaData() ;
                int columns = mdata.getColumnCount() ;
                columnNames = new String[columns] ;
                for(int i=0;i<columns;i++){
                    columnNames[i] = mdata.getColumnLabel(i+1) ;
                }
                rows_data.clear() ; //清空行数据
                String[] rowData ;
                while(results.next()){
                    rowData = new String[columns] ;
                    for(int i=0;i<columns;i++)
                        rowData[i] = results.getString(i+1) ;
                    rows_data.addElement(rowData) ;
                }
                fireTableChanged(null);
            }catch(SQLException se){
                se.printStackTrace() ;
            }

        }

        public void insertEmptyRow(){
            String[] rowData = new String[columnNames.length] ;
            for(int i=0;i< columnNames.length;i++)
                rowData[i] = "" ;
            rows_data.addElement(rowData) ;
            fireTableChanged(null);
        }

        public int getColumnCount(){
            return columnNames.length ;
        }
        public int getRowCount(){
            return rows_data == null ? 0:rows_data.size() ;
        }
        public String getValueAt(int row,int column){
            return rows_data.elementAt(row)[column] ;
        }
        public String getColumnName(int column){
            return columnNames[column];
        }

    }


