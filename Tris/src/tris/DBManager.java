package tris;

import java.sql.*;


/**
 * Il mananager del Database. Qualunque comunicazione verso/dal Database avviene attraverso questa classe. 
 * La classe è implementata tramite Singleton.
 */
public class DBManager { 

	private static DBManager instance = null;
	private DBManager() {}
	private Connection connect = null;
	private Statement statement;
	private ResultSet resultSet = null;
	private int rowsAffected = 0;
	
	/**
	 * Funzione che restituisce l'istanza del DBManager, o ne crea una se non è stata già creata
	 */

	public static DBManager getIstance()
	{
		if (instance == null)
		{
			instance = new DBManager();
			return instance;
		}
		return instance;
	}
	
	/**
	 * Carica il driver (MySql in questo caso) ed avvia la connessione al server.
	 * Il collegamento al DB avviene tramite l'utente "%" che ha i permessi di SELECT, UPDATE e INSERT
	 */
	public void getConnection() throws Exception
	{
			  try
			  {
				  
				  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				  connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe?serverTimezone=UTC","%","");
			  }
			  catch (Exception e)
			  {
				  throw e;
			  }
	 }
	
	/**
	 * Esegue una query il cui risultato si trova nel ResultSet, che dovrà poi essere interpretato dalle relative classi DB
	 */

	public ResultSet executeQuery(String query) throws Exception
	{
		 try
		  {
			    statement = connect.createStatement();
				resultSet = statement.executeQuery(query);
				return resultSet;
			
		  }
		  catch (Exception e)
		  {
			  throw e;
		  }
	}
	
	/**
	 * Esegue una query di tipo inserimento
	 */
	public int insertQuery(String query) throws Exception
	{
		 try
		  {
			statement = connect.createStatement();
			rowsAffected = statement.executeUpdate(query);
			return rowsAffected;
		  }
		  catch (Exception e)
		  {
			  throw e;
		  }

	}
	
	/**
	 * Chiude la connessione col il DB
	 */
	public void closeConnection()
	{
		try {
		      if (resultSet != null) {
		        resultSet.close();
		      }
		      if (statement != null) {
		        statement.close();
		      }
		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {
		    }
	};
	
}
