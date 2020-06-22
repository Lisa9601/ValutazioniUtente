package application;

import java.util.List;

import application.UtenteValutazione.ValutazioneUtente;

public interface InterfacciaFiltri {

	// ordina una lista in base al criterio scelto 
	public List<ValutazioneUtente> sortUserEvaluations(List<ValutazioneUtente> uel, String criterio);
	
	// filtra una lista secondo i criteri scelti
	public List<ValutazioneUtente> filterUserEvaluations(List<ValutazioneUtente> uel, List<Filtro> filtri);
	
}
