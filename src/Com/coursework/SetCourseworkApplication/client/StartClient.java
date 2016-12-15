package Com.coursework.SetCourseworkApplication.client;

import javax.swing.*;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class StartClient {
    public static void main(String[] args) {
        try {
            JFrame SC = new ClientWindow();
            SC.setResizable(false);
            SC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SC.setVisible(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
