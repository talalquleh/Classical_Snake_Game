package helperClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class StopWatch {

    public long startTime;
    public long elapsedTime;
    public double elapsedTimeInSeconds;
    /**
     * calculates the time elapses form the beginning of the game
     */
    Timer timer = new Timer(100, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = System.currentTimeMillis() - startTime;
            elapsedTimeInSeconds = (double) elapsedTime / 1000;
        }
    });

    public StopWatch() {

    }

    public void start() {
        this.timer.start();
    }

    public void stop() {
        this.timer.stop();
    }

    public double getTime() {
        return elapsedTimeInSeconds;
    }

}
