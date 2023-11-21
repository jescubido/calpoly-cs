
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UserView implements Observer, Visitable {
    
    private String username;
    private String userTweet;

    private static JList<String> currentFollowing;
    private static JList<String> newsFeed;
    private static DefaultListModel<String> followings = new DefaultListModel<String>();
	private static DefaultListModel<String> tweets = new DefaultListModel<String>();
    private static User user = new User();
    private Analytics analytics = new Analytics();


    public static void showDialog(JFrame parent, String username) {


        JDialog dlg = new JDialog(parent, username , false);
		dlg.setSize(550, 350);
		dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topAddUserPanel = new JPanel(new GridLayout(1,2));
        JTextField userIDTextField = new JTextField();

        JButton addUserButton = new JButton("Follow User");
        addUserButton.addActionListener((ae -> {
            String userid = userIDTextField.getText();
			if(AdminControlPanel.groups.contains(userid)){
				followings.addElement(userid);
				userIDTextField.setText("");
				user.addFollowing(userid);
			}
			else{
				JOptionPane.showMessageDialog(dlg, "User does not exist");
			}
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
        postTweetButton.addActionListener((ae -> {
            String message = tweetTextArea.getText();
            accept(analytics);
			String usertweet = username + ": " + message;
			tweets.addElement(usertweet);
			tweetTextArea.setText("");
			user.postTweets(message);
        }));

        bottomTweetPanel.add(tweetTextArea);
        bottomTweetPanel.add(postTweetButton);

        JTextArea newsFeedTextArea = new JTextArea("News Feed:\n");
        newsFeedTextArea.setEditable(false);

        currentFollowing = new JList<String> (followings);	
        JScrollPane followScrollPane = new JScrollPane(currentFollowing);

	    newsFeed = new JList<String>(tweets);
        JScrollPane newScrollPane = new JScrollPane(newsFeed);

        JPanel bottomPanel = new JPanel(new GridLayout(0,1));
        bottomPanel.add(bottomTweetPanel);
        bottomPanel.add(newsFeedTextArea);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(topPanel);
        panel.add(bottomPanel);

        dlg.add(panel);
        dlg.setLocationRelativeTo(null);
		dlg.setVisible(true);
    }

    public void post(String message){
		tweets.addElement(message);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
			userTweet = (String) arg;
			post(userTweet);
        }
    }
}
