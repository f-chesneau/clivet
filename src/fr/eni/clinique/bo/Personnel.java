package fr.eni.clinique.bo;

public class Personnel
{
	private int codePers;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;
	
	//Constructors
	public Personnel(int codePers, String nom, String motPasse, String role, boolean archive)
	{
		super();
		this.codePers = codePers;
		this.nom = nom;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	
	public Personnel(String nom, String motDePasse, String role, boolean archive)
	{
		super();
		this.nom = nom;
		this.motPasse = motDePasse;
		this.role = role;
		this.archive = archive;
	}
	
	//Setters/Getters
	public int getCodePersonne() {
		return codePers;
	}

	public void setCodePersonne(int codePersonne) {
		this.codePers = codePersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	//toString
	@Override
	public String toString() {
		return "Personnel [codePersonne=" + codePers + ", nom=" + nom + ", motDePasse=" + motPasse + ", role="
				+ role + ", archive=" + archive + "]";
	}
	
	
	

	
	
	
}
