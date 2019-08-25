package fr.eni.clinique.bo;

public class Animal {
	private int codeAnimal;
	private String nomAnimal;
	private Sexe sexe;
	private String couleur;
	private String race;
	private String espece;
	private int codeClient;
	private String tatouage;
	private String antecedents;
	private boolean archive;
	
	public enum Sexe {M, F};
	
	public Animal(int codeAnimal, String nomAnimal, Sexe sexe, String couleur, String race, String espece,
			int codeClient, String tatouage, String antecedents, boolean archive)
	{
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	public Animal(String nomAnimal, Sexe sexe, String couleur, String race, String espece, int codeClient,
			String tatouage, String antecedents, boolean archive) 
	{
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	public int getCodeAnimal() {
		return codeAnimal;
	}
	public String getNomAnimal() {
		return nomAnimal;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public String getCouleur() {
		return couleur;
	}
	public String getRace() {
		return race;
	}
	public String getEspece() {
		return espece;
	}
	public int getCodeClient() {
		return codeClient;
	}
	public String getTatouage() {
		return tatouage;
	}
	public String getAntecedents() {
		return antecedents;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}
	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	@Override
	public String toString() {
		return "Animal [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", race=" + race + ", espece=" + espece + ", codeClient=" + codeClient + ", tatouage="
				+ tatouage + ", antecedents=" + antecedents + ", archive=" + archive + "]";
	}

}
