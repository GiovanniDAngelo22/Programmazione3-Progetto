package tris;

import java.util.HashMap;

/**
 * Strategia che può utilizzare la CPU.
 * E' implementata con l'algoritmo MiniMax, che seleziona la mossa che minimizza la massima perdita possibile.
 */

public class MiniMaxStrategy implements Strategy {
	HashMap<Integer,Integer> value = new HashMap<>(); //LookUpTable per valutare le foglie dell'albero minimax
	
	@Override
	public int effettuaMossa(int simboloGiocatore) {
		int[][] board = GameController.getBoard();
		int sceltai = 0;
		int sceltaj = 0;
		int scelta = 0;
		int scoreMAX = Integer.MIN_VALUE;
		int score;
		if (simboloGiocatore == 0) // La valutazione euristica delle foglie dipende dal simbolo della CPU
		{
		value.put(-1,0); //Se la partita finisse in parità (-1) avremmo un punteggio neutro
		value.put(0,10); //Se la partita la vince la CPU avremmo un punteggio positivo
		value.put(1,-10); //Se la partita la vince Il giocatore avremmo un punteggio negativo
		}
		else  
		{
		value.put(-1,0); 
		value.put(0,-10); 
		value.put(1,10); 
		}
		
		for (int i = 0; i < 3; i++) //scorre la scacchiera
		{
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == -1) // controlla se è disponibile la mossa
				{
					if (simboloGiocatore == 0) 
					{
						board[i][j] = 0; //Riempio la casella i,j con il mio simbolo
						score = minimax(board, false, simboloGiocatore); //Il prossimo turno sarà del giocatore che vuole minimizzare il punteggio
						board[i][j] = -1; //Ripristino la casella i,j
						if (score > scoreMAX)  //Prendo lo score "peggiore" dal punto di vista del giocatore minimizzante, quindi il massimo
						{
							scoreMAX = score;
							sceltai = i;
							sceltaj = j;
						}
					}
					else
					{
						board[i][j] = 1;
						score = minimax(board, false, simboloGiocatore);
						board[i][j] = -1;
						if (score > scoreMAX)
						{
							scoreMAX = score;
							sceltai = i;
							sceltaj = j;
						}
						
					}
				}
			}
		}
		scelta = (sceltai * 3) + sceltaj; //effettuo il cambio da indice [3x3] a indice [0...8]
		return scelta;
	}
	
	/**
	 * La funzione minimax è una funzione ricorsiva. In input prende una board dove valutare quale sarà la migliore mossa.
	 * La migliore mossa viene valutata analizzando tutti i possibili outcome, seguendo la logica che un turno sarà del giocatore 
	 * che vuole massimizzare il punteggio, l'altro sarà del giocatore che vuole minimizzarlo. isMaximizing indica se è il giocatore
	 * massimizzante o minimizzante. 
	 */
	
	public int minimax(int[][] board, boolean isMaximizing, int simbolo) 
	{
		Integer vincita = controllaVittoria(board, isMaximizing); //Caso base, la partita è finita ed è stato constatato un vincitore/pareggio
		if (vincita != null)
			{
			return value.get(vincita); 
			}
		//
		if (isMaximizing) //se il giocatore vuole massimizzare il punteggio
		{
			int scoreMax = Integer.MIN_VALUE;
			//Valuto il punteggio che ottengo in base a dove posiziono il simbolo e ne prendo il massimo
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if (board[i][j] == -1)
					{	
						if (simbolo == 0)
							board[i][j] = 0;
						else
							board[i][j] = 1;
						int score = minimax(board,  false, simbolo); //il turno dopo sarà del giocatore minimizzante
						board[i][j] = -1;
						scoreMax = Math.max(score,scoreMax);
						
					}
				}
			}
			return scoreMax;
		}
		else //Il giocatore vuole minimizzare il punteggio
		{
			int scoreMin = Integer.MAX_VALUE;
			//Valuto il punteggio che ottengo in base a dove posiziono il simbolo e ne prendo il minimo
			for (int i = 0; i < 3; i++) 
			{
				for (int j = 0; j < 3; j++)
				{
					if (board[i][j] == -1)
					{
						
						if (simbolo == 0)
							board[i][j] = 1;
						else
							board[i][j] = 0;
		
						int score = minimax(board, true, simbolo); //il turno dopo sarà del giocatore massimizzante
						board[i][j] = -1;
						scoreMin = Math.min(score, scoreMin);
						
					}
				}
			}
			return scoreMin;
		}
	}
	
	public Integer controllaVittoria (int[][] board, boolean isMaximizing) { // null se non si vince, -1 parità, +/-10 se vince/perde il suo simbolo
		Integer vincitore = null;
		int count = 0;

		
		for (int i = 0; i < 3; i++)
		{
			if (board[i][0] != -1 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
			{
				vincitore = board[i][0];
				return vincitore;

			}
			if (board[0][i] != -1 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
			{
				vincitore = board[0][i];
				return vincitore;
			}
		}
		if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
		{
			vincitore = board[0][0];
			return vincitore;

		}
		if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
		{
			vincitore = board[0][2];
			return vincitore;
		}
		
		//controllo quante caselle sono riempite per valutare se la partita è finita in pareggio
		for (int i = 0; i < 3; i++)
		{
			{
				for (int j = 0; j < 3; j++)
				{
					if (board[i][j] != -1)
					{
						count++;
					}
				}
			}
		}
		if (count >8)
		{
			return -1;
		}
		
		
	
		return null;
	}
}
