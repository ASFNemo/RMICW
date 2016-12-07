package Com.coursework.SetCourseworkApplication.Com.setCourseworkApplication.client;

import Com.coursework.NotificationFramework.NotificationSink;
import Com.coursework.SetCourseworkApplication.Server.NewTask;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class ClientWindow extends JFrame {

    JLabel title;

    CWList courseworkList;
    NotificationSink notificationSink;
    SourceManager sourceManager;

    public ClientWindow() throws RemoteException{
        super();



        this.setSize(new Dimension(700,500));

        title = new JLabel("Your coursework assignments");

        TasksWindow TW = new TasksWindow();
        CourseWorkFeeds CWF = new CourseWorkFeeds();



        this.add(title);
        this.add(TW);
        this.add(CWF);

        title.setBounds(250, 20, 200, 50);
        TW.setBounds(200, 100, 500, 400);
        CWF.setBounds(0, 200, 200, 300);
    }

    public void setup() throws RemoteException{
        this.courseworkList = new CWList();
        this.notificationSink = new NotificationSink(courseworkList);
    }
}
