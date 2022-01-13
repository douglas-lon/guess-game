package com.players;

public class Player {

    private int numberGuessed;
    private int score;
    private int attempts;
    private boolean isNumber;

    public Player() {
        
        this.score = 0;
        this.attempts = 0;
    }

    public void setNumberGuessed(int number) {

        this.numberGuessed = number;
    }


    public int getNumberGuessed() {

        return this.numberGuessed;
    }

    public int getScore() {

        return this.score;
    }

    public int getAttempts() {

        return this.attempts;
    }

    public void setIsNumber(boolean n) {

        this.isNumber = n;
        this.calculeAttemptAndScore();
    }

    private void calculeAttemptAndScore() {

        if (this.isNumber) {
            this.score += 10;
            return;
        }

        this.attempts++;

    }
}