import java.util.*;

/*
 * Question class includes everything dealing with all the questions
 * and answers needed to simulate voting system.
 * 
 * @author Jarisse Escubido
 */
public abstract class Question{
    
    protected String question;
    List<String> selections = new ArrayList<String>();
    List<String> answers = new ArrayList<String>();

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
    public void printSelections() {
        for (int i = 0; i < selections.size(); i++) {
            System.out.println(selections.get(i));
        }
    }

    /*
     * method to add choices to a question.
     */
    public abstract void addSelections(String selections, boolean correctAnswer);

    public List<String> getSelections() {
        return this.selections;
    }

    public List<String> getAnswers() {
        return this.answers;
    }

}