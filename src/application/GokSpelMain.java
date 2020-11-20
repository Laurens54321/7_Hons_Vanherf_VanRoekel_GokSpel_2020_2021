package application;
	
import controller.AdminController;
import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.GamblerView;

public class GokSpelMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		AdminController adminController = new AdminController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
