package tris;

import java.sql.ResultSet;

/**
 *  La classe che si occupa delle operazioni relative alla tabella User. 
 *  E' la classe che fa da tramite al programma per il @DBManager.
 */
public class DBUser implements User {
	
	private DBManager dbmanager;
	public DBUser()
	{
		dbmanager = DBManager.getIstance();
	}
	
	
	
	/**
	 * Funzione di Login, prende come input un username e password e ritorna true nel caso un utente con
	 * quelle credenziali è presente nel DB, false altrimenti.
	 */
	@Override
	public boolean login(String username, String password) throws Exception {
		String query = new String("SELECT * FROM `user` WHERE `Username` LIKE '" + username + "' AND `Password` LIKE '" + password + "'");
		ResultSet resultSet;
		resultSet = dbmanager.executeQuery(query);
		  while (resultSet.next())
		  {
			System.out.println("login riuscito");
			return true;
		  }
		System.out.println("login non riuscito");
		return false;
	}



	/**
	 * Funzione di registrazione. Prende come input solo un username ed una password.
	 * Ritorna true se la registrazione è avvenuta con successo, false se l'utente esiste già 
	 * (tipicamente l'username è chiave primaria nel DB)
	 */
	public boolean register(UserConcreto user) throws Exception {
		String query = new String("INSERT INTO `user` (`Username`, `Password`) VALUES ('" + user.getUsername() + "','" + user.getPassword() + "');");
		int rowsAffected;
		try 
		{
			rowsAffected = dbmanager.insertQuery(query);
		}
		  catch (Exception e)
		  {
				System.out.println("registrazione non riuscita");
			  return false;
		  }
		if (rowsAffected > 0)
		  {
			System.out.println("registrazione riuscita");
			return true;
		  }
		return false;

	}	

}
