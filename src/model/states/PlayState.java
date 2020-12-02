package model.states;

import view.GamblerView;

public class PlayState implements RequestState {

    @Override
    public void handleState(GamblerView gamblerView) {
        gamblerView.disableAllesiIsEvenButton(true);
        gamblerView.disableSomIs21Button(true);
        gamblerView.disableHogerDanVorigeButton(true);
        gamblerView.disableStartGameButton(true);
        gamblerView.disableConfirmChoiceButton(true);
    }
}
