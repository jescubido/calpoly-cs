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

    private String getUserID() {
        return name;
    }

    public void add(DefaultMutableTreeNode node, String name) {
        node.add(new DefaultMutableTreeNode(name));
    }

    public void addFollowers(String follower) {
        this.followers.add(follower);
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void addFollowing(String following) {
        this.followings.add(following);
    }

    public List<String> getFollowings() {
        return followings;
    }

    public void postTweets(String tweet) {
        this.tweets.add(tweet);
    }

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
