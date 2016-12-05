package Com.coursework.SetCourseworkGUI.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class NewTask extends JPanel {

    JLabel assignmentName;
    JLabel assignmentDesctiption;
    JLabel assignee;
    JLabel dueDate;

    JTextField assignmentNameInput;
    JTextArea assignmentDescriptionInput;
    JTextField assigneeInput;
    JFormattedTextField dateInput;

    JButton sendAssignment;


    public NewTask(){
        super();


    }


    public void addElements(){
        this.setSize(new Dimension(500, 400));
        this.setVisible(true);

        this.setLayout(null);


        assignmentName = new JLabel("Assignement name: ");
        assignmentNameInput = new JTextField();

        assignmentDesctiption = new JLabel("Description: ");



    }

}
