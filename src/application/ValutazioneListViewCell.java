package application;

import java.io.IOException;

import org.controlsfx.control.Rating;

import application.UtenteValutazione.ValutazioneUtente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ValutazioneListViewCell extends ListCell<ValutazioneUtente>{
	
	//Classe necessaria per gestire la grafica delle singole valutazioni dell'utente

	@FXML
	private Text name;
	@FXML
	private Text info;
	@FXML
	private Text recensione;
	@FXML
	private AnchorPane anchor;
	@FXML
	private ImageView image;
	@FXML
	private Rating rating;
	
	private FXMLLoader mLLoader;
	
	
    @Override
    protected void updateItem(ValutazioneUtente val, boolean empty) {
        super.updateItem(val, empty);

        if(empty || val == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("listCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
            name.setText(val.getRecensore());
            info.setText(val.getCategoria()+"\n"+val.getPeriodo()+"\n"+val.getDataInserimento());
            rating.setRating(val.getStelle());
            
            if(val.getTesto() == null) {
            	recensione.setVisible(false);
            }
            else { 
            	recensione.setVisible(true);
                recensione.setText(val.getTesto());	
            }
            
            setText(null);
            setGraphic(anchor);
        }

    }
	
}
