package Com.coursework.SetCourseworkApplication.Com.setCourseworkApplication.client;

import Com.coursework.SetCourseworkApplication.Server.ServerWindow;

import javax.swing.*;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class StartClient {
    public static void main(String[] args) {
        // todo add where the server admin should add the binding URL


        JFrame SC = new ClientWindow();
        SC.setResizable(false);
        SC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SC.setVisible(true);
    }
}
