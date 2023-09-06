package com.cjtignap;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private long timeStarted;
    private JLabel timerLabel;

    private TimerThread timerThread;
    public TimerPanel() {
        super();

        timeStarted = System.currentTimeMillis();
        this.setBackground(new Color(153, 255, 204));
        this.setSize(new Dimension(800,125));
        this.setLocation(25,450);
        this.setLayout(null);

        Font font = new Font("Lucida Console",Font.PLAIN,30);
        timerLabel = new JLabel();
        timerLabel.setFont(font);
        timerLabel.setSize(300,50);
        timerLabel.setOpaque(true);
        int x = (this.getWidth()-timerLabel.getWidth())/2;
        int y =(this.getHeight()-timerLabel.getHeight())/2;
        timerLabel.setLocation(x,y);

        timerLabel.setVisible(true);
        this.add(timerLabel);

        this.setVisible(true);
    }

    public long startTimer(){
        timerThread = new TimerThread(timerLabel,timeStarted);
        timerThread.start();
        return timeStarted;
    }

    public void stopTimer(){

        timerThread.interrupt();

    }
}


class TimerThread extends Thread{
    private JLabel jLabel;
    private long timeStarted;
    private int secs=0;
    @Override
    public void run() {
        while(!this.isInterrupted()){
            if(((System.currentTimeMillis()-timeStarted)/1000)<=secs){
                continue;
            }
            secs++;
            jLabel.setText(secs+"");
            jLabel.revalidate();
            jLabel.repaint();
        }
    }

    public TimerThread(JLabel jlabel,long timeStarted){
        this.jLabel = jlabel;
        this.timeStarted=timeStarted;
    }
}
