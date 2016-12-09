package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NotificationSink;
import Com.coursework.NotificationFramework.NotificationSource;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CWSourcesManager extends AbstractListModel{

    NotificationSink notificationSink;

    ArrayList<CWSource> cwSourceList = new ArrayList<>();

    public CWSourcesManager(NotificationSink notificationSink) {
        this.notificationSink = notificationSink;
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

    public void joinModuleSource (String sourceURL) throws RemoteException, MalformedURLException{
        NotificationSource notificationSource = new NotificationSource(sourceURL);
        notificationSource.addSink(this.notificationSink);
        cwSourceList.add(new CWSource(sourceURL, notificationSource));
        fireIntervalAdded(this, cwSourceList.size(), cwSourceList.size()); // I think i have this wrong and it should be -1
    }

    public void leavemoduleSource(int sourceListIndex){

        CWSource cwSource = cwSourceList.remove(sourceListIndex);
        fireIntervalRemoved(this, sourceListIndex, sourceListIndex);
        cwSource.notificationSource.removeSink(this.notificationSink);

    }
}
