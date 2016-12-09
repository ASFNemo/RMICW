package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.Notification;
import Com.coursework.NotificationFramework.NotificationListener;
import Com.coursework.NotificationFramework.NotificationSource;
import Com.coursework.SetCourseworkApplication.Coursework;

import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CWs extends AbstractListModel implements NotificationListener{

    ArrayList<Coursework> courseworks = new ArrayList();

    @Override
    public void recieveNotification(NotificationSource notificationSource, Notification notification) {

        if (!courseworks.contains(notification)){
            courseworks.add((Coursework) notification);
            // apparently the best way to update the JList we havehttp://stackoverflow.com/questions/6815726/jlist-and-arraylist-update
            fireIntervalAdded(this, 0, 0);
            // but that would mean implementing the abstract list model -- not sure i want to do that
        }

    }

    public JList getCourseworksasJlist(){

        JList list = new JList(courseworks.toArray());

        return list;
    }


    @Override
    public int getSize() {
        return courseworks.size();
    }

    @Override
    public Object getElementAt(int index) {
        // becaus we are adding the elements to the jlist backwards.
        return courseworks.get(getSize()  - index - 1);
    }
}
