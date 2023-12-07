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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import javax.swing.tree.*;

public class AdminControlPanel extends JFrame implements Visitable {
    
    private Group group = new Group();
    static List<String> users = new ArrayList<>();
    static List<String> groups = new ArrayList<>();
    private Analytics analytics = new Analytics();

    /*
    * Implementation of Singleton pattern.
    */
    private static AdminControlPanel instance = new AdminControlPanel();
        
    public static AdminControlPanel getInstance() {
        if(instance == null) {
        instance = new AdminControlPanel();
        }
        return instance;
    }


    /*
     * Initialize tree model.
     * Implementation of Composite pattern.
     */
    public DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
	DefaultTreeModel treeModel = new DefaultTreeModel(root);
	JTree tree = new JTree(treeModel);
    
    AdminControlPanel() {
       
        // Initialize frame.
        JFrame frame = new JFrame("Mini Twitter - Admin Control Panel");
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TreeSelectionModel treeSelection = tree.getSelectionModel();
        treeSelection.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane scrollpane = new JScrollPane(tree);

        treeModel.reload(root);

        /*
         * Top JPanel contains: userIdTextArea, addUserButton, groupIDTextArea, addGroupButton
         */
        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        JTextField userIDTextArea = new JTextField();

        /*
         * User Button adds a new user to tree.
         */
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener((ae -> {
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String userid = userIDTextArea.getText();

            if(userid.isEmpty() || node == null){
	        	JOptionPane.showMessageDialog(frame, "Please enter a User ID or select a parent node!");
	        }
	        
	        else{
	        	if(node != root)	        	
	        		root = node;	        			  
	        	
	        	User user = new User();
	        	user.add(root, userid);
	        	
	        	users.add(userid);
	        	user.accept(analytics);
        		userIDTextArea.setText("");
	        }	        	      	

			treeModel.reload(root);
        }));

        /*
         * Group Button creates new group to add to tree model.
         */
        JTextField groupIDTextArea = new JTextField();
        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener((ae -> {
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String groupid = groupIDTextArea.getText();
            groups.add(root.toString());
            
            if(groupid.isEmpty() || node == null){
            JOptionPane.showMessageDialog(frame, "Please enter a Group ID or select a parent node!");
            }

            else{
                if(node != root){
                    root = node;
                }

            DefaultMutableTreeNode subroot = new DefaultMutableTreeNode(groupid);
            group.add(treeModel, root, subroot);
            groupIDTextArea.setText("");
            accept(analytics);
            treeModel.reload(root);
            }
        }));

        topPanel.add(userIDTextArea);
        topPanel.add(addUserButton);
        topPanel.add(groupIDTextArea);
        topPanel.add(addGroupButton);

        /*
         * Validate ID Button validates if all IDs used in users and groups are valid
         * - All IDs are unique
         * - All IDs do not contain spaces
         */
        JButton validateID = new JButton("Validate IDs");
        validateID.addActionListener((ae ->
        {
            String userid = userIDTextArea.getText();
            String groupid = groupIDTextArea.getText();

            if(users.contains(userid)){
	        	JOptionPane.showMessageDialog(frame, "This user already exists!", "Alert", JOptionPane.WARNING_MESSAGE);
	        }
            else if(groups.contains(groupid)){
                JOptionPane.showMessageDialog(frame, "This group already exists!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            else if(groupid.contains(" ") || (userid.contains(" "))){
                JOptionPane.showMessageDialog(frame, "Invalid ID! May not contain spaces!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }));

        /*
         * Last Updated User Button prints the latest user created
         */
        JButton lastUpdatedUser = new JButton("Last Updated User");
        lastUpdatedUser.addActionListener((ae ->
        {
            String latestUser = users.get(users.size() - 1);

            JOptionPane.showConfirmDialog(frame, "User's last update: " + latestUser);
        }));

        /*
         * Open User View Button opens new dialog window of each user.
         */
        JButton openUserViewButton = new JButton("Open User View");
        openUserViewButton.addActionListener((ae -> {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node == null){
				JOptionPane.showMessageDialog(frame, "Please select a User!");
			}
			else{
                String username = tree.getLastSelectedPathComponent().toString();
                String title = "Mini Twitter - " + username;
			    UserView.showDialog(frame, title, username);
			}
        }));

        /*
         * Bottom JPanel contains: showUserTotalButton, showGroupTotalButton,
         * showMessagesTotalButton, showPositivePercentageButton
         */
        JPanel bottomPanel = new JPanel(new GridLayout(2,2));

        /*
         * Displays message dialog of the total number of users.
         */
        JButton showUserTotalButton = new JButton("Show User Total");
        showUserTotalButton.addActionListener((ae -> {
            int numOfUsers = analytics.getUserTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Users: " + numOfUsers);
        }));

        /*
         * Displays message dialog of the total number of groups.
         */
        JButton showGroupTotalButton = new JButton("Show Group Total");
        showGroupTotalButton.addActionListener((ae -> {
            int numOfGroups = analytics.getGroupTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Groups: " + numOfGroups);
        }));

        /*
         * Displays message dialog of the total number of tweets.
         */
        JButton showMessagesTotalButton = new JButton("Show Messages Total");
        showMessagesTotalButton.addActionListener((ae -> {
            int numOfTweets = analytics.getTweetTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Tweets: " + numOfTweets);
        }));

        /*
         * Displays message dialog of the percentage of positive tweets.
         */
        JButton showPositivePercentageButton = new JButton("Show Positive Percentage");
        showPositivePercentageButton.addActionListener((ae -> {
            int positivePerc = analytics.getPositivePercentage();
            JOptionPane.showMessageDialog(null,"Total Percent of Positive Tweets: " + positivePerc + "%");
        }));

        /*
         * Add buttons to bottom panel.
         */
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}