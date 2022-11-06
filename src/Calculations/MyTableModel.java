package Calculations;

import javax.swing.table.DefaultTableModel;

import static Constants.Constants.columns;

public abstract class MyTableModel extends DefaultTableModel {

    public MyTableModel(){
        this.setColumnIdentifiers(columns);
        this.setNumRows(0);
    }

    public MyTableModel newTable(double[] payments, double[] principalAmounts, double[] interestAmounts, double[] debtsLeft, int x){
        this.setNumRows(0);
        for (int i = 0; i < x; ++i) {
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

    public abstract MyTableModel newTable(double[] payments, double[] principalAmounts, double[] interestAmounts, double[] debtsLeft, int x, int y);
}