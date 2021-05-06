package tris;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe che si occupa delle operazioni relative alla tabella Partita. 
 * E' la classe che fa da tramite al programma per il @DBManager.
 */
public class DBPartita implements Partita {

	private DBManager dbmanager;
	
	public DBPartita()
	{
		dbmanager = DBManager.getIstance();
	}


	/**
	 * Inserisce la partita nel database
	 * Prende come input una @PartitaConcreta
	 */
	@Override
	public void InserisciPartita(PartitaConcreta pc) {
		String query = new String("INSERT INTO partita (Username, Difficolta, Turno) VALUES ('" 
									+ pc.getUsername() + "','" + pc.getDifficolta() + "','" + pc.getTurni() + "');");
		System.out.println(query);
		int rowsAffected = 0;
		try 
		{
			rowsAffected = dbmanager.insertQuery(query);
		}
		  catch (Exception e)
		  {
				System.out.println("inserimento non riuscito");
		  }
		if (rowsAffected > 0)
		  {
			System.out.println("inserimento riuscito");
		  }

	}

	/**
	 * Ritorna una lista di @PartitaConcreta finite, ordinate per difficoltà e turno di fine.
	 */
	@Override
	public List<Object> getHighScore() {
		List<Object> list = new ArrayList<>();
		String query = "SELECT DISTINCT Username, Difficolta, Turno FROM partita ORDER BY Difficolta, Turno";
		ResultSet resultSet = null;
		try {
			resultSet = dbmanager.executeQuery(query);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		  try {
			while (resultSet.next())
			  {
				list.add(new PartitaConcreta(resultSet.getString("Username"),resultSet.getString("Difficolta"),resultSet.getInt("Turno")));
			  }
			System.out.println("Partite recuperate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;		
	}
}
