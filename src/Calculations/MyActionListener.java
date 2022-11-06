package Calculations;

import GUI.MainPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class MyActionListener implements ActionListener {
    List<JFormattedTextField> FIELDS;
    JComboBox<String> mortgageMethod;
    MainPanel mp;

    public MyActionListener(List<JFormattedTextField> FIELDS, JComboBox<String> mortgageMethod,MainPanel mp){
        this.FIELDS = FIELDS;
        this.mortgageMethod = mortgageMethod;
        this.mp = mp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String method = mortgageMethod.getItemAt(mortgageMethod.getSelectedIndex());

        if (Objects.equals(method, "annuity")) {
            new Annuity(FIELDS,mp);
        }
        else if (Objects.equals(method, "linear")){
            new Linear(FIELDS,mp);
        }
    }
}
