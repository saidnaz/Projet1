package backEnd;

public class Personne{

	//Proprietes
	
	private String nom;
	private String prenom;
	

	
	//Constructeur
	
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	
	//Getter setter
		
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	

	//toString
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
	
}
