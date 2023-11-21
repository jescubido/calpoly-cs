import java.util.ArrayList;
import java.util.List;

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
