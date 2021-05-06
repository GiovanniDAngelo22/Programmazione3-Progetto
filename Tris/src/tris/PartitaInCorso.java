package tris;

/**
 * Descrive uno dei possibili @State concreti che può avere una partita.
 * Durante questo stato, l'unica operazione che può compiere è proseguire cambiando il giocatore, andandolo indirettamente ad avvisare
 * che è iniziato il suo turno.
 */

public class PartitaInCorso implements State {

	@Override
	public void computa() {
		GameController.cambiaGiocatore();
	}


}
