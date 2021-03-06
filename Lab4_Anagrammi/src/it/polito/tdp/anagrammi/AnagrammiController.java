/**
 * Sample Skeleton for 'Anagrammi.fxml' Controller Class
 */

package it.polito.tdp.anagrammi;
import it.polito.tdp.anagrammi.model.*;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.anagrammi.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AnagrammiController {

	Dizionario d=new Dizionario();
    private char[] caratteri;
    private String anagrammi="";
    List<Text> testo=new LinkedList<Text>();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="bttCalcola"
    private Button bttCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextFlow txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="bttReset"
    private Button bttReset; // Value injected by FXMLLoader

    @FXML
    void doCalcola(ActionEvent event) {
    	String parola=txtParola.getText();
    	ric(parola, 0);
    	List<RichWord> r=new LinkedList<RichWord>();
    		r=d.spellCheckText(d.dividiTesto(anagrammi));
    		this.stampe(r);
    	}
    
	public void ric(String p, int pos){
		if(pos==p.length()){
			anagrammi+=p+"\n";
			System.out.print(p+"\n");
			return;
		}
			for(int i=pos; i<p.length(); i++){
				caratteri=p.toCharArray();
				char a=caratteri[i];
				caratteri[i]=caratteri[pos];
				caratteri[pos]=a;
				ric(String.copyValueOf(caratteri),pos+1);
		}
	}
	
	void stampe(List<RichWord> r){
    	//String ris="";
    	for(RichWord t: r){
    		Text y=new Text();
    		if(t.isCorretta()==false){
    			String s=t.toString()+"\n";
    			y.setText(s);
    			y.setFill(javafx.scene.paint.Color.RED);
    			testo.add(y);
    			//ris+=t.toString()+" ";
    		} else {
    			//ris+=t.toString()+" ";
    			y.setText(t.toString()+"\n");
    			testo.add(y);
    		}
    	}
    	for(Text t: testo)
    		txtRisultato.getChildren().add(t);
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtParola.setText("");
    	testo.clear();
    	anagrammi="";
    	txtRisultato.getChildren().clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert bttCalcola != null : "fx:id=\"bttCalcola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert bttReset != null : "fx:id=\"bttReset\" was not injected: check your FXML file 'Anagrammi.fxml'.";

    }
}