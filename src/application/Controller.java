package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import application.UtenteValutazione.Utente;
import application.UtenteValutazione.UtenteDao;
import application.UtenteValutazione.ValutazioneUtente;
import application.UtenteValutazione.ValutazioneUtenteDao;


public class Controller{
	
	private VisualThread visual;
	private RefreshThread thread;
	private ValutazioneUtenteDao vd = new ValutazioneUtenteDao();	
	private UtenteDao ud = UtenteDao.getInstance();
	private InterfacciaFiltri f = new ApplicaFiltro();		
	private Utente u;  	
	private List<ValutazioneUtente> val;
	
	// Caselle testuali 
	@FXML 
	private Text username;
	@FXML
	private Text descrizione;
	@FXML
	private Text textRatingTot;
	@FXML
	private Text pul;
	@FXML
	private Text disp;
	@FXML
	private Text edu;
	@FXML
	private Text error;
	
	// Combo box 
	@FXML
	private ComboBox<String> ordinamento;
	
	// Rating sulle varie categorie
	@FXML
	private ProgressBar ratingPul;
	@FXML
	private ProgressBar ratingDisp;
	@FXML
	private ProgressBar ratingEdu;
	
	// Check box per filtri
	@FXML
	private CheckBox categoriaD;
	@FXML
	private CheckBox categoriaE;
	@FXML
	private CheckBox categoriaP;
	@FXML
	private CheckBox linguaIta;
	@FXML
	private CheckBox linguaIng;
	@FXML
	private CheckBox linguaFra;
	@FXML
	private CheckBox periodoMM;
	@FXML
	private CheckBox periodoGA;
	@FXML
	private CheckBox periodoSN;
	@FXML
	private CheckBox periodoDF;
	@FXML
	private CheckBox stelle1;
	@FXML
	private CheckBox stelle2;
	@FXML
	private CheckBox stelle3;
	@FXML
	private CheckBox stelle4;
	@FXML
	private CheckBox stelle5;
	
	@FXML
	private ImageView icon;
	
	@FXML
	private ListView<ValutazioneUtente> evaluationList = new ListView<ValutazioneUtente>();
	public static final ObservableList<ValutazioneUtente> data = FXCollections.observableArrayList();

	
// Torna alla Home
	@FXML
	void goToUserList(MouseEvent actionEvent) {
		
		thread.stopThread();	//Fa terminare il thread che si occupa dell'aggiornamento periodico
		
		try {
			
			FXMLLoader secondPaneLoader = new FXMLLoader(getClass().getResource("listaUtenti.fxml"));
			Parent secondPane = secondPaneLoader.load();
			
			 Scene secondScene = new Scene(secondPane, 700, 500);
		        secondScene.getStylesheets().add(getClass().getResource("start.css").toExternalForm());    	
		        
		        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		        
		        primaryStage.setScene(secondScene);
				primaryStage.setTitle("FERSA - Lista utenti");
				primaryStage.show();        
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	@FXML
	void ordina(ActionEvent event) {
		
		ordinaPer(val);
		
	}
	

// Ordina le valutazioni secondo il criterio selezionato	
	public void ordinaPer(List<ValutazioneUtente> list) {
	
		if(list == null) {
			return;
		}		
		
		if(ordinamento.getValue() != null) {
			
			list = f.sortUserEvaluations(list,ordinamento.getValue());
			data.clear();
			
			for(int i=0; i<list.size();i++) {
				
				ValutazioneUtente v = list.get(i);
				data.add(v);
								
			}

			evaluationList.setItems(data);
	        evaluationList.setCellFactory(valutazioneListView -> new ValutazioneListViewCell());
		}
		
	}
	

// Filtra la lista secondo il criterio scelto 	
	@FXML
    void applicaFiltri(ActionEvent event) {
		
		val = vd.trovaValutazionePerRecensito(u.getNickName());
		
		if(val.isEmpty()) {
			return;
		}
		
				
		List<Filtro> filtri = new ArrayList<Filtro>();
		List<String> categorie_selezionate = new ArrayList<String>();
		List<String> lingue_selezionate = new ArrayList<String>();
		List<String> periodo_selezionato = new ArrayList<String>();
		List<String> stelle_selezionate = new ArrayList<String>();
		
		
		if(categoriaD.isSelected()) 	categorie_selezionate.add("Disponibilità");
		
		if(categoriaE.isSelected()) 	categorie_selezionate.add("Educazione");
			
		if(categoriaP.isSelected()) 	categorie_selezionate.add("Pulizia");
		
		filtri.add(new Filtro("categoria",categorie_selezionate));
		
		
		if(linguaIta.isSelected()) 		lingue_selezionate.add("Italiano");
		
		if(linguaIng.isSelected())		lingue_selezionate.add("Inglese");
			
		if(linguaFra.isSelected()) 		lingue_selezionate.add("Francese");
		
		filtri.add(new Filtro("lingua",lingue_selezionate));
		

		if(periodoMM.isSelected()) 		periodo_selezionato.add("Marzo-Maggio");

		if(periodoGA.isSelected()) 		periodo_selezionato.add("Giugno-Agosto");
		
		if(periodoSN.isSelected()) 		periodo_selezionato.add("Settembre-Novembre");
		
		if(periodoDF.isSelected()) 		periodo_selezionato.add("Dicembre-Febbraio");

		filtri.add(new Filtro("periodo",periodo_selezionato));
		
		
		if(stelle1.isSelected()) 		stelle_selezionate.add("1");
		
		if(stelle2.isSelected()) 		stelle_selezionate.add("2");
		
		if(stelle3.isSelected()) 		stelle_selezionate.add("3");
		
		if(stelle4.isSelected()) 		stelle_selezionate.add("4");
		
		if(stelle5.isSelected()) 		stelle_selezionate.add("5");
		
		filtri.add(new Filtro("stelle",stelle_selezionate));
		
		
		f.filterUserEvaluations(val, filtri);
		ordinaPer(val);	//Richiama la funzione per l'ordinamento della lista
		
		data.clear();
		
		if(val.isEmpty()) {
			error.setVisible(true);
			error.setText("Nessuna valutazione");
		}
		else {	
			error.setVisible(false);
		}
		
		for(int i=0; i<val.size();i++) {
			
			ValutazioneUtente v = val.get(i);			
			data.add(v);
							
		}

		evaluationList.setItems(data);
        evaluationList.setCellFactory(valutazioneListView -> new ValutazioneListViewCell());	
		
    }	
	
	
	public void impostaPagina(String nickName) {
		
		u.setRecensioni(vd.trovaValutazionePerRecensito(nickName));
		val = u.getRecensioni();
		
		//Imposta la media generale e sulle varie categorie
		textRatingTot.setText("("+ Double.toString(u.getMediaTot())+"/5)");
		
		ratingPul.setProgress(u.getMediaPulizia()/5);
		pul.setText(Double.toString(u.getMediaPulizia()));
		
		ratingDisp.setProgress(u.getMediaDisponibilita()/5);
		disp.setText(Double.toString(u.getMediaDisponibilita()));
		
		ratingEdu.setProgress(u.getMediaEducazione()/5);
		edu.setText(Double.toString(u.getMediaEducazione()));
		
		applicaFiltri(new ActionEvent());
	
	}

	
	public void initialize(String nickName) {
		
		//Chiama il thread che si occupa di aggiornare il numero delle visualizzazioni del profilo
		visual = new VisualThread(nickName);	
		visual.start();
		
		ObservableList<String> modalita_ordinamento = FXCollections.observableArrayList("Valutazione crescente","Valutazione decrescente");
		ordinamento.setItems(modalita_ordinamento);
				
		//Imposta le informazioni dell'utente selezionato
		u = ud.trovaInfoUtente(nickName);
		username.setText("@"+u.getNickName());
		
		if(u.getSesso() != null) {
			descrizione.setText(u.getSesso() + "\n " + u.getNazionalita() + "\n" + u.getDataNascita() +"\n"
					+ u.getDataIscrizione()+"\n\n" + u.getVisualizzazioni());
		}
		else {
			descrizione.setText("\n" + u.getNazionalita() + "\n" + u.getDataNascita() +"\n"
					+ u.getDataIscrizione() + "\n\n" + u.getVisualizzazioni());
		}
		

		impostaPagina(u.getNickName());
		
		
		//Thread che si occupa di aggiornare la lista delle valutazioni	
        Runnable updater = new Runnable() {

            @Override
            public void run() {
				impostaPagina(u.getNickName());
            }
        };
        
        thread = new RefreshThread(updater);      
        thread.setDaemon(true);
        thread.start();
		
	}
	

}
