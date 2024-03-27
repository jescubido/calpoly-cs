import java.util.*;

/*
 * @author Jarisse Escubido
 */

public class VotingService {
    private Map<String, List<String>> classAnswers = new Hashtable<String,List<String>>();;
    public Question ques;

    VotingService(Question ques) {
        this.ques = ques;
    }

    /*
     * Puts all the student's answer, with unique ID, to a set.
     */
    public void addStudentAnswers(String studentID, List<String> answers) {
        this.classAnswers.put(studentID, answers);
    }

    /*
     * Prints the question and corresponding question.
     */
    public void askQuestion() {
        this.ques.printQuestion();
        this.ques.printSelections();
    }

    /*
     * Calculates the number of right and wrong answers.
     */
    public void statistics() {
        List<String> answers = this.ques.answers;
        int right = 0;
        int wrong = 0;

        for (List<String> studentAnswers : classAnswers.values()) {
            if(studentAnswers.equals(answers)) {
                right++;
            }
            else {
                wrong++;
            }
        }
        System.out.println("\nThe correct answer is: " + answers);
        System.out.println("# Students Correct: " + right);
        System.out.println("# Students Incorrect: " + wrong);

    }
}
