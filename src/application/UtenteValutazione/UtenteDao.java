package application.UtenteValutazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtenteDao {

	//Singleton class 
	
	private static UtenteDao ud = null;
	
	private static String user = "root";
    private static String password = "lisa";
    private static String db_url = "jdbc:mysql://127.0.0.1:3306/progetto_ispw";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    private UtenteDao() {
    	try {
    	
        	Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(db_url, user, password);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static synchronized UtenteDao getInstance() {
    	if(ud == null) {
    		ud = new UtenteDao();
    	}
    	
    	return ud;
    }
    
    //Cerca le informazioni dell'utente con il nickname specificato
    public Utente trovaInfoUtente(String nickName){
    	
        Statement stmt = null;
        Utente u = null;
        
        try {
            
            //query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM utenti WHERE nick = '" + nickName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
            	
                String nick = rs.getString("nick");
                String sesso = rs.getString("sesso");
                Date dataN = rs.getDate("data_nascita");
                String nazione = rs.getString("nazionalita");
                Date dataI = rs.getDate("data_iscrizione");
                int visual = rs.getInt("visualizzazioni");

                u = new Utente(nick,sesso,dataN,nazione,dataI); 
                u.setVisualizzazioni(visual);
                
            }   
            
            rs.close();
            stmt.close();
	       
			
        }catch(Exception e) {
        	e.printStackTrace();
        }
    
        
		return  u;

    }
      
    
    // Ritorna la lista dei nickname degli utenti registrati nel sistema
    public List<String> trovaListaUtenti(){
    
        Statement stmt = null;
        List<String> utenti = new ArrayList<String>();
        
        try {
            
            //query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT nick FROM utenti";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {

	            utenti.add(rs.getString("nick"));
            
            }   
            
            rs.close();
            stmt.close();
	       
			
        }catch(Exception e) {
        	e.printStackTrace();
        }
    
        return utenti;
    	
    }
    
    
    //Aumenta di 1 le visualizzazioni del profilo dell'utente specificato
    public void aumentaVisualizzazioni(String user) {
    	
        Statement stmt = null;
        
        try {
            
            //query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "UPDATE utenti SET visualizzazioni = visualizzazioni + 1 WHERE nick = '"+ user +"'";
            stmt.executeUpdate(sql);  
            stmt.close();
	       
			
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
    }
	
}
