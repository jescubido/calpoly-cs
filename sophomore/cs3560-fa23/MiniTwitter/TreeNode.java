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

/*
 * Implementation of Composite pattern.
 */
public class TreeNode {
    private String name;
	private List<User> users;
	
	public TreeNode(String name){
		this.setName(name);
		users = new ArrayList<User>();
	}
	
	public void add(User user){
		users.add(user);
	}
	
	public List<User> getUsers(){
		return users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
