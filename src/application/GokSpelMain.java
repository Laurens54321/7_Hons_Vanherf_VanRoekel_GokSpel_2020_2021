package application;
	
import controller.AdminController;
import controller.GamblerController;
import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.PlayerDB;
import view.AdminView;
import view.GamblerView;

public class GokSpelMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		PlayerDB playerDB = new PlayerDB();

		AdminController adminController = new AdminController(playerDB);
		GamblerController gamblerController = new GamblerController(playerDB);
		playerDB.addObserver(gamblerController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
