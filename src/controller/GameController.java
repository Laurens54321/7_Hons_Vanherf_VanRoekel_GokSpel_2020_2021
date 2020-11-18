package controller;

import model.database.PlayerDB;
import model.database.TxtController;
import view.AdminView;
import view.GamblerView;

public class GameController {
    public GameController(){
        PlayerDB playerDB= new PlayerDB();
        TxtController txtController = new TxtController();
        txtController.update(playerDB);

        AdminView adminView = new AdminView(playerDB);
        GamblerView gamblerView = new GamblerView();
    }
}
