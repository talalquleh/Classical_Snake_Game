package gameStates;

import gameStates.GameFrame;
import entities.Food;
import entities.Rock;
import entities.Snake;
import helperClasses.Helper;
import helperClasses.Movement;
import helperClasses.StopWatch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenuFrame extends JPanel implements MouseListener {

    private Rectangle startGame;
    private Rectangle dashboardGame;
//    private Rectangle exitGame;
    private BaseFrame MainMenuFrame;
    private DashBoard dashboard;
    private GameFrame startingGame;

    /**
     * creates rectangles to be placed on the main menu game
     */
    public MainMenuFrame() {
        MainMenuFrame = new BaseFrame();
        setPreferredSize(new Dimension(Helper.FRAME_WIDTH, Helper.FRAME_HEIGHT));
        startGame = new Rectangle(70, 150, 500, 100, "NEW GAME");
        dashboardGame = new Rectangle(startGame.x, startGame.y + startGame.height + 20, 500, 100, "Dashboard");
        this.addMouseListener(this);
        MainMenuFrame.add(this);
    }

    //Game menu
    /**
     * draw the rectangles into the screen
     *
     * @param g graphics for the current panel
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Helper.loadImage("./res/gameBackground.png"), 0, 0, Helper.FRAME_WIDTH, Helper.FRAME_HEIGHT, this);
        g2d.setPaint(Color.red);
        g2d.fillRect(startGame.x, startGame.y, startGame.width, startGame.height);
        g2d.fillRect(dashboardGame.x, dashboardGame.y, dashboardGame.width, dashboardGame.height);

        g2d.setPaint(Color.white);
        g2d.drawString(startGame.text, startGame.getStringX(), startGame.getStringY());
        g2d.drawString(dashboardGame.text, dashboardGame.getStringX(), dashboardGame.getStringY());

    }

    private class Rectangle {

        public int x;
        public int y;
        public int width;
        public int height;
        private String text;

        public Rectangle(int x, int y, int width, int height, String text) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.text = text;
        }

        private int getStringX() {
            return this.width / 2;
        }

        private int getStringY() {
            return this.y + this.height / 2;
        }

        public boolean insideOption(int x, int y) {
            return (x >= this.x && x <= this.x + this.width) && (y >= this.y && y <= this.y + this.height);
        }

    }

    /**
     *determines weather one of the rectangles is clicked
     * @param e the mouse event on the panel
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (startGame.insideOption(e.getX(), e.getY())) {
            System.out.println("startingGame..");
            //startingGame
  
            MainMenuFrame.dispose();
            startingGame = new GameFrame();

        } else if (dashboardGame.insideOption(e.getX(), e.getY())) {
            System.out.println("Dashboard");
            //showing dashboard
            dashboard = new DashBoard();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
