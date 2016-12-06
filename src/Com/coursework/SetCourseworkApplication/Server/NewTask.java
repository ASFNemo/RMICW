package Com.coursework.SetCourseworkApplication.Server;

import Com.coursework.SetCourseworkApplication.Coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
   JTextField dateInput;

    JButton sendAssignment;


    public NewTask(){
        super();

        addElements();
    }


    public void addElements(){
        this.setSize(new Dimension(600, 400));

        this.setLayout(null);


        assignmentName = new JLabel("Assignement name: ");
        assignmentNameInput = new JTextField();

        assignmentDesctiption = new JLabel("Description: ");
        assignmentDescriptionInput = new JTextArea();
        JScrollPane descriptionScroll = new JScrollPane(assignmentDescriptionInput,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        assignmentDescriptionInput.setLineWrap(true);
        assignmentDescriptionInput.setWrapStyleWord(true);

        assignee = new JLabel("Assignee name: ");
        assigneeInput = new JTextField();

        dueDate = new JLabel("date due: ");
        dateInput = new JTextField();

        // when clicking submit - check that what they have entered is a correct date
        // use
        // JOptionPane.showMessageDialog(null, "Please Enter Valid");

        sendAssignment = new JButton("send");
        sendAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String CWName = assignmentNameInput.getText();
                String CWDescription = assignmentDescriptionInput.getText();
                String CWAsssignee = assigneeInput.getText();
                String date = dateInput.getText();

                if (!CWName.equals("") && !CWDescription.equals("") && !CWAsssignee.equals("")){
                    String[] dates = date.split("/");
                    System.out.println(" I AM SOOO DONE");
                    System.out.println(dates.length);
                    System.out.println(dates[0]);
                    System.out.println(dates[1]);
                    System.out.println(dates[2]);
                    if (dates.length == 3){
                        System.out.println("in theresssss");
                        try {
                            NumberFormat.getInstance().parse(date);
                            System.out.println("here");
                            if ((Integer.parseInt(dates[0]) > 31 || Integer.parseInt(dates[0]) < 0) ||
                                    (Integer.parseInt(dates[1]) > 12 || Integer.parseInt(dates[1]) < 0) ||
                                    (dates[2].length() < 4 || dates[2].length() > 4)) {


                                JOptionPane.showMessageDialog(null, "Please enter a correctly formated date (dd/mm/yyyy)");
                            } else {

                                System.out.println("now I am in here :)");
                                // if yes send the CW and do the notification stuff

                                Coursework cw = new Coursework(CWName, CWAsssignee, CWDescription,
                                        Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));

                                // pass the cw to notification framework (it is serializable)

                                JOptionPane.showMessageDialog(null, "The coursework has been sent");


                            }
                        }catch (ParseException error){
                            JOptionPane.showMessageDialog(null, "Please enter a correctly formated date (dd/mm/yyyy)");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields");
                }

                // check if the date is filled in correctly
                // check if everything is filled in correctly.

                // if not
                // show pop-up box:
                // and then let them fill in
            }
        });




        this.add(assignmentName);
        this.add(assignmentNameInput);
        this.add(assignmentDesctiption);
        this.add(descriptionScroll);
        this.add(assignee);
        this.add(assigneeInput);
        this.add(dueDate);
        this.add(dateInput);
        this.add(sendAssignment);

        assignmentName.setBounds(75, 75, 150, 25);
        assignmentNameInput.setBounds(225, 75, 320, 25);
        assignee.setBounds(75, 110, 150, 25);
        assigneeInput.setBounds(225, 110, 320, 25);
        dueDate.setBounds(75, 145, 150, 25);
        dateInput.setBounds(225, 145, 320, 25);
        assignmentDesctiption.setBounds(75, 180, 150, 25);
        descriptionScroll.setBounds(225, 180, 320, 150);
        sendAssignment.setBounds(450, 340, 100, 25);





    }

}
