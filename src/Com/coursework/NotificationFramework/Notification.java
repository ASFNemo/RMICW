package Com.coursework.NotificationFramework;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public interface Notification extends Serializable{

    // class Notification <T extends Serialisable> implements Serializable{


    /*
        this is just used as a stub to show that the notification that will be sent/recieved is serializable.

        haven't specified the exact notification as in the Lecture Corina mentioned that we should build the underlying
        notifications framework seperately and then put the usage seperately (this clear distinction is showed by using
        seperate packages)
     */
}
