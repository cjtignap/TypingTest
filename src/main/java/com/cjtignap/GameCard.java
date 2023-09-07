package com.cjtignap;

import javax.swing.*;
import java.awt.*;

public class GameCard extends JPanel {
    private long timeStarted;
    private String sentence;

    private TimerPanel timerPanel;
    private GameArea gameArea;
    private MenuCard menuCard;
    public GameCard(String sentence, MenuCard menuCard){
        super();
        this.sentence = sentence;
        this.setLayout(null);
        this.menuCard = menuCard;
        this.setSize(850,600);
        gameArea = new GameArea(sentence,this);
        gameArea.setLocation(25,25);

        timerPanel = new TimerPanel();
        timeStarted = timerPanel.startTimer();

        this.add(timerPanel);
        this.add(gameArea);
        this.setVisible(true);
    }

    public void displayResult(){

        int milliSEllapsed =(int)( System.currentTimeMillis()-timeStarted);
        double seconds = (double)milliSEllapsed/1000;
        double numberOfWords = (double)sentence.length()/5;
        double wpm =  Math.round(((numberOfWords/seconds)*60));


        double percentError = (double) gameArea.getErrors()/sentence.length();
        percentError = percentError*100;
        int accuracy =(int) Math.round(100-percentError);

        timerPanel.stopTimer();

        String message = "Your typing speed :"+wpm+" words per minute. \nAccuracy : "+accuracy+ "%";
        JOptionPane.showMessageDialog(null,
                message ,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);

        menuCard.addScore(new TestResult((int)wpm,accuracy,sentence));
        this.setVisible(false);
    }
}
