package tris;

import java.io.Serializable;

/**
 *  GameData è la classe che contiene le informazioni relative da una determinata partita, in modo da poter essere salvate 
 *  o recuperate in un secondo momento.
 *  Implementano l'interfaccia @Serializable, in modo da poter essere scritte/lette su/da file.
 */
public class GameData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String[] player_names;
	private int[] simboli;
	private int k;
	private int turni;
	private int board[][] = new int[3][3];
	private int playerAttivo;
	
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getTurni() {
		return turni;
	}
	public void setTurni(int turni) {
		this.turni = turni;
	}
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int board[][]) {
		this.board = board;
	}

	public int getPlayerAttivo() {
		return playerAttivo;
	}
	public void setPlayerAttivo(int playerAttivo) {
		this.playerAttivo = playerAttivo;
	}
	public String[] getPlayer_names() {
		return player_names;
	}
	public void setPlayer_names(String[] player_names) {
		this.player_names = player_names;
	}
	public int[] getSimboli() {
		return simboli;
	}
	public void setSimboli(int[] simboli) {
		this.simboli = simboli;
	}

}
