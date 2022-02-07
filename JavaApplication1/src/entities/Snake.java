package entities;

import java.util.List;

import helperClasses.Helper;
import helperClasses.Movement;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake {

    private int bodyParts = 2;
    private int foodEaten = 0;
    private Food food;
    private List<Rock> rocks;

    /**
     *
     * @param food current food for the snake
     * @param rocks current rocks standing in the way of the snake
     */
    public Snake() {

    }

    public void setFoodAndRocks(Food food, List<Rock> rocks) {

        this.food = food;
        this.rocks = rocks;
    }

    /**
     * checks if the snake collides with the screen border or its own body, and
     * stops the game timer
     */
    public void checkCollisons() {
        for (int i = bodyParts; i > 0; i--) {

            // heads collide with body
            if ((Helper.x[0] == Helper.x[i]) && (Helper.y[0] == Helper.y[i])) {
                Helper.running = false;
            }
            // head touch borders
            if (Helper.x[0] < 0 || Helper.x[0] > Helper.FRAME_WIDTH || Helper.y[0] > Helper.FRAME_HEIGHT
                    || Helper.y[0] < 0) {
                Helper.running = false;
            }
            for (int j = 0; j < rocks.size(); j++) {
                if ((Helper.x[0] == rocks.get(j).getX()) && (Helper.y[0] == rocks.get(j).getY())) {
                    Helper.running = false;
                }
            }

            if (!Helper.running) {
                Helper.timer.stop();
            }

        }
    }

    /**
     *
     * determines the way the snake will move
     */
    public void move() {

        for (int i = bodyParts; i > 0; i--) {
            Helper.x[i] = Helper.x[i - 1];
            Helper.y[i] = Helper.y[i - 1];
        }

        switch (Helper.Direction) {
            case 'U':
                Helper.y[0] = Helper.y[0] - Helper.UNIT_SIZE;
                break;
            case 'D':
                Helper.y[0] = Helper.y[0] + Helper.UNIT_SIZE;
                break;
            case 'R':
                Helper.x[0] = Helper.x[0] + Helper.UNIT_SIZE;
                break;
            case 'L':
                Helper.x[0] = Helper.x[0] - Helper.UNIT_SIZE;
                break;
        }

    }

    /**
     * create a new food for the snake on the game panel
     */
    public void updateFood() {
        this.food.newFood();
    }

    /**
     * checks if the snake collides with an apple and grows it's size by on unit
     */
    public void checkFood() {
        if ((Helper.x[0] == food.getX()) && (Helper.y[0] == food.getY())) {
            bodyParts++;
            foodEaten++;
            updateFood();

        }

    }

    /**
     *
     * @param level current level
     * @return number of rocks to be in each level
     */
    public int getNumRocksLevel(int level) {
        //levels
        return level+2;

    }

    /**
     *
     * @return an image of the body of the snake
     */
    public Image getBodyImg() {
        return Helper.loadImage("./res/snakeBody.png");
    }

    /**
     *
     * @return an image of the head of the snake
     */
    public Image getHeadImg() {
        return Helper.loadImage("./res/snakeHead.png");
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public void setFoodEaten(int foodEaten) {
        this.foodEaten = foodEaten;
    }

    public void setRocks() {
        // TODO Auto-generated method stub

    }

}
