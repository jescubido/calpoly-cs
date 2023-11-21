/**
 * Name: Escubido, Jarisse
 * Assignment: #2
 * Due: 14 November 2023
 * Course: cs-3560-01-fa23
 * 
 * Description:
 *      Implementing a Java-based Mini Twitter with graphical user interface (GUI) using Java Swing.
 */

public class Analytics implements Visitor {

    private int totalUsers;
    private int totalGroups;
    private int totalTweets;
    private int positive;
    private int positivePercentage;

    /*
     * Returns total number of users.
     */
    public int getUserTotal() {
        return totalUsers;
    }

    /*
     * Returns total number of groups.
     */
    public int getGroupTotal() {
        return totalGroups;
    }

    /*
     * Returns total number of tweets.
     */
    public int getTweetTotal() {
        return totalTweets;
    }

    /*
     * Calculates percentage of positive tweets.
     */
    public void calculatePositiveTweets() {
        positivePercentage = positive/totalTweets * 100;
    }

    /*
     * Returns percentage of positive tweets.
     */
    public int getPositivePercentage() {
        calculatePositiveTweets();
        return positivePercentage;
    }

    /*
     * Implementation of Visitor pattern.
     */
    @Override
    public void visit(AdminControlPanel admin) {
        totalGroups++;
    }

    @Override
    public void visit(User user) {
        totalUsers++;
    }

    @Override
    public void visit(UserView userView) {
        totalTweets++;
    }

    @Override
    public void visit(PositiveTweets positiveTweets) {
        positive++;
    }
    
}
