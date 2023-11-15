import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.tree.DefaultMutableTreeNode;

public class User extends DefaultMutableTreeNode implements UserInterface {

    String username;
    private List<Post> myFeed;
    private List<String> followers;
    private List<String> followings;
    
    public User(String username) {
        this.username = username;
        myFeed = new ArrayList<>();
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        isLeaf();
    }

    public void addFollower(String follower) {
        followers.add(follower);
    }

    public void addFollowing(String following) {
        followings.add(following);
    }

    @Override
    public String getName() {
        return username;
    }

    public boolean isLeaf() {
        return true;
    }
}
