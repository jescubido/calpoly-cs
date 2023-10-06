public class MultipleChoiceQuestion extends Question {

    /*
     * MultipleChoiceQuestion extends Question class to include questions that require
     * multiple answers.
     * Adds the choices to a list.
     */
    @Override
    public void addChoices(String choices, boolean correctAnswer) {
        this.choicesList.add(choices);
        if(correctAnswer)
            this.answerList.add(choices);
    }
    
}
