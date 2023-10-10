/*
 * @author Jarisse Escubido
 */

public class SingleChoiceQuestion extends Question{

    /*
     * SingleChoiceQuestion class extends Question class to include questions
     * with only one answer.
     * answerList must only contain one correct answer.
     */
    @Override
    public void addSelections(String selections, boolean correctAnswer) {
        this.selections.add(selections);
        if(correctAnswer && answers.isEmpty()) {
            this.answers.add(selections);
        }
        else if (correctAnswer && !answers.isEmpty()) {
            System.out.println("Error: single choice question must have only one answer!");
        }
    }
    
}
