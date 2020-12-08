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

		GamblerController gamblerController = new GamblerController(playerDB);
		AdminViewController adminViewController = new AdminViewController(playerDB, gamblerController);

		playerDB.addObserver(gamblerController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
