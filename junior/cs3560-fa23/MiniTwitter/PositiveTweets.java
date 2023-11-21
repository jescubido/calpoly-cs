/**
 * Name: Escubido, Jarisse
 * Assignment: #2
 * Due: 14 November 2023
 * Course: cs-3560-01-fa23
 * 
 * Description:
 *      Implementing a Java-based Mini Twitter with graphical user interface (GUI) using Java Swing.
 */

public class PositiveTweets implements Visitable {

    private Analytics analytics = new Analytics();
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    /*
     * Determines whethere a tweet contins positive words.
     */
    public boolean isPositive (String tweet){
		String message = tweet.toLowerCase();
		
		if(message.contains("good")){
			return true;
		}
		else if(message.contains("great")){
			return true;
		}
        else if(message.contains("yay")){
			return true;
		}
		else if(message.contains("awesome")){
			return true;
		}
        else if(message.contains("hooray")){
			return true;
		}
		else if(message.contains("nice")){
			return true;
		}
		else if(message.contains("happy")){
			return true;
		}
		else
			return false;
	}

    public void checkTweet(String tweet){
		if(isPositive(tweet)){
			accept(analytics);
		}
	}
}
