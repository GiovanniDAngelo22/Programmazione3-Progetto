package tris;

/**
 * Interfaccia del pattern Observer. Viene implementata negli oggetti osservatori, che attendono i cambiamenti dell'oggetto osservato. 
 */

public interface Observer{

	void update(); 	//funzione che viene richiamata dall'observer quando deve notificare l'osservatore

}
