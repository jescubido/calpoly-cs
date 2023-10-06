import java.util.*;

public class Student {
    private String studentID = UUID.randomUUID().toString();
    private List<String> answers = new ArrayList<String>();

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /*
     * return the student's unique ID number.
     */
    public String getStudentID(){
        return studentID;
    }

    /*
     * Adds a student's answer to their answer list.
     */
    public void addAnswers(String answers) {
        this.answers.add(answers);
    }

    /*
     * Method to obtain answers of the student.
     */
    public List<String> getAnswers() {
        return answers;
    }

    /*
     * Displays the answer the students selects.
     */
    public void printAnswers() {
        System.out.println(getAnswers());
    }

}