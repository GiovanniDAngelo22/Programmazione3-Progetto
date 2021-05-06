package tris;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import button.MenuButton;
import javax.swing.Box;

public class MenuGUI extends JFrame{

	/**
	 *  Interfaccia grafica che rappresenta il menu di gioco, dov'è possibile avviare una nuova partita, 
	 *  caricarne una esistente o visualizzare la classifica dei giocatori. Viene avviata dopo aver effettuato il login in @LoginGUI
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public MenuGUI() {
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 437);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("");
		contentPane.add(titleLabel);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setIcon(new ImageIcon(MenuGUI.class.getResource("/images/title.gif")));
		
		JButton btnNuovaPartita = MenuButton.createJButton();
		btnNuovaPartita.setText("Nuova Partita");
		contentPane.add(btnNuovaPartita);
		
		btnNuovaPartita.addActionListener(arg0 -> { 
			DifficoltaGUI dialog = new DifficoltaGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		});
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut);
		
		JButton btnContinua = MenuButton.createJButton();
		contentPane.add(btnContinua);
		btnContinua.setText("Continua Partita");
		btnContinua.addActionListener(arg0 -> { 
			CaricaPartitaGUI dialog = new CaricaPartitaGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_1);
		
		JButton btnPunteggi = MenuButton.createJButton();
		contentPane.add(btnPunteggi);
		btnPunteggi.setText("Punteggi");
		btnPunteggi.addActionListener(arg0 -> { 
			ScoreGUI dialog = new ScoreGUI(MainController.getDbPartita());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		});
	}
}
