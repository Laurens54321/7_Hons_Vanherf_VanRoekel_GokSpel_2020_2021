package view.observer;

import model.database.PlayerDB;

public interface PlayerObserver {
    public void update(PlayerDB db);
}
