package view.panels;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class GamblerOverviewPane extends GridPane{
	//private TableView<Speler> table;
	
	
	public GamblerOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("Spelers:"), 0, 0, 1, 1);		
	}	
}
