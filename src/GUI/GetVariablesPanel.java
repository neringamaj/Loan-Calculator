package GUI;

import Constants.Constants;
import Calculations.MyActionListener;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GetVariablesPanel extends JPanel {

    private final JFormattedTextField LoanAmount;
    private final JFormattedTextField YearlyRate;
    private final JFormattedTextField LoanTerm;
    private final JFormattedTextField LoanPostStart;
    private final JFormattedTextField LoanPostLength;
    private final JFormattedTextField LoanYearlyRate;
    private final JComboBox<String> mortgageMethod;

    public GetVariablesPanel(MainPanel mp) {
        setSize(300, Constants.WINDOW_HEIGHT);
        setLocation(0, 0);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        String[] choice = new String[]{"annuity", "linear"};

        //Creating text fields and the CALCULATE button
        JButton done;
        {
            JLabel i1 = new JLabel("Loan amount:     " + "\n");
            LoanAmount = new JFormattedTextField(NumberFormat.getNumberInstance());
            LoanAmount.setColumns(20);
            LoanAmount.setValue((double) 1000);

            JLabel i2 = new JLabel("Yearly interest rate (%) : ");
            YearlyRate = new JFormattedTextField();
            YearlyRate.setColumns(20);
            YearlyRate.setValue((double) 10);

            JLabel i3 = new JLabel("Loan term in months: ");
            LoanTerm = new JFormattedTextField();
            LoanTerm.setColumns(20);
            LoanTerm.setValue((double) 10);

            JLabel i4 = new JLabel("Start month of loan postponement: ");
            LoanPostStart = new JFormattedTextField();
            LoanPostStart.setColumns(20);
            LoanPostStart.setValue((double) 0);

            JLabel i5 = new JLabel("Length of loan postponement: ");
            LoanPostLength = new JFormattedTextField();
            LoanPostLength.setColumns(20);
            LoanPostLength.setValue((double) 0);

            JLabel i6 = new JLabel("Yearly interest rate of loan postponement: ");
            LoanYearlyRate = new JFormattedTextField();
            LoanYearlyRate.setColumns(20);
            LoanYearlyRate.setValue((double) 0);

            JLabel i7 = new JLabel("    Mortgage type: ");
            mortgageMethod = new JComboBox<>(choice);
            done = new JButton("Calculate");
            JLabel i8 = new JLabel("          \n   ");

            add(i1);
            add(LoanAmount);
            add(i2);
            add(YearlyRate);
            add(i3);
            add(LoanTerm);
            add(i4);
            add(LoanPostStart);
            add(i5);
            add(LoanPostLength);
            add(i6);
            add(LoanYearlyRate);
            add(i7);
            add(mortgageMethod);
            add(i8);
            add(done);
        }

        done.addActionListener(new MyActionListener(getFields(), getMethod(), mp));
        setVisible(true);
    }

    public List<JFormattedTextField> getFields() {
        List<JFormattedTextField> FIELDS = new ArrayList<>(6);

        FIELDS.add(LoanAmount);
        FIELDS.add(YearlyRate);
        FIELDS.add(LoanTerm);
        FIELDS.add(LoanPostStart);
        FIELDS.add(LoanPostLength);
        FIELDS.add(LoanYearlyRate);

        return FIELDS;
    }

    public JComboBox<String> getMethod() {
        return mortgageMethod;
    }
}
