package application;

import java.util.List;

import application.UtenteValutazione.ValutazioneUtente;


public class ApplicaFiltro implements InterfacciaFiltri {

		
	public ApplicaFiltro() {}
	
	// Ordina una lista in base al criterio scelto 
	public List<ValutazioneUtente> sortUserEvaluations(List<ValutazioneUtente> uel, String criterio) {	
		
		if(uel == null) {
			return uel;
		}
		else {
			if (criterio.equals("Valutazione crescente")) {
				quickSortCrescente(uel,0,uel.size()-1);				
			}
			else if (criterio.equals("Valutazione decrescente")) {				
				quickSortDecrescente(uel,0,uel.size()-1);			
			}	
			
			return uel;
		}
		
	}
	
	
	// Filtra una lista secondo i criteri scelti
	public List<ValutazioneUtente> filterUserEvaluations(List<ValutazioneUtente> uel, List<Filtro> filtri){
		
		for(int i=0; i<filtri.size();i++) {
			
			Filtro f = filtri.get(i);
				
			switch(f.getTipo()) {
			
				case "categoria" : uel = this.filtraPerCategoria(uel,f.getValori());
									break;
				
				case "stelle" : uel = this.filtraPerNumeroStelle(uel,f.getValori());
									break;
									
				case "lingua" : uel = this.filtraPerLingua(uel,f.getValori());
									break;
				
				case "periodo" : uel = this.filtraPerPeriodo(uel,f.getValori());			
									break;
				
				default : System.out.println("ERRORE! La categoria scelta non è presente nella lista!");
									break;
			}		
		}		
		return uel;
	}
	
	
	//quicksort con ordinamento crescente in base alle valutazioni
	public void quickSortCrescente(List<ValutazioneUtente> list, int low,int high){

		if (low < high){

	       int pi = partition(list, low, high);

	        quickSortCrescente(list, low, pi - 1);  
	        quickSortCrescente(list, pi + 1, high); 
	    }
	}
	

    int partition(List<ValutazioneUtente> list, int low, int high){
    	
        int pivot = list.get(high).getStelle();  
        int i = (low-1); 
        for (int j=low; j<high; j++) { 
        
            if (list.get(j).getStelle() <= pivot) { 
            	
                i++;  
                ValutazioneUtente temp = list.get(i) ; 
                list.set(i,list.get(j)); 
                list.set(j, temp); 
            } 
        } 
  
        ValutazioneUtente temp = list.get(i+1); 
        list.set(i+1, list.get(high)); 
        list.set(high, temp); 
  
        return i+1; 
    }
    
    //Quicksort con ordinamento decrescente in base alle valutazioni
	public void quickSortDecrescente(List<ValutazioneUtente> list, int low,int high){
		
		if (low < high){

	       int pi = partitionDecrescente(list, low, high);

	        quickSortDecrescente(list, low, pi - 1); 
	        quickSortDecrescente(list, pi + 1, high); 
	        
	    }
	}
		
	
	
    int partitionDecrescente(List<ValutazioneUtente> list, int low, int high){
    	
        int pivot = list.get(high).getStelle();  
        int i = (low-1); 
        for (int j=low; j<high; j++) { 
        
            if (list.get(j).getStelle() >= pivot) { 
            	
                i++; 
                ValutazioneUtente temp = list.get(i) ; 
                list.set(i,list.get(j)); 
                list.set(j, temp); 
            } 
        } 
  
        ValutazioneUtente temp = list.get(i+1); 
        list.set(i+1, list.get(high)); 
        list.set(high, temp); 
  
        return i+1; 
    }
    
    public List<ValutazioneUtente> filtraPerPeriodo(List<ValutazioneUtente> uel, List<String> periodo){  	
    	
    	if(periodo.size() < 1) {    	
    		return uel;    		
    	}   	
    	
    	int i=0;
	
    	while(i < uel.size()) {
		
    		if (!periodo.contains(uel.get(i).getPeriodo())) {
			
    			uel.remove(i);
			
    		}
    		else {
    			i++;
    		}
    	}
	
    	return uel;
    }	

    
    public List<ValutazioneUtente> filtraPerLingua(List<ValutazioneUtente> uel, List<String> lingua){
    	
    	if(lingua.size() < 1) {        	
    		return uel;   		
    	}   
    	
    	int i=0;
    	
    	while(i < uel.size()) {
    		
    		if (!lingua.contains(uel.get(i).getLingua())) {
    			
    			uel.remove(i);
    			
    		}
    		else {
    			i++;
    		}
    	}
    	
    	return uel;
    }
    
    
    public List<ValutazioneUtente> filtraPerCategoria(List<ValutazioneUtente> uel, List<String> categoria){
 
    	if(categoria.size() < 1) {        	
    		return uel;    		
    	}   
    	
    	int i=0;
    	
    	while(i < uel.size()) {
    		
    		if (!categoria.contains(uel.get(i).getCategoria())) {   			
    			uel.remove(i);			
    		}
    		else {
    			i++;
    		}
    	}
    	
    	return uel;
    } 
    
 
    public List<ValutazioneUtente> filtraPerNumeroStelle(List<ValutazioneUtente> uel, List<String> numeroStelle){
    	
    	if(numeroStelle.size() >= 0) {       	
    		return uel;
    	} 
    	
    	if(numeroStelle.size() < 1) {       	
    		return uel;    		
    	} 
    	
    	int i=0;
    	
    	while(i < uel.size()) {
    		
    		if (!numeroStelle.contains(Integer.toString(uel.get(i).getStelle())) ) {
    			
    			uel.remove(i);
    			
    		}
    		else {
    			i++;
    		}
    	}
    	
    	return uel;
    }
	
}
