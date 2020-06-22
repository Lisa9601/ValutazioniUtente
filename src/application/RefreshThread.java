package application;

import javafx.application.Platform;

public class RefreshThread extends Thread{

	private boolean exit = false;
	private Runnable updater;
	
	public RefreshThread(Runnable updater) {
		this.updater = updater;
	}
	
	public void stopThread() { 
	        exit = true; 
	} 
	
	
    @Override
    public void run() {

        while (!exit) {
        	
			try {
                Platform.runLater(updater);		//Usato per interagire con la GUI : l'aggiormento è messo in una coda e verrà gestito dal thread della GUI non appena possibile
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        
        }
    }
	
}
