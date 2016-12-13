package Com.coursework.SetCourseworkApplication.Server;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class StartSever {

    public static void main(String[] args) throws RemoteException {
        // todo add where the server admin should add the binding URL

        // The argument shoud be the module name. the rest will be done automatically later on.


        // pass in the URL for the registry when you run the programme  //loc
        JFrame SW = new ServerWindow(args[0]);
        SW.setResizable(false);
        SW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SW.setVisible(true);
    }
}
