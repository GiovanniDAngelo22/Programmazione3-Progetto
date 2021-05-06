package tris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;

/**
 * Semplice interfaccia grafica che viene mostrata quando finisce una partita.
 */
public class VincitoreGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private ImageIcon ic = null;
	private final JPanel contentPanel = new JPanel();


	public VincitoreGUI(int vincitore, GameBoard gameBoard) {
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel risultatoLabel = new JLabel("");
		getContentPane().add(risultatoLabel);
		risultatoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		risultatoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (ic != null)
			ic.getImage().flush(); //Funzione necessaria per riavviare la gif nel caso sia stata già utilizzata
		
		//seleziono la gif da impostare nella label in base al vincitore.
		if (vincitore ==  GameController.getPlayers()[0].getSimbolo())
		{
			risultatoLabel.setIcon(ic = new ImageIcon(MenuGUI.class.getResource("/images/vittoria.gif")));
		}
		else if (vincitore == GameController.getPlayers()[1].getSimbolo())
		{
			risultatoLabel.setIcon(ic = new ImageIcon(MenuGUI.class.getResource("/images/perdita.gif")));
		}
		else
		{
			risultatoLabel.setIcon(ic = new ImageIcon(MenuGUI.class.getResource("/images/pareggio.gif")));
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new BorderLayout(0, 0));
		
	}

}
