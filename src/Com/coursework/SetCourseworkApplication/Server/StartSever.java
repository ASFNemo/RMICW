package Com.coursework.SetCourseworkApplication.Server;

import javax.swing.*;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class StartSever {

    public static void main(String[] args) {
        // todo add where the server admin should add the binding URL


        JFrame SW = new ServerWindow();
        SW.setResizable(false);
        SW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SW.setVisible(true);
    }
}
