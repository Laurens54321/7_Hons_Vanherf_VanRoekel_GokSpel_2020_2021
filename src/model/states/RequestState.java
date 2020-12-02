package model.states;

import controller.GamblerController;
import view.GamblerView;

public interface RequestState {
    public void handleState(GamblerView gamblerView);
}
