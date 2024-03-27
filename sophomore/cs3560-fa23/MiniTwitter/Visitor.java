/**
 * Name: Escubido, Jarisse
 * Assignment: #2
 * Due: 14 November 2023
 * Course: cs-3560-01-fa23
 * 
 * Description:
 *      Implementing a Java-based Mini Twitter with graphical user interface (GUI) using Java Swing.
 */

public interface Visitor {

    public void visit(AdminControlPanel admin);
	public void visit(User user);
	public void visit(UserView userView); 
    public void visit(PositiveTweets positiveTweets);

}
