import java.util.*;

/*
 * Question class includes everything dealing with all the questions
 * and answers needed to simulate voting system.
 */
public abstract class Question{
    
    protected String question;
    List<String> choicesList = new ArrayList<String>();
    List<String> answerList = new ArrayList<String>();

    /*
     * creates Question object
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /*
     * Displays the question to the console.
     */
    public void printQuestion() {
        System.out.println(question);
    }

    /*
     * Displays the choices/answers to the question to the console.
     */
    public void printChoices() {
        for (int i = 0; i < choicesList.size(); i++) {
            System.out.println(choicesList.get(i));
        }
    }

    /*
     * method to add choices to a question.
     */
    public abstract void addChoices(String choices, boolean correctAnswer);

    public List<String> getChoices() {
        return this.choicesList;
    }

    public List<String> getAnswers() {
        return this.answerList;
    }

}