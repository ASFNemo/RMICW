package Com.coursework.NotificationFramework;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class NotificationSink extends UnicastRemoteObject implements NSink, Serializable{

    // I chose 4511 because it spells Ash :p
    private static final long serialVersionUID = 4511L;
    NotificationListener listener;

    public NotificationSink(NotificationListener notificationListener) throws RemoteException {
        super();

        this.listener = notificationListener;
    }

    // Todo: may need to synchronise this method, let's see if the threads cause a problem before adding complexity
    // forward the new notification to the notificationlistener
    synchronized public void recieveNotification(Notification notification, NotificationSource notificationSource) throws RemoteException{

        // Todo: see if you can change this so it sends it to the method directly rather than through the listener
        listener.recieveNotification(notificationSource, notification) ;
    }
}
