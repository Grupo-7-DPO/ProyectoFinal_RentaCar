package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class LoginPage implements ActionListener {
	
	JFrame frame;
	RentaCar rentaCar;
	Usuario usuario;
	
	JButton loginButton = new JButton("LOGIN");
	JTextField loginText = new JTextField(15);
	JPasswordField passwordText = new JPasswordField(15);
	JLabel title = new JLabel("¡Bienvenido a Nuestra Renta de Carros!", SwingConstants.CENTER);
	JLabel titleLogin = new JLabel("Inicia Sesion",SwingConstants.CENTER);
	JLabel usernameLabel = new JLabel("Usuario:");
	JLabel passwordLabel = new JLabel("Contraseña:");
	JPanel panelLogin = new JPanel();
	
	
	LoginPage(RentaCar renta_carros, JFrame frame){
		this.rentaCar = renta_carros;
		this.frame = frame;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Nuesta Renta de Carros");
		frame.setResizable(false);
		frame.setSize(700, 500);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		title.setPreferredSize(new Dimension(900,70));
		title.setBackground(new Color(0xFF5757));
		title.setOpaque(true);
		title.setFont(new Font("Times New Roman", Font.BOLD, 30));
		frame.add(title, BorderLayout.NORTH);
		
		
		panelLogin.setLayout(new FlowLayout());
		
		titleLogin.setPreferredSize(new Dimension(900,70));
		titleLogin.setFont(new Font("Times New Roman", Font.BOLD, 28));
		title.setOpaque(true);
		panelLogin.add(titleLogin);
		
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JPanel panelUsername = new JPanel();
		panelUsername.setPreferredSize(new Dimension(900,70));
		panelUsername.add(usernameLabel);
		panelUsername.add(loginText);
		panelLogin.add(panelUsername);
		
		JPanel panelPassword = new JPanel();
		panelPassword.setPreferredSize(new Dimension(900,70));
		panelPassword.add(passwordLabel);
		panelPassword.add(passwordText);
		panelLogin.add(panelPassword);
		
		loginButton.setPreferredSize(new Dimension(200,50));
		loginButton.setBackground(new Color(0xFF5757));
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panelLogin.add(loginButton);
		
		frame.add(panelLogin, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			String username = loginText.getText();
			String password = String.valueOf(passwordText.getPassword());
			this.usuario = rentaCar.encontrarUsuario(username, password);
			if (this.usuario == null) {
				passwordText.setText("");
				loginText.setText("");
				JLabel message = new JLabel("Usuario o Password Incorrectos");
				message.setPreferredSize(new Dimension(900,70));
				message.setForeground(Color.red);
				message.setFont(new Font("Times New Roman", Font.BOLD, 12));
				panelLogin.add(message);
			}
			else {
				JLabel message = new JLabel("Bienvenido " + this.usuario.getNombre());
				message.setForeground(Color.red);
				message.setFont(new Font("Times New Roman", Font.BOLD, 12));
				panelLogin.add(message);
				usuarioIniciado();
			}
		}
	}
	
	private void usuarioIniciado() {
		ConsolaRentaCar.setUsuario(this.usuario);
		if (this.usuario.getTipo().equals("AG")) {
			try {
				ConsolaRentaCar.consolaAdminG();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (this.usuario.getTipo().equals("A")) {
			try {
				ConsolaRentaCar.consolaAdmin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (this.usuario.getTipo().equals("E")) {
			try {
				ConsolaRentaCar.consolaEmpleado();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (this.usuario.getTipo().equals("C")) {
				ConsolaRentaCar.consolaCliente();
		}
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
}
