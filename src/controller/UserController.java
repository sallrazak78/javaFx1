package controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Utilisateur;
import service.UserDao;
import utilitaire.LoadView;

public class UserController {

	 @FXML
	    private JFXTextField loginTfd;

	    @FXML
	    private JFXPasswordField pwdPfd;

	    @FXML
	    private JFXButton connexionBtn;

    @FXML
    void connexionHandler(ActionEvent event) {
    	Utilisateur user= UserDao.getUser(loginTfd.getText(),pwdPfd.getText());
    	if(user!=null) {
    		LoadView.showView("Menu", "Menu.fxml", 1);
    	}else {
    		Alert a=new Alert(Alert.AlertType.ERROR);
    		a.setTitle("Coursjava Fx");
    		a.setContentText("Login ou mot de passe incorrecte");
    		a.showAndWait();
    	}
    		
    }

}
