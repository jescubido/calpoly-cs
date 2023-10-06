public class SingleChoiceQuestion extends Question{

    /*
     * SingleChoiceQuestion class extends Question class to include questions
     * with only one answer.
     * answerList must only contain one correct answer.
     */
    @Override
    public void addChoices(String choices, boolean correctAnswer) {
        this.choicesList.add(choices);
        if(correctAnswer)
            this.answerList.add(choices);
        else
            System.out.println("Error: single choice question must have only one answer!");
    }
    
}
