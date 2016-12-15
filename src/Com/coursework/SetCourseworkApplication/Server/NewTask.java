package Com.coursework.SetCourseworkApplication.Server;

import Com.coursework.NotificationFramework.NotificationSource;
import Com.coursework.SetCourseworkApplication.Coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class NewTask extends JPanel {

    JLabel moduleSource;
    JLabel assignmentName;
    JLabel assignmentDesctiption;
    JLabel assignee;
    JLabel dueDate;

    JTextField assignmentNameInput;
    JTextArea assignmentDescriptionInput;
    JTextField assigneeInput;
   JTextField dateInput;

    JButton sendAssignment;

    NotificationSource notificationSource;

    DefaultComboBoxModel<NotificationSource> sourceArrayList;

    JLabel module_url;
    JTextField URL_input;
    JButton create;

    JComboBox sourceList;


    /*
        this class is for a source to create a new assignment to be sent to the sinks. in this panel we have also added
        the ability to crete new sinks
     */

    public NewTask(String url){
        sourceArrayList = new DefaultComboBoxModel();

        try {
            notificationSource = new NotificationSource("rmi://localhost:1099/"+url);
            sourceArrayList.addElement(notificationSource);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There has been an issue running the RMI registry. make sure it is running on this host");
            System.exit(1);
        }


        addElements();
    }


    /*
        this method adds everything to the pannel, the area to fill out the assignment details, send it and create new
        modules
     */
    public void addElements(){
        this.setSize(new Dimension(600, 400));

        this.setLayout(null);


        moduleSource = new JLabel("Module: ");
        sourceList = new JComboBox<>(sourceArrayList);


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


        /*
            when the user clicks send here, we first check that all the required fields are filled out.
            then we check if the date is formatted as required. if everything is ok, then we send the notification to
            all the subscirbers of this source. otherwise we bring up a pop-up that explains the problem and why the
            assignment hasn't been sent.
         */
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

                                NotificationSource NS = (NotificationSource) sourceList.getItemAt(sourceList.getSelectedIndex());

                                // pass the cw to notification framework (it is serializable)
                                NS.sendSinksNotification(new Coursework(CWName, CWAsssignee, CWDescription,
                                        Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2])));


                                assignmentNameInput.setText("");
                                assignmentDescriptionInput.setText("");
                                assigneeInput.setText("");
                                dateInput.setText("");

                                JOptionPane.showMessageDialog(null, "The coursework has been sent");


                            }
                        }catch (ParseException error){
                            JOptionPane.showMessageDialog(null, "Please enter a correctly formated date (dd/mm/yyyy)");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields");
                }
            }
        });


        /*
            here we allow for the creation of additional sinks. we expect input of a module name. if there is a problem
            a popup box is shown, otherwise a new source is created.
         */
        module_url = new JLabel("enter new Module URL:");
        URL_input = new JTextField();
        create = new JButton("create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo: create the new source, add the source to the source list and add it to the dropdown menu

                String newURL = URL_input.getText();
                URL_input.setText("");

                try {
                    NotificationSource nSource = new NotificationSource("rmi://localhost:1099/"+newURL);
                    sourceArrayList.addElement(nSource);
                } catch (RemoteException e1) {
                    System.out.println(e1.getMessage());
                    JOptionPane.showMessageDialog(null, "there was a remote problem");
                } catch (MalformedURLException e2) {
                    System.out.println(e2.getMessage());

                    JOptionPane.showMessageDialog(null, "The URL was malformed or already in use! " +
                            "\n please remember \'rmi://localhost:1099/\' is added for you and you only need to add " +
                            "the module name");
                }

            }
        });





        this.add(moduleSource);
        this.add(sourceList);
        this.add(assignmentName);
        this.add(assignmentNameInput);
        this.add(assignmentDesctiption);
        this.add(descriptionScroll);
        this.add(assignee);
        this.add(assigneeInput);
        this.add(dueDate);
        this.add(dateInput);
        this.add(sendAssignment);
        this.add(module_url);
        this.add(URL_input);
        this.add(create);

        moduleSource.setBounds(75, 95, 150, 25);
        sourceList.setBounds(225, 95, 150, 25);
        assignmentName.setBounds(75, 125, 150, 25);
        assignmentNameInput.setBounds(225, 125, 320, 25);
        assignee.setBounds(75, 160, 150, 25);
        assigneeInput.setBounds(225, 160, 320, 25);
        dueDate.setBounds(75, 195, 150, 25);
        dateInput.setBounds(225, 195, 320, 25);
        assignmentDesctiption.setBounds(75, 230, 150, 25);
        descriptionScroll.setBounds(225, 230, 320, 150);
        sendAssignment.setBounds(450, 390, 100, 25);
        module_url.setBounds(60, 430, 170, 25);
        URL_input.setBounds(220, 430, 130, 25);
        create.setBounds(380, 430, 100, 25 );





    }

}
