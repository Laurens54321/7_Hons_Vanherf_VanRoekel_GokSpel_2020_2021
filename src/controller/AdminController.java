package controller;

import model.database.PlayerDB;
import view.AdminView;
import view.GamblerView;

public class AdminController {
    private PlayerDB playerDB;
    private AdminView adminView;

    public AdminController(){
        playerDB = new PlayerDB();
        playerDB.loadPlayers();

        GameController gameController = new GameController();
        gameController.setPlayerDB(playerDB);

        adminView = new AdminView();
        adminView.setBorderPane(gameController);
        GamblerView gamblerView = new GamblerView();
    }

}
