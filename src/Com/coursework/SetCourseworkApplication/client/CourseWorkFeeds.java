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

    /*
        very simply, this is where the user can sign-up for new modules (sources). they input the name of a source, if
        they are already a member, there is no such source or the source was unreachable they are notified, otherwise
        this sink is added ot the source
     */


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

                if (CWSourcesManager.containsCWSource("rmi://localhost:1099/" + sourceToJoin)){
                    JOptionPane.showMessageDialog(null, "you are already subscribed to this module source");
                } else {
                    try {
                        CWSourcesManager.joinModuleSource("rmi://localhost:1099/"+sourceToJoin);
                        JOptionPane.showMessageDialog(null, "you have joined module: " + sourceToJoin );
                    } catch (RemoteException RE){
                        System.out.println(RE.getMessage());
                        JOptionPane.showMessageDialog(null, "Are you sure this module exist? we are having trouble reaching it!");
                    } catch (MalformedURLException MUE){
                        System.out.println(MUE.getMessage());
                        JOptionPane.showMessageDialog(null, "The module seems to have been inputed incorrectly, please check and try again!");
                    } catch (NotBoundException NBE){
                        System.out.println(NBE.getMessage());
                        JOptionPane.showMessageDialog(null, "there is no such module in the registry");
                    }
                }
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
