package com.cjtignap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCard extends JPanel implements ActionListener{
    String sentence;
    JTextArea textArea;
    Main main;
    public MenuCard(Main main){
        super();
        this.main = main;
        this.setLayout(null);
        this.setSize(850,600);
        this.setBackground(new Color(165, 255, 191));

        textArea = new JTextArea();
        textArea.setFont(new Font("Lucida Console",Font.PLAIN,15));
        textArea.setColumns(100);
        textArea.setLocation(20,45);
        textArea.setVisible(true);
        textArea.setSize(600,400);
        textArea.setLineWrap(true);
        JLabel label = new JLabel("Enter text below");
        label.setLocation(20,20);
        label.setSize(150,20);
        label.setFont(new Font("Open Sans",Font.BOLD,16));
        label.setVisible(true);

        JButton btnProceed = new JButton("PROCEED");
        btnProceed.setFont(new Font("Open Sans",Font.PLAIN,12));
        btnProceed.setSize(100,25);
        btnProceed.setLocation(520,450);
        btnProceed.setBackground(new Color(7, 133, 255));

        btnProceed.setVisible(true);

        this.add(btnProceed);
        this.add(label);
        this.add(textArea);
        this.setVisible(true);

        btnProceed.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sentence=textArea.getText().trim();
        main.showGameCard(sentence);
        this.setVisible(false);
    }
}
