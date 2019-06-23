package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import utilitaire.LoadView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LoadView.showView("Cours Java FX","FLogin.fxml",1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
