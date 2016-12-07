package Com.coursework.SetCourseworkApplication.Com.setCourseworkApplication.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CourseWorkFeeds extends JPanel {

    JLabel addSource;
    JTextField sourceInput;
    JButton add;



    public CourseWorkFeeds(){
        super();

        addElements();
    }

    public void addElements(){
        this.setSize(200, 400);
        this.setLayout(null);

        addSource = new JLabel("Join module: ");
        sourceInput = new JTextField();
        add = new JButton("join");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceToJoin = sourceInput.getText();

                System.out.println(sourceToJoin);

                // check if this is a actual source, if yes add this sink to the source and add the source to the list
                // otherwish bring up a jOprion box warning that this is an unrecognised sink
            }
        });




        this.add(addSource);
        this.add(sourceInput);
        this.add(add);

        addSource.setBounds(10, 70, 180, 30);
        sourceInput.setBounds(10, 110, 180, 30);
        add.setBounds(90, 150, 100, 30);

    }
}
