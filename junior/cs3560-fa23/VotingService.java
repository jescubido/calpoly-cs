import java.util.*;

public class VotingService {
    private Map<UUID, List<String>> classAnswers;
    private Set<Question> questionSet;
    public Question ques;

    VotingService(Question ques) {
        this.ques = ques;
    }

    /*
     * Puts all the student's answer, with unique ID, to a set.
     */
    public void addStudentAnswers(UUID studentID, List<String> answerList) {
        this.classAnswers.put(studentID, answerList);
    }

    /*
     * Prints the question and corresponding question.
     */
    public void askQuestion() {
        this.ques.printQuestion();
        this.ques.printChoices();
    }

    /*
     * Calculates the number of right and wrong answers.
     */
    public void statistics() {
        List<String> answerList = this.ques.answerList;
        int right = 0;
        int wrong = 0;
        for (List<String> studentAnswers : classAnswers.values()) {
            if(studentAnswers.equals(answerList)) {
                right++;
            }
            else {
                wrong++;
            }
        }
    }
}
