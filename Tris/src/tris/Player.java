package tris;

/**
 * Classe astratta che definisce un giocatore. 
 * In una partita di Tris, il giocatore assume sia il ruolo di osservatore che osservato
 * in modo da poterne gestire la sincronizzazione. 
 * 
 */

public abstract class Player implements  Observer, Observable {
	private String nomePlayer;
	private int simbolo;
	private boolean stato=false; 
	private Observer avversario;
	
	public boolean getState() {
		return stato;
	};
	
	//Indica se è il turno del giocatore o meno.
	public void setState(boolean stato) {
		this.stato = stato;
		if (stato == false) // Quando viene settato a false, indica che è il turno dell'avversario, andando a notificarlo
		{
			this.notifica();
		}
	};
	
	public void notifica() //Va ad avvertire l'avversario che è il suo turno. 
	{
		avversario.update(); 
	}
	
	@Override
	public void attach(Observer observer) {
		this.avversario = observer;	
	}

	@Override
	public void detach() {
		this.avversario = null;
	}
	
	public String getNome() {
		return nomePlayer;
	}

	public void setNome(String nome) {
		this.nomePlayer = nome;
	}
	
	public int getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(int simbolo) {
		this.simbolo = simbolo;
	}
	
	//Stampa il proprio simbolo sul pulsante preso in input nella schermata presa in input.
	public void mostraSimbolo(GameBoard Gui, int pulsante) {
		Gui.mostraSimbolo(pulsante, simbolo);
	}

}
