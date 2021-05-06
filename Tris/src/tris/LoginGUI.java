package tris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

/**
 *  La classe rappresenta l'interfaccia grafica della schermata di login. 
 */
public class LoginGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	
	public LoginGUI(User user) {
		setBounds(100, 100, 311, 286);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
	
		JLabel title = new JLabel("");
		contentPanel.add(title);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(new ImageIcon(MenuGUI.class.getResource("/images/title.gif")));
	
		
		JPanel pnlUser = new JPanel();
		pnlUser.setBackground(Color.WHITE);
		contentPanel.add(pnlUser);
		pnlUser.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 102, 102));
		lblUsername.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		pnlUser.add(lblUsername);

		lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtUsername = new JTextField();
		pnlUser.add(txtUsername);
		txtUsername.setColumns(15);
		txtUsername.setMaximumSize( txtUsername.getPreferredSize());
		pnlUser.setMaximumSize( pnlUser.getPreferredSize());
		
		JPanel pnlPassword = new JPanel();
		pnlPassword.setBackground(Color.WHITE);
		contentPanel.add(pnlPassword);
				
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 102, 102));
		lblPassword.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		pnlPassword.add(lblPassword);
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

				
		txtPassword = new JPasswordField();
		pnlPassword.add(txtPassword);
		txtPassword.setColumns(15);
		txtPassword.setMaximumSize( txtPassword.getPreferredSize());
		pnlPassword.setMaximumSize( pnlUser.getPreferredSize());
		
		JPanel pnlButtons = new JPanel();
		contentPanel.add(pnlButtons);
		pnlButtons.setLayout(new BorderLayout(0, 0));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		btnLogin.setForeground(new Color(255, 102, 102));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				try {
					if (user.login(username, password) == true) //viene eseguita una query al db per la verifica dell'username e password
					{
						GameController.inizializzaPartita(username); //Se il login va a buon fine, viene inizalizzato il GameController
						JOptionPane.showMessageDialog(null, "Login effettuato!");
						MenuGUI frame = new MenuGUI();
						setVisible(false);
						frame.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nessun utente con queste credenziali");
						txtUsername.setText("");
						txtPassword.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		pnlButtons.add(btnLogin, BorderLayout.NORTH);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		btnRegister.setForeground(new Color(255, 102, 102));
		btnRegister.setBackground(new Color(255, 255, 255));
		pnlButtons.add(btnRegister, BorderLayout.CENTER);
		pnlButtons.setMaximumSize( pnlButtons.getPreferredSize());
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				try {
					if (username.length() < 1 )
					{
						JOptionPane.showMessageDialog(null, "L'username deve contenere almeno 1 carattere!");
					}
					else if (user.register(new UserConcreto(username, password)) == true) //Ci si registra seguendo le regole di buisness del datatabase
					{
						JOptionPane.showMessageDialog(null, "Registrazione effettuata!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username o Password non validi!");
						txtUsername.setText("");
						txtPassword.setText("");
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});


	}

}
