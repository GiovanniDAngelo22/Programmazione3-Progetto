package tris;


import javax.swing.ImageIcon;


/**
 * Interfaccia funzionale per la Factory delle texture, che si occupa di caricare le immagini. 
 * Viene usata in @GameBoardGUI
 */
@FunctionalInterface
public interface FactoryTexture {
	
	/**
	 * Unico metodo astratto
	 */
	ImageIcon creaImmagine();

	/**
	 * Metodo implementato di default, va a richiamare il metodo astratto che necessita di una implementazione
	 */

	default ImageIcon crea() {
		return creaImmagine();
	}
}

