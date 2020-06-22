package application;

import java.util.List;

public class Filtro {

	private String tipo;    		//tipo del filtro (es. categoria, periodo, numero di stelle )
	private List<String> valori;	//valore da applicare nel filtro
	
	
	public Filtro(String tipo, List<String> valori) {
		
		this.tipo = tipo;
		this.valori = valori;
		
	}
	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public List<String> getValori() {
		return valori;
	}


	public void setValori(List<String> valori) {
		this.valori = valori;
	}	
	
}
