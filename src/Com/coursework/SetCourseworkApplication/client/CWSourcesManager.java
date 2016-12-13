package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NSink;
import Com.coursework.NotificationFramework.NSource;
import Com.coursework.NotificationFramework.NotificationSource;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CWSourcesManager extends AbstractListModel{

    NSink Nsink;

    ArrayList<CWSource> cwSourceList = new ArrayList<>();

    public CWSourcesManager(NSink notificationSink) {
        this.Nsink = notificationSink;
    }


    @Override
    public int getSize() {
        return cwSourceList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return cwSourceList.get(index);
    }

    public boolean containsCWSource(String url){
        for(int i = 0; i < cwSourceList.size() - 1; i++){
            CWSource cws = cwSourceList.get(i);

            if (cws.getURL().equals(url)) {
                return true;
            }
        }
        return false;
    }

    public void joinModuleSource (String sourceURL) throws RemoteException, MalformedURLException, NotBoundException{
        //NotificationSource notificationSource = null;
            System.out.println("in the join module method");
            //todo maybe i have changed this inccorectly!!!
            //notificationSource = new NotificationSource(sourceURL);

            NSource ns = NotificationSource.getSource(sourceURL);
            ns.addSink(Nsink);
            cwSourceList.add(new CWSource(sourceURL, ns));
            fireIntervalAdded(this, cwSourceList.size(), cwSourceList.size());// I think i have this wrong and it should be -1


    }

    public void leavemoduleSource(int sourceListIndex){

        // todo check if this should go after
        CWSource cwSource = cwSourceList.remove(sourceListIndex);
        fireIntervalRemoved(this, sourceListIndex, sourceListIndex);
        try {
            cwSource.notificationSource.removeSink(this.Nsink);
        } catch (RemoteException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "There was an error while trying to leave the module.");
        }

    }
}
