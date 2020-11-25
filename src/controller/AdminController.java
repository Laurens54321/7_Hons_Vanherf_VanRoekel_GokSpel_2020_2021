package controller;

import model.database.PlayerDB;
import view.AdminView;
import view.GamblerView;

public class AdminController {

    private AdminView adminView;
    private PlayerDB playerDB;

    public AdminController(PlayerDB playerDB){
        this.playerDB = playerDB;
        playerDB.loadPlayers();

        GameController gameController = new GameController();
        gameController.setPlayerDB(playerDB);

        adminView = new AdminView();
        adminView.setBorderPane(gameController);
    }

}
