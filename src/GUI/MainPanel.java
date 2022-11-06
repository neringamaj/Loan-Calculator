package GUI;

import Calculations.MyTableModel;
import javax.swing.*;
import static Constants.Constants.*;

public class MainPanel extends JPanel {
    JTable table;
    JPanel tablePanel, graphPanel;
    MyTableModel model;

    public MainPanel(){
        super(null);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        table = new JTable();
        model = new MyTableModel() {
            @Override
            public MyTableModel newTable(double[] payments, double[] principalAmounts, double[] interestAmounts, double[] debtsLeft, int x, int y) {
                return null;
            }
        };
        tablePanel = new JPanel(null);
        graphPanel = new JPanel();

        tablePanel.setSize( TABLE_WIDTH+100,TABLE_HEIGHT);
        tablePanel.setLocation(400,10);
        tablePanel.setVisible(true);

        graphPanel.setSize(GRAPH_WIDTH,GRAPH_HEIGHT);
        graphPanel.setLocation(400,TABLE_HEIGHT+10);
        graphPanel.setVisible(true);

        add(tablePanel);
        add(graphPanel);
        add(new GetVariablesPanel(this));

        setVisible(true);
        validate();
    }

    public void addTable(MyTableModel model, JPanel graph, JPanel filter) {
        table.removeAll();
        table.setModel(model);
        table.setSize(TABLE_WIDTH,TABLE_HEIGHT);
        JScrollPane p = new JScrollPane(table);

        p.setSize(TABLE_WIDTH,TABLE_HEIGHT);
        p.setVisible(true);

        graph.setSize(GRAPH_WIDTH,GRAPH_HEIGHT);
        graph.setVisible(true);

        filter.setLocation(TABLE_WIDTH,0);
        filter.setVisible(true);

        tablePanel.removeAll();
        tablePanel.add(p);
        tablePanel.add(filter);

        graphPanel.removeAll();
        graphPanel.add(graph);

        tablePanel.validate();
        graphPanel.validate();
        revalidate();
        repaint();
    }
}
