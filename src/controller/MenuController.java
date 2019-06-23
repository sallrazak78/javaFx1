package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilitaire.LoadView;

public class MenuController {

    @FXML
    void ouvreClient(ActionEvent event) {
    	LoadView.showView("Ajouter Vehicule", "FClient.fxml", 1);
    }

    @FXML
    void ouvreLocation(ActionEvent event) {
    	LoadView.showView("Location de voiture", "FLocation.fxml", 1);
    }

    @FXML
    void ouvreVoiture(ActionEvent event) {
    	LoadView.showView("Voiture", "FVehicule.fxml", 1);
    }

}
