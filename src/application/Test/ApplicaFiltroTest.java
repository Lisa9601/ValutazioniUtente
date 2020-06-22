package application.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.ApplicaFiltro;
import application.Filtro;
import application.UtenteValutazione.ValutazioneUtente;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ApplicaFiltroTest {

	private List<String> categorie = new ArrayList<>();
	private List<String> lingue = new ArrayList<>();
	private List<String> periodi = new ArrayList<>();

	@BeforeEach
	public void inizializzaListe() {

		System.out.println("Inizializzazione ...");

		categorie.add("Disponibilità");
		categorie.add("Educazione");
		categorie.add("Pulizia");

		lingue.add("Italiano");
		lingue.add("Inglese");
		lingue.add("Francese");

		periodi.add("Marzo-Maggio");
		periodi.add("Giugno-Agosto");
		periodi.add("Settembre-Novembre");
		periodi.add("Dicembre-Febbraio");

	}

	@AfterEach
	public void pulisciListe() {

		System.out.println("Chiusura ...");

		categorie.clear();
		lingue.clear();
		periodi.clear();

	}

	@Test
	public void testSortUserEvaluation() {

		System.out.println("Test ordinamento");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ApplicaFiltro f = new ApplicaFiltro();
		Random r = new Random();
		List<ValutazioneUtente> valList = new ArrayList<ValutazioneUtente>();

		for (int i = 0; i < 100; i++) {
			try {
				Date date = formatter.parse("1996-09-01");

				ValutazioneUtente v = new ValutazioneUtente(r.nextInt(100), "utente1", "utente2", date, r.nextInt(6),
						categorie.get(r.nextInt(3)), lingue.get(r.nextInt(3)), periodi.get(r.nextInt(4)));
				valList.add(v);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

// Sorting crescente -----------------------------------------------------------------------------------------------------------------------------

		List<ValutazioneUtente> result1 = f.sortUserEvaluations(valList, "Valutazione crescente");

		for (int i = 0; i < result1.size() - 1; i++) {

			assertTrue(result1.get(i).getStelle() <= result1.get(i + 1).getStelle(), "La lista non è crescente!");

		}

// Sorting decrescente ---------------------------------------------------------------------------------------------------------------------------

		List<ValutazioneUtente> result2 = f.sortUserEvaluations(valList, "Valutazione decrescente");

		for (int i = 0; i < result2.size() - 1; i++) {

			assertTrue(result2.get(i).getStelle() >= result2.get(i + 1).getStelle(), "La lista non è decrescente!");

		}

	}

	@Test
	public void testFilterUserEvaluation() {

		System.out.println("Test filtri");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ApplicaFiltro f = new ApplicaFiltro();
		Random r = new Random();
		List<ValutazioneUtente> valList = new ArrayList<ValutazioneUtente>();

		for (int i = 0; i < 1000; i++) {
			try {
				Date date = formatter.parse("1990-08-02");

				ValutazioneUtente v = new ValutazioneUtente(r.nextInt(100), "utente1", "utente2", date, r.nextInt(6),
						categorie.get(r.nextInt(3)), lingue.get(r.nextInt(3)), periodi.get(r.nextInt(4)));
				valList.add(v);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

// Filter ----------------------------------------------------------------------------------------------------------------------------------------

		
		List<Filtro> filtri = new ArrayList<Filtro>();

		List<String> categoriaScelta = new ArrayList<String>();
		categoriaScelta.add(categorie.get(r.nextInt(3)));

		List<String> linguaScelta = new ArrayList<String>();
		linguaScelta.add(lingue.get(r.nextInt(3)));

		List<String> periodoScelto = new ArrayList<String>();
		periodoScelto.add(periodi.get(r.nextInt(4)));

		List<String> stelleScelte = new ArrayList<String>();
		stelleScelte.add(Integer.toString(r.nextInt(6)));

		filtri.add(new Filtro("categoria", categoriaScelta));
		filtri.add(new Filtro("lingua", linguaScelta));
		filtri.add(new Filtro("periodo", periodoScelto));
		filtri.add(new Filtro("stelle", stelleScelte));

		List<ValutazioneUtente> result = f.filterUserEvaluations(valList, filtri);

		for (int i = 0; i < result.size(); i++) {

			assertEquals(result.get(i).getCategoria(), categoriaScelta.get(0));
			assertEquals(result.get(i).getLingua(), linguaScelta.get(0));
			assertEquals(result.get(i).getPeriodo(), periodoScelto.get(0));
			assertEquals(Integer.toString(result.get(i).getStelle()), stelleScelte.get(0));

		}

	}

}
