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
    Listener listener;

    public NotificationSink(Listener notificationListener) throws RemoteException {
        super();

        this.listener = notificationListener;
    }


    /*
        this will forward the notification to the implementation of notificaiton listner
     */
    synchronized public void recieveNotification(Notification notification, NSource notificationSource) throws RemoteException{

        listener.recieveNotification(notificationSource, notification) ;
    }
}
