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
    final String moduleName;
    HashSet sinksSet = new HashSet();

    public NotificationSource(String sourceURL) throws RemoteException, MalformedURLException {
        super();

        try{
            LocateRegistry.createRegistry(1099);
        } catch (Exception e){

        }

        LocateRegistry.getRegistry(1099);

        java.rmi.registry.LocateRegistry.getRegistry(1099).rebind(sourceURL, this);
        moduleName =  sourceURL.split("/")[sourceURL.split("/").length - 1];
    }

    // checks for a notification source with the given URL and returns the source
    // must be static as lookup can happen if a source is already in existence and thus, we do not need to instantiate a new one but still need to use the method
    synchronized public static NSource getSource(String sourceURL) throws RemoteException, MalformedURLException, NotBoundException{
        return (NSource) java.rmi.registry.LocateRegistry.getRegistry(1099).lookup(sourceURL);
    }

    /*
        adds a sink that is subscibing to the list of siks already subscirbed
     */
    @Override
    public void addSink(NSink sinkToAdd) {
        sinksSet.add(sinkToAdd);
        Iterator<NSink> iterator = sinksSet.iterator();
        while (iterator.hasNext()){
            try {
                System.out.println(iterator.next().toString());
            } catch (Exception e){
                System.out.println(e.toString());
            }


        }
    }

    /*
        removes the sink from the list of subscribed sinks
     */
    @Override
    public void removeSink(NSink sinkToRemove) {
        if (sinksSet.contains(sinkToRemove)){
            sinksSet.remove(sinkToRemove);
        }
    }



    /*
         we want to send every notification to every sink that has subscribed to this sink therefore we iterate over
         the hash set of sinks. If a sink is offline we try again and if that does not work we remove them from the list
         of subscribed sinks
     */
    synchronized public void sendSinksNotification(Notification notification){
        Iterator<NSink> iterator = sinksSet.iterator();

        while (iterator.hasNext()){
            NSink current = iterator.next();
            try {
                current.recieveNotification(notification, this);
            } catch (Exception e){
                //System.out.println(e.toString());

                try {
                    current.recieveNotification(notification, this);
                } catch (Exception et){
                    //System.out.println(et.getMessage());
                    System.out.println("the sink is unreachable for a second time so will be removed from sinks to message");
                    sinksSet.remove(current);
                }
            }


        }
    }

    /*
        sets the name of the module for use by the server and client
     */
    public String toString() {
        return moduleName;
    }
}
