/**
 * Name: Escubido, Jarisse
 * Assignment: #2
 * Due: 14 November 2023
 * Course: cs-3560-01-fa23
 * 
 * Description:
 *      Implementing a Java-based Mini Twitter with graphical user interface (GUI) using Java Swing.
 */

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class AdminControlPanel extends JFrame{

    int numOfTweets = 0;
    int numOfGroups = 0;
    int numOfUsers = 0;
    int positvePerc = 0;
    private JTree tree = new JTree();
    private List<String> groupNames = new ArrayList<String>();
    private List<String> userNames = new ArrayList<String>();
    private List<User> users = new ArrayList<User>();

    private static AdminControlPanel instance;
        
    public static AdminControlPanel getInstance() {
        if(instance == null) {
        instance = new AdminControlPanel();
        }
        return instance;
    }
    
    AdminControlPanel() {
       
        // Initialize frame.
        JFrame frame = new JFrame("Mini Twitter - Admin Control Panel");
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Initialize Tree Model
         */
        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
        JScrollPane scrollpane = new JScrollPane(tree);

        /*
         * Top JPanel contains: userIdTextArea, addUserButton, groupIDTextArea, addGroupButton
         */
        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        JTextField userIDTextArea = new JTextField();

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener((ae -> {
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
             if (userIDTextArea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No Username Entered", "Message", JOptionPane.INFORMATION_MESSAGE);
            } 
            else{
                model.insertNodeInto(new DefaultMutableTreeNode(userIDTextArea.getText()), selectedNode, selectedNode.getChildCount());  
                userNames.add(userIDTextArea.getText());
                userIDTextArea.setText("");
                numOfUsers++;
            }
        }));

        JTextField groupIDTextArea = new JTextField();
        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener((ae -> {
            if (groupIDTextArea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No Group name Entered", "Message", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                User nUser = new User(groupIDTextArea.getText());
                users.add(nUser);
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(groupIDTextArea.getText());

                if (groupNames.contains(groupIDTextArea.getText())) {
                    JOptionPane.showMessageDialog(null, "Group already exists", "Message", JOptionPane.INFORMATION_MESSAGE);
                } 
                else {
                    if (selectedNode != null) {
                        if (!groupIDTextArea.getText().trim().equals("")) {
                            model.insertNodeInto(newUser, selectedNode, selectedNode.getChildCount());
                            model.reload();
                            model.reload();
                            groupIDTextArea.setText("");
                            numOfGroups++;
                        }
                    } else {
                        selectedNode = (DefaultMutableTreeNode) model.getRoot();
                        if (!groupIDTextArea.getText().trim().equals("")) {
                            model.insertNodeInto(newUser, selectedNode, selectedNode.getChildCount());
                            newUser = (DefaultMutableTreeNode) model.getRoot();
                            groupIDTextArea.setText("");
                            model.reload();
                            numOfGroups++;
                        }
                    }
                }
                groupNames.add(groupIDTextArea.getText());
            }
        }));

        topPanel.add(userIDTextArea);
        topPanel.add(addUserButton);
        topPanel.add(groupIDTextArea);
        topPanel.add(addGroupButton);

        JButton openUserViewButton = new JButton("Open User View");
        openUserViewButton.addActionListener((ae -> {
            String username = "Mini Twitter - " + tree.getLastSelectedPathComponent().toString();
            UserView.showDialog(instance, username);
        }));

        /*
         * Bottom JPanel contains: showUserTotalButton, showGroupTotalButton,
         * showMessagesTotalButton, showPositivePercentageButton
         */
        JPanel bottomPanel = new JPanel(new GridLayout(2,2));
        JButton showUserTotalButton = new JButton("Show User Total");
        showUserTotalButton.addActionListener((ae -> {
            JOptionPane.showMessageDialog(null,"Total Number of Users: " + numOfUsers);
        }));

        JButton showGroupTotalButton = new JButton("Show Group Total");
        showGroupTotalButton.addActionListener((ae -> {
            JOptionPane.showMessageDialog(null,"Total Number of Groups: " + numOfGroups);
        }));

        JButton showMessagesTotalButton = new JButton("Show Messages Total");
        showMessagesTotalButton.addActionListener((ae -> {
            JOptionPane.showMessageDialog(null,"Total Number of Tweets: " + numOfTweets);
        }));

        JButton showPositivePercentageButton = new JButton("Show Positive Percentage");
        showPositivePercentageButton.addActionListener((ae -> {
            JOptionPane.showMessageDialog(null,"Total Percent of Positive Tweets: " + positvePerc + "%");
        }));

        bottomPanel.add(showUserTotalButton);
        bottomPanel.add(showGroupTotalButton);
        bottomPanel.add(showMessagesTotalButton);
        bottomPanel.add(showPositivePercentageButton);

        /*
         * Add topPanel and bottomPanel into a single panel
         */
        JPanel rightPanel = new JPanel(new GridLayout(0,1));
        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(openUserViewButton, BorderLayout.CENTER);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        JSplitPane splitpane = new JSplitPane();
        splitpane.setRightComponent(rightPanel);
        splitpane.setLeftComponent(scrollpane);
        splitpane.setResizeWeight(0.5);

        /*
         * Add components to content pane.
         */
        frame.add(splitpane);
        frame.setVisible(true);
    }
}