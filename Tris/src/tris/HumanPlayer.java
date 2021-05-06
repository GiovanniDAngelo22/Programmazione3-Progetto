package tris;

/**
 * Classe che rappresenta un giocatore umano. Usata per poter comunicare con @IAPlayer come Observer/Observable
 */
public class HumanPlayer extends Player{
	public HumanPlayer (String nome)
	{
		this.setNome(nome);
		super.setState(true);
	}
	

	/**
	 * Funzione update dell'Observer. Quando viene richiamata, significa che è il suo turno di fare una mossa
	 */
	public void update()
	{
		super.setState(true);
	}


}
