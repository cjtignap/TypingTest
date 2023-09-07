package com.cjtignap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MenuCard extends JPanel implements ActionListener, ListSelectionListener {
    String sentence;
    JTextArea textArea;
    TextArea txtHighScores;
    Main main;
    LinkedList<TestResult> testResults = new LinkedList<>();

    String phrases[] = {"The quick brown fox jumps over the lazy dog. Jack and Jill went up the hill to fetch a pail of " +
            "water. Mary had a little lamb, its fleece was white as snow. In a hole in the ground, there lived a hobbit.",
            "Peter Piper picked a peck of pickled peppers. How can a clam cram in a clean cream can? She sells seashells " +
                    "by the seashore. How much wood would a woodchuck chuck if a woodchuck could chuck wood?",
            "As the sun dipped below the horizon, painting the sky with hues of pink and orange, Sarah sat by the window," +
                    " lost in thought. She reminisced about the adventures of her childhood, the laughter of friends, and " +
                    "the dreams she once held so dearly. Time had passed, but those memories remained etched in her heart" +
                    ", guiding her toward new horizons and fresh dreams.","In the heart of the bustling city, amidst the " +
            "towering skyscrapers and honking traffic, there was a small park, an oasis of green tranquility. It was a place" +
            " where people from all walks of life gathered to find solace amid the chaos, to read, to play chess, or simply" +
            " to watch the world go by. The old oak tree at the center of the park stood as a silent witness to the passage " +
            "of time, its branches offering shade and comfort to generations of city dwellers.",
            "The ancient castle perched on the hill, its stone walls weathered by centuries of history. Tourists gathered " +
                    "to explore its secrets, while the wind whispered tales of knights and kings.",
            "The old lighthouse stood tall against the crashing waves, its beacon a guiding light for ships navigating " +
                    "the treacherous coastline.",
            "Beneath the starry night sky, the campfire crackled, casting flickering shadows on the faces of friends gathered " +
                    "around, sharing stories and laughter.",
            "Custom text"

    };
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
        textArea.setEditable(false);

        JLabel label = new JLabel("Typing test preview");
        label.setLocation(20,20);
        label.setSize(225,25);
        label.setFont(new Font("Open Sans",Font.BOLD,16));
        label.setVisible(true);

        JLabel label2 = new JLabel("Select text below");
        label2.setLocation(625,20);
        label2.setSize(150,20);
        label2.setFont(new Font("Open Sans",Font.BOLD,16));
        label2.setVisible(true);

        JButton btnProceed = new JButton("PROCEED");
        btnProceed.setFont(new Font("Open Sans",Font.PLAIN,12));
        btnProceed.setSize(100,25);
        btnProceed.setLocation(520,450);
        btnProceed.setBackground(new Color(7, 133, 255));
        btnProceed.setVisible(true);


        DefaultListModel<String> model = new DefaultListModel<>();
        for(int ctr = 0;ctr<phrases.length;ctr++){
            String phrase=phrases[ctr];
            if(phrase.length()<30){
                model.addElement(ctr +1+ ". "+phrase);
            }
            else{
                model.addElement(ctr +1+ ". "+phrase.substring(0,30)+" ...");
            }
        }
        JList<String> jList = new JList<>(model);
        jList.setFont(new Font("Open Sans",Font.PLAIN,12));
        jList.setBounds(625,45,200,400);
        jList.setVisible(true);

        txtHighScores = new TextArea();
        txtHighScores.setBounds(20,450,450,100);
        JScrollPane sp=new JScrollPane(txtHighScores);
        txtHighScores.setEditable(false);
        txtHighScores.setVisible(true);

        this.add(txtHighScores);
        this.add(label2);
        this.add(jList);
        this.add(btnProceed);
        this.add(label);
        this.add(textArea);
        this.setVisible(true);

        btnProceed.addActionListener(this);
        jList.addListSelectionListener(this);

        displayScores();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textArea.getText().trim().length()==0){
            return;
        }
        sentence=textArea.getText().trim();

        GameCard gameCard = new GameCard(sentence,this);
        main.frame.getContentPane().add(gameCard);
        gameCard.setVisible(true);
        this.setVisible(false);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList<String>) e.getSource();


        int index = list.getSelectedIndex();
        if(index==7){
            textArea.setEditable(true);
            textArea.setText("");
            return;
        }
        textArea.setText(phrases[index]);
        textArea.setEditable(false);

    }

    public void addScore(TestResult testResult){
        if(testResults.size()==0){
            testResults.addFirst(testResult);

            displayScores();
            return;
        }
        int index = 0;
        while(index<testResults.size()){
            if(testResults.get(index).getScore()>=testResult.getScore()){
                index++;
                continue;
            }
            testResults.add(index,testResult);
            break;
        }
        displayScores();
    }
    public void displayScores(){
        txtHighScores.setText("");
        txtHighScores.setText("Score/WPM  |  Accuracy  |  Test\n");
        for(TestResult testResult:testResults){
            String resultSentence = testResult.getSentence().length()>=30?testResult.getSentence().substring(0,30)+"...":testResult.getSentence();
            txtHighScores.append(testResult.getScore()+"  |  "+testResult.getAccuracy()+"  |  "+resultSentence+"\n");
        }
        txtHighScores.revalidate();
        txtHighScores.repaint();
    }
}
