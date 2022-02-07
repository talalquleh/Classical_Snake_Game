package game;

import gameStates.MainMenuFrame;
import helperClasses.Helper;
import helperClasses.HighScores;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Random;
import java.sql.SQLException;
/**
 * 
 * @author Talal
 */
 
public class Lanucher {
        /**
         * initializes the mainFrame and sets the database
         * @param args 
         */
	public static void main(String[] args) {
		new MainMenuFrame();
            try {
                Helper.highScores =new  HighScores(10);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Lanucher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
	}


}
