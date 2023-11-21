
public interface Visitor {

    public void visit(AdminControlPanel admin);
	public void visit(User user);
	public void visit(UserView userView); 
    public void visit(PositiveTweets positiveTweets);

}
