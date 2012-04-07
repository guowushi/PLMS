package PLMS.Utils;
import java.awt.Color;

import java.awt.Component;

import java.awt.Graphics;



import javax.swing.border.SoftBevelBorder;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-2-28
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class SlightBevelBorder extends SoftBevelBorder{
    private static final long serialVersionUID = 1L;



    public SlightBevelBorder(int bevelType) {

        super(bevelType);

    }



    public SlightBevelBorder(int bevelType, Color highlight, Color shadow) {

        super(bevelType, highlight, shadow);

    }



    /**

     * Paints the border for the specified component with the specified

     * position and size.

     * @param c the component for which this border is being painted

     * @param g the paint graphics

     * @param x the x position of the painted border

     * @param y the y position of the painted border

     * @param width the width of the painted border

     * @param height the height of the painted border

     */

    public void paintBorder(Component c, Graphics g, int x, int y, int width,

                            int height) {

        Color oldColor = g.getColor();

        g.translate(x, y);



        if (bevelType == RAISED) {

            g.setColor(getHighlightOuterColor(c));

            g.drawLine(0, 0, width - 2, 0);

            g.drawLine(0, 1, 0, height - 2);



            g.setColor(getShadowOuterColor(c));

            g.drawLine(0, height - 1, width - 1, height - 1);

            g.drawLine(width - 1, 0, width - 1, height - 1);



        } else if (bevelType == LOWERED) {

            g.setColor(getShadowOuterColor(c));

            g.drawLine(0, 0, width - 2, 0);

            g.drawLine(0, 0, 0, height - 2);
            g.setColor(getHighlightOuterColor(c));

            g.drawLine(0, height - 1, width - 1, height - 1);

            g.drawLine(width - 1, 0, width - 1, height);

        }
        g.translate(-x, -y);
        g.setColor(oldColor);

    }
}


