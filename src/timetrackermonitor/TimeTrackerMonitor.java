/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackermonitor;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

/**
 *
 * @author amrlotfy
 */
public class TimeTrackerMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Thread trackerMonitorThread = new Thread() {
            public void run() {

                String onImageFileName = "resources/on.png";
                String offImageFileName = "resources/off.png";
                String onSoundFileName = "resources/on.wav";
                String OffSoundFileName = "resources/off.wav";
                Region lastFound = null;
                boolean result = false;
                Settings.MinSimilarity = 0.98;
                long sleepTime = 60000;

                while (true) {
                    int numOfScreens = Screen.getNumberScreens();
                    Screen[] screens = new Screen[numOfScreens];
                    for (int i = 0; i < numOfScreens; i++) {
                        screens[i] = new Screen(i);
                    }
                    for (int i = 0; i < numOfScreens; i++) {
                        try {
                            Screen screen = screens[i];
                            if ((screen.exists(onImageFileName, 5)) != null) {
                                try {
                                    System.out.println(new Date() + " - Counting Time.");
                                    SimpleAudioPlayer.playMp3("resources/on.wav");
                                    Thread.sleep(sleepTime);
                                    break;
                                } catch (Exception ex) {
                                    Logger.getLogger(TimeTrackerMonitor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if ((screen.exists(offImageFileName, 2)) != null) {
                                try {
                                    System.out.println(new Date() + " - >>>>>>>>>>>>>>>> Not Counting Time !!");
                                    SimpleAudioPlayer.playMp3("resources/off.wav");
                                    Thread.sleep(sleepTime);
                                    break;
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(TimeTrackerMonitor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            Thread.sleep(sleepTime);
                            System.out.println("Can not recognize !");
                            SimpleAudioPlayer.playMp3("resources/off.wav");
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TimeTrackerMonitor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        trackerMonitorThread.start();

        Thread kbThread = new Thread() {
            public void run() {
                System.out.println("Type x to exit.");
                while (true) {
                    System.out.print("Command: ");
                    String input = System.console().readLine();
                    if ("x".toLowerCase().equalsIgnoreCase(input)) {
                        System.out.println("Exiting");
                        System.exit(0);
                    }
                }

            }
        };
        kbThread.start();

    }

}
