package view.panels;


import controller.GameController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.database.PlayerDB;
import view.panels.GamblerOverviewPane;
import view.panels.InstellingPane;
import view.panels.SpelVerloopPane;
import view.panels.StatistiekPane;

public class AdminMainPane extends BorderPane {
	public AdminMainPane(GameController db){
	    TabPane tabPane = new TabPane();

        SpelVerloopPane spelVerloopPane = new SpelVerloopPane();
        GamblerOverviewPane gamblerOverviewPane = new GamblerOverviewPane(db);
        InstellingPane instellingPane = new InstellingPane();
        StatistiekPane statistiekPane = new StatistiekPane();


	    Tab spelVerloopTab = new Tab("Spelverloop", spelVerloopPane);
        Tab spelerTab = new Tab("Spelers", gamblerOverviewPane);
        Tab instellingTab = new Tab("Instellingen", instellingPane);
        Tab statistiekTab = new Tab("Statistieken", statistiekPane);
        tabPane.getTabs().add(spelVerloopTab);
        tabPane.getTabs().add(spelerTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}
}
