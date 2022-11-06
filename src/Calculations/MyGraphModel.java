package Calculations;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static Constants.Constants.*;

public class MyGraphModel extends JPanel {
    private final double[] payments;
    private final int n;
    private ChartPanel cp;

    public MyGraphModel(double[] payments, int n){
        this.payments = payments;
        this.n = n;
        this.setSize(GRAPH_WIDTH,GRAPH_HEIGHT);
        UpdateData();
        add(cp);
    }

    public MyGraphModel getGraph(){
        return this;
    }

    private XYDataset createData(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries s = new XYSeries("Loan payment");
        for (int i = 0; i < n; ++i){
            s.add(i+1,payments[i]);
        }
        dataset.addSeries(s);
        return dataset;
    }

    private void UpdateData(){
        removeAll();
        JFreeChart ch = ChartFactory.createXYLineChart(
                "Payments",
                "Months", "Payment amount",
                createData(),
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int scale =1 ; //chart will be rendered at thrice the resolution.
        try {
            ChartUtilities.writeScaledChartAsPNG(bout, ch, GRAPH_WIDTH, GRAPH_HEIGHT, scale, scale);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cp = new ChartPanel(ch);
        revalidate();
        repaint();
    }
}
