package classes;

public class Categorie {
	
	Integer id = null;
	String nom = null;
	
	public Categorie() {
		super();
	}

	public Categorie(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return id + " - " + nom;
	}
	
	
}
