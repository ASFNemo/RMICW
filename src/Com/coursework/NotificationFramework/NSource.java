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


//    static NotificationSource getSource(String sourceURL) throws RemoteException, MalformedURLException, NotBoundException {
////        NotificationSource notificationSource = (NotificationSource) Naming.lookup(sourceURL);
////        System.out.println(notificationSource);
//        //return notificationSource;
//
//        return (NotificationSource) Naming.lookup(sourceURL);
//    }

   void addSink(NSink sinkToAdd) throws RemoteException;

    void removeSink(NSink sinkToRemove) throws RemoteException;

   void sendSinksNotification(Notification notification) throws RemoteException;


}
