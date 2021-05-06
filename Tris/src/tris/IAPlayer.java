package tris;

/**
 * Giocatore gestito dalla CPU. 
 */
public class IAPlayer extends Player  {
	private Strategy strategia;
	
	public IAPlayer (String nome)
	{
		this.setNome(nome);
	}
	
	/**
	 * Funzione che viene chiamata quando è il turno della CPU
	 * La cpu esegue una mossa che dipende in base alla @Strategy scelta
	 * Nell'attuale implementazione, La difficoltà scelta ad inizio partita dà un valore soglia (Facile = 25, Medio = 50, Difficile 75
	 * Impossibile = 100) che, se superato, imposta la strategia @MiniMaxStrategy, altrimenti @RandomStrategy . 
	 */
	public void update() {
		int rng;
		rng = (int) (Math.random() * 101);
		if (rng > GameController.getK())
		{
			System.out.println("Scelta random");
			this.setStrategia(new RandomStrategy());
		}
		else
		{
			System.out.println("Scelta del minimax");
			this.setStrategia(new MiniMaxStrategy());
		}
		Integer i = strategia.effettuaMossa(getSimbolo());
		//Una volta scelta la casella da voler premere, avvio la chain of responsability.
		GameController.eseguiMossa(i.toString());
	}

	public Strategy getStrategia() {
		return strategia;
	}

	public void setStrategia(Strategy strategia) {
		this.strategia = strategia;
	}


}
