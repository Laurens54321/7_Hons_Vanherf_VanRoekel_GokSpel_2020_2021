package application;
	
import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.GamblerView;

public class GokSpelMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		GameController gameController= new GameController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
