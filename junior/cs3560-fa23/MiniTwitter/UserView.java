
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;

public class UserView extends JDialog {
    
    private static JTree tree;
    public static List<User> users = new ArrayList<User>();
    private static List<String> userNames = new ArrayList<String>();

    public static void showDialog(JFrame parent, String username) {


        JDialog dlg = new JDialog(parent, username , false);
		dlg.setSize(550, 350);
		dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topAddUserPanel = new JPanel(new GridLayout(1,2));
        JTextField userIDTextField = new JTextField();

        JButton addUserButton = new JButton("Follow User");
        addUserButton.addActionListener((ae -> {
            User nUser = new User(userIDTextField.getText());
            users.add(nUser);
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(userIDTextField.getText());

            if (userNames.contains(userIDTextField.getText())) {
				JOptionPane.showMessageDialog(null, "No Username Entered", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                selectedNode = (DefaultMutableTreeNode) model.getRoot();
                if (!userIDTextField.getText().trim().equals("")) {
                    model.insertNodeInto(newUser, selectedNode, selectedNode.getChildCount());
                    model.reload();
                }
            }
            userNames.add(userIDTextField.getText());
        }));

        topAddUserPanel.add(userIDTextField);
        topAddUserPanel.add(addUserButton);

        JTextArea myFeed = new JTextArea("Current Following:\n");
        myFeed.setEditable(false);

        JPanel topPanel = new JPanel(new GridLayout(0,1));
        topPanel.add(topAddUserPanel);
        topPanel.add(myFeed);

        JPanel bottomTweetPanel = new JPanel(new GridLayout(1,2));
        JTextArea tweetTextArea = new JTextArea();
        tweetTextArea.setLineWrap(true);
        tweetTextArea.setWrapStyleWord(true);
        JButton postTweetButton = new JButton("Post Tweet");
        bottomTweetPanel.add(tweetTextArea);
        bottomTweetPanel.add(postTweetButton);

        JTextArea newsFeed = new JTextArea("News Feed:\n");
        newsFeed.setEditable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(0,1));
        bottomPanel.add(bottomTweetPanel);
        bottomPanel.add(newsFeed);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(topPanel);
        panel.add(bottomPanel);

        dlg.add(panel);
        dlg.setLocationRelativeTo(null);
		dlg.setVisible(true);
    }
}
