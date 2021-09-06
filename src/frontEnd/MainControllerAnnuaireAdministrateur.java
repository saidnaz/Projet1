package frontEnd;



import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;



import backEnd.Stagiaire;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainControllerAnnuaireAdministrateur implements Initializable {

	// Declaration
	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfPrenom;
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnImprimer;
	@FXML
	private Button btnRechercher;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private Button btnRetour;
	@FXML
	private TableView<Stagiaire> tbStagiaire;
	@FXML
	private TableColumn<Stagiaire, String> colNom;
	@FXML
	private TableColumn<Stagiaire, String> colPrenom;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		afficherStagiaire();

	}

	
	// HandleButton
		@FXML
		public void handleButtonAction(ActionEvent event) {

			if (event.getSource() == btnAjouter) {

				ajouterStagiaire();
				afficherStagiaire();
				
			} else if (event.getSource() == btnSupprimer) {

				supprimerStagiaire();
				afficherStagiaire();
				
			} else if (event.getSource() == btnRechercher) {
				
				if (tfNom.getText().isEmpty()  && tfPrenom.getText() != null) {
					recherchePrenom();
					afficherRecherchePrenom();
					
				} else if (tfPrenom.getText().isEmpty() && tfNom.getText() != null){
					recherche();
					afficherRecherche();
				}
			} else if (event.getSource() == btnRetour) {
				afficherStagiaire();}
				 else if (event.getSource() == btnImprimer) {
						imprimer();
			}
	
			
		}
	
		
		
	
	public void imprimer() {
		PrinterJob job = PrinterJob.getPrinterJob();

		job.setPrintable(new Printer("C:\\developement\\outils\\Projetvendredi\\Projet-1\\Projet1\\noms2.DON"));

		boolean doPrint = job.printDialog();
		if(doPrint) {
			try {
				job.print();
			}
			catch (PrinterException e1) {
				e1.printStackTrace();
			}
			
		}
	}
		         
		      
		   


	// Methode ajouter

	public void ajouterStagiaire() {

		// Tri
		List<String> fichier = lireFichier(); // On récupère la liste de la méthode lireFichier
		System.out.println(fichier);

		fichier.add(tfNom.getText().toUpperCase() + " " + tfPrenom.getText()); // Il serait bon de forcer le prénom a
																				// avoir la 1ère lettre en majuscule

		Collections.sort(fichier); // On met les éléments dans l'ordre
		System.out.println(fichier);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("noms2.DON"))) {

			for (String ligne : fichier) {
				bufferedWriter.write(ligne);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// Classe qui permet de lire le fichier
	public static List<String> lireFichier() {
		List<String> fichier = new ArrayList<>(); // Liste qui va réceptionner toutes les lignes du fichier

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));) { // try with ressource
																									// il va fermer le
																									// buffered reader
																									// automatiquement

			String line; // On déclare une variable String pour y affecter chaque ligne du fichier

			while ((line = bufferedReader.readLine()) != null) { // line reçoit la valeur en String de la ligne, si elle
																	// est différente de null (et donc qu'il y a encore
																	// des chose à lire, on continue, ATTENTION A NE PAS
																	// METTRE D'ESPACE DANS LE FICHIER)
				fichier.add(line); // on ajoute le contenu de la ligne dans la liste (en supposant que NOM et
									// PRENOM soient bien espacés)
			}

		} catch (IOException e) { // Erreur si le fichier n'est pas trouvé
			e.printStackTrace();
		}
		return fichier; // On retourne la liste

	}

	// Methode supprimerStagiaire
	public void supprimerStagiaire() {

		List<String> fichier = supprimerElement(tfNom.getText());
		Collections.sort(fichier);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("noms2.DON"))) {

			for (String ligne : fichier) {
				bufferedWriter.write(ligne);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

	public static List<String> supprimerElement(String nom) {
		List<String> fichier = new ArrayList<>();
		String[] analyse;
		int compteur = 0;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				analyse = line.split(" ");
				if (analyse[0].equalsIgnoreCase(nom) && compteur == 0) {
					compteur++;
				} else {
					fichier.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fichier;

	}

	

	public ObservableList<Stagiaire> getStagiaire() {

		ObservableList<Stagiaire> listStagiaire = FXCollections.observableArrayList();

		String[] x;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("noms2.DON"));) {

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				x = line.split(" ");

				listStagiaire.add(new Stagiaire(x[0], x[1]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listStagiaire;

	}

	public void afficherStagiaire() {

		try {

			colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

			colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

			tbStagiaire.setItems(getStagiaire());

		} catch (NullPointerException e) {
			System.out.println("Error" + e);

		}
	}

	// Methode de recherche

	public List<String> recherche() {
		List<String> fichier = lireFichier();
		List<String> result = fichier.stream()
				
				.filter(line -> line.startsWith(tfNom.getText().toUpperCase()))
				.collect(Collectors.toList());
		System.out.println(result);
		return result;
	}

	
	public List<String> reverseList() {
		String split[];
		List<String> fichier = lireFichier();
		List<String> reverseList = new ArrayList<>();
		for (String line : fichier) {
			split = line.split(" ");
			reverseList.add(split[1] + " " + split[0]);
				
			}
		System.out.println(reverseList);
		return reverseList;
		}
	
	public List<String> recherchePrenom() {
		List<String> fichier = reverseList();
		List<String> result = fichier.stream()
				
				.filter(line -> line.startsWith(tfPrenom.getText()))
				.collect(Collectors.toList());
		System.out.println(result);
		return result;
	}
	
	public ObservableList<Stagiaire> getStagiaireRecherchePrenom() {

		ObservableList<Stagiaire> listStagiairePrenom = FXCollections.observableArrayList();
		String[] split;

		List<String> recherche = recherchePrenom();
		for (String ligne : recherche) {
			split = ligne.split(" ");

			listStagiairePrenom.add(new Stagiaire(split[1], split[0]));
		}

		return listStagiairePrenom;

	}
	
	
	public ObservableList<Stagiaire> getStagiaireRecherche() {

		ObservableList<Stagiaire> listStagiaire = FXCollections.observableArrayList();
		String[] split;

		List<String> recherche = recherche();
		for (String ligne : recherche) {
			split = ligne.split(" ");

			listStagiaire.add(new Stagiaire(split[0], split[1]));
		}

		return listStagiaire;

	}

	public void afficherRecherche() {

		try {

			colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

			colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

			tbStagiaire.setItems(getStagiaireRecherche());

		} catch (NullPointerException e) {
			System.out.println("Error" + e);
		}
	}
	
	public void afficherRecherchePrenom() {

		try {

			colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

			colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

			tbStagiaire.setItems(getStagiaireRecherchePrenom());

		} catch (NullPointerException e) {
			System.out.println("Error" + e);
		}
	}
	
}
