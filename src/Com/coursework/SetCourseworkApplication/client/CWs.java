package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NSource;
import Com.coursework.NotificationFramework.Notification;
import Com.coursework.NotificationFramework.Listener;
import Com.coursework.SetCourseworkApplication.Coursework;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CWs extends AbstractListModel implements Listener {

    ArrayList<Coursework> courseworks = new ArrayList();

    /*
        this recieves a notification, adds it to the listModel and calls fireIntervalAdded, which updates the taskWindow
     */
    @Override
    public void recieveNotification(NSource notificationSource, Notification notification) {
        if (!courseworks.contains(notification)){
            courseworks.add((Coursework) notification);
            // apparently the best way to update the JList we havehttp://stackoverflow.com/questions/6815726/jlist-and-arraylist-update
            fireIntervalAdded(this, 0, 0);
        } else {
            duplicateNotification(notificationSource, notification);
        }

    }

    /*
        deals with duplicate notificaitons. in the future this can be used to make this system muc hsturdier.
     */
    @Override
    public void duplicateNotification(NSource nSource, Notification dupNot){
        System.out.println(" a duplicate notification " + dupNot + " from " + nSource);
    }



    /*
        returns the courseworks as a jlist
     */
    public JList getCourseworksasJlist(){

        JList list = new JList(courseworks.toArray());

        return list;
    }


    /*
        returns the size of the list
     */
    @Override
    public int getSize() {
        return courseworks.size();
    }

    /*
        returns the element at a specific index
     */
    @Override
    public Object getElementAt(int index) {
        // becaus we are adding the elements to the jlist backwards.
        return courseworks.get(getSize()  - index - 1);
    }
}
