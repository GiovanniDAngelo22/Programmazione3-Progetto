package tris;

import javax.swing.JDialog;


/**
 * La classe MainController si occupa di inizializzare il gioco attraverso la funzione init. 
 * In particolare, inizializza il Database Manager ed avvia la schermata di Login.
 *
 */
public class MainController {
	
	private static DBManager db;
	private static DBPartita dbPartita;
	private static DBUser dbUser;
	
	public MainController(){}
	public  void avvia(){
		init();
		}
	
	/**
	 * Il primo processo che viene avviato, si occupa di creare una connessione al database e avviare la schermata di login.
	 * Instanzia la classe @DBUser e la passa alla schermata di login ed instanzia la classe @DBPartita conservandone il riferimento.
	 */
	private void init() {

		try {
			MainController.setDBUser(new DBUser());
			MainController.setDbPartita(new DBPartita());
			db = DBManager.getIstance();
			db.getConnection();
			LoginGUI dialog = new LoginGUI(MainController.getUser());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			} catch (Exception e)
			{
			e.printStackTrace();
			}
	}
	


	public static Partita getDbPartita() {
		return dbPartita;
	}


	public static void setDbPartita(DBPartita dbPartita) {
		MainController.dbPartita = dbPartita;
	}
	
	public static DBUser getUser() {
		return dbUser;
	}
	
	public static void setDBUser(DBUser dbUser) {
		MainController.dbUser = dbUser;
	}

}
