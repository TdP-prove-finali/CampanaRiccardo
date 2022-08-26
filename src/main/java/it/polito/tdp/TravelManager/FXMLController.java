package it.polito.tdp.TravelManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.TravelManager.model.Aeroporto;
import it.polito.tdp.TravelManager.model.AirBnB;
import it.polito.tdp.TravelManager.model.Itinerario;
import it.polito.tdp.TravelManager.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class FXMLController {
	
	Model model;
	String arrival_city;
	Itinerario choice;
	AirBnB choiceBnB;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Itinerario, String> clFare;

    @FXML
    private TableColumn<Itinerario, String> clItinerary;
    
    @FXML
    private Button btnSearchAirBnBs;
    
    @FXML
    private Button btnClearAirBnBs;

    @FXML
    private TableColumn<AirBnB, String> clNome;

    @FXML
    private TableColumn<AirBnB, Integer> clOspiti;

    @FXML
    private TableColumn<AirBnB, Integer> clPrezzo;

    @FXML
    private TableColumn<Itinerario, String> clPrices;

    @FXML
    private TableColumn<AirBnB, String> clQuartiere;

    @FXML
    private TableColumn<AirBnB, Integer> clRating;

    @FXML
    private TableColumn<AirBnB, Integer> clRecensioni;

    @FXML
    private TableColumn<Itinerario, String> clStops;

    @FXML
    private TableColumn<AirBnB, String> clTipo;

    @FXML
    private ComboBox<String> cmbArrivo;

    @FXML
    private ComboBox<String> cmbPartenza;

    @FXML
    private ComboBox<String> cmbRating;

    @FXML
    private ComboBox<String> cmbRecensioni;

    @FXML
    private ComboBox<String> cmbScali;

    @FXML
    private ComboBox<String> cmbTipo;

    @FXML
    private Label lblErroreAirBnB;

    @FXML
    private Label lblErroreVolo;

    @FXML
    private TableView<AirBnB> tblBnB;

    @FXML
    private TableView<Itinerario> tblVolo;

    @FXML
    private TextField txtOspiti;

    @FXML
    private TextField txtPrezzoBnB;

    @FXML
    private TextField txtPrezzoVolo;
    
    @FXML
    private TextField txtTotal;
    
    @FXML
    void handleBnBChoice(MouseEvent event) {

    }

    @FXML
    void handleCercaBnBs(ActionEvent event) {
    	choiceBnB = tblBnB.getSelectionModel().getSelectedItem();
        
    	if (choiceBnB != null){
    		
    		//aggiungo a total il prezzo del bnb ricordando che devo fare l'esponenziale
    		
    		txtTotal.setText("");
    	}
    }

    @FXML
    void handleCercaVoli(ActionEvent event) {
    	cmbRating.setDisable(true);
		txtPrezzoBnB.setDisable(true);
		cmbTipo.setDisable(true);
		txtOspiti.setDisable(true);
		cmbRecensioni.setDisable(true);
		btnClearAirBnBs.setDisable(true);
		btnSearchAirBnBs.setDisable(true);
		tblBnB.setDisable(true);
		
		lblErroreVolo.setTextFill(Color.RED);
    	
    	String departure = null;
    	String arrival = null;
    	double price = 0;
    	int stops = -1;
    	
    	if(cmbPartenza.getValue() != null && cmbPartenza.getValue() != "") {
    		departure = cmbPartenza.getValue();
    	} else {
    		lblErroreVolo.setText("Select a departure Airport");
    	}
    	
    	if(cmbArrivo.getValue() != null && cmbArrivo.getValue() != "") {
    		arrival = cmbArrivo.getValue();
    	} else {
    		lblErroreVolo.setText("Select an arrival Airport");
    	}
    	
    	try {
    		if(txtPrezzoVolo.getText() != null) {
    			price = Integer.parseInt(txtPrezzoVolo.getText());
    		}
    	} catch(NumberFormatException e) {
    		lblErroreVolo.setText("Price option not valid");
    	}
    	
    	if(cmbScali.getValue() != null && cmbScali.getValue() != "") {
    		String Sstops = cmbScali.getValue();
    		if(Sstops.compareTo("Non-Stop") == 0) {
    			stops = 0;
    		}
    		if(Sstops.compareTo("1 Stop") == 0) {
    			stops = 1;
    		}
    		if(Sstops.compareTo("2 Stops") == 0) {
    			stops = 2;
    		}
    		
    	} else {
    		lblErroreVolo.setText("Select how many stops you desire");
    	}
    	
    	if(!departure.isBlank() && !arrival.isBlank() && price != 0 && stops <= 2 && stops >= 0) {
    		lblErroreVolo.setText("");
    		List<Itinerario> result = this.model.percorso(departure, arrival, stops, price);
    		
    		if(!result.isEmpty()) {
    			
    			lblErroreVolo.setTextFill(Color.BLACK);
    			lblErroreVolo.setText("Select your choice");
    			
    			if(this.model.getMappaNomi().get(arrival).getCity().compareTo("Los Angeles") == 0 
        				|| this.model.getMappaNomi().get(arrival).getCity().compareTo("New York") == 0 
        				|| this.model.getMappaNomi().get(arrival).getCity().compareTo("San Francisco") == 0
        				|| this.model.getMappaNomi().get(arrival).getCity().compareTo("Chicago") == 0
        				|| this.model.getMappaNomi().get(arrival).getCity().compareTo("Boston") == 0
        				|| this.model.getMappaNomi().get(arrival).getCity().compareTo("Washington D.C.") == 0) {
        			
    				arrival_city = this.model.getMappaNomi().get(arrival).getCity();
    				this.model.loadBnBs();
    				
        			cmbRating.setDisable(false);
        			txtPrezzoBnB.setDisable(false);
        			cmbTipo.setDisable(false);
        			txtOspiti.setDisable(false);
        			cmbRecensioni.setDisable(false);
        			btnClearAirBnBs.setDisable(false);
        			btnSearchAirBnBs.setDisable(false);
        			tblBnB.setDisable(false);
        			
        		}
    		} else {
    			lblErroreVolo.setText("No results in the Database");
    		}
    		
    		Collections.sort(result);
    		
    		clFare.setCellValueFactory(new PropertyValueFactory<Itinerario, String>("fare"));
    		clStops.setCellValueFactory(new PropertyValueFactory<Itinerario, String>("stops"));
    		clItinerary.setCellValueFactory(new PropertyValueFactory<Itinerario, String>("itinerary"));
    		clPrices.setCellValueFactory(new PropertyValueFactory<Itinerario, String>("prices"));
    		
    		tblVolo.setItems(FXCollections.observableArrayList(result));
    	}
    }

    @FXML
    void handleFlightChoice(MouseEvent event) {
    	choice = tblVolo.getSelectionModel().getSelectedItem();
        
    	if (choice != null){
    		txtTotal.setText(choice.getFare());
    	}
    }
    
    @FXML
    void handleClearAirBnB(ActionEvent event) {
    	cmbTipo.getSelectionModel().clearSelection();
    	cmbRating.getSelectionModel().clearSelection();
    	cmbRecensioni.getSelectionModel().clearSelection();
    	txtPrezzoBnB.clear();
    	txtOspiti.clear();
    	tblBnB.getItems().clear();
    	txtTotal.clear();
    }

    @FXML
    void handleClearVoli(ActionEvent event) {
    	txtPrezzoVolo.clear();
    	cmbScali.getSelectionModel().clearSelection();
    	cmbPartenza.getSelectionModel().clearSelection();
    	cmbArrivo.getSelectionModel().clearSelection();
    	tblVolo.getItems().clear();
    	txtTotal.clear();
    	
    	lblErroreVolo.setTextFill(Color.RED);
    	
    	cmbRating.setDisable(true);
		txtPrezzoBnB.setDisable(true);
		cmbTipo.setDisable(true);
		txtOspiti.setDisable(true);
		cmbRecensioni.setDisable(true);
		btnClearAirBnBs.setDisable(true);
		btnSearchAirBnBs.setDisable(true);
		tblBnB.setDisable(true);
    }

    @FXML
    void initialize() {
    	assert btnClearAirBnBs != null : "fx:id=\"btnClearAirBnBs\" was not injected: check your FXML file 'Scene.fxml'.";
    	assert btnSearchAirBnBs != null : "fx:id=\"btnSearchAirBnBs\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clFare != null : "fx:id=\"clFare\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clItinerary != null : "fx:id=\"clItinerary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clNome != null : "fx:id=\"clNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clOspiti != null : "fx:id=\"clOspiti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPrezzo != null : "fx:id=\"clPrezzo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPrices != null : "fx:id=\"clPrices\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRating != null : "fx:id=\"clRating\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRecensioni != null : "fx:id=\"clRecensioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clStops != null : "fx:id=\"clStops\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clTipo != null : "fx:id=\"clTipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRating != null : "fx:id=\"cmbRating\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRecensioni != null : "fx:id=\"cmbRecensioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbScali != null : "fx:id=\"cmbScali\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbTipo != null : "fx:id=\"cmbTipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErroreAirBnB != null : "fx:id=\"lblErroreAirBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErroreVolo != null : "fx:id=\"lblErroreVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblBnB != null : "fx:id=\"tblBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblVolo != null : "fx:id=\"tblVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOspiti != null : "fx:id=\"txtOspiti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrezzoBnB != null : "fx:id=\"txtPrezzoBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrezzoVolo != null : "fx:id=\"txtPrezzoVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTotal != null : "fx:id=\"txtTotal\" was not injected: check your FXML file 'Scene.fxml'.";


    }

    public void setModel(Model model) {
	    this.model = model;
		
		this.model.loadAll();
		
		List<String> temp = new ArrayList<String>();
		for(Aeroporto a : this.model.getAeroporti()) {
			temp.add(a.getName());
		}
		
		Collections.sort(temp);
		cmbPartenza.getItems().addAll(temp);
		cmbArrivo.getItems().addAll(temp);
		
		cmbScali.getItems().add("Non-Stop");
		cmbScali.getItems().add("1 Stop");
		cmbScali.getItems().add("2 Stops");
		
		cmbTipo.getItems().addAll(this.model.loadTypes());
		
		for(int i=0; i<100; i+=10) {
			cmbRating.getItems().add(i + "+");
		}
		
		for(int j=0; j<=600; j+=25) {
			cmbRecensioni.getItems().add(j + "+");
		}
		
		
    }
    
}
