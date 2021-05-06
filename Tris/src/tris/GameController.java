package tris;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  La classe GameController si occupa di gestire la logica di gioco ed è responsabile del salvataggio/caricamento dei dati
 */
public class GameController {
	
	static private  GameBoard gameBoard; //Interfaccia grafica necessaria per rappresentare la scacchiera.
	static private  Player players[] = new Player[2];
	static private  int playerAttivo = 0; // 0 è il giocatore, 1 è il computer
	static private  int[][] board = new int[3][3];
	static private  int vincitore; 
	static private  int turni;
	static private  String difficolta; 
	static private  State statoPartita;
	static private  int k; //percentuale che viene scelta ad inizio partita, determina la difficoltà
	
	/**
	 *   Funzione avviata a priori, inizializza il GameController
	 */

	static public void inizializzaPartita(String username)
	{
		Player players[] = new Player[2];
		players[0] = new HumanPlayer(username);
		players[1] = new IAPlayer("CPU");
		players[0].setSimbolo(1);
		players[1].setSimbolo(0);
		players[0].attach(players[1]);
		players[1].attach(players[0]);
		GameController.setPlayers(players);
		
	}
	
	/**
	 *   Setta una nuova partita. Prende come input la difficoltà (scelta in base alla percentuale k) e la scelta di partire 
	 *   per primi o secondi
	 */

	
	static public void nuovaPartita(int k, int primo) //giocatore singolo
	{
		
		for (int i = 0; i < 9; i++)
			GameController.setBoardValue(i, -1);
		turni = 0;
		vincitore =-1;
		GameController.setK(k);
		if (primo == 0) // non è primo giocatore, inizia il pc
		{
			GameController.getPlayers()[0].setSimbolo(0);
			GameController.getPlayers()[1].setSimbolo(1);
			GameController.setPlayer_attivo(1);
			players[playerAttivo].update();
		}
		else
		{
			GameController.getPlayers()[0].setSimbolo(1);
			GameController.getPlayers()[1].setSimbolo(0);
			GameController.setPlayer_attivo(0);
			players[playerAttivo].update();
		}
		
	}
	
	/**
	 *   Continua una partita salvata in precedenza. L'id preso come input indica il numero del salvataggio
	 *   Il salvataggio viene cercato in una cartella nella Home dell'user, avente come nome il nickname del giocatore 1.
	 *   Carica un oggetto di tipo GameData, ne estrapola le informazioni e le imposta nel GameController
	 */

	
	static public void continuaPartita(int id) {
		GameData save = null;
		String userHome = System.getProperty("user.home");
	    userHome = userHome.replace("\\","/");
	    userHome = userHome + File.separator + GameController.getPlayers()[0].getNome();
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(userHome+File.separator+"save"+id);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            save = (GameData)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Salvataggio caricato");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Salvataggio non caricato");
        }
        if (save != null)
        {
        	GameController.setPlayer_attivo(save.getPlayerAttivo());
        	GameController.players[0].setNome(save.getPlayer_names()[0]);
        	GameController.players[1].setNome(save.getPlayer_names()[1]);
        	GameController.players[0].setSimbolo(save.getSimboli()[0]);
        	GameController.players[1].setSimbolo(save.getSimboli()[1]); 
        	GameController.setK(save.getK());
        	GameController.setBoard(save.getBoard());
        	GameController.setTurni(save.getTurni());
        }
	}
	
	/**
	 *   Funzione di salvataggio. Se non è presente, crea una cartella nella cartella con l'username del giocatore. 
	 *   Crea un oggetto di tipo GameData e ne salva le informazioni.
	 */

	
	static public void salvaPartita() 
	{
		GameData game = new GameData();
		game.setBoard(board);
		game.setK(k);
		game.setPlayerAttivo(playerAttivo);
		String[] names = new String[2];
		names[0] = GameController.getPlayers()[0].getNome();
		names[1] = GameController.getPlayers()[1].getNome();
		int[] simboli = new int[2];
		simboli[0] = GameController.getPlayers()[0].getSimbolo();
		simboli[1] = GameController.getPlayers()[1].getSimbolo();
		game.setPlayer_names(names);
		game.setSimboli(simboli);
		game.setTurni(turni);
		String userHome = System.getProperty("user.home");
	    userHome = userHome.replace("\\","/");
	    userHome = userHome + File.separator + players[0].getNome();
	    if (new File(userHome).mkdir()) 
        System.out.println("Cartella " + userHome + " creata.");
	    
	    try {
	    	//ricerco nella cartella tutti i saves dell'utente in modo da dare al nuovo save un nome unico
	    	List<Path> fileList = new ArrayList<>();
            Files.newDirectoryStream(Paths.get(userHome)).forEach(p -> fileList.add(p));
            int max = fileList.size();
            max++;
            FileOutputStream fileOutputStream = new FileOutputStream(userHome+File.separator+"save"+max);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Partita salvata in " + userHome);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Partita non salvata");

        }
	}

	/**
	 *  Funzione che avvia la catena di comando sul pulsante premuto, che sia esso preso in input dalla scacchiera dal giocatore
	 *  umano o eseguita come funzione dal giocatore IA
	 */

	static public void eseguiMossa(String pulsantePremuto) {
		int pulsante_premuto = Integer.parseInt(pulsantePremuto);
		if(getBoardIndex(pulsante_premuto) == -1) //Se il pulsante non è stato già premuto, avvia la catena
			CatenaDiComando.iniziaOperazioni(pulsante_premuto);
		
	};
	
	public static Player[] getPlayers() {
		return players;
	}
	public static void setPlayers(Player players[]) {
		GameController.players = players;
	}

	public static int getPlayer_attivo() {
		return playerAttivo;
	}
	public static void setPlayer_attivo(int player_attivo) {
		GameController.playerAttivo = player_attivo;
	}

	
	/**
	 *   Dato un indice da 0 a 8 (il numero delle caselle) ritorna il simbolo che si trova sulla casella corrispondente
	 *   Es: dato l'indice 5 ritorna il valore in board[1][2]
	 */
	
	public static int getBoardIndex(int indice) {
		return board[indice/3][indice%3];
	}
	
	public static void setBoardValue(int indice, int valore) {
		board[indice/3][indice%3] = valore;
	}
	
	public static void setBoard(int [][] board)
	{
		GameController.board = board;
	}
	
	public static int[][] getBoard()
	{
		return board;
	}

	public static int getK() {
		return k;
	}
	public static void setK(int k) {
		GameController.k = k;
	}
	
	public static int getTurni() {
		return turni;
	}
	
	public static void setTurni(int turni) {
		GameController.turni = turni;
	}
	
	public static String getDifficolta() {
		return difficolta;
	}

	public static void setDifficolta(String difficolta) {
		GameController.difficolta = difficolta;
	}

	public static GameBoard getGameBoardGUI() {
		return gameBoard;
	}
	
	public static void setGameBoardGUI(GameBoard gameBoard) {
		GameController.gameBoard = gameBoard;
	}
	

	static void cambiaGiocatore()
	{
		playerAttivo++;
		//Setto lo stato del giocatore attivo a falso, visto che non è più il suo turno. Avvisando indirettamente all'avversario che è il suo turno
		players[(playerAttivo-1)%2].setState(false); 
		
	}
	
	/**
	 *   Ritorna una lista con i nomi dei salvataggi in formato String.
	 */

	public static List<String> getSaves(String nome) {
		List<String> saves = new ArrayList<>();
		String userHome = System.getProperty("user.home");
	    userHome = userHome.replace("\\","/");
	    userHome = userHome + File.separator + GameController.getPlayers()[0].getNome();
		File folder = new File(userHome);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null)
		{
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) 
			    saves.add(listOfFiles[i].getName());  
			}
			return saves;
		}
		else
			return null;
	}

	public static State getStatoPartita() {
		return statoPartita;
	}

	public static void setStatoPartita(State statoPartita) {
		GameController.statoPartita = statoPartita;
	}

	/**
	 *   La catena di comando è un implementazione specifica delle Catena di Responsabilità. 
	 *   La classe si occupa di far eseguire le operazioni in un esatto ordine.
	 *   In questo caso particolare, la catena viene avviata quando viene scelta una mossa valida e si occupa di:
	 *   -Stampare il simbolo selezionato sull'interfaccia grafica
	 *   -Valutare lo stato della partita, quindi vedere se è ancora in corso o è finita
	 *   -Computare lo stato della partita, quindi lasciar continuare la partita o avviare la schermata di vittoria
	 */
	static private class CatenaDiComando {

		static void iniziaOperazioni(int pulsantePremuto) {
			StampaSimbolo(pulsantePremuto);
			GameController.setStatoPartita(ValutaPartita());
			GameController.getStatoPartita().computa();
		}
		
		static void StampaSimbolo (int pulsantePremuto) {
		
			turni++;
			players[playerAttivo%2].mostraSimbolo(gameBoard, pulsantePremuto);
			setBoardValue(pulsantePremuto,players[playerAttivo%2].getSimbolo());
		};
		
		static State ValutaPartita () {
			for (int i = 0; i < 3; i++)
			{
				if (board[i][0] != -1 && board[i][0] == board[i][1] && board[i][1] == board[i][2])
				{
					vincitore = board[i][0];
					return new GameOver(vincitore);

				}
				if (board[0][i] != -1 && board[0][i] == board[1][i] && board[1][i] == board[2][i])
				{
					vincitore = board[0][i];
					return new GameOver(vincitore);

				}
			}
			if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
			{
				vincitore = board[0][0];
				return new GameOver(vincitore);

			}
			if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
			{
				vincitore = board[0][2];
				System.out.println("vincitore = " + vincitore);
				return new GameOver(vincitore);
				

			}
			
			if (turni > 8)	//pareggio
			{
				return new GameOver(-1);
			}
			return new PartitaInCorso();
		}
	}

}
