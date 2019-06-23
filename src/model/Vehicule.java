package model;

public class Vehicule {
	
	int id,prix;
	String matricule,marque,modele,couleur;
	public Vehicule(int id, int prix, String matricule, String marque, String modele, String couleur) {
		super();
		this.id = id;
		this.prix = prix;
		this.matricule = matricule;
		this.marque = marque;
		this.modele = modele;
		this.couleur = couleur;
	}
	public Vehicule() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", prix=" + prix + ", matricule=" + matricule + ", marque=" + marque + ", modele="
				+ modele + ", couleur=" + couleur + "]";
	}
	
}
