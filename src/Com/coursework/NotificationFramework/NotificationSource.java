package Com.coursework.NotificationFramework;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class NotificationSource extends UnicastRemoteObject implements NSource, Serializable {

    static final long serialVersionUID = 4511L;
    NSource stub;

    public NotificationSource(String sourceURL) throws RemoteException, MalformedURLException {
        super();
        //stub = (NSource) UnicastRemoteObject.exportObject(this, 0);

        try{
            LocateRegistry.createRegistry(1099);
        } catch (Exception e){

        }

        LocateRegistry.getRegistry(1099);

        java.rmi.registry.LocateRegistry.getRegistry(1099).rebind(sourceURL, this);

        //Naming.rebind(sourceURL, this);
    }

    // TODO: WORK OUT IF THIS NEEDS TO BE SYNCHRONISED.
    // checks for a notification source with the given URL and returns the source
    // must be static as lookup can happen if a source is already in existence and thus, we do not need to instantiate a new one but still need to use the method
    synchronized public static NSource getSource(String sourceURL) throws RemoteException, MalformedURLException, NotBoundException{
//        NotificationSource notificationSource = (NotificationSource) Naming.lookup(sourceURL);
//        System.out.println(notificationSource);
        //return notificationSource;

        //return (NSource) Naming.lookup(sourceURL);
        return (NSource) java.rmi.registry.LocateRegistry.getRegistry(1099).lookup(sourceURL);
    }

    @Override
    public void addSink(NSink sinkToAdd) {
        sinksSet.add(sinkToAdd);

        Iterator<NSink> iterator = sinksSet.iterator();

        while (iterator.hasNext()){
            try {
                System.out.println(iterator.next().toString());
            } catch (Exception e){
                System.out.println(e.toString());
                // consider catching the specific exception --- the remote exception
                // consider tryig to resend a few times and if it does not work then removing the sink from the list.
            }


        }
    }

    @Override
    public void removeSink(NSink sinkToRemove) {
        if (sinksSet.contains(sinkToRemove)){
            sinksSet.remove(sinkToRemove);
        }
    }


    HashSet sinksSet = new HashSet();


    synchronized public void sendSinksNotification(Notification notification){
        // we want to send every notification to every sink that has subscribed to this sink
        // therfore we want to itereate through the whole hashset

        System.out.println("boooooo");

        Iterator<NSink> iterator = sinksSet.iterator();

        while (iterator.hasNext()){
            try {
                System.out.println("in here");
                iterator.next().recieveNotification(notification, this);
            } catch (Exception e){
                System.out.println(e.toString());
                e.printStackTrace();
                // consider catching the specific exception --- the remote exception
                // consider tryig to resend a few times and if it does not work then removing the sink from the list.
            }


        }
    }



}
