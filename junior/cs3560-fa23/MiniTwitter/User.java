/**
 * Name: Escubido, Jarisse
 * Assignment: #2
 * Due: 14 November 2023
 * Course: cs-3560-01-fa23
 * 
 * Description:
 *      Implementing a Java-based Mini Twitter with graphical user interface (GUI) using Java Swing.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.tree.DefaultMutableTreeNode;


public class User extends Observable implements Visitable {
    private String name;
    private String userTweet;
    private List<String> followers;
    private List<String> followings;
    private List<String> tweets;

    public User() {
        setUserID(name);
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    private void setUserID(String name) {
        this.name = name;
    }

    /*
     * Returns user's id.
     */
    private String getUserID() {
        return name;
    }

    /*
     * Adds the user to the tree.
     */
    public void add(DefaultMutableTreeNode node, String name) {
        node.add(new DefaultMutableTreeNode(name));
    }

    /*
     * Adds the user's followers to a list.
     */
    public void addFollowers(String follower) {
        this.followers.add(follower);
    }

    /*
     * Returns the list of followers a user has.
     */
    public List<String> getFollowers() {
        return followers;
    }

    /*
     * Adds user's followings to a list.
     */
    public void addFollowing(String following) {
        this.followings.add(following);
    }

    /*
     * Returns the list of followings a user has.
     */
    public List<String> getFollowings() {
        return followings;
    }

    /*
     * Adds the tweets to a list.
     */
    public void postTweets(String tweet) {
        this.tweets.add(tweet);
    }

    /*
     * Returns the list of tweets a user has posted.
     */
    public List<String> getTweets() {
        return tweets;
    }

    public void setTweet(String tweet){
		this.userTweet = name + ": " + tweet;
		setChanged();
		notifyObservers(userTweet);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
