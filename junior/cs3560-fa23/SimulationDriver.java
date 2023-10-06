import java.util.HashSet;
import java.util.Set;

/*
 * class containing main to simulate voting.
 */
public class SimulationDriver {
    public static void main(String[] args) {

        Set<Student> classSize = generateStudents(30);
        Set<Question> questionSet = generateQuestions();

    }

    private static Set<Student> generateStudents(int numOfStudents) {

        // If numStudents is not positive, we can't generate any students
        if (numOfStudents <= 0){
            throw new IllegalArgumentException("numOfStudents must be a positive integer");
        }

        Set<Student> classSize = new HashSet<Student>();
        
        // Add students to student set
        for(int i = 0; i < numOfStudents; i++){
            classSize.add(new Student());
        }
        
        // Return the student set
        return classSize;
    }

    private static Set<Question> generateQuestions() {
        // generate single or multiple choice question examples
        Question q1 = new SingleChoiceQuestion();
        q1.setQuestion("What is the last color of the rainbow?");
        q1.addChoices("Red", false);
        q1.addChoices("Indigo", true);
        q1.addChoices("Purple", false);
        q1.addChoices("Yellow", false);
        

        // Adds question to question set.
        Set<Question> questionSet = new HashSet<Question>();
        questionSet.add(q1);
        return questionSet;
    }
}
