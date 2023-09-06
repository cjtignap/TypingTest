package com.cjtignap;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;

public class LetterTile extends JLabel {
    private char displayedLetter;
    private char hiddenLetter;
    public LetterTile(char letter){
        super();
        this.displayedLetter=letter;
        this.setText(this.displayedLetter+"");
        Font font = new Font("Lucida Console",Font.PLAIN,30);
        this.setFont(font);
        setForeground(new Color(40, 40, 40));
        this.setOpaque(true);
        this.setVisible(true);
    }

    public char getDisplayedLetter() {
        return displayedLetter;
    }

    public void setDisplayedLetter(char letter) {
        this.displayedLetter = letter;

    }

    public char getHiddenLetter() {
        return hiddenLetter;
    }

    public boolean setHiddenLetter(char hiddenLetter) {
        this.hiddenLetter = hiddenLetter;
        return repaintColor();
    }

    public boolean repaintColor(){
        boolean result = true;
        if(hiddenLetter==0){
            setForeground(new Color(40, 40, 40));
            result = true;
        }
        else if(displayedLetter==' '&&hiddenLetter!=displayedLetter){
            setBackground(Color.red);
            result = false;
        }
        else if(hiddenLetter==displayedLetter){
            setForeground(new Color(0, 129, 0));
            result=true;
        }
        else{
            setForeground(Color.red);
            result=false;
        }

        this.revalidate();
        this.repaint();
        return result;
    }

    public void setActive(boolean active){

        this.setBackground(new Color(238,238,238,255));
        if(active){
            this.setBackground(new Color(134, 192, 248));
        }
        this.revalidate();
        this.repaint();
    }
}
