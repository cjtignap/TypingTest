package com.cjtignap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameArea extends Panel implements KeyListener {

    ArrayList<LetterTile> letterTiles= new ArrayList<LetterTile>();
    int pointer = 0;
    ArrayList<String> innerSentences = new ArrayList<>();
    private String sentence;
    private String typedSentence="";
    int errorIndex =-1;
    GameCard gameCard;
    private int errors = 0;

    public GameArea(String sentence,GameCard gameCard){
        super();
        this.sentence=sentence;
        this.gameCard = gameCard;
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setHgap(1);
        this.setLayout(flowLayout);
        this.setSize(800,400);
        this.setFocusable(true);
        this.addKeyListener(this);


        fillLetters();
        this.setVisible(true);
        this.requestFocus();
    }

    public void fillLetters(){

        wrapSentences();
        for(String innerSentence : innerSentences) {


            for(char letter : innerSentence.toCharArray()){

                LetterTile letterTile =new  LetterTile(letter);
                letterTiles.add(letterTile);
                this.add(letterTile);
            }

            for(int ctr = 0;ctr<Math.abs(innerSentence.length()-42);ctr++){
                LetterTile letterTile =new  LetterTile(' ');
                this.add(letterTile);
            }
        }
    }

    public void wrapSentences(){

        String senteceCopy = sentence;
        int indexResult = 0;
        while(senteceCopy.length()>0) {

            if(senteceCopy.length()<43){
                innerSentences.add(senteceCopy) ;
                break;
            }
            if(senteceCopy.indexOf(' ',indexResult+1)<43){
                indexResult =senteceCopy.indexOf(' ',indexResult+1);
            }
            else{
                String sentenceToAdd = senteceCopy.substring(0,indexResult+1    );
                innerSentences.add(sentenceToAdd) ;
                senteceCopy=senteceCopy.substring(indexResult+1,senteceCopy.length());
                indexResult=0;
            }
        }
    }

    public void checkError(boolean result){
        if(result&&pointer==errorIndex){
            errorIndex=-1;
        }
        if(!result&&errorIndex==-1){//only the first error will be logged
            errorIndex=pointer;
        }
    }

    public int getErrors() {
        return errors;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='\b'){
            return;
        }
        if(pointer<letterTiles.size()) {
            letterTiles.get(pointer).setActive(false);

            boolean result = letterTiles.get(pointer).setHiddenLetter(e.getKeyChar());
            checkError(result);
            errors = !result?errors+1:errors;
            if(pointer+1<letterTiles.size()){
                pointer++;
                letterTiles.get(pointer).setActive(true);
            }
            else if(pointer+1>=letterTiles.size()&&errorIndex==-1){
                showResult();
            }
        }
    }
    public void showResult(){
       gameCard.displayResult();
    }
    @Override
    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode()== KeyEvent.VK_BACK_SPACE&&pointer>0){
            letterTiles.get(pointer).setHiddenLetter((char)0);
            letterTiles.get(pointer).setActive(false);
            pointer--;
            letterTiles.get(pointer).setActive(true);
        }


    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
