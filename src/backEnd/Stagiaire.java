package backEnd;



public class Stagiaire  {

	private String nom;
	private String prenom;
	
	
	
	
	public Stagiaire(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	
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


	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + "]";
	}
		
	



}
