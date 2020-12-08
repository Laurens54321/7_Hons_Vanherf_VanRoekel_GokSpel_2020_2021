package view.panels;


import controller.GamblerOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Player;


public class GamblerOverviewPane extends GridPane{
	private ObservableList<Player> players;
	private TableView<Player> table;
	private GamblerOverviewController gamblerOverviewController;
	
	
	public GamblerOverviewPane(GamblerOverviewController gamblerOverviewController) {
		this.gamblerOverviewController = gamblerOverviewController;


        Label lblHeading = new Label ("Player List: ");
        lblHeading.setFont(new Font("Arial", 20));

    	table = new TableView<Player>();
    	refresh();

		TableColumn<Player, String> colFirstName = new TableColumn<Player, String>("First name");
		colFirstName.setMinWidth(100);
		colFirstName.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));

		TableColumn<Player, String> colLastName = new TableColumn<>("Last name");
		colLastName.setMinWidth(100);
		colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));

		TableColumn<Player, String> colUserid = new TableColumn<>("userid");
		colUserid.setMinWidth(100);
		colUserid.setCellValueFactory(new PropertyValueFactory<Player, String>("userid"));

		TableColumn<Player, Integer> colMoney = new TableColumn<>("money");
		colMoney.setMinWidth(100);
		colMoney.setCellValueFactory(new PropertyValueFactory<Player, Integer>("money"));

		table.getColumns().addAll(colFirstName, colLastName, colUserid, colMoney);

		this.add(table, 5, 5, 5, 5);
	}

	public void refresh(){
		players = FXCollections.observableArrayList(gamblerOverviewController.getPlayers());
		table.setItems(players);
		table.refresh();
	}
}
