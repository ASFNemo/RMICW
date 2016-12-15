package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.SetCourseworkApplication.Coursework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class TasksWindow extends JPanel {

    CWs courseWorklist;
    JList courseWList;
    JScrollPane CWList;
    JButton moreInfo;

    public TasksWindow(CWs courseWorklist) {
        super();
        this.courseWorklist = courseWorklist;

        addElements();
    }

    /*
        this is the panel where all the task are shown. this uses the abstractLsitModel of CWs. when a user clicks on
        a notification and then "more info" they are shown a popup box with the details of the coursework.
     */
    public void addElements(){
        this.setSize(500, 400);
        this.setLayout(null);

        courseWList = new JList(this.courseWorklist);
        CWList = new JScrollPane(courseWList);

        moreInfo = new JButton("More info");
        moreInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (courseWList.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "please select the assignment you want more information about!");
                } else {

                    Coursework cw = (Coursework) courseWorklist.getElementAt(courseWList.getSelectedIndex());

                    JOptionPane.showMessageDialog(null, "Name: " + cw.getName() +
                                                        "\n Assignee: " + cw.getLecName() +
                                                        "\n Due Date: " + cw.getDay() + "/" + cw.getMonth() + "/" + cw.getYear() +
                                                        "\n Description: " + cw.getDescription());

                }
            }
        });


        this.add(CWList);
        this.add(moreInfo);

        CWList.setBounds(0, 0, 470, 350);
        moreInfo.setBounds(350, 355, 120, 25);

    }
}
