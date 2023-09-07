package com.cjtignap;

public class TestResult {
    private int score;
    private int accuracy;
    private String sentence;

    public TestResult() {
    }

    public TestResult(int score, int accuracy, String sentence) {
        this.score = score;
        this.accuracy = accuracy;
        this.sentence = sentence;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
