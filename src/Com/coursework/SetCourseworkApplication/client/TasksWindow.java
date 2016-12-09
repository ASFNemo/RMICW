package Com.coursework.SetCourseworkApplication.client;

import javax.swing.*;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class TasksWindow extends JPanel {

    CWs courseWorklist;
    JList courseWList;
    JScrollPane CWList;

    public TasksWindow(CWs courseWorklist) {
        super();
        this.courseWorklist = courseWorklist;

        addElements();
    }

    public void addElements(){
        this.setSize(500, 400);
        this.setLayout(null);

        courseWList = new JList(this.courseWorklist);
        CWList = new JScrollPane(courseWList);


        this.add(CWList);

        CWList.setBounds(0, 0, 470, 350);

    }
}
