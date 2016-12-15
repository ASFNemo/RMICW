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

    /*
        returns the amount of sources subscribed ot
     */
    @Override
    public int getSize() {
        return cwSourceList.size();
    }

    /*
        returns the source at a specific index
     */
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

    /*
        allows the sink to join a new source
     */
    public void joinModuleSource (String sourceURL) throws RemoteException, MalformedURLException, NotBoundException{
            NSource ns = NotificationSource.getSource(sourceURL);
            ns.addSink(Nsink);
            cwSourceList.add(new CWSource(sourceURL, ns));
            fireIntervalAdded(this, cwSourceList.size(), cwSourceList.size());
    }

    /*
        allows a user to leave a source (module). if there is an issue they are shown a popup box.
     */
    public void leavemoduleSource(int sourceListIndex){
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
