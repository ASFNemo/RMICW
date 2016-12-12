package Com.coursework.SetCourseworkApplication.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by asherfischbaum on 06/12/2016.
 */
public class CourseWorkFeeds extends JPanel {

    JLabel addSource;
    JTextField sourceInput;
    JButton add;
    CWSourcesManager CWSourcesManager;
    JList joinedModules;
    JButton remove;



    public CourseWorkFeeds(CWSourcesManager sm){
        super();

        this.CWSourcesManager = sm;

        addElements();
    }

    public void addElements(){
        this.setSize(200, 400);
        this.setLayout(null);


        /*
         * this section is to join different sources
         */
        addSource = new JLabel("Join module: ");
        sourceInput = new JTextField();
        add = new JButton("join");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceToJoin = sourceInput.getText();
                sourceInput.setText("");

                System.out.println(sourceToJoin);

                if (CWSourcesManager.containsCWSource(sourceToJoin)){
                    JOptionPane.showMessageDialog(null, "you are already subscribed to this module source");
                } else {
                    try {
                        CWSourcesManager.joinModuleSource(sourceToJoin);
                        JOptionPane.showMessageDialog(null, "you have joined module: " + sourceToJoin );
                    } catch (RemoteException RE){
                        System.out.println(RE.getMessage());
                        JOptionPane.showMessageDialog(null, "Are you sure this URL exist? we are having trouble reaching it!");
                    } catch (MalformedURLException MUE){
                        System.out.println(MUE.getMessage());
                        JOptionPane.showMessageDialog(null, "The URL seems to have been formed incorrectly, please check and try again!");
                    } catch (NotBoundException NBE){
                        System.out.println(NBE.getMessage());
                        JOptionPane.showMessageDialog(null, "there is no such URL in the registry");
                    }
                }
                // check if this is a actual source, if yes add this sink to the source and add the source to the list
                // otherwish bring up a jOprion box warning that this is an unrecognised sink
            }
        });

        /*
            This is the list of sources someone is subscribed to
         */

        joinedModules = new JList(CWSourcesManager);
        JScrollPane moduleList = new JScrollPane(joinedModules);

        /*
            this section is for a user to remove themselves from a module source
         */

        remove = new JButton("remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (joinedModules.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "please select the module you want to remove!");
                } else {
                    JOptionPane.showMessageDialog(null, "you have been removed from module: " +
                            CWSourcesManager.getElementAt(joinedModules.getSelectedIndex()));
                    CWSourcesManager.leavemoduleSource(joinedModules.getSelectedIndex());
                }
            }
        });




        this.add(addSource);
        this.add(sourceInput);
        this.add(add);
        this.add(moduleList);
        this.add(remove);

        addSource.setBounds(10, 80, 180, 30);
        sourceInput.setBounds(10, 110, 180, 30);
        add.setBounds(90, 140, 100, 30);
        moduleList.setBounds(10, 180, 180, 240);
        remove.setBounds(90, 430, 100, 30);

    }
}
