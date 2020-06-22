package application.UtenteValutazione;

import java.util.Date;

public class ValutazioneUtente {

	//informazioni sulla valutazione 
	
	private int numero;
	private String recensore;			//nickname del recensore
	private String recensito;			//nickname del recensito
	private Date dataInserimento;
	private int stelle;
	private String categoria;
	private String lingua = null;
	private String testo = null;
	private String periodo;

	
	public ValutazioneUtente(int numero, String recensore, String recensito, Date dataInserimento, int stelle, String categoria, String lingua, String periodo) {
		this.numero = numero;
		this.recensore = recensore;
		this.recensito = recensito;
		this.dataInserimento = dataInserimento;
		this.stelle = stelle;
		this.categoria = categoria;
		this.lingua = lingua;
		this.periodo = periodo;
	}
	
	public ValutazioneUtente(int numero, String recensore, String recensito, Date dataInserimento, int stelle, String categoria, String lingua, String periodo, String testo) {
		this.numero = numero;
		this.recensore = recensore;
		this.recensito = recensito;
		this.dataInserimento = dataInserimento;
		this.stelle = stelle;
		this.categoria = categoria;
		this.lingua  = lingua;
		this.periodo = periodo;
		this.testo = testo;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getRecensore() {
		return recensore;
	}


	public void setRecensore(String recensore) {
		this.recensore = recensore;
	}


	public String getRecensito() {
		return recensito;
	}


	public void setRecensito(String recensito) {
		this.recensito = recensito;
	}

	
	public Date getDataInserimento() {
		return dataInserimento;
	}


	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getStelle() {
		return stelle;
	}


	public void setStelle(int stelle) {
		this.stelle = stelle;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public String getTesto() {
		return testo;
	}


	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	
	
}
