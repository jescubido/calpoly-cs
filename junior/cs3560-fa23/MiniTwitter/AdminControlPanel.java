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
import javax.swing.tree.*;


public class AdminControlPanel extends JFrame implements Visitable {
    
    private static Group group = new Group();
    private static List<String> users = new ArrayList<>();
    static List<String> groups = new ArrayList<>();
    private Analytics analytics = new Analytics();

    private static AdminControlPanel instance = new AdminControlPanel();
        
    public static AdminControlPanel getInstance() {
        if(instance == null) {
        instance = new AdminControlPanel();
        }
        return instance;
    }

    public DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
	DefaultTreeModel treeModel = new DefaultTreeModel(root);
	JTree tree = new JTree(treeModel);
    
    AdminControlPanel() {
       
        // Initialize frame.
        JFrame frame = new JFrame("Mini Twitter - Admin Control Panel");
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Initialize Tree Model
         */
        TreeSelectionModel treeSelection = tree.getSelectionModel();
        treeSelection.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane scrollpane = new JScrollPane(tree);

        treeModel.reload(root);

        /*
         * Top JPanel contains: userIdTextArea, addUserButton, groupIDTextArea, addGroupButton
         */
        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        JTextField userIDTextArea = new JTextField();

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener((ae -> {
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String userid = userIDTextArea.getText();

            if(userid.isEmpty() || node == null){
	        	JOptionPane.showMessageDialog(frame, "Please enter a User ID or select a parent node!");
	        }
	        
	        else if(users.contains(userid)){
	        	JOptionPane.showMessageDialog(frame, "This user is already added!");
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

        JTextField groupIDTextArea = new JTextField();
        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener((ae -> {
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String groupid = groupIDTextArea.getText();
            groups.add(root.toString());
            
            if(groupid.isEmpty() || node == null){
            JOptionPane.showMessageDialog(frame, "Please enter a Group ID or select a parent node!");
            }
            
            else if(groups.contains(groupid)){
            JOptionPane.showMessageDialog(frame, "This ID already exists");
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

        JButton openUserViewButton = new JButton("Open User View");
        openUserViewButton.addActionListener((ae -> {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node == null){
				JOptionPane.showMessageDialog(frame, "Please select a User!");
			}
			else{
                String username = "Mini Twitter - " + tree.getLastSelectedPathComponent().toString();
			    UserView.showDialog(frame, username);
			}
        }));

        /*
         * Bottom JPanel contains: showUserTotalButton, showGroupTotalButton,
         * showMessagesTotalButton, showPositivePercentageButton
         */
        JPanel bottomPanel = new JPanel(new GridLayout(2,2));
        JButton showUserTotalButton = new JButton("Show User Total");
        showUserTotalButton.addActionListener((ae -> {
            int numOfUsers = analytics.getUserTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Users: " + numOfUsers);
        }));

        JButton showGroupTotalButton = new JButton("Show Group Total");
        showGroupTotalButton.addActionListener((ae -> {
            int numOfGroups = analytics.getGroupTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Groups: " + numOfGroups);
        }));

        JButton showMessagesTotalButton = new JButton("Show Messages Total");
        showMessagesTotalButton.addActionListener((ae -> {
            int numOfTweets = analytics.getTweetTotal();
            JOptionPane.showMessageDialog(null,"Total Number of Tweets: " + numOfTweets);
        }));

        JButton showPositivePercentageButton = new JButton("Show Positive Percentage");
        showPositivePercentageButton.addActionListener((ae -> {
            int positivePerc = analytics.getPositivePercentage();
            JOptionPane.showMessageDialog(null,"Total Percent of Positive Tweets: " + positivePerc + "%");
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}