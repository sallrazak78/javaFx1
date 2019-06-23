package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
import service.ClientDao;
import utilitaire.LoadView;

public class ClientController implements Initializable {
	int id;

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
	private JFXButton nouveau;

	@FXML
	private JFXButton ajouter;

	@FXML
	private JFXButton modifier;

	@FXML
	private JFXButton supprimer;
	@FXML
	private TableView<Client> table;

	@FXML
	private TableColumn<Client, String> cpiece;

	@FXML
	private TableColumn<Client, LocalDate> cdate;

	@FXML
	private TableColumn<Client, String> cprenom;

	@FXML
	private TableColumn<Client, String> cnom;

	@FXML
	private TableColumn<Client, String> cemail;

	@FXML
	private TableColumn<Client, String> cadresse;

	@FXML
	void ajouterClient(ActionEvent event) throws Exception {
		Client x = new Client(1, nom.getText(), prenom.getText(), email.getText(), adresse.getText(), piece.getText(),
				date.getValue());
		try {
			ClientDao.addClient(x);
			populateTable();
		} catch (Exception e) {
			throw e;
		}
	}

	@FXML
	void dataHandler(MouseEvent event) {
		Client x = table.getSelectionModel().getSelectedItem();
		this.id=x.getId();
		nouveau.setDisable(false);
		ajouter.setDisable(true);
		modifier.setDisable(false);
		supprimer.setDisable(false);
		
		nom.setText(x.getNom());
		prenom.setText(x.getPrenom());
		email.setText(x.getEmail());
		date.setValue(x.getDate());
		adresse.setText(x.getAdresse());
		piece.setText(x.getPiece());
		
		

	}

	@FXML
	void modifierClient(ActionEvent event) throws Exception {
		if(this.id != 0) {
		Client x=new Client();
			x.setId(this.id);
			x.setNom(nom.getText());
			x.setPrenom(prenom.getText());
			x.setAdresse(adresse.getText());
			x.setEmail(email.getText());
			x.setDate(date.getValue());
			x.setPiece(piece.getText());
			ClientDao.modifyClient(x);
		}
		populateTable();
	}

	@FXML
	void nouveauClient(ActionEvent event) {
		nom.setText(null);
	 	prenom.setText(null);
	 	email.setText(null);
	 	adresse.setText(null);
	 	date.setValue(null);
	 	piece.setText(null);

	 	nouveau.setDisable(true);
	 	ajouter.setDisable(false);
	 	supprimer.setDisable(true);
	 	modifier.setDisable(true);
	}

	@FXML
	void supprimerClient(ActionEvent event) throws Exception {
		if(this.id!=0) {
			ClientDao.supprimerClient(this.id);
		}
		populateTable();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			cpiece.setCellValueFactory(new PropertyValueFactory<>("piece"));
			cdate.setCellValueFactory(new PropertyValueFactory<>("date"));
			cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			cprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			cadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			cemail.setCellValueFactory(new PropertyValueFactory<>("email"));

			
			populateTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nouveau.setDisable(false);
		ajouter.setDisable(true);
		modifier.setDisable(false);
		supprimer.setDisable(false);
	}

	private void populateTable() throws Exception {
		
		ObservableList<Client> vList = ClientDao.getAllRecords();
		table.setItems(vList);
	}
	 @FXML
	 void retour(ActionEvent event) {
		 LoadView.showView("Menu","Menu.fxml",1);
	  }


}
