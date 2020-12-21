package controller;

import model.database.PlayerDB;
import view.AdminView;
import view.GamblerView;
import view.panels.AdminMainPane;

public class AdminViewController {

    private AdminView adminView;
    private AdminMainController adminMainController;
    private GamblerController gamblerController;

    private PlayerDB playerDB;

    public AdminViewController(PlayerDB playerDB, GamblerController gamblerController){
        this.playerDB = playerDB;
        playerDB.loadPlayers();

        this.gamblerController = gamblerController;

        adminView = new AdminView(this);
        adminMainController = new AdminMainController(this, playerDB, gamblerController);
        adminView.setAdminPane(adminMainController.getAdminMainPane());
    }

    public GamblerController getGamblerController() {
        return gamblerController;
    }
}
