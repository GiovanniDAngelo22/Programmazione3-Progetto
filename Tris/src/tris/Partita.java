package tris;

import java.util.List;

/**
 *  Interfaccia che indica le funzioni necessarie che deve avere un @DBPartita
 */

public interface Partita {
	
	void InserisciPartita(PartitaConcreta pc); //Inserisce una partita del db
	List<Object> getHighScore(); //Recupera una lista di Partite ordinate per punteggio
}
