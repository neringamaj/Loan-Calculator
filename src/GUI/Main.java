package GUI;

import javax.swing.*;
import Constants.Constants;

//Neringa Majauskaite 4gr 2pogr

public class Main extends JFrame {
    MainPanel mp = new MainPanel();

    public static void main(String[] args){
        new Main();
    }

    private Main(){
        super("Loan calculator");
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mp);
        setVisible(true);
    }
}
