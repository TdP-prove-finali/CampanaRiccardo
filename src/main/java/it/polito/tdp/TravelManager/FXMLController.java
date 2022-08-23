package it.polito.tdp.TravelManager;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.TravelManager.model.Aeroporto;
import it.polito.tdp.TravelManager.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> clAeroportoArrivo;

    @FXML
    private TableColumn<?, ?> clAeroportoPartenza;

    @FXML
    private TableColumn<?, ?> clCittaArrivo;

    @FXML
    private TableColumn<?, ?> clCittaBnB;

    @FXML
    private TableColumn<?, ?> clCittaPartenza;

    @FXML
    private TableColumn<?, ?> clNome;

    @FXML
    private TableColumn<?, ?> clOspiti;

    @FXML
    private TableColumn<?, ?> clPrezzo;

    @FXML
    private TableColumn<?, ?> clPrezzoVolo;

    @FXML
    private TableColumn<?, ?> clQuartiere;

    @FXML
    private TableColumn<?, ?> clRating;

    @FXML
    private TableColumn<?, ?> clRecensioni;

    @FXML
    private TableColumn<?, ?> clScali;

    @FXML
    private TableColumn<?, ?> clStatoArrivo;

    @FXML
    private TableColumn<?, ?> clStatoPartenza;

    @FXML
    private TableColumn<?, ?> clTipo;

    @FXML
    private ComboBox<String> cmbArrivo;

    @FXML
    private ComboBox<String> cmbPartenza;

    @FXML
    private ComboBox<?> cmbRating;

    @FXML
    private ComboBox<?> cmbRecensioni;

    @FXML
    private ComboBox<String> cmbScali;

    @FXML
    private ComboBox<?> cmbTipo;

    @FXML
    private TableView<?> tblBnB;

    @FXML
    private TableView<?> tblVoli;

    @FXML
    private TextField txtOspiti;

    @FXML
    private TextField txtPrezzoBnB;

    @FXML
    private TextField txtPrezzoVolo;

    @FXML
    void handleCercaVoli(ActionEvent event) {

    }
    
    @FXML
    void handleCercaBnBs(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert clAeroportoArrivo != null : "fx:id=\"clAeroportoArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clAeroportoPartenza != null : "fx:id=\"clAeroportoPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clCittaArrivo != null : "fx:id=\"clCittaArrivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clCittaBnB != null : "fx:id=\"clCittaBnB\" was not injected: check your FXML file 'Scene.fxml'.";
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

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.model.loadAll();
    	
    	for(Aeroporto a : this.model.getAeroporti()) {
    		cmbPartenza.getItems().add(a.getCity());
    		cmbArrivo.getItems().add(a.getCity());
    	}
    	
    	cmbScali.getItems().add("Diretto");
    	cmbScali.getItems().add("1");
    	cmbScali.getItems().add("2");
    }

}
