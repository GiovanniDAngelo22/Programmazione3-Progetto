package button;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Factory fornita dal plugin WindowBuilder per istanziare i pulsanti del menu' principale
 * forndendogli le caratteristiche estetiche necessarie. 
 */

public final class MenuButton {

	public static JButton createJButton() {
		JButton button = new JButton("");
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(255, 102, 102));
		button.setMaximumSize(new Dimension(180, 50));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentY(Component.CENTER_ALIGNMENT);
		button.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 13));
		return button;
	}
}