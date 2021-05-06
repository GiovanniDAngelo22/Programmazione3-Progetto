package tris;

/**
 * Una delle possibili @Strategy che può selezionare l'@IAPlayer
 * quando viene chiamato effettuaMossa() Viene restituita una casella libera a caso
 */


public class RandomStrategy implements Strategy {
	
	private int scelta;

	public int effettuaMossa(int simbolo) {
		
		boolean cond = false;
		
		while (!cond)
		{
			scelta = (int) (Math.random() * 9);
			if (GameController.getBoardIndex(scelta) == -1)
			{
				cond = true;
			}
		}
	
		return scelta;
		

	}

}
