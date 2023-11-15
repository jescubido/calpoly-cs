import java.util.List;

public class CountVisitor implements Visitor {

    private int counter = 0;
    private int totalGroups;
    private int totalUsers;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void visitFeed(List<Post> feed) {
        // TODO
    }

    @Override
    public void visitGroup(List<Group> groupList) {
        totalGroups = 0;

        for(Group g: groupList) {
            totalGroups += g.getGroupSize();
            }
            totalGroups += groupList.size();
    }

    @Override
    public void visitUser(List<String> userList) {
        totalUsers = userList.size();
    }

    public int getTotalGroups() {
        return totalGroups;
    }

    public int getTotalUsers() {
        return totalUsers;
    }
    
}
