package tris;

/**
 * Interfaccia astratta della classe State, per implementarne l'ononimo pattern. Il comportamento di computa() varia 
 * in base allo stato concreto.
 */

public interface State {
	public void computa();
}
