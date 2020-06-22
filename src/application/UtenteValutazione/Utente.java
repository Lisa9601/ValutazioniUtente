package application.UtenteValutazione;

import java.util.ArrayList;
import java.util.Date;

public class Utente {

	//caratteristiche dell'utente
	private String nickName;
	private String sesso = null;
	private Date dataNascita;
	private String nazionalita;
	private Date dataIscrizione;
	private int visualizzazioni;

	private ArrayList<ValutazioneUtente> recensioni;
	
	private double mediaTot = 0;
	private double mediaPulizia = 0;
	private double mediaDisponibilita = 0;
	private double mediaEducazione = 0;


	public Utente(String nickName, String sesso, Date dataNascita, String nazionalita, Date dataIscrizione) {
		
		this.nickName = nickName;
		
		if(sesso != null) {	
			this.sesso = sesso;
		}
		
		this.dataNascita = dataNascita;
		this.nazionalita = nazionalita;
		this.dataIscrizione = dataIscrizione;
	}
	
public Utente(String nickName, String sesso, Date dataNascita, String nazionalita, Date dataIscrizione, ArrayList<ValutazioneUtente> recensioni) {
		
		this.nickName = nickName;
		
		if(sesso != null) {
			this.sesso = sesso;
		}
		
		this.dataNascita = dataNascita;
		this.nazionalita = nazionalita;
		this.dataIscrizione = dataIscrizione;
		setRecensioni(recensioni);
		
	}
	
	
	public double trovaMedia(ArrayList<ValutazioneUtente> list, String categoria) {
		
		if(list == null) {
			return 0;
		}
		
		double media = 0;
		int num = 0;
		
		for(int i=0; i<list.size(); i++) {
			
			if (list.get(i).getCategoria().equals(categoria) || categoria == null) {
				
				media += list.get(i).getStelle();
				num += 1;
				
			}

		}
				
	    double scale = Math.pow(10, 2);
	    return Math.round((media/num) * scale) / scale;
	
	}
	
	
	//getters e setters
		
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	public Date getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	
	public ArrayList<ValutazioneUtente> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(ArrayList<ValutazioneUtente> recensioni) {
		
		this.recensioni = recensioni;
		this.mediaDisponibilita = trovaMedia(recensioni, "Disponibilità");
		this.mediaPulizia = trovaMedia(recensioni, "Pulizia");
		this.mediaEducazione = trovaMedia(recensioni, "Educazione");
		this.mediaTot = trovaMedia(recensioni, null);
		
	}

	public double getMediaPulizia() {
		return mediaPulizia;
	}

	public void setMediaPulizia(double mediaPulizia) {
		this.mediaPulizia = mediaPulizia;
	}

	public double getMediaDisponibilita() {
		return mediaDisponibilita;
	}

	public void setMediaDisponibilita(double mediaDisponibilita) {
		this.mediaDisponibilita = mediaDisponibilita;
	}

	public double getMediaEducazione() {
		return mediaEducazione;
	}

	public void setMediaEducazione(double mediaEducazione) {
		this.mediaEducazione = mediaEducazione;
	}

	public double getMediaTot() {
		return mediaTot;
	}

	public void setMediaTot(double mediaTot) {
		this.mediaTot = mediaTot;
	}
	
	public int getVisualizzazioni() {
		return visualizzazioni;
	}

	public void setVisualizzazioni(int visualizzazioni) {
		this.visualizzazioni = visualizzazioni;
	}
	
	
}
