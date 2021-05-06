package tris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;


/**
 * Interfaccia Grafica che consente il caricamento di una partita salvata. 
 */
public class CaricaPartitaGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	public CaricaPartitaGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JLabel selezionaLabel = new JLabel("Seleziona il salvataggio");
		selezionaLabel.setForeground(new Color(255, 102, 102));
		selezionaLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		selezionaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(selezionaLabel);
	
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		JButton caricaButton = new JButton("Carica");
		caricaButton.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		caricaButton.setForeground(new Color(255, 102, 102));
		caricaButton.setBackground(new Color(255, 255, 255));
		buttonPane.add(caricaButton);
		getRootPane().setDefaultButton(caricaButton);
	
		//Creo una lista contenente i salvataggi ed il relativo nome
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		List<String> saves = new ArrayList<>();
		saves =  GameController.getSaves(GameController.getPlayers()[0].getNome());
		if (saves != null)
		{
			for (int i = 0; i < saves.size();i++)
			{
				listModel.addElement(saves.get(i));
			}
		}
		
        JList<String> list = new JList<String>(listModel);
        list.setForeground(new Color(255, 102, 102));
        list.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        getContentPane().add(listScrollPane);
        
    	caricaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selezionato = list.getSelectedIndex();
				if (selezionato < 0)
				{
					JOptionPane.showMessageDialog(null,"Seleziona un salvataggio!" );
				}
				else
				{
					GameController.continuaPartita(selezionato+1); //Gli elementi della lista partono da 0, i salvataggi da 1
					GameBoardGUI dialog = new GameBoardGUI(); //Creo una nuova scacchiera dove far avvenire la partita caricata
					GameController.setGameBoardGUI(dialog); //Setto l'interfaccia per il GameController
					dialog.CaricaBoard(); //Aggiorno la GUI con le informazioni del GameController
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dispose();
				}
			}
		});
	}
}
