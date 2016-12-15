package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NotificationSink;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class ClientWindow extends JFrame {

    /*
        creates a panel where the user can sign up to/leave sources as well as creates a pain for the notifications. it
        also sets up a object of coursework list which implements abstractListModel to e used by the tasks window. It
        also sets itself up as the notificationSink and the Courseworks manager
     */

    JLabel title;

    CWs courseworkList;
    NotificationSink notificationSink;
    CWSourcesManager CWSourcesManager;

    public ClientWindow() throws RemoteException{
        super();


        setup();

        this.setSize(new Dimension(700,500));

        title = new JLabel("Your coursework assignments");

        TasksWindow TW = new TasksWindow(courseworkList);
        CourseWorkFeeds CWF = new CourseWorkFeeds(this.CWSourcesManager);



        this.add(title);
        this.add(TW);
        this.add(CWF);

        title.setBounds(250, 20, 200, 50);
        TW.setBounds(200, 90, 500, 400);
        CWF.setBounds(0, 200, 200, 300);
    }

    public void setup() throws RemoteException{
        this.courseworkList = new CWs();
        this.notificationSink = new NotificationSink(courseworkList);
        CWSourcesManager = new CWSourcesManager(this.notificationSink);
    }
}
