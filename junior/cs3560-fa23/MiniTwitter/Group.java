import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class Group extends DefaultMutableTreeNode implements UserInterface {

    public Group() {
        setGroupName(groupName);
        isLeaf();
    }

    private String groupName;
    private List<String> groupMembers;
    
    public void addGroupMember(String groupMember) {
        groupMembers.add(groupMember);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public int getGroupSize() {
        return groupMembers.size();
    }

    @Override
    public String getName() {
        return groupName;
    }

    public boolean isLeaf() {
        return false;
    }

}
