package Com.coursework.NotificationFramework;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by asherfischbaum on 09/12/2016.
 */
public interface NSink extends Remote{
    void recieveNotification(Notification notification, NSource notificationSource) throws RemoteException;
}
