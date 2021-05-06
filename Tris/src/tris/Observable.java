package tris;

/**
 * Interfaccia del pattern Observer. Viene implementata negli oggetti che devono essere osservati, tenendo traccia di tutti i suoi
 * osservatori. 
 */

public interface Observable  {

	void notifica(); //Manda un segnale a tutti i suoi subscriber, andando a richiamare la loro funzione di update
	void attach(Observer observer); //aggiungi un subscriber alla sua lista di subscribers
	void detach(); //elimina un subscriber dalla sua lista di subscribers
	
}
