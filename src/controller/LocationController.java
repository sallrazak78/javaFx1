package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Client;
import model.Location;
import model.LocationModel;
import model.Vehicule;
import service.LocationDao;
import service.Utilitaire;
import utilitaire.LoadView;

public class LocationController implements Initializable {
	//var table column
	@FXML
    private TableView<LocationModel> table;
    @FXML
    private TableColumn<LocationModel, LocalDate> cDate;
    @FXML
    private TableColumn<LocationModel,Integer> cJours;
    @FXML
    private TableColumn<LocationModel, String> cCommentaire;
    @FXML
    private TableColumn<LocationModel, String> cNom;
    @FXML
    private TableColumn<LocationModel, String> cMatricule;
    @FXML
    private TableColumn<LocationModel, Integer> cPrix;
    @FXML
    private TableColumn<LocationModel, Integer> cMontant;
	//var input
	@FXML
    private JFXTextField piece;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField matricule;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField modele;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXDatePicker dateLoc;
    @FXML
    private JFXComboBox<Integer> jours;
    @FXML
    private JFXTextField montant;
    @FXML
    private JFXTextArea commentaire;
    @FXML
    private JFXButton enregistrer;
    @FXML
    private JFXTextField couleur;

    @FXML
    void enregistrerLocation(ActionEvent event) {
    	if(V==null) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Location de voiture");
			a.setHeaderText("Recherche de vehicule");
			a.setContentText("Aucun vehicule trouver");
			a.showAndWait();
    	}
    	if (C==null) {
   		 C=new Client();
   		 C.setDate(date.getValue());
   		 C.setAdresse(adresse.getText());
   		 C.setEmail(email.getText());
   		 C.setNom(nom.getText());
   		 C.setPrenom(prenom.getText());
   		 C.setPiece(piece.getText());	
		}
    	Location l=new Location();
    	l.setDate(dateLoc.getValue());
    	l.setClient(C);
    	l.setCommentaire(commentaire.getText());
    	l.setMontant(calcul(null));
    	l.setJours(jours.getValue());
    	l.setVehicule(V);
    	try {
			LocationDao lDao =new LocationDao();
			lDao.addLocation(l);
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.setTitle("Cours javaFx");
			a.setHeaderText("Location de voiture");
			a.setContentText("Location enregistree");
			a.showAndWait();
		} catch (Exception e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Cours javaFx");
			a.setHeaderText("Location de voiture");
			a.setContentText("erreur : "+e.getMessage());
			a.showAndWait();
		}
    }
    
    @FXML
    int calcul(ActionEvent event) {
    	int prixs=Integer.parseInt(prix.getText());
    	int nbjour=jours.getValue();
    	int mont=prixs*nbjour;
    	montant.setText(mont+"");
    	return  mont;
    }
    @FXML
	 void retour(ActionEvent event) {
		 LoadView.showView("Menu","Menu.fxml",1);
	  }
    //variable
    Vehicule V=new Vehicule();
    LocationDao Loc=new LocationDao();
    Client C= new Client();
    ObservableList<LocationModel> locationModel;
    private void populate() {
    	try {
			List <Location> locations=Loc.getAllRecords();
			locationModel=FXCollections.observableArrayList(Utilitaire.connvertToLocationModele(locations));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setItems(locationModel);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		marque.setDisable(true);modele.setDisable(true);couleur.setDisable(true);prix.setDisable(true);
		//Combo jours
		jours.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7));
		//Table et list
		cDate.setCellValueFactory(cellData->new ReadOnlyObjectWrapper<LocalDate>(cellData.getValue().getLocation().get().getDate()));
		cJours.setCellValueFactory(cellData->new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getLocation().get().getJours()));
		cCommentaire.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getLocation().get().getCommentaire()));
		cNom.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getLocation().get().getClient().getNom()));
		cPrix.setCellValueFactory(cellData->new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getLocation().get().getVehicule().getPrix()));
		cMontant.setCellValueFactory(cellData->new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getLocation().get().getMontant()));
		cMatricule.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getLocation().get().getVehicule().getMatricule()));
			populate();
		
		//recherche
		matricule.focusedProperty().addListener((Observable,ldValue,newValue)->{
			try {
				V=Loc.findVehicule(matricule.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(V!=null) {
				matricule.setText(V.getMatricule());
				marque.setText(V.getMarque());
				modele.setText(V.getModele());
				couleur.setText(V.getCouleur());
				prix.setText(V.getPrix()+"");
				
			}else {
				marque.setText("");
				modele.setText("");
				couleur.setText("");
				prix.setText("");
			}
		});
		piece.focusedProperty().addListener((Observable,ldValue,newValue)->{
			try {
				C=Loc.findClient(piece.getText());
				if(C!=null) {
					piece.setText(C.getPiece());
					nom.setText(C.getNom());nom.setDisable(true);
					prenom.setText(C.getPrenom());prenom.setDisable(true);
					adresse.setText(C.getAdresse());adresse.setDisable(true);
					email.setText(C.getEmail());email.setDisable(true);
					date.setValue(C.getDate());date.setDisable(true);
				}else {
					nom.setText("");
   					prenom.setText("");
   					date.setValue(null);
   					email.setText("");
   					adresse.setText("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}

}
