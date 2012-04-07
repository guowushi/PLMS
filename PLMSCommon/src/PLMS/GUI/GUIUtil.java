package PLMS.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: guowushi
 * Date: 12-4-2
 * Time: 下午9:21
 * To change this template use File | Settings | File Templates.
 */
public class GUIUtil {
    public static Border createBorder(String title)
    {
        EtchedBorder border1=new EtchedBorder();
        TitledBorder titleborder1= BorderFactory.createTitledBorder(border1, title);
        titleborder1.setTitleColor(new Color(160,160,160));

        return titleborder1;
    }
}
