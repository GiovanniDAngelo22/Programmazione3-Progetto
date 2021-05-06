package tris;

/**
 * Interfaccia utilizzata per implementare il pattern Strategy.
 * Descrive quale strategia deve attuare un determinato oggetto. Nel progetto, viene usato per descrivere la strategia che deve
 * attuare la classe @IAPlayer nel momento in cui deve effettuare una mossa
 */

public interface Strategy {
	public int effettuaMossa(int simbolo);
}
