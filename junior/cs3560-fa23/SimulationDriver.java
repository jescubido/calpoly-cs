/*
 * class containing main to simulate voting.
 * @author Jarisse Escubido
 */
public class SimulationDriver {
    public static void main(String[] args) {

        /*
         * Add students to a list.
         * Generate questions for the voting simulation.
         */
        Student[] classSize = generateStudents(30);
        Question q1 = new SingleChoiceQuestion();
        q1.setQuestion("Question 1: What is the last color of the rainbow?");
        q1.addSelections("A. Red", false);
        q1.addSelections("B. Indigo", true);
        q1.addSelections("C. Purple", false);
        q1.addSelections("D. Yellow", false);
        System.out.println("");

        classSize = generateStudentAnswers(classSize, q1);
        runSimulation(q1, classSize);

        Question q2 = new MultipleChoiceQuestion();
        q2.setQuestion("\n\nQuestion 2: Which months have 31 days?");
        q2.addSelections("A. January", true);
        q2.addSelections("B. February", false);
        q2.addSelections("C. November", false);
        q2.addSelections("D. December", true);
        
        classSize = generateStudentAnswers(classSize, q2);
        runSimulation(q2, classSize);
    }

    /*
     * Creates simulation for question.
     * Asks the question to the console.
     * Adds all students answers to a list along with their unique ID number.
     * Prints statistics of each question to console.
     */
    private static void runSimulation(Question q1, Student[] classSize) {
        VotingService sim = new VotingService(q1);
        sim.askQuestion();

        for (int i = 0; i < classSize.length; i++) {
			sim.addStudentAnswers(classSize[i].getStudentID(), classSize[i].getAnswers());
		}
        sim.statistics();
    }

    /*
     * Randomly chooses answers for each student.
     */
    private static Student[] generateStudentAnswers(Student[] classSize, Question q1) {
        if (q1.getClass().toString().contains("SingleChoiceQuestion")) {
			for(int i = 0; i < classSize.length; i++){
				classSize[i].addAnswers(q1.selections.get((int)(Math.random()*q1.selections.size())));
			}
        } else if (q1.getClass().toString().contains("MultipleChoiceQuestion")) {
			for (int i = 0; i < classSize.length; i++) {
				for (String selection : q1.getSelections()) {
					if (((Math.random() * 100) % 4) == 0)
						classSize[i].addAnswers(selection);
				}
			}

		}
		return classSize;
    }

    /*
     * Creates number of students initialized for the simulation.
     */
    private static Student[] generateStudents(int students) {
        Student classSize[] = new Student[students];
		for (int i = 0; i < students; i++) {
			classSize[i] = new Student();
		}
		return classSize;

	}
}
