package helperClasses;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement extends KeyAdapter {
    /**
     * determines the way the snake will be moving
     * @param e key event on game panel
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (Helper.Direction != 'R') {
                    Helper.Direction = 'L';
                }
                break;

            case KeyEvent.VK_D:
                if (Helper.Direction != 'L') {
                    Helper.Direction = 'R';
                }
                break;

            case KeyEvent.VK_W:
                if (Helper.Direction != 'D') {
                    Helper.Direction = 'U';
                }
                break;
            case KeyEvent.VK_S:
                if (Helper.Direction != 'U') {
                    Helper.Direction = 'D';
                }
                break;

        }

    }

}
