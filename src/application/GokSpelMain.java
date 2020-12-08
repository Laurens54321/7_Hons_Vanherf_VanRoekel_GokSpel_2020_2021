package application;
	
import controller.AdminViewController;
import controller.GamblerController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.PlayerDB;

public class GokSpelMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		PlayerDB playerDB = new PlayerDB();

		AdminViewController adminViewController = new AdminViewController(playerDB);
		GamblerController gamblerController = new GamblerController(playerDB);
		playerDB.addObserver(gamblerController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
