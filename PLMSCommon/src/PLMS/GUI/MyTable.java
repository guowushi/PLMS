package PLMS.GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义的表格
 */
public class MyTable<T> extends JPanel {
    private String title;                                       //  标题
    private MyTableModel<T> tableModel;                       //table model
    protected JTable table = new JTable();
    private JPanel actionBar = new JPanel();
    private JPanel titleBar = new JPanel();
    Class<T> clazz;

    // -------------------------------------------------------------------------
    {
        initActionBar();
    }

    // -----------------------------------------------
    public MyTable(Class<T> clazz) {
        this.clazz = clazz;
        this.setLayout(new BorderLayout());
        this.add(titleBar, BorderLayout.NORTH);
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setRowHeight(20);
        //  添加行号列
        TableColumn colHeader=new TableColumn();
        table.addColumn(colHeader);
        //table.setAutoCreateColumnsFromModel(false);
        this.add(sp, BorderLayout.CENTER);
        this.add(actionBar, BorderLayout.SOUTH);

    }

    // ------------------------------------------------
    public void initActionBar() {
        JButton btnAdd;
        JButton btnDel;
        JButton btnSave;
        JButton btnFirst;
        JButton btnNext;
        JButton btnPrev;
        JButton btnLast;
        JLabel lblInfo;
        btnAdd = new JButton("添加");
        btnDel = new JButton("删除");
        btnSave = new JButton("修改");
        btnFirst = new JButton("第一条");
        btnPrev = new JButton("上一条");
        btnNext = new JButton("下一条");
        btnLast = new JButton("最后一条");
        // ------------------------------------
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // addNew();
                tableModel.addRow(clazz);
            }
        });
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRowCount() > 1) {
                    int[] rowids = table.getSelectedRows();
                    for (int i = rowids.length - 1; i >= 0; i--) {
                        delete(rowids[i]);
                    }

                } else {
                    delete(table.getSelectedRow());

                }
                table.updateUI();
            }
        });
        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first();
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                last();
            }
        });
        // ------------------------------------
        actionBar.add(btnAdd);
        actionBar.add(btnDel);
        actionBar.add(btnSave);
        actionBar.add(btnFirst);
        actionBar.add(btnPrev);
        actionBar.add(btnNext);
        actionBar.add(btnLast);
        // ---------------------------------------
    }

    public void loadData() {

    }

    public void addNew() {
        // tableModel.addRow();
    }

    public void delete(int row) {
        tableModel.deleteRow(row);
    }

    public MyTableModel<T> getTableModel() {
        return tableModel;
    }

    public void setTableModel(MyTableModel<T> tableModel) {
        this.tableModel = tableModel;
        table.setModel(tableModel);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    private void scrollTo(int row, int column) {
        table.getValueAt(row, column);
        if (table.isEditing())
            table.editingStopped(new ChangeEvent(table));
        table.scrollRectToVisible(table.getCellRect(row, column, true));
    }

    private void goToRow(int row) {
        table.setRowSelectionInterval(row, row);
    }

    public void first() {
        goToRow(0);
    }

    public void last() {
        int row = table.getRowCount() - 1;
        goToRow(row);
    }

    public void previous() {
        int row = table.getSelectedRow() - 1;
        if (row < 0) return;
        goToRow(row);
    }

    public void next() {
        int row = table.getSelectedRow() + 1;
        if (row >= table.getRowCount())
            return;
        goToRow(row);
    }

}
