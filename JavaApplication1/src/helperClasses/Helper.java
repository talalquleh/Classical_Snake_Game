package helperClasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
/**
 * 
 * @author talal
 */
public class Helper {

    
    public static final int FRAME_WIDTH = 650;
    public static final int FRAME_HEIGHT = 650;
    public static Clip musicClip;

    public static boolean running;
    public static int DEALY = 150;
    public static final int UNIT_SIZE = 25;

    public static final int GAME_UNITS = Helper.FRAME_HEIGHT * Helper.FRAME_WIDTH / UNIT_SIZE;
    public static char Direction = 'R';
    public static HighScores highScores;
    public static final int x[] = new int[GAME_UNITS];
    public static final int y[] = new int[GAME_UNITS];

    public static Timer timer;

    public static Image loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
