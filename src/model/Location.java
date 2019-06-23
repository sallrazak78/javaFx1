package model;

import java.time.LocalDate;

public class Location {
	private Client client;
	private Vehicule vehicule;
	private int id,jours,montant;
	LocalDate date;
	String commentaire;
	public Location(Client client, Vehicule vehicule, int id, int jours, int montant, LocalDate date,
			String commentaire) {
		super();
		this.client = client;
		this.vehicule = vehicule;
		this.id = id;
		this.jours = jours;
		this.montant = montant;
		this.date = date;
		this.commentaire = commentaire;
	}
	public Location() {}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJours() {
		return jours;
	}
	public void setJours(int jours) {
		this.jours = jours;
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
	
}
