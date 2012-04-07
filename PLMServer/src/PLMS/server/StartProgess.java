package PLMS.server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.C
 * Date: 12-3-11
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class StartProgess extends JWindow {

    public static StartProgess startProgess = new StartProgess();
    BufferedImage image;
    int maxValue = 100;
    int currValue = 0;
    int len = 0;

    private StartProgess() {
        // FontMetrics fm=sun.font.FontDesignMetrics.getMetrics(getFont());
        len = (int) ((float) getWidth() / maxValue);

        wordHeight = 20;
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension ssize = tk.getScreenSize();
        setLayout(new BorderLayout());

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("image\\img.png")));

        setSize(400, 250);
        setLocation((ssize.width - getWidth()) / 2, (ssize.height - getHeight()) / 2);
        setVisible(true);


    }

    boolean first = true;
    int offset = 5;

    int wordHeight;

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("image\\img.png")), 0, 0, getWidth(), getHeight(), this);
        if (first) {
            try {
                image = (new Robot()).createScreenCapture(new Rectangle(getX(), getY() + getHeight() - offset - wordHeight, getWidth(), wordHeight));

            } catch (AWTException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            first = false;
        }

    }

    public void setText(String text) {
        Graphics g = this.getGraphics();
        g.setColor(Color.cyan);
        g.drawImage(image, 0, getHeight() - offset - wordHeight, this);
        g.drawString(text, 10, getHeight() - offset - 10);

    }

    public void plus() {
        //    progressBar1.setValue(progressBar1.getValue() + 1);
        drawProgrecssBar();
        currValue++;
        if (currValue == maxValue) {
            this.setVisible(false);
            this.dispose();
        }

    }

    protected void drawProgrecssBar() {
        try {
            Graphics g = getGraphics();
            g.setColor(Color.cyan);
            //  g.fillRect(currValue*len,getHeight()-10,len,5);

            g.drawLine(currValue * len, getHeight() - 100, currValue * len + len, getHeight() - 100);
        } catch (NullPointerException ex) {
        }
    }

    public void setMaxValue(int max) {
        maxValue = max;
        len = (int) ((float) getWidth() / maxValue);

    }


}
