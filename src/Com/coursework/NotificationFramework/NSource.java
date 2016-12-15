package Com.coursework.NotificationFramework;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by asherfischbaum on 09/12/2016.
 */
public interface NSource extends Remote {



    /*
        will allow sinks to be added to the source
     */
    void addSink(NSink sinkToAdd) throws RemoteException;

    /*
        will allow sinks to be removed from the source
     */
    void removeSink(NSink sinkToRemove) throws RemoteException;

    /*
        will send notificatiosn to the siks subscribed to the source
     */
    void sendSinksNotification(Notification notification) throws RemoteException;

}
