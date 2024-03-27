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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class Group {

    private String groupName;
    private List<String> groupMembers;
    private long createGroup;

    public Group() {
        setGroupID(groupName);
        this.groupMembers = new ArrayList<>();
    }
    
    public void setGroupID(String groupName) {
        this.groupName = groupName;
    }

    /*
     * Returns group name.
     */
    public String getGroupID() {
        return groupName;
    }

    /*
     * Determines where to add the node.
     */
    public void add(DefaultTreeModel treeModel, DefaultMutableTreeNode root,DefaultMutableTreeNode subroot){
		treeModel.insertNodeInto(subroot,root, 0);
    }

    /*
     * Adds usernames to group.
     */
    public void addGroupMember(String groupMember) {
        groupMembers.add(groupMember);
    }

    /*
     * Returns list of users in the group.
     */
    public List<String> getGroupMembers() {
        return groupMembers;
    }

    /*
     * Returns number of users in a group.
     */
    public int getGroupSize() {
        return groupMembers.size();
    }

    /*
     * Starts time for when object was created.
     */
    public void setCreationTime() {
        createGroup = System.currentTimeMillis();
    }

    /*
     * Returns the time when object was created.
     */
    public long getCreationTime() {
        return createGroup;
    }

}
