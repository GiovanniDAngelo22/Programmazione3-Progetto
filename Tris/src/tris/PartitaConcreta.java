package tris;

/**
 *
 *	Questa classe rappresenta un oggetto di tipo Partita, necessaria quando bisogna 
 *	inserire/recuperare le tuple dal database.
 */
public class PartitaConcreta {

	private String username;
	private String difficolta;
	private int turni;
	
	public PartitaConcreta(String username, String difficolta, int turni)
	{
		this.setUsername(username);
		this.setDifficolta(difficolta);
		this.setTurni(turni);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(String difficolta) {
		this.difficolta = difficolta;
	}

	public int getTurni() {
		return turni;
	}

	public void setTurni(int turni) {
		this.turni = turni;
	}
	
	
}
