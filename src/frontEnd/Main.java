package frontEnd;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Vue Connexion
		Parent root = FXMLLoader.load(getClass().getResource("InterfaceAuthentification.fxml")); // On charge le main.FX

		Scene scene = new Scene(root); // On crée un scene

		primaryStage.setScene(scene); // On affiche la scene

		primaryStage.setTitle("Connexion"); // On met un titre pour la scene

		primaryStage.show();  // Ne jamais oublier le .show
		
		
	}
	
	
	public static void main(String[] args) { // Permet de lancer l'application
		launch(args);


	}


}
