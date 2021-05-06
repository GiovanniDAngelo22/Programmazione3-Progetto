package tris;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
/**
 * 
 *  La classe rappresenta l'interfaccia grafica della schermata di gioco. 
 */
public class GameBoardGUI extends JDialog implements GameBoard {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static HashMap<Integer, FactoryTexture> factory = new HashMap<>();
    private ArrayList<JButton> buttons = new ArrayList<>();

	public GameBoardGUI() {
		
		avviaFactory(); 
		setModal(true);
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Tris");
		setBounds(100, 100, 547, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255))
		;
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel turnoAttivoLabel = new JLabel("");
		contentPanel.setLayout(new GridLayout(3,3));
		 for (int i = 0; i <9; i++) {
	         JButton button = new JButton("");
	 		 button.setBackground(new Color(255,255,255));
	         button.setActionCommand("" + i);
	         buttons.add(button);
	      }
		 for (JButton casellaDiGioco : buttons)
		 {
			 casellaDiGioco.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int color = GameController.getPlayers()[GameController.getPlayer_attivo()%2].getSimbolo();
						if (color == 1)
						{
							turnoAttivoLabel.setForeground(new Color(255, 102, 102));
							turnoAttivoLabel.setText("E' IL TURNO DI 'X'");
						}
						else
						{
							turnoAttivoLabel.setForeground(new Color(51, 153, 255));
							turnoAttivoLabel.setText("E' IL TURNO DI 'O'");
						}
						GameController.eseguiMossa(casellaDiGioco.getActionCommand());
					}
				});
			 contentPanel.add(casellaDiGioco);
		 }
	
		JPanel panel = new JPanel();
		
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton saveButton = new JButton("Salva Partita");
		saveButton.addActionListener(arg0 -> GameController.salvaPartita());

		saveButton.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		saveButton.setForeground(new Color(255, 102, 102));
		saveButton.setBackground(new Color(255, 255, 255));
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(saveButton, BorderLayout.EAST);
	
		
		turnoAttivoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(turnoAttivoLabel, BorderLayout.CENTER);
		turnoAttivoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnoAttivoLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 16));
		turnoAttivoLabel.setBackground(new Color(255, 255, 255));
		
	}
	
	/**
	 * Questa funzione implementa la factory per la creazione delle immagini di simboli dei giocatori.
	 * Nella hashtable factory vengono accoppiati un intero ad un ImageIcon che rappresenta il simbolo di un giocatore.
	 * Viene usata la funzione lambda e l'interfaccia funzionale, messe a disposizione da Java 8.
	 * In particolare, la funzione lambda va a definire un implementazione di CreaImmagine, la funzione astratta 
	 * dell'interfaccia funzionale @FactoryTexture
	 */
	private void avviaFactory() {
		factory.put(0, () -> {
			return new ImageIcon(new ImageIcon(getClass().getResource("/images/circle.png"))
						.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));}
		);
		factory.put(1, () -> {
			return new ImageIcon(new ImageIcon(getClass().getResource("/images/cross.png"))
						.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));}
		);
		
	}

	/**
	 * 
	 * Una volta finita una partita, viene aperta un'istanza di @VincitoreGUI che mostra il vincitore della partita.
	 * Alla sua chiusura viene chiusa anche la scacchiera, visto che non ci sono più azioni disponibili da poter fare.
	 */

	@Override
	public void mostraVincitore(int vincitore)
	{
		try {
			VincitoreGUI dialog = new VincitoreGUI(vincitore, this);
			dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosing(WindowEvent e) {
		                {
		                    dialog.dispose(); 
		                    chiudi();
		                }
		            }
		        });
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void chiudi()
	{
		this.dispose();
	}


	/**
	 * Disegna il simbolo del giocatore giocante sulla scacchiera.
	 */

	@Override
	public void mostraSimbolo(int indice, int simbolo) {
		ImageIcon ic = factory.get(simbolo).crea();
		buttons.get(indice).setIcon(ic);
	}
	
	/**

	 *	Funzione che viene richiamata quando viene caricata una partita. Prende le informazioni dal @GameController su quali 
	 *	caselle sono già state riempite e da chi
	 */
	public void CaricaBoard()
	{
		for (int i=0; i < 9; i++)
		{
			if (GameController.getBoardIndex(i) != -1)
			{
				mostraSimbolo(i,GameController.getBoardIndex(i));
			}
		}
	}
	
	


}
