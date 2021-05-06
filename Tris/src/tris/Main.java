package tris;
import java.awt.EventQueue;


/**
 * Il Main si occupa solo di avviare il @MainController 
 */
public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController mainController = new MainController();
					mainController.avvia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

}
