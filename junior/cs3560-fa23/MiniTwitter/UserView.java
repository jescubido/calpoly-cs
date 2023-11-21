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
import java.util.Observable;
import java.util.Observer;

public class UserView implements Observer, Visitable {
    
    private String username;
    private String userTweet;

    private static JList<String> currentFollowing;
    private static JList<String> newsFeed;
    private static DefaultListModel<String> followings = new DefaultListModel<String>();
	private static DefaultListModel<String> tweets = new DefaultListModel<String>();

    private PositiveTweets posTweets = new PositiveTweets();
    private static User user = new User();
    private Analytics analytics = new Analytics();

    public UserView(String name) {
        this.username = name;
    }

    public static void showDialog(JFrame parent, String username) {

        // Initialize Dialog frame
        JDialog dlg = new JDialog(parent, username , false);
		dlg.setSize(550, 350);
		dlg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /*
         * TopPanel contains: following users and showing list of current following users.
         */
        JPanel topAddUserPanel = new JPanel(new GridLayout(1,2));
        JTextField userIDTextField = new JTextField();

        JButton addUserButton = new JButton("Follow User");
        addUserButton.addActionListener((ae -> {
            String userid = userIDTextField.getText();
			if(AdminControlPanel.users.contains(userid)){
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

        JPanel currentFollowingPanel = new JPanel();
        JLabel currentFollowingLabel = new JLabel("Current Following:\n");
        currentFollowing = new JList<String> (followings);	
        JScrollPane followScrollPane = new JScrollPane(currentFollowing);

        currentFollowingPanel.add(currentFollowingLabel);
        currentFollowingPanel.add(followScrollPane);

        JPanel topPanel = new JPanel(new GridLayout(0,1));
        topPanel.add(topAddUserPanel);
        topPanel.add(currentFollowingPanel);

        /*
         * Bottom Panel contains: posting a tweet and news feed.
         */
        JPanel bottomTweetPanel = new JPanel(new GridLayout(1,2));
        JTextArea tweetTextArea = new JTextArea();
        tweetTextArea.setLineWrap(true);
        tweetTextArea.setWrapStyleWord(true);

        JButton postTweetButton = new JButton("Post Tweet");
        postTweetButton.addActionListener((ae -> {
            String message = tweetTextArea.getText();
			String usertweet = username + ": " + message;
            //accept(analytics);
			tweets.addElement(usertweet);
			tweetTextArea.setText("");
			user.postTweets(message);
        }));

        bottomTweetPanel.add(tweetTextArea);
        bottomTweetPanel.add(postTweetButton);

        JLabel newsFeedLabel = new JLabel("News Feed:\n");

	    newsFeed = new JList<String>(tweets);
        JScrollPane newScrollPane = new JScrollPane(newsFeed);

        JPanel bottomPanel = new JPanel(new GridLayout(0,1));
        bottomPanel.add(bottomTweetPanel);
        bottomPanel.add(newsFeedLabel);
        bottomPanel.add(newScrollPane);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(topPanel);
        panel.add(bottomPanel);

        /*
         * Adding content to content pane.
         */
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
