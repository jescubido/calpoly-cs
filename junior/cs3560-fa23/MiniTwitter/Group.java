import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Group {

    private String groupName;
    private List<String> groupMembers;

    public Group() {
        setGroupID(groupName);
        this.groupMembers = new ArrayList<>();
    }
    
    public void setGroupID(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupName;
    }

    public void add(DefaultTreeModel treeModel, DefaultMutableTreeNode root,DefaultMutableTreeNode subroot){
		treeModel.insertNodeInto(subroot,root, 0);
    }

    public void addGroupMember(String groupMember) {
        groupMembers.add(groupMember);
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public int getGroupSize() {
        return groupMembers.size();
    }

}
