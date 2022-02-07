package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import helperClasses.Helper;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rock {

    private Random rand;
    private int x;
    private int y;

    public Rock() {
        rand = new Random();
        newRock();
    }
    /**
     * generate random rock to be places into the game panel
     */
    public void newRock() {
        this.setX(rand.nextInt((int) Helper.FRAME_WIDTH / Helper.UNIT_SIZE) * Helper.UNIT_SIZE);
        this.setY(rand.nextInt((int) Helper.FRAME_HEIGHT / Helper.UNIT_SIZE) * Helper.UNIT_SIZE);

    }
    public Image  getRockImg(){
            return Helper.loadImage("./res/rock.png");
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
