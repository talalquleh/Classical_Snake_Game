package gameStates;

import helperClasses.Helper;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class DashBoard extends JFrame {

    /**
     * sets the dashboard frame
     */
    public DashBoard() {
        try {
            setSize(450, 450);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(10, 1));
            List<JLabel> labels = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                labels.add(new JLabel("", SwingConstants.CENTER));
                labels.get(i).setBorder(BorderFactory.createLineBorder(Color.black));
                add(labels.get(i));
            }
            for (int i = 0; i < Helper.highScores.getHighScores().size(); i++) {
                labels.get(i).setText(Helper.highScores.getHighScores().get(i).toString());
            }
            setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(BaseFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
