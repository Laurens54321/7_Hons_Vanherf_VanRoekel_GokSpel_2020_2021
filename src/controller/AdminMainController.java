package controller;

import javafx.scene.control.Tab;
import model.database.PlayerDB;
import view.panels.*;

public class AdminMainController {
    private AdminViewController adminViewController;
    private AdminMainPane adminMainPane;

    private PlayerDB playerDB;

    private SpelVerloopPane spelVerloopPane;
    private GamblerOverviewController gamblerOverviewController;
    private InstellingPane instellingPane;
    private StatistiekPane statistiekPane;


    public AdminMainController(AdminViewController adminViewController, PlayerDB playerDB){
        this.adminViewController = adminViewController;
        adminMainPane = new AdminMainPane(this);


        this.playerDB = playerDB;



        spelVerloopPane = new SpelVerloopPane();
        gamblerOverviewController = new GamblerOverviewController(playerDB);
        instellingPane = new InstellingPane();
        statistiekPane = new StatistiekPane();

        Tab spelVerloopTab = new Tab("Spelverloop", spelVerloopPane);
        Tab spelerTab = new Tab("Spelers", gamblerOverviewController.getGamblerOverviewPane());
        Tab instellingTab = new Tab("Instellingen", instellingPane);
        Tab statistiekTab = new Tab("Statistieken", statistiekPane);

        adminMainPane.addTab(spelVerloopTab);
        adminMainPane.addTab(spelerTab);
        adminMainPane.addTab(instellingTab);
        adminMainPane.addTab(statistiekTab);
    }

    public AdminMainPane getAdminMainPane() {
        return adminMainPane;
    }
}
