package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import application.UtenteValutazione.UtenteDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class Main extends Application {

	private UtenteDao ud = UtenteDao.getInstance();
    //lista utenti registrati nel sistema 
	private List<String> users = ud.trovaListaUtenti();
	
	@FXML 
	private Text text;	
	@FXML
	private ComboBox<String> utenti; 	
	@FXML
	public static final ObservableList<String> data = FXCollections.observableArrayList();	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
	
	@Override
	public void start(Stage primaryStage) {
		try {	
		
			FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("listaUtenti.fxml"));
	        Parent firstPane = firstPaneLoader.load();
	        Scene firstScene = new Scene(firstPane, 700, 500);
	        firstScene.getStylesheets().add(getClass().getResource("start.css").toExternalForm());            
	        
			primaryStage.setScene(firstScene);
			primaryStage.setTitle("FERSA - Lista utenti");
			primaryStage.show();				
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		
		text.setText("Selezionare un utente : ");		
		data.clear();
		
		for (int i=0; i<users.size(); i++) {			
			data.add(users.get(i));			
		}
		
		utenti.setItems(data);
		
	}
	
	//Apre il profilo dell'utente selezionato
    public void openSecondScene(ActionEvent actionEvent){
    	
    	try {
	    	FXMLLoader secondPaneLoader = new FXMLLoader(getClass().getResource("grafica.fxml"));
	        Parent secondPane = secondPaneLoader.load();
	        
	        if(utenti.getValue() == null) {
	        	return;
	        }
	        
	        Controller controller = (Controller) secondPaneLoader.getController();
	        controller.initialize(utenti.getValue());
	        
	        Scene secondScene = new Scene(secondPane, 1000, 800);
	        secondScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());    	
	        
	        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
	        
	        primaryStage.setScene(secondScene);
			primaryStage.setTitle("FERSA - Profilo di " + utenti.getValue());
			primaryStage.show();
	        
    	}catch(Exception e) {    		
    		e.printStackTrace();    		
    	}
        
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
