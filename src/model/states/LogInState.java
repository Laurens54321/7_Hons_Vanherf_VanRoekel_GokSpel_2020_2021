package model.states;

import controller.GamblerController;
import view.GamblerView;

public class LogInState implements RequestState {
    @Override
    public void handleState(GamblerView gamblerView) {
        gamblerView.disableAllesiIsEvenButton(true);
        gamblerView.disableSomIs21Button(true);
        gamblerView.disableHogerDanVorigeButton(true);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableConfirmChoiceButton(true);
    }
}
