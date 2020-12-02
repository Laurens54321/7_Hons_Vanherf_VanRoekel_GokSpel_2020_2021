package model.states;

import view.GamblerView;

public class ChooseState implements RequestState{
    @Override
    public void handleState(GamblerView gamblerView) {
        gamblerView.disableAllesiIsEvenButton(false);
        gamblerView.disableSomIs21Button(false);
        gamblerView.disableHogerDanVorigeButton(false);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableConfirmChoiceButton(false);
    }
}
