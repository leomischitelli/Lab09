package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCalcolaConfini;

    @FXML
    private Button btnStatiRaggiungibili;

    @FXML
    private ComboBox<Country> cmbStatoPartenza;

    @FXML
    private TextField txtAnno;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	Set<Country> countries = model.calcolaConfini(Integer.parseInt(txtAnno.getText()));
    	TreeSet<Country> cc = new TreeSet<>(countries);
    	String output = "";
    	for(Country c : cc) {
    		output+=c.toString() + ": " + c.getStatiConfinanti() + " stati confinanti\n";
    	}
    	
    	txtResult.appendText("Componenti connesse: " + Integer.toString(model.getComponentiConnesse()) + "\n");
    	txtResult.appendText(output);
    	
    }

    @FXML
    void doStatiRaggiungibili(ActionEvent event) {

    	txtResult.clear();
    	Set<Country> countries = model.getStatiConfinanti(cmbStatoPartenza.getValue());
    	if(countries == null) {
    		txtResult.setText("Eseguire prima il calcolo dei confini!");
    		return;
    	}
    	TreeSet<Country> cc = new TreeSet<>(countries);
    	txtResult.appendText("Stati raggiungibili: " + cc.size() + "\n");
    	for(Country c : cc) {
    		txtResult.appendText(c.toString() + "\n");
    	}
    }

    @FXML
    void initialize() {
        assert btnCalcolaConfini != null : "fx:id=\"btnCalcolaConfini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStatiRaggiungibili != null : "fx:id=\"btnStatiRaggiungibili\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStatoPartenza != null : "fx:id=\"cmbStatoPartenza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	cmbStatoPartenza.getItems().addAll(model.loadAllCountries());
    }

}



