package tris;

/**
 * Descrive uno dei possibili @State concreti che pu� avere una partita.
 * Durante questo stato, l'unica operazione che pu� compiere � proseguire cambiando il giocatore, andandolo indirettamente ad avvisare
 * che � iniziato il suo turno.
 */

public class PartitaInCorso implements State {

	@Override
	public void computa() {
		GameController.cambiaGiocatore();
	}


}
