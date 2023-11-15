import java.util.List;

public interface Visitor {

    public void visitFeed(List<Post> feed);
    public void visitGroup(List<Group> groupList);
    public void visitUser(List<String> userList);
    
}
