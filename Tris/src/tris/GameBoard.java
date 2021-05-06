package tris;

/**
 *	Interfaccia con le funzioni devono essere implementate in una comune scacchiera da gioco, implementata da @GameBoardGUI
 */

public interface GameBoard {
	
	void mostraSimbolo(int indice, int simbolo);
	void mostraVincitore(int vincitore);
	void chiudi();

}
