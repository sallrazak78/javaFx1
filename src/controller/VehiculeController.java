package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Vehicule;
import service.VehiculeDao;
import utilitaire.LoadView;

public class VehiculeController implements Initializable {

	@FXML
	private JFXTextField matTfd;
	private int ID=0;
	@FXML
	private JFXTextField marqueTfd;

	@FXML
	private JFXTextField modeleTfd;

	@FXML
	private JFXTextField couleurTfd;

	@FXML
	private JFXTextField prixTfd;

	@FXML
	private JFXButton nouveau;

	@FXML
	private JFXButton ajouter;

	@FXML
	private JFXButton modifier;

	@FXML
	private JFXButton supprimer;

	@FXML
	private TableView<Vehicule> table;
	@FXML
	private TableColumn<Vehicule, String> cmatricule;

	@FXML
	private TableColumn<Vehicule, String> cmarque;

	@FXML
	private TableColumn<Vehicule, String> cmodele;

	@FXML
	private TableColumn<Vehicule, String> ccouleur;

	@FXML
	private TableColumn<Vehicule, Integer> cprix;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			cmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
			cmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
			cmodele.setCellValueFactory(new PropertyValueFactory<>("modele"));
			ccouleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
			cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
			populateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		nouveau.setDisable(false);
		ajouter.setDisable(true);
		modifier.setDisable(true);
		supprimer.setDisable(true);

	}
	@FXML
	 void retour(ActionEvent event) {
		 LoadView.showView("Menu","Menu.fxml",1);
	  }

	@FXML
	void displayVihicule(MouseEvent event) {
		Vehicule vd = table.getSelectionModel().getSelectedItem();
		nouveau.setDisable(false);
		ajouter.setDisable(true);
		modifier.setDisable(false);
		supprimer.setDisable(false);
		matTfd.setText(vd.getMatricule());
		marqueTfd.setText(vd.getMarque());
		modeleTfd.setText(vd.getModele());
		couleurTfd.setText(vd.getCouleur());
		prixTfd.setText(Integer.valueOf(vd.getPrix())+"");
		this.ID= vd.getId();

	}

	private void populateTable() throws Exception {
		ObservableList<Vehicule> vList = VehiculeDao.getAllRecords();
		table.setItems(vList);
	}

	@FXML
	void vehiculeHandler(ActionEvent event) throws Exception {
		try {

			Vehicule v = new Vehicule();
			v.setMatricule(matTfd.getText());
			v.setPrix(Integer.parseInt(prixTfd.getText()));
			v.setModele(modeleTfd.getText());
			v.setMarque(marqueTfd.getText());
			v.setCouleur(couleurTfd.getText());
			VehiculeDao.addVehicule(v);
			Alert a = new Alert(Alert.AlertType.INFORMATION);
			a.setTitle("Enregistrement d'un vehicule ");
			a.setContentText("Vehicule enregistrer");
			a.showAndWait();
			initialize(null, null);
		} catch (Exception e) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Enregistrement d'un vehicule");
			a.setContentText(e.getMessage());
			a.showAndWait();
			throw e;
		}
	}
	 @FXML
	    void nouveauVehicule(ActionEvent event) {
		 	matTfd.setText("");
		 	marqueTfd.setText("");
		 	couleurTfd.setText("");
		 	modeleTfd.setText("");
		 	prixTfd.setText("");
		 	nouveau.setDisable(true);
		 	ajouter.setDisable(false);
		 	supprimer.setDisable(true);
		 	modifier.setDisable(true);
	    }

	@FXML
	void modifierVehicule(ActionEvent event) throws Exception {
		int id=this.ID;
		Vehicule vd =new Vehicule(id,Integer.parseInt(prixTfd.getText()),matTfd.getText(),marqueTfd.getText(),modeleTfd.getText(),couleurTfd.getText()) ;
		
			System.out.println(vd.getCouleur());
			System.out.println(couleurTfd.getText());
			VehiculeDao.modifyVehicule(vd);
			initialize(null, null);
			System.out.println("Modification effectuer");
		nouveau.setDisable(false);
		ajouter.setDisable(true);
		modifier.setDisable(true);
		supprimer.setDisable(true);
		this.ID=0;
	}

	@FXML
	void supprimerVehicule(ActionEvent event) throws Exception {
		
		int id=this.ID;
		if(id!=0) {
			VehiculeDao.supprimerVehicule(id);
			initialize(null, null);}
		this.ID=0;
	}
	

}
