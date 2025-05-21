package ohm.softa.a07.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import jdk.incubator.vector.VectorOperators;
import ohm.softa.a07.model.Meal;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

	// use annotation to tie to component in XML
	@FXML
	private Button btnRefresh;

	@FXML
	private ListView<Meal> mealsList;

	@FXML
	private Button btnClose;

	@FXML
	private CheckBox chkVegetarian;

	private ObservableList<Meal> myList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set the event handler (callback)
		btnRefresh.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// create a new (observable) list and tie it to the view
				ObservableList<Meal> list = FXCollections.observableArrayList();
				mealsList.setItems(list);
			}
		});

		//muss man nicht in Labdafunktionen bzw. in der Initialize machen
		btnClose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Stage stage = (Stage) btnClose.getScene().getWindow();
				stage.close();
			}
		});

		chkVegetarian.selectedProperty().addListener((observable, oldValue, newValue) ->{
			if(newValue){
				Test();
			}
		});
	}

	private void Test(){
		ObservableList<Meal> meals = mealsList.getItems();
		for(Meal i : meals){
			if(i.isVegetarian()){
				myList.add(i);
			}
		}
	}
}
