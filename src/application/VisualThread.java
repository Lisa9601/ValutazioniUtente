package application;

import application.UtenteValutazione.UtenteDao;

public class VisualThread extends Thread{

	private String user;

	
	public VisualThread(String u) {
		user = u;
	}
	
	
	static synchronized void incrementCounter(String user) {
			
		UtenteDao ud = UtenteDao.getInstance();
		ud.aumentaVisualizzazioni(user);
		
	}
	
    @Override
    public void run() {
    	incrementCounter(user);
    }
    
    
	
}
