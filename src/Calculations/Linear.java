package Calculations;

import GUI.MainPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Linear extends MyTableModel{
    private final double amount;
    private final double rate;
    private final int term;
    private final int postStart;
    private final int postLength;
    private final double postRate;
    protected int n;
    MainPanel mp;

    protected double[] payments;
    protected double[] principalAmounts;
    protected double[] interestAmounts;
    protected double[] debtsLeft;

    public Linear(List<JFormattedTextField> FIELDS, MainPanel mp){
        this.mp = mp;
        amount = ((Number) (FIELDS.get(0)).getValue()).doubleValue();
        rate = ((Number) FIELDS.get(1).getValue()).doubleValue();
        term = ((Number) FIELDS.get(2).getValue()).intValue();
        postStart = ((Number) FIELDS.get(3).getValue()).intValue();
        postLength = ((Number) FIELDS.get(4).getValue()).intValue();
        postRate = ((Number) FIELDS.get(5).getValue()).doubleValue();

        Calculations();
    }
    public void Calculations(){
        double A = amount / term; //principal amount
        double debtLeft = amount;
        double credit =  amount * (1 + rate/100) - amount;

        payments = new double[(int) (term + postLength)];
        principalAmounts = new double[(int) (term + postLength)];
        interestAmounts = new double[(int) (term + postLength)];
        debtsLeft = new double[(int) (term + postLength)];

        n = 0;

        for (int i = 0; i < term; ++i){
            if (i == postStart)
                for (int ii = postStart; ii < postStart + postLength; ii++){
                    double payment = debtLeft * (postRate/100);
                    payments[n] = Math.round(payment * 100.0) / 100.0;
                    principalAmounts[n] = Math.round(0 * 100.0) / 100.0;
                    interestAmounts[n] = Math.round(payment * 100.0) / 100.0;
                    debtsLeft[n] = Math.round(debtLeft * 100.0) / 100.0;
                    n++;
                }
            double add = credit / term;
            double payment = A + add;
            credit -= add;
            debtLeft -= A;
            payments[n] = Math.round(payment * 100.0) / 100.0;
            principalAmounts[n] = Math.round(A * 100.0) / 100.0;
            interestAmounts[n] = Math.round(add * 100.0) / 100.0;
            debtsLeft[n] = Math.round(debtLeft * 100.0) / 100.0;
            n++;
        }
        Print();

    }
    private void Print(){
        Object[] num = new Object[n];
        for (int i = 0; i < n; ++i)
            num[i] = i + 1;

        JLabel filter1 = new JLabel("   From:");
        JComboBox<Object> from = new JComboBox<>(num);
        JLabel filter2 = new JLabel("       To:");
        JComboBox<Object> to = new JComboBox<>(num);
        JButton f = new JButton("Filter");
        JButton print = new JButton(("To PDF"));

        JPanel fil = new JPanel(new FlowLayout(FlowLayout.LEADING));
        fil.setSize(90,400);
        fil.add(filter1);
        fil.add(from);
        fil.add(filter2);
        fil.add(to);
        fil.add(f);
        fil.add(print);

        MyGraphModel gp = new MyGraphModel(payments,n);
        mp.addTable(super.newTable(payments,principalAmounts,interestAmounts,debtsLeft,n), gp, fil);

        final int[] x = {1};
        final int[] y = { n };
        int finalN = n;
        f.addActionListener(e -> {
            x[0] = from.getSelectedIndex();
            y[0] = to.getSelectedIndex() + 1;
            mp.addTable(newTable(payments,principalAmounts,interestAmounts,debtsLeft,x[0],y[0]), gp, fil);
        });
        print.addActionListener(e -> new PDF(finalN, payments,principalAmounts,interestAmounts,debtsLeft,x[0],y[0]));
    }

    @Override
    public MyTableModel newTable(double[] payments, double[] principalAmounts, double[] interestAmounts, double[] debtsLeft, int x, int y){
        this.setNumRows(0);
        for (int i = x; i < y; ++i) {
            this.addRow(new Object[]{
                    i + 1,
                    payments[i],
                    principalAmounts[i],
                    interestAmounts[i],
                    debtsLeft[i]
            });
        }
        return this;
    }
}
