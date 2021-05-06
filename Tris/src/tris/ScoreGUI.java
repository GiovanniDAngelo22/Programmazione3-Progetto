package tris;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.awt.Color;

/**
 * Interfaccia grafica che mostra la classifica delle partite effettuate, presenti sul database.
 * Prende le tuple delle partite effettuate dal DB e le mostra, ordinate per numero di turni e divise per difficoltà
 */

public class ScoreGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public ScoreGUI(Partita partita) {
		setBackground(new Color(255, 255, 255));
		setModal(true); // La finestra principale non è interagibile mentre si visualizza lo score
		setBounds(100, 100, 400, 200);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		List<Object> highscore = partita.getHighScore();
		DefaultTableModel model = new DefaultTableModel();
		JTable scoreTable=new JTable(model);
		model.addColumn("Username");
		model.addColumn("Difficoltà");
		model.addColumn("Turno");
		for(Object p : highscore) {
			PartitaConcreta pc = (PartitaConcreta)p;
			Vector row = new Vector();
		    row.add(pc.getUsername());
		    row.add(pc.getDifficolta());
		    row.add(pc.getTurni());
		    model.addRow(row);
		}
			
		scoreTable.setGridColor(new Color(255, 255, 255));
		scoreTable.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		scoreTable.setBounds(30,40,200,300);          
		scoreTable.setForeground(new Color(255, 102, 102));
		scoreTable.getTableHeader().setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		scoreTable.getTableHeader().setBackground(new Color(255, 255, 255));
		scoreTable.getTableHeader().setForeground(new Color(255, 102, 102));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		JScrollPane sp=new JScrollPane(scoreTable);
		sp.setBackground(new Color(255, 255, 255));
		
		getContentPane().add(sp);
	}

}
