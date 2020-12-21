package controller;

import javafx.scene.control.Tab;
import model.database.PlayerDB;
import view.panels.*;

public class AdminMainController {
    private AdminViewController adminViewController;
    private AdminMainPane adminMainPane;

    private PlayerDB playerDB;

    private SpelVerloopController spelVerloopController;
    private GamblerOverviewController gamblerOverviewController;
    private InstellingController instellingController;
    private StatistiekController statistiekController;





    public AdminMainController(AdminViewController adminViewController, PlayerDB playerDB, GamblerController gamblerController){
        this.adminViewController = adminViewController;
        adminMainPane = new AdminMainPane(this);

        this.playerDB = playerDB;

        spelVerloopController = new SpelVerloopController(gamblerController);
        gamblerOverviewController = new GamblerOverviewController(playerDB);
        instellingController = new InstellingController(gamblerController);
        statistiekController = new StatistiekController(gamblerController);

        Tab spelVerloopTab = new Tab("Spelverloop", spelVerloopController.getSpelVerloopPane());
        Tab spelerTab = new Tab("Spelers", gamblerOverviewController.getGamblerOverviewPane());
        Tab instellingTab = new Tab("Instellingen", instellingController.getInstellingPane());
        Tab statistiekTab = new Tab("Statistieken", statistiekController.getStatistiekPane());

        adminMainPane.addTab(spelVerloopTab);
        adminMainPane.addTab(spelerTab);
        adminMainPane.addTab(instellingTab);
        adminMainPane.addTab(statistiekTab);
    }

    public AdminMainPane getAdminMainPane() {
        return adminMainPane;
    }
}
