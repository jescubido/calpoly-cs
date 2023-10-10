/*
 * @author Jarisse Escubido
 */

public class MultipleChoiceQuestion extends Question {

    /*
     * MultipleSelectionQuestion extends Question class to include questions that require
     * multiple answers.
     * Adds the selections to a list.
     */
    @Override
    public void addSelections(String selections, boolean correctAnswer) {
        this.selections.add(selections);
        if(correctAnswer)
            this.answers.add(selections);
    }
    
}
