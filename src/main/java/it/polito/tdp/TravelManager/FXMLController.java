package it.polito.tdp.TravelManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.TravelManager.model.Aeroporto;
import it.polito.tdp.TravelManager.model.Itinerario;
import it.polito.tdp.TravelManager.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Aeroporto, String> clAeroportoArrivo;

    @FXML
    private TableColumn<Aeroporto, String> clAeroportoPartenza;

    @FXML
    private TableColumn<Aeroporto, String> clCittaArrivo;

    @FXML
    private TableColumn<Aeroporto, String> clCittaPartenza;
    
    @FXML
    private TableColumn<Itinerario, Aeroporto> clArrival;
    
    @FXML
    private TableColumn<Itinerario, Aeroporto> clDeparture;
    
    @FXML
    private TableColumn<Itinerario, Double> clPrezzoVolo;
    
    @FXML
    private TableColumn<Itinerario, Double> clScali;

    @FXML
    private TableColumn<Aeroporto, String> clStatoArrivo;

    @FXML
    private TableColumn<Aeroporto, String> clStatoPartenza;

    @FXML
    private TableColumn<?, ?> clNome;

    @FXML
    private TableColumn<?, ?> clOspiti;

    @FXML
    private TableColumn<?, ?> clPrezzo;

    @FXML
    private TableColumn<?, ?> clQuartiere;

    @FXML
    private TableColumn<?, ?> clRating;

    @FXML
    private TableColumn<?, ?> clRecensioni;

    @FXML
    private TableColumn<?, ?> clTipo;

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
    private TableView<?> tblBnB;

    @FXML
    private TableView<Itinerario> tblVoli;

    @FXML
    private TextField txtOspiti;

    @FXML
    private TextField txtPrezzoBnB;

    @FXML
    private TextField txtPrezzoVolo;

    @FXML
    void handleCercaVoli(ActionEvent event) {
    	String departure = null;
    	String arrival = null;
    	double price = 0;
    	int stops = -1;
    	
    	if(cmbPartenza.getValue() != null) {
    		departure = cmbPartenza.getValue();
    	} else {
    		lblErroreVolo.setText("Select a departure Airport");
    	}
    	
    	if(cmbArrivo.getValue() != null) {
    		arrival = cmbPartenza.getValue();
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
    	
    	if(cmbScali.getValue() != null) {
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
    		List<Itinerario> result = this.model.percorso(departure, arrival, stops, price);
       		
    		clDeparture.setCellValueFactory(new PropertyValueFactory<Itinerario, Aeroporto>("Departure"));
    		clArrival.setCellValueFactory(new PropertyValueFactory<Itinerario, Aeroporto>("Arrival"));
    		clStatoPartenza.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("state"));
    		clCittaPartenza.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("city"));
    		clAeroportoPartenza.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("IATA"));
    		clStatoArrivo.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("state"));
    		clCittaArrivo.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("city"));
    		clAeroportoArrivo.setCellValueFactory(new PropertyValueFactory<Aeroporto, String>("IATA"));
    		clPrezzoVolo.setCellValueFactory(new PropertyValueFactory<Itinerario, Double>("prezzo"));
    		clScali.setCellValueFactory(new PropertyValueFactory<Itinerario, Double>("stops"));
    		
    		tblVoli.setItems(FXCollections.observableArrayList(result));
    		
    	}
    	
    	
    }
    
    @FXML
    void handleCercaBnBs(ActionEvent event) {

    }
    
    @FXML
    void handleClearAirBnB(ActionEvent event) {

    }

    @FXML
    void handleClearVoli(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	assert clDeparture != null : "fx:id=\"clDeparture\" was not injected: check your FXML file 'Scene.fxml'.";
    	assert clArrival != null : "fx:id=\"clArrival\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clAeroportoArrivo != null : "fx:id=\"clAeroportoArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clAeroportoPartenza != null : "fx:id=\"clAeroportoPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clCittaArrivo != null : "fx:id=\"clCittaArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clCittaPartenza != null : "fx:id=\"clCittaPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clNome != null : "fx:id=\"clNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clOspiti != null : "fx:id=\"clOspiti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPrezzo != null : "fx:id=\"clPrezzo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPrezzoVolo != null : "fx:id=\"clPrezzoVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRating != null : "fx:id=\"clRating\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRecensioni != null : "fx:id=\"clRecensioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clScali != null : "fx:id=\"clScali\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clStatoArrivo != null : "fx:id=\"clStatoArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clStatoPartenza != null : "fx:id=\"clStatoPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clTipo != null : "fx:id=\"clTipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRating != null : "fx:id=\"cmbRating\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbRecensioni != null : "fx:id=\"cmbRecensioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbScali != null : "fx:id=\"cmbScali\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbTipo != null : "fx:id=\"cmbTipo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblBnB != null : "fx:id=\"tblBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblVoli != null : "fx:id=\"tblVoli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOspiti != null : "fx:id=\"txtOspiti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrezzoBnB != null : "fx:id=\"txtPrezzoBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrezzoVolo != null : "fx:id=\"txtPrezzoVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErroreAirBnB != null : "fx:id=\"lblErroreAirBnB\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErroreVolo != null : "fx:id=\"lblErroreVolo\" was not injected: check your FXML file 'Scene.fxml'.";
        
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
    }

}
