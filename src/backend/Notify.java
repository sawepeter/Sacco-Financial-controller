package backend;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.*;

public class Notify {
   String title;
   String message;
   String notificationtype;
   int duration;
   
   TrayNotification tray = new TrayNotification();

   public void fadeAnimation(String t,String m,String n,int d){
       this.title = t;
       this.message = m;
       this.notificationtype = n;
       this.duration = d;
      
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.valueOf(n));
        tray.setAnimationType(AnimationType.FADE);
        tray.showAndDismiss(Duration.seconds(3));
        
   }
   
}