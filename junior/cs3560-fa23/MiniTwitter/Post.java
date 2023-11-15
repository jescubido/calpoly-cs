
public class Post {

    private String user;
    private String tweet;

    public Post(String user, String tweet) {
        this.user = user;
        this.tweet = tweet;
    }

    public Post getPost() {
        return this;
    }

    public String getUsername() {
        return this.user;
    }

    public String getTweet() {
        return this.tweet;
    }
    
}
