import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-12
 * Time: 下午11:06
 * To change this template use File | Settings | File Templates.
 */
public class RealTimeChart extends ChartPanel implements Runnable{

    private static TimeSeries timeSeries;
    private long value=0;

    public RealTimeChart(String chartContent,String title,String yaxisName)
    {
        super(createChart(chartContent,title,yaxisName));
    }

    private static JFreeChart createChart(String chartContent,String title,String yaxisName){
        //创建时序图对象
        timeSeries = new TimeSeries(chartContent,Millisecond.class);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"time(minutes)",yaxisName,timeseriescollection,true,true,false);
        XYPlot xyplot = jfreechart.getXYPlot();
        //纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
        //自动设置数据轴数据范围
        valueaxis.setAutoRange(true);
        //数据轴固定数据范围 30s
        valueaxis.setFixedAutoRange(30000D);

        valueaxis = xyplot.getRangeAxis();
        //valueaxis.setRange(0.0D,200D);

        return jfreechart;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                timeSeries.add(new Millisecond(), randomNum());
                Thread.sleep(3000);
            }
            catch (InterruptedException e)  {   }
        }
    }

    private long randomNum()
    {
//        System.out.println((Math.random()*20+80));
        return (long)(Math.random()*20+80);
    }

    public static void main(String[] args) {

        JFrame frame=new JFrame("Test Chart");
        RealTimeChart rtcp=new RealTimeChart("Random Data","Random","value");
        frame.getContentPane().add(rtcp,new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        (new Thread(rtcp)).start();
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }

        });
    }
}
