package Com.coursework.SetCourseworkApplication.Server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class ServerWindow extends JFrame{

    JLabel title;



    public ServerWindow(String url){
        super();

        this.setSize(new Dimension(600, 500));

        title = new JLabel("Set your coursework!!");

        NewTask NT = new NewTask(url);


        this.add(title);
        this.add(NT);

        title.setBounds(150, 20, 150, 50);
        NT.setBounds(0, 100, 600, 400);

    }



}
