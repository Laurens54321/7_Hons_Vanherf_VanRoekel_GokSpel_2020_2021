package model.states;

import view.GamblerView;

public class BetState implements RequestState {
    @Override
    public void handleState(GamblerView gamblerView) {
        gamblerView.disableAllesiIsEvenButton(true);
        gamblerView.disableSomIs21Button(true);
        gamblerView.disableHogerDanVorigeButton(true);
        gamblerView.disableStartGameButton(false);
        gamblerView.disableConfirmChoiceButton(true);
    }
}
