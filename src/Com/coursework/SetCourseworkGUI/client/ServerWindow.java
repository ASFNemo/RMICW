package Com.coursework.SetCourseworkGUI.client;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class ServerWindow extends JFrame{

    JLabel title;


    public ServerWindow(){
        super();

        this.setSize(new Dimension(500, 500));
        this.setResizable(false);

        title = new JLabel("Set your coursework!!");

        NewTask NT = new NewTask();


        this.add(title);
        this.add(NT);

        title.setBounds(50, 50, 50, 50);

    }



}
