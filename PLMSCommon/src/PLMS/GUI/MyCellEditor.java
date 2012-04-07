package PLMS.GUI;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义的单元格编辑器
 * 点击单元格，弹出对话框
 */
public class MyCellEditor extends AbstractCellEditor implements TableCellEditor,ActionListener{
    JButton button;
    JDialog dialog;
    Object  cellValue;
    Color currentColor;
    JColorChooser colorChooser;
    protected static final String EDIT = "edit";
    //-------------------------------------------------------------
    public MyCellEditor() {
        button = new JButton();
        button.setActionCommand(EDIT); //设置按钮
        button.addActionListener(this);
        button.setBorderPainted(false); //不要边框
        dialog = JColorChooser.createDialog(button,
                "Pick a Color",
                true,  //modal
                colorChooser,
                this,  //OK button handler
                null); //no CANCEL button handler

    }

    // ------------------------------------------------------------------------------------
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentColor = (Color)value;
        return button;

    }

    /**
     * 返回编辑器的值
     * @return
     */
    @Override
    public Object getCellEditorValue() {
        return cellValue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //如果用户点击单元格，则显示对话框
            button.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);
            fireEditingStopped(); //Make the renderer reappear.
        } else { // 点击OK
            currentColor = colorChooser.getColor();
        }

    }
}
