package Com.coursework.NotificationFramework;

import java.io.Serializable;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public interface Listener extends Serializable {

    /*
        This method will take the notification from the sink, when one is passed across
     */
    void recieveNotification(NSource notificationSource, Notification notification);

    /*
        this method will deal with duplicate notifications.
     */
    void duplicateNotification(NSource nSource, Notification dupNot);
}
