package gameStates;

import javax.sound.sampled.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import helperClasses.Helper;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class BaseFrame extends JFrame {
//	protected JPanel mainPanel;
//	private Clip musicClip;

    private MainMenuFrame mainMenuOpt;
    private JFrame highscoresFrame;

    /**
     * sets the base frame of the game which will be use on every game frame
     */
    public BaseFrame() {
        setSize(Helper.FRAME_WIDTH, Helper.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("./res/gameIcon.png").getImage());
        JMenuBar mb = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem res = new JMenuItem("Restart");
        JMenuItem pas = new JMenuItem("Pause");

        JMenuItem mute = new JMenuItem("Mute Music");
        JMenuItem exit = new JMenuItem("Exit");

        options.add(res);
        options.add(pas);

        options.add(mute);
        options.add(exit);
        mb.add(options);
        setJMenuBar(mb);
        File file = new File("./res/gameMusic.wav");
        AudioInputStream as;
        try {
            as = AudioSystem.getAudioInputStream(file);
            Helper.musicClip = AudioSystem.getClip();
            Helper.musicClip.open(as);
//			Helper.musicClip.start();
//            Helper.musicClip.loop(Helper.musicClip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e1) {
        }

        mute.addActionListener(e -> {
            if (Helper.musicClip.isRunning()) {
                mute.setText("Activate Music");
                Helper.musicClip.stop();
            } else {
                mute.setText("Mute Music");
                Helper.musicClip.start();
            }

        });
        exit.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        pas.addActionListener(e -> {

            if (Helper.timer.isRunning()) {
                Helper.timer.stop();
                pas.setText("Resume");
                //pasued string
                Graphics g = getGraphics();
                g.setColor(Color.red);
                g.setFont(new Font("Monospaced", Font.BOLD, 75));
                FontMetrics metrics3 = getFontMetrics(g.getFont());
                g.drawString("Paused", (Helper.FRAME_WIDTH - metrics3.stringWidth("Paused")) / 2, Helper.FRAME_HEIGHT / 2);

            } else {
                Helper.timer.start();
                pas.setText("Pause");

            }

        });
        res.addActionListener(e -> {
            this.dispose();
            Helper.musicClip.stop();
           
            mainMenuOpt = new MainMenuFrame();

        });

        setVisible(true);

    }

}
