/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.itunes.model.Adiacenze;
import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="btnMassimo"
    private Button btnMassimo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCanzone"
    private ComboBox<Track> cmbCanzone; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<Genre> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void btnCreaLista(ActionEvent event) {
    	txtResult.clear();
    	int m;
    	Track canzone = null;
    	
    	try {
    		m = Integer.parseInt(txtMemoria.getText());
    	} catch(NumberFormatException e) {
    		return;
    	}
    	
    	if(cmbCanzone.getValue() != null) {
			canzone = cmbCanzone.getValue();
			Set<Track> comp = new HashSet<Track>(this.model.getCompConnessa(canzone));
			txtResult.appendText("Componente connessa della canzone scelta: \n");
			
			for(Track t : comp) {
				txtResult.appendText(t +" bytes: " + t.getBytes() + "\n");
			}
		}
    	
		List<Track> result = new ArrayList<Track>(this.model.getListaCanzoni(m, canzone));
		
		txtResult.appendText("\n\n\n\nrisultato: \n");
		for(Track t : result) {
			txtResult.appendText(t.toString() + "\n");
		}
		
		
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	if(cmbGenere.getValue() != null) {
    		this.model.creaGrafo(cmbGenere.getValue());
    		txtResult.appendText("Grafo creato: Vertici " + this.model.sizeV() + " Archi " + this.model.sizeE());
    	} else {
    		txtResult.appendText("Seleziona un genere");
    	}
    	
    	cmbCanzone.getItems().clear();
    	cmbCanzone.getItems().addAll(this.model.getGrafo().vertexSet());
    }

    @FXML
    void doDeltaMassimo(ActionEvent event) {
    	txtResult.clear();
    	if(this.model.getAdiacenze().size() == 0) {
    		txtResult.appendText("Crea prima il grafo");
    	} else {
    		List<Adiacenze> a = new ArrayList<Adiacenze>(this.model.getDeltaMax());
    		for(Adiacenze ad : a) {
    			txtResult.appendText(ad.toString() + "\n");
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMassimo != null : "fx:id=\"btnMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCanzone != null : "fx:id=\"cmbCanzone\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbGenere.getItems().addAll(this.model.getAllGenres());
    }

}
