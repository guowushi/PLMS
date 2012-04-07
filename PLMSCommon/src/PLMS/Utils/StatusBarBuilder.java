package PLMS.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.JTextComponent;

/*

// 按默认设置，为一个已创建的JFrame对象fr创建一个状态条。
StatusbarBuilder statusBar = StatusbarBuilder.getInstance(fr);
// 在状态条中添加显示格
JLabel l = new JLabel("Hello")
statusBar.add(l, 40);
并不仅限于JLabel，只要属JComponent的子类就行。
以后，比如：l.setText("Good Morning")，会在状态条上显示。
 程序中的信息可以在状态条的提示部分显示，比如：
statusBar.notice("准备完毕");
 再比如，当按钮移动到一个菜单，或按钮时
statusBar.notice("该部件的功能……");
*/
public class StatusBarBuilder {

    //final static Dimension XGAP = new Dimension(2, 0);
    private JPanel bar;


    /**
     * matains all instances created, each instance associate
     * <p/>
     * with an existing window object.<br>
     * <p/>
     * you can create and to obtain MenuObj instance using newInstance
     * <p/>
     * method with MenuObj specified window object.
     */

    private static    Hashtable<Window, StatusBarBuilder> instances =
            new Hashtable<Window, StatusBarBuilder>();
    private Window window;
    final public static int PLAIN = 0;
    final public static int LOWERED = 1;
  /**
     * @deprecated
     */
     final public static int RAISED = 2;
  // for builder
    private static int commonBarStyle = LOWERED;
  // for instance
    private AbstractBorder border = null;
    private int style;
    public static void setBarStyle(int style) {

        if (style < PLAIN || style > LOWERED) {

            commonBarStyle = PLAIN;

        } else {

            commonBarStyle = style;

        }

    }

    // for builder

    private static Color commonBkColor = null;
    // for instance
    private Color bkColor = null;
    /**
     * set background color of bar.
     * <p/>
     * <br><i>WARNING: </i>result of this method can only
     * <p/>
     * effect the instance created next but not an instance
     * <p/>
     * has been created prior. And you also be caution that
     * <p/>
     * the effect will be acting for any instance created
     * <p/>
     * subsequently until you invoked it again to set other
     * <p/>
     * value. <br>
     * <p/>
     * call reset method can reset all properties changed to
     * <p/>
     * be default.
     *
     * @param color
     */

    public static void setBackground(Color color) {

        commonBkColor = color;

    }


    final static int DEFAULT_HEIGHT = 23;
    private static int commonHeight = 0;
     private int height = 0;
    /**
     * set the height of cell in bar
     * <p/>
     * <br><i>WARNING: </i>result of this method can only
     * <p/>
     * effect the instance created next but not an instance
     * <p/>
     * has been created prior. And you also be caution that
     * <p/>
     * the effect will be acting for any instance created
     * <p/>
     * subsequently until you invoked it again to set other
     * <p/>
     * value. <br>
     * <p/>
     * call reset method can reset all properties changed to
     * <p/>
     * be default.
     *
     * @param height
     */

    public static void setHeight(int height) {

        commonHeight = height;

    }
    final static int DEFAULT_GAP_WIDTH = 2;
    private static int gapWidth = DEFAULT_GAP_WIDTH;
    private int gw = 0;
    /**
     * In fact, it is used to set the width of gap.
     * <p/>
     * <br><i>WARNING: </i>result of this method can only
     * <p/>
     * effect the instance created next but not an instance
     * <p/>
     * has been created prior. And you also be caution that
     * <p/>
     * the effect will be acting for any instance created
     * <p/>
     * subsequently until you invoked it again to set other
     * <p/>
     * value. <br>
     * <p/>
     * call reset method can reset all properties changed to
     * <p/>
     * be default.
     *
     * @param width
     */

    public static void setGap(int width) {

        gapWidth = width;

    }
    private static JComponent commonNotice = null;

    private JComponent notice;

    /**
     * I think perhaps you've got MenuObj custom component that
     * <p/>
     * can show help message more effective.
     * <p/>
     * I hope it can be used on this bar directly.
     * <p/>
     * <br><i>WARNING: </i>result of this method can only
     * <p/>
     * effect the instance created next but not an instance
     * <p/>
     * has been created prior. And you also be caution that
     * <p/>
     * the effect will be acting for any instance created
     * <p/>
     * subsequently until you invoked it again to set other
     * <p/>
     * value. <br>
     * <p/>
     * call reset method can reset all properties changed to
     * <p/>
     * be default.
     *
     * @param comp MenuObj custom component can show help message.
     */

    public static void setNotice(JComponent comp) {

        commonNotice = comp;

    }


    public static void reset() {

        commonBarStyle = LOWERED;

        commonBkColor = null;

        commonHeight = DEFAULT_HEIGHT;

        gapWidth = DEFAULT_GAP_WIDTH;

        commonNotice = null;

    }


    /**
     * send MenuObj message to bar, so can be appeared in bar.<br>
     * <p/>
     * <i>be cauthion</i> that it will do nothing if it is MenuObj custom
     * <p/>
     * component you applied, and it isn't an instance of JLabel
     * <p/>
     * and any other subclass of JTextComponent.
     *
     * @param s MenuObj message
     */

    public void notice(String s) {

        if (notice instanceof JLabel) {

            ((JLabel) notice).setText(s);

        } else if (notice instanceof JTextComponent) {

            ((JTextComponent) notice).setText(s);

        }

    }


    /**
     * By default, notice cell hasn't border. so this method
     * <p/>
     * can be use to set whether notice cell can has MenuObj border.
     *
     * @param style @see @link
     */

    public void setNoticeStyle(int style) {

        if (style == PLAIN) {

            if (border instanceof EmptyBorder) {

                notice.setBorder(border);

            } else {

                notice.setBorder(

                        new EmptyBorder(1, 1, 1, 1));

            }

        } else if (style == LOWERED) {

            if (border instanceof BevelBorder) {

                notice.setBorder(border);

            } else {

                notice.setBorder(

                        new SlightBevelBorder(BevelBorder.LOWERED));

            }

        }

    }


    /**
     * Create MenuObj status bar for the window given. by default, the
     * <p/>
     * window use BorderLayout as its layout mananger, and the
     * <p/>
     * status bar created will reside on bottom of the window.
     *
     * @param window
     * @return
     */

    public static StatusBarBuilder getInstance(Window window) {

        return getInstance(window, BorderLayout.PAGE_END);

    }


    /**
     * Create MenuObj status bar for MenuObj window given. User apply
     * <p/>
     * constraints for status to reside in the window.
     *
     * @param window
     * @param constraints
     * @return
     */

    public static StatusBarBuilder getInstance(Window window,

                                               Object constraints) {

        // get instance from pool.

        StatusBarBuilder instance = instances.get(window);


        // create MenuObj new instance if there isn't MenuObj instance associate

        // the window.

        if (instance == null) {

            instance = new StatusBarBuilder();
            // pool the new instance.
            instances.put(window, instance);
            window.add(instance.bar, constraints);
            instance.window = window;
// create MenuObj monitor on window, so that statusbar can be
            // released before window wiil be deactivate.
            window.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    Window window = e.getWindow();
                    System.out.println(window);
                    StatusBarBuilder inst = instances.get(window);
                    System.out.println(inst);
                     inst = null;
                     instances.remove(window);
                    System.out.println("gfgfff");
                 }

            });

        }

        return instance;

    }


    private RBCorner corner;
    /**
     * create MenuObj inew instance of status bar. Each status bar created
     * <p/>
     * belones to MenuObj window specified.
     */
    private StatusBarBuilder() {
        bar = new JPanel();
        bar.setLayout(new BoxLayout(bar, BoxLayout.LINE_AXIS));
        bar.setBorder(new EmptyBorder(2, 0, 1, 1));
        // initialize instance variable.
        height = commonHeight == 0 ? DEFAULT_HEIGHT : commonHeight;
        bkColor = commonBkColor == null ? bar.getBackground() : commonBkColor;
        // prepare border
        style = commonBarStyle;
        if (commonBarStyle == LOWERED) {
            border = new SlightBevelBorder(BevelBorder.LOWERED);
        } else if (commonBarStyle == RAISED) {
            border = new SlightBevelBorder(BevelBorder.RAISED);
        } else {
            border =
                    //new LineBorder(bkColor);
                    new EmptyBorder(2, 2, 2, 2);
        }
        // initialize MenuObj gap used for separate cells.
        // gw = gapWidth == 0 ? DEFAULT_GAP_WIDTH : gapWidth;
        gw = gapWidth;
        bar.setBackground(bkColor);
        //bar.setMinimumSize(new Dimension(50, height));
        //bar.setMaximumSize(new Dimension(5000, height));
        bar.add(Box.createRigidArea(new Dimension(0, height)));
        corner = new RBCorner();
        corner.setBorder(border);
        bar.add(corner);
        // At first, it used to show notice such as help message.
        // In addition, it used to fill increased space if bar
        // was stretched.

        if (commonNotice == null) {

            notice = new JLabel();

        } else {

            notice = commonNotice;

        }

        notice.setMinimumSize(new Dimension(50, height));

        notice.setMaximumSize(new Dimension(5000, height));


        //notice.setBorder(border);

        bar.add(notice);

        bar.add(createGap());

    }


    // 增加显示格。在添加到Container之前，设置其Border。

    /**
     * add MenuObj cell to the bar, and its height will be
     * <p/>
     * changed to predefined height.
     *
     * @param comp
     */

    public void add(JComponent comp) {

        Dimension minSize = comp.getMinimumSize();

        Dimension prefSize = comp.getPreferredSize();

        Dimension maxSize = comp.getMaximumSize();


        add(comp, minSize.width, prefSize.width, maxSize.width);

    }


    /**
     * add MenuObj cell with MenuObj specified solid width.
     *
     * @param comp
     * @param solidWidth
     */

    public void add(JComponent comp, int solidWidth) {

        add(comp, solidWidth, solidWidth, solidWidth);

    }


    /**
     * add MenuObj cell with specified minimum width and max width.
     *
     * @param comp
     * @param minWidth
     * @param maxWidth
     */

    public void add(JComponent comp, int minWidth, int maxWidth) {

        add(comp, minWidth, minWidth, maxWidth);

    }


    /**
     * add MenuObj component to the bar with specified minimum
     * <p/>
     * width, preffered width and maximum width.<br>
     * <p/>
     * The effect doesn't observable. It seens that width
     * <p/>
     * of other cell won't change until notice cell reduced
     * <p/>
     * to its minimum size when the window's width reduced.
     *
     * @param comp
     * @param minWidth
     * @param prefWidth
     * @param maxWidth
     */

    public void add(JComponent comp, int minWidth, int prefWidth, int maxWidth) {

        if (comp != corner) {

            bar.remove(corner);

        }


        comp.setBorder(border);
        comp.setMinimumSize(new Dimension(minWidth, height));
        comp.setPreferredSize(new Dimension(prefWidth, height));
        comp.setMaximumSize(new Dimension(maxWidth, height));
        // 按默认的添加方式，显示格会挤在一起，所以之间填充一个水平的刚性支撑。
        //System.out.println(bar.add(gap));
        //bar.add(gap);
        bar.add(comp);
        bar.add(createGap());
        if (comp != corner) {
            bar.add(corner);
        }
    }
    private Component createGap() {
        Component gap;
        if (gw == 0) {
            gap = Box.createHorizontalStrut(0);
        } else {
            if (commonBarStyle == PLAIN) {
                gap = new Gap(gw);
            } else {
                gap = Box.createHorizontalStrut(gw);
            }
        }
        return gap;
    }
    private class SolidCell extends JComponent {
        int cellWidth;
        SolidCell(int width) {
            super();
            this.cellWidth = width;
        }

        public Dimension getMinimumSize() {

            return new Dimension(cellWidth, height);

        }


        public Dimension getMaximumSize() {

            return new Dimension(cellWidth, height);

        }


        public Dimension getPreferredSize() {

            return new Dimension(cellWidth, height);

        }

    }


    class Gap extends SolidCell {
        public Gap(int width) {
           super(width);
        }

        public void paint(Graphics g) {
             super.paint(g);
            g.setColor(bkColor.darker());
             g.drawLine(0, 0, 0, height - 1);
            g.setColor(bkColor.brighter().brighter());
            g.drawLine(1, 0, 1, height - 1);
        }
     }


    final static int DEFAULT_CORNER_WIDTH = 18;
    /**
     * Do you take notice of that there is MenuObj manner of
     * <p/>
     * status bar in many other programs, that you can drag
     * <p/>
     * the right-bottom corner to resize the window? <br>
     * <p/>
     * Okey, I just want to achieve it. I've spent many
     * <p/>
     * more hours on it, but it isn't ideal yet.<br>
     * <p/>
     * Be it so.
     *
     * @author Jason
     */

    class RBCorner extends SolidCell {

        Rectangle resizeArea;

        public RBCorner() {

            super(DEFAULT_CORNER_WIDTH);

            ResizeAdapter mouseAdapter =

                    new ResizeAdapter();


            addMouseMotionListener(mouseAdapter);

            addMouseListener(mouseAdapter);

        }


        /**
         * this class in response to monitor several mouse
         * <p/>
         * events, as response, it changes Cursor when mouse
         * <p/>
         * over and leave it, and it can also resize the
         * <p/>
         * window that it resides in when you drag it.
         *
         * @author Jason
         */

        class ResizeAdapter

                extends MouseInputAdapter {

            Cursor oldCursor;

            boolean entered;

            boolean holded;

            Point p;


            public void mouseDragged(MouseEvent e) {

                Point p1 = e.getPoint();

                window.setSize(

                        window.getWidth() + (p1.x - p.x),

                        window.getHeight() + (p1.y - p.y)

                );

                p = p1;

            }

            public void mousePressed(MouseEvent e) {

                p = e.getPoint();

                holded = true;

            }

            public void mouseReleased(MouseEvent e) {

                p = null;

                holded = false;

                window.validate();

                window.repaint();

            }

            public void mouseEntered(MouseEvent e) {

                entered = true;

                oldCursor = getCursor();

                setCursor(

                        Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));

            }

            public void mouseExited(MouseEvent e) {

                if (entered) {

                    setCursor(oldCursor);

                    entered = false;

                }

            }

        }


        public void paint(Graphics g) {

            super.paint(g);

            Rectangle r = g.getClipBounds();

            Color c = g.getColor();


            for (int i = 0; i < 3; i++) {

                g.setColor(Color.GRAY);

                g.drawLine(r.width - i * 4 - 4, r.height - 1, r.width - 1,

                        r.height - i * 4 - 4);

                g.drawLine(r.width - i * 4 - 5, r.height - 1, r.width - 1,

                        r.height - i * 4 - 5);


                g.setColor(Color.WHITE);

                g.drawLine(r.width - i * 4 - 6, r.height - 1, r.width - 1,

                        r.height - i * 4 - 6);

            }
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(r.width - 13, r.height - 1, r.width - 1, r.height - 1);
            g.drawLine(r.width - 1, r.height - 13, r.width - 1, r.height - 1);

        }

    }

}