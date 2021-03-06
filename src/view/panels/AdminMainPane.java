package view.panels;


import controller.AdminMainController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class AdminMainPane extends BorderPane {
    AdminMainController adminMainController;

    TabPane tabPane;


    public AdminMainPane(AdminMainController adminMainController){
        this.adminMainController = adminMainController;
	    tabPane = new TabPane();
        this.setCenter(tabPane);
	}

	public void addTab(Tab tab){
        System.out.println("added a tab");
        tabPane.getTabs().add(tab);
        this.setCenter(tabPane);
    }
}
