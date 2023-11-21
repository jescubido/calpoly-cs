public class Analytics implements Visitor{

    private int totalUsers;
    private int totalGroups;
    private int totalTweets;
    private int positive;
    private int positivePercentage;

    public int getUserTotal() {
        return totalUsers;
    }

    public int getGroupTotal() {
        return totalGroups;
    }

    public int getTweetTotal() {
        return totalTweets;
    }

    public void calculatePositiveTweets() {
        positivePercentage = positive/totalTweets * 100;
    }

    public int getPositivePercentage() {
        calculatePositiveTweets();
        return positivePercentage;
    }

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
