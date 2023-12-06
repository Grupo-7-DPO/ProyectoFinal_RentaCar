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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Loader.RentaCliente;
import Model.Renta;
import Model.Usuario;

public class LoginPage implements ActionListener {
	
	JFrame frame;
	Renta rentaCar;
	Usuario usuario;
	
	JButton loginButton = new JButton("LOGIN");
	JButton crearButton = new JButton("CREAR CUENTA");
	JTextField loginText = new JTextField(15);
	JPasswordField passwordText = new JPasswordField(15);
	JLabel title = new JLabel("¡Bienvenido a Nuestra Renta de Carros!", SwingConstants.CENTER);
	JLabel titleLogin = new JLabel("Inicia Sesion",SwingConstants.CENTER);
	JLabel usernameLabel = new JLabel("Usuario:");
	JLabel passwordLabel = new JLabel("Contraseña:");
	JPanel panelLogin = new JPanel();
	ImageIcon icon = new ImageIcon("./imagenes/Icono.png");
	
	
	public LoginPage(Renta renta_carros, JFrame frame){
		this.rentaCar = renta_carros;
		this.frame = frame;
		
		frame.getContentPane().removeAll();
		frame.setIconImage(icon.getImage());
		frame.repaint();
		frame.setBackground(Color.white);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Nuesta Renta de Carros");
		frame.setResizable(false);
		frame.setSize(700, 500);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		title.setPreferredSize(new Dimension(900,70));
		title.setBackground(new Color(0xFF5757));
		title.setForeground(Color.BLACK);
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
		loginButton.setForeground(Color.BLACK);
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panelLogin.add(loginButton);
		
		frame.add(panelLogin, BorderLayout.CENTER);
		
		frame.getContentPane().revalidate();
	    frame.repaint(); 
	    frame.setVisible(true);
		
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
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error Inicio de Sesion", JOptionPane.ERROR_MESSAGE);
			}
			else {
				usuarioIniciado();
			}
		}
	}
	
	private void usuarioIniciado() {
		ConsolaRentaCar.setUsuario(this.usuario);
		if (this.usuario.getTipo().equals("AG")) {
			ConsolaRentaCar.consolaAdminG();
		}
		else if (this.usuario.getTipo().equals("A")) {
			ConsolaRentaCar.consolaAdmin();
		}
		else if (this.usuario.getTipo().equals("E")) {
			ConsolaRentaCar.consolaEmpleado();
		}
		else if (this.usuario.getTipo().equals("C")) {
			ConsolaRentaCar.consolaCliente();
		}
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
}
