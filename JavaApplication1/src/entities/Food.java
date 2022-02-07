package entities;

import java.util.Random;

import helperClasses.Helper;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Food {

    private Random rand;
    private int x;
    private int y;

    public Food() {
        rand = new Random();
        newFood();
    }
    /**
     * generates random coordinates for the apples to be placed
     */
    public void newFood() {
        this.x = rand.nextInt((int) Helper.FRAME_WIDTH / Helper.UNIT_SIZE) * Helper.UNIT_SIZE;
        this.y = rand.nextInt((int) Helper.FRAME_HEIGHT / Helper.UNIT_SIZE) * Helper.UNIT_SIZE;

    }
    /**
     * 
     * @return  an image of the apple
     */
     public Image  getFoodImg(){
            return Helper.loadImage("./res/apple.png");
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
