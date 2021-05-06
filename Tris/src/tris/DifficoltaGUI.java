package tris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**
 * Interfaccia grafica che va a settare le impostazioni di una nuova partita.
 * Viene data la possibilità di selezionarne la difficoltà e se si vuole iniziare per primi o per secondi.
 */

public class DifficoltaGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private String[] difficolta = new String[] {"Facile", "Medio", "Difficile", "Impossibile"};
	private String difficolta_selezionata = "Facile";
	private	int simbolo = 1;
	private HashMap<String, Integer> difficoltaMap = new HashMap<>(); 


	private final JPanel contentPanel = new JPanel();
	
	public DifficoltaGUI() {
		setModal(true); //Finchè è aperta la schermata si può solo chiudere o confermare 
		for (int i = 0; i < difficolta.length; i++)
		{
			difficoltaMap.put(difficolta[i], (100/(difficolta.length) * (i+1))); 
		}
		
		setBounds(100, 100, 533, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setForeground(new Color(255, 255, 255));
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("Chi vuoi essere?");
				lblNewLabel.setBackground(new Color(255, 255, 255));
				lblNewLabel.setForeground(new Color(255, 102, 102));
				lblNewLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
				panel.add(lblNewLabel);
			}
					JRadioButton b1 = new JRadioButton("Primo giocatore");
					b1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							simbolo = 1;
						}
					});
					b1.setBackground(new Color(255, 255, 255));
					b1.setForeground(new Color(255, 102, 102));
					b1.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
					JRadioButton b2 = new JRadioButton("Secondo giocatore");
					b2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							simbolo = 0;
						}
					});
					b2.setBackground(new Color(255, 255, 255));
					b2.setForeground(new Color(102, 153, 255));
					b2.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
					b1.setSelected(true);
					ButtonGroup g = new ButtonGroup();
					g.add(b1);
					g.add(b2);
					panel.add(b1);
					JLabel iconx = new JLabel("");
					iconx.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cross.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
					panel.add(iconx);
					panel.add(b2);
					JLabel icono = new JLabel("");
					icono.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/circle.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

					panel.add(icono);
				
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel);
			
			JLabel lblScegliLaDifficolt = new JLabel("Scegli la difficolta'");
			lblScegliLaDifficolt.setForeground(new Color(255, 102, 102));
			lblScegliLaDifficolt.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
			lblScegliLaDifficolt.setBackground(Color.WHITE);
			panel.add(lblScegliLaDifficolt);
		
			
			JComboBox<String[]> comboBox = new JComboBox<String[]>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					difficolta_selezionata =  comboBox.getSelectedItem().toString();
				}
			});
			comboBox.setRequestFocusEnabled(false);
			comboBox.setForeground(new Color(102, 153, 255));
			comboBox.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
			comboBox.setBackground(new Color(255, 255, 255));
			comboBox.setModel(new DefaultComboBoxModel(difficolta));
			panel.add(comboBox);
		
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameBoardGUI dialog = new GameBoardGUI();
						GameController.setGameBoardGUI(dialog);
						GameController.nuovaPartita(difficoltaMap.get(difficolta_selezionata),simbolo);
						GameController.setDifficolta(difficolta_selezionata);
						System.out.println("difficoltà = " + difficoltaMap.get(difficolta_selezionata));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dispose();
					}
				});
				okButton.setForeground(new Color(255, 102, 102));
				okButton.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
				cancelButton.setForeground(new Color(255, 102, 102));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
