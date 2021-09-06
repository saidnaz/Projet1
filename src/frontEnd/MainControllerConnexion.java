package frontEnd;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainControllerConnexion implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane rootPane;
	
	//Déclaration des composant du controller
	
	@FXML
	private TextField tfIdentifiant;
	@FXML
	private TextField tfMotDePasse;
	@FXML
	private Button btnConnexion;
	
	MainControllerAnnuaireStagiaire personne = new MainControllerAnnuaireStagiaire();
	
	//Methode si on appuie sur un bouton
		
	@FXML
	public void handleButtonAction (ActionEvent event) throws IOException  { 

		if (event.getSource() == btnConnexion) {

			if (connexion()== 1) {
			Parent root = FXMLLoader.load(getClass().getResource("InterfaceAnnuaireStagiaire.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			personne.afficherStagiaire();
			}
			
			else if (connexion()== 2) {
				Parent root = FXMLLoader.load(getClass().getResource("InterfaceAnnuaireAdministrateur.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				personne.afficherStagiaire();
				}
			
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("Connexion echoue");
				alert.show();
			}
		}
		
		
		
	}
	
	
	
	//Methode initialisation du controller
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	//Methode connexion
	
	public int connexion() {

		HashMap<String, String> user = new HashMap<String, String>();
		HashMap<String, String> admin = new HashMap<String, String>();

		user.put("user", "user1");
		admin.put("admin", "admin1");

		if (user.containsKey(tfIdentifiant.getText()) && user.get(tfIdentifiant.getText()).equals(tfMotDePasse.getText())) {
				return 1;}
				
		if (admin.containsKey(tfIdentifiant.getText()) && admin.get(tfIdentifiant.getText()).equals(tfMotDePasse.getText())){
					return 2;}

		 else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("Identifiant ou mot de passe incorrect");
				alert.show();
				return 3;
		 }
}
}

