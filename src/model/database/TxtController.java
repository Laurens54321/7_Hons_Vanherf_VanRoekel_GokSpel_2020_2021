package model.database;

import model.Player;


public class TxtController implements PlayerObserver {
    private TxtReader reader;

    public TxtController(){
        reader = new TxtReader();
    }

    @Override
    public void update(PlayerDB db) {
        db.setDB(reader.read());
    }
}
