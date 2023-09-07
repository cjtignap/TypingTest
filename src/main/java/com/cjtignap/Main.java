package com.cjtignap;

import javax.swing.*;
import java.awt.*;

public class Main {

    JFrame frame;
    public static void main(String[] args) {
        new Main();
    }

    public Main(){

        setLookAndFeel();
       frame = new JFrame("TypingTest");
        frame.setSize(new Dimension(850,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());



        MenuCard menuCard = new MenuCard(this);
        frame.add(menuCard);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}