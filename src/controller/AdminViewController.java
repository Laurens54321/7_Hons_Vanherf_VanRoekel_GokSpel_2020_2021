package controller;

import model.database.PlayerDB;
import view.AdminView;
import view.GamblerView;
import view.panels.AdminMainPane;

public class AdminViewController {

    private AdminView adminView;
    private AdminMainController adminMainController;

    private PlayerDB playerDB;

    public AdminViewController(PlayerDB playerDB){
        this.playerDB = playerDB;
        playerDB.loadPlayers();

        GameController gameController = new GameController();
        gameController.setPlayerDB(playerDB);


        adminView = new AdminView(this);
        adminMainController = new AdminMainController(this, playerDB);
        adminView.setAdminPane(adminMainController.getAdminMainPane());

    }
}
