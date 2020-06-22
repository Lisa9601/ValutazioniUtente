package application.UtenteValutazione;

import java.sql.*;
import java.util.ArrayList;

public class ValutazioneUtenteDao {
	
	private static String user = "root";
    private static String password = "lisa";
    private static String db_url = "jdbc:mysql://127.0.0.1:3306/progetto_ispw";
    private static String driver = "com.mysql.jdbc.Driver";

    
    public ValutazioneUtenteDao() {
    	
    }
    
    
    // Cerca le informazioni della valutazione specificata dal numero 
    public ValutazioneUtente trovaInfoValutazione(int num){
    	
    	
    	Statement stmt = null;
        Connection conn = null;
        ValutazioneUtente v = null;
        
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(db_url, user, password);
            
            //query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM valutazioni WHERE numero = '" + num + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
                int numero = rs.getInt("numero");
                String recensore = rs.getString("recensore");
                String recensito = rs.getString("recensito");
                Date dataInserimento = rs.getDate("data_inserimento");
                String categoria = rs.getString("categoria");
                String lingua = rs.getString("lingua");
                int stelle = rs.getInt("stelle"); 
                String periodo = rs.getString("periodo");
                String testo = rs.getString("testo");
                
                if (testo != null) {
                  	 
                  	 v = new ValutazioneUtente(numero,recensore,recensito,dataInserimento,stelle,categoria,lingua,periodo,testo);
                   
                   }
                   else {
                  	 
                  	 v = new ValutazioneUtente(numero,recensore,recensito,dataInserimento,stelle,categoria,lingua,periodo);
                  	 
                   }
            }
            
            rs.close();
            stmt.close();
			conn.close();
	       
			
        }catch(Exception e) {
        	e.printStackTrace();
        }
    
        return v;

    }
    
    
    // Cerca la lista delle valutazioni associate al nickname passato come parametro
    public ArrayList<ValutazioneUtente> trovaValutazionePerRecensito(String nome) {
    	
    	Statement stmt = null;
        Connection conn = null;
    	ArrayList<ValutazioneUtente> list = new ArrayList<ValutazioneUtente>();
    	
    	try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(db_url, user, password);
             
             //query
             stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             String sql = "SELECT * FROM valutazioni WHERE recensito = '" + nome + "'";
             ResultSet rs = stmt.executeQuery(sql);
             
             while(rs.next()) {

	             int numero = rs.getInt("numero");
	             String recensore = rs.getString("recensore");
	             String recensito = rs.getString("recensito");
	             Date dataInserimento = rs.getDate("data_inserimento");
	             String categoria = rs.getString("categoria");
	             String lingua = rs.getString("lingua");
	             int stelle = rs.getInt("stelle");
	             String periodo = rs.getString("periodo");
	             String testo = rs.getString("testo");
	             
	             if (testo != null) {
	            	 
	            	 list.add(new ValutazioneUtente(numero,recensore,recensito,dataInserimento,stelle,categoria,lingua,periodo,testo));
	             
	             }
	             else {
	            	 
	            	 list.add(new ValutazioneUtente(numero,recensore,recensito,dataInserimento,stelle,categoria,lingua,periodo));
	            	 
	             }
             }
             
             
             rs.close();
             stmt.close();
 			conn.close();
 	       
 			
         }catch(Exception e) {
         	e.printStackTrace();
         }
    	
    	return list;
    	
    }

}
