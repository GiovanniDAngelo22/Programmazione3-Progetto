package tris;

/**
 * Descrive uno dei possibili stati concreti di una partita, secondo il paradigma @State
 * L'unica azione possibile che può essere eseguita è quella di mostrare il vincitore della partita.
 */

public class GameOver implements State {

	private int vincitore;
	
	public GameOver(int vincitore)
	{
		this.vincitore = vincitore;
	}
	
	@Override
	public void computa() {
		
		
		if (vincitore == GameController.getPlayers()[0].getSimbolo()) // ha vinto il giocatore 1
		{
			//inserisco la vittoria nel db
			PartitaConcreta pc = new PartitaConcreta(GameController.getPlayers()[0].getNome(), GameController.getDifficolta(), GameController.getTurni());
			MainController.getDbPartita().InserisciPartita(pc);
			//mostro il vincitore
			GameController.getGameBoardGUI().mostraVincitore(vincitore);
		}
		else //pareggio o perdita
		{
			GameController.getGameBoardGUI().mostraVincitore(vincitore);
		}
	}

}
