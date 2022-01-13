package com.players;

import java.util.Random;

public class Computer {
    
    private int number;

    public Computer() {
        this.randNumber();
    }

    public int checkNumber(int n) {

        int resp;

        if (n == this.number) {
            return 1;
        }

        if (n < this.number && n > 0) {
            resp = 2;
        } else if (n > 0 && n > this.number){
            resp = 3;
        } else {
            resp = 4;
        }
        

        return resp;
    }

    public boolean checkScore(int n) {

        if (n == number) {
            return true;
        }

        return false;
    }

    public void randNumber() {
        Random rand = new Random();
        this.number = 1 + (rand.nextInt(99));

    }

    public void getNumber() {

        System.out.println("The number: " + this.number);
    }
}
