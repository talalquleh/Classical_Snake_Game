package gameStates;

import entities.Food;
import entities.Rock;
import entities.Snake;
import gameStates.BaseFrame;
import gameStates.MainMenuFrame;
import helperClasses.Helper;
import helperClasses.Movement;
import helperClasses.StopWatch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements ActionListener {

    private MainMenuFrame mainFrame;
    private Food food;
   
    private Snake snake;
    private BaseFrame baseFrame;
    //num of rocks for each level
    private List<Rock> obst;
    private int currentLevel = 1;
    private StopWatch stopWatch;

    /**
     * sets the game frame and add keyboard listeners
     */
    public GameFrame() {
        setFocusable(true);
        setPreferredSize(new Dimension(Helper.FRAME_WIDTH, Helper.FRAME_HEIGHT));
        baseFrame = new BaseFrame();
        baseFrame.add(this);
        baseFrame.pack();
        this.addKeyListener(new Movement());
        stopWatch = new StopWatch();
        startGame();
    }

    /**
     * starts the game timer and creates the food and the rocks for the snake
     */
    public void startGame() {
        food = new Food();
        stopWatch.start();
        stopWatch.startTime = System.currentTimeMillis();
       snake = new Snake();
        obst = new ArrayList<Rock>();
        for (int i = 0; i < snake.getNumRocksLevel(currentLevel); i++) {
            obst.add(new Rock());
        }
        snake.setFoodAndRocks(food, obst); 

        Helper.running = true;
        Helper.timer = new Timer(Helper.DEALY, this);
        Helper.timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    /**
     * the game logic and all the rendering needed is here
     *
     * @param g graphics for the game panel
     */
    public void draw(Graphics g) {
        g.drawImage(Helper.loadImage("./res/desertBackground.png"), 0, 0, Helper.FRAME_WIDTH, Helper.FRAME_HEIGHT, this);
        // grid to help visualize
        if (Helper.running) {
//            for (int i = 0; i < Helper.FRAME_HEIGHT / Helper.UNIT_SIZE; i++) {
//                 g.drawLine(i * Helper.UNIT_SIZE, 0, i * Helper.UNIT_SIZE, Helper.FRAME_HEIGHT);
//                 g.drawLine(0, i * Helper.UNIT_SIZE, Helper.FRAME_WIDTH, i * Helper.UNIT_SIZE);
//            }
            // new food
            g.setColor(Color.green);
            g.drawImage(food.getFoodImg(), food.getX(), food.getY(), this);

            //rocks not in same place with food or snake
            for (int i = 0; i < obst.size(); i++) {
                g.setColor(Color.red);
                if ((obst.get(i).getX() == food.getX() && obst.get(i).getY() == food.getY()) || (Helper.x[0] == obst.get(i).getX() && Helper.y[0] == obst.get(i).getY())) {
                    obst.set(i, new Rock());
                    while ((obst.get(i).getX() == food.getX() && obst.get(i).getY() == food.getY()) || (Helper.x[0] == obst.get(i).getX() && Helper.y[0] == obst.get(i).getY())) {
                        obst.set(i, new Rock());
                    }

                } else {
                    g.drawImage(obst.get(i).getRockImg(), obst.get(i).getX(), obst.get(i).getY(), this);
                }

            }
            if (snake.getBodyParts() == 10 * currentLevel) {
                currentLevel++;
                obst.clear();
                for (int i = 0; i < snake.getNumRocksLevel(currentLevel); i++) {
                    obst.add(new Rock());
                }
                Helper.DEALY -= 10;
                Helper.timer.setDelay(Helper.DEALY);
//                Helper.timer.restart();
            }
            for (int i = 0; i < snake.getBodyParts(); i++) {
                if (i == 0) {
                    g.setColor(Color.white);
                    g.drawImage(snake.getHeadImg(), Helper.x[i], Helper.y[i], this);

                } else {
                    g.setColor(Color.blue);
                    g.drawImage(snake.getBodyImg(), Helper.x[i], Helper.y[i], this);
                }

                //current score 
                g.setColor(Color.white);
                g.setFont(new Font("Ink Free", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + snake.getFoodEaten(), (Helper.FRAME_WIDTH - metrics.stringWidth("Score: " + snake.getFoodEaten())) / 2, g.getFont().getSize());
                //timepassed

                g.setColor(Color.white);
                g.setFont(new Font("Ink Free", Font.BOLD, 20));
                FontMetrics metrics2 = getFontMetrics(g.getFont());
                g.drawString("Time: " + stopWatch.getTime(), (Helper.FRAME_WIDTH - metrics2.stringWidth("Time: " + stopWatch.getTime())) / 2, Helper.FRAME_WIDTH - g.getFont().getSize());
                g.drawString("Level: " + currentLevel, (Helper.FRAME_WIDTH - metrics2.stringWidth("Time: " + stopWatch.getTime())), Helper.FRAME_WIDTH - g.getFont().getSize());

            }

        } else {
            stopWatch.stop();
            gameOver(g);

        }

    }

    /**
     * writes game over state for the game
     *
     * @param g graphics for the game panel
     */
    public void gameOver(Graphics g) {
        //score

        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + snake.getFoodEaten(), (Helper.FRAME_WIDTH - metrics1.stringWidth("Score: " + snake.getFoodEaten())) / 2, g.getFont().getSize());
        //time
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Time: " + stopWatch.getTime(), (Helper.FRAME_WIDTH - metrics2.stringWidth("Time: " + stopWatch.getTime())) / 2, Helper.FRAME_WIDTH - g.getFont().getSize());
        g.drawString("Level: " + currentLevel, (Helper.FRAME_WIDTH - metrics2.stringWidth("Time: " + stopWatch.getTime())), Helper.FRAME_WIDTH - g.getFont().getSize());
        //game over
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.BOLD, 75));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (Helper.FRAME_WIDTH - metrics3.stringWidth("Game Over")) / 2, Helper.FRAME_HEIGHT / 2);
        Helper.running=false;
        JFrame confim = new JFrame();
        String name = JOptionPane.showInputDialog(confim, "Enter your name: ", "well done...", JOptionPane.INFORMATION_MESSAGE);

        if (name != null && !name.isEmpty()) {
            System.out.println("will be saved");
            try {
                Helper.highScores.putHighScore(name, snake.getFoodEaten());
            } catch (SQLException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        Helper.timer.start();
//        drawGameOverStrings(g);
//        repaint();
    }

    /**
     * as long as the game running , moves the snake and checks if it has eaten
     * food and sets all the rocks and checks all collisions
     *
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Helper.running) {
            snake.move();
            snake.checkFood();
            snake.setRocks();
            snake.checkCollisons();
        }
        repaint();
    }
}
