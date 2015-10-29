/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackermonitor;

import java.io.File;
import static java.lang.System.in;
import java.util.Date;
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
        // TODO code application logic here
        int numOfScreens=Screen.getNumberScreens();
	Screen[] screens=new  Screen[numOfScreens];///Users/amrlotfy/NetBeansProjects/TimeTrackerMonitor/src/timetrackermonitor
        String onImageFileName = "resources/on.png";
        String offImageFileName = "resources/off.png";
        String onSoundFileName = "resources/on.wav";
        String OffSoundFileName = "resources/off.wav"; 
        Region lastFound=null;
        boolean result=false;
        Settings.MinSimilarity=0.90;
        
        for(int i=0;i<numOfScreens;i++ ){
            screens[i]=new Screen(i);
        }
        while(true){
            for(int i = 0; i < numOfScreens; i++){
                Screen screen = screens[i];
                if ((screen.exists(onImageFileName, 5)) != null) {
                    System.out.println(new Date() + " - Counting Time.");
                    SimpleAudioPlayer.playMp3("resources/on.wav");
                    Thread.sleep(60000);
                    break;
                }
                if ((screen.exists(offImageFileName, 2)) != null) {
                    System.out.println(new Date() + " - >>>>>>>>>>>>>>>> Not Counting Time !!");
                    SimpleAudioPlayer.playMp3("resources/off.wav");
                    Thread.sleep(60000);
                    break;
                }
                Thread.sleep(60000);
                System.out.println("Can not recognize !");
                SimpleAudioPlayer.playMp3("resources/off.wav");
            }
        }
        
    }
    
}
