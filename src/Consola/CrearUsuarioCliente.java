package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import Loader.RentaCar;

public class CrearUsuarioCliente implements ActionListener {

	
	JFrame frame;
	RentaCar rentaCar;
	
	JLabel titulo = new JLabel("Crear Nueva Cuenta", SwingConstants.CENTER);
	JTextField nombreTxt = new JTextField(15);
	JTextField usuarioTxt = new JTextField(15);
	JTextField passwordTxt = new JTextField(15);
	JButton crearUsuarioButton = new JButton("Crear Usuario");
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	JButton atrasButton = new JButton();
	
	public CrearUsuarioCliente(JFrame frame2, RentaCar renta_carros) {
		frame = frame2;
		rentaCar = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0x266099));
		titulo.setPreferredSize(new Dimension(700,80));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 35));
		titulo.setForeground(Color.WHITE);
		frame.add(titulo, BorderLayout.NORTH);
		
		JPanel panelPrincipal = new JPanel(new GridLayout(3,2,5,5));
		panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 100));
		
		JLabel nombreLbl = crearLabelTabla("Nombre");
		panelPrincipal.add(nombreLbl);
		panelPrincipal.add(nombreTxt);
		
		JLabel usuarioLbl = crearLabelTabla("Usuario");
		panelPrincipal.add(usuarioLbl);
		panelPrincipal.add(usuarioTxt);
		
		JLabel passwordLbl = crearLabelTabla("Constraseña");
		panelPrincipal.add(passwordLbl);
		panelPrincipal.add(passwordTxt);

		frame.add(panelPrincipal, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new BorderLayout());
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		atrasButton.setIcon(imagenArreglada);
		atrasButton.setFocusable(false);
		atrasButton.setPreferredSize(new Dimension(70,70));
		atrasButton.addActionListener(this);
		panelSouth.add(atrasButton, BorderLayout.WEST);
		
		JPanel panelBuscar = new JPanel(new FlowLayout());
		panelBuscar.setBackground(new Color(0xFAB0B9));
		panelBuscar.setOpaque(true);
		crearUsuarioButton.setBackground(new Color(0xF2465D));
		crearUsuarioButton.setOpaque(true);
		crearUsuarioButton.setForeground(Color.WHITE);
		crearUsuarioButton.setPreferredSize(new Dimension(210,30));
		crearUsuarioButton.setFocusable(false);
		crearUsuarioButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		crearUsuarioButton.addActionListener(this);
		panelBuscar.add(crearUsuarioButton);
		panelSouth.add(panelBuscar, BorderLayout.CENTER);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
		
	}
	
	private JLabel crearLabelTabla(String topic) {
		Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
		JLabel label = new JLabel(topic, SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label.setBackground(new Color(0xF2465D));
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBorder(border);
		
		return label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == atrasButton) {
			ConsolaRentaCar.login();
		}
		else if(e.getSource() == crearUsuarioButton) {
			String nombre = nombreTxt.getText();
			String tipo = "C";
			String username = usuarioTxt.getText();
			String password = passwordTxt.getText();
			if (rentaCar.encontrarUsername(username)) {
				String message = "El usuario " + username + " ya está en uso";
				JOptionPane.showMessageDialog(null, message,
						"Error Usuario Existente", JOptionPane.ERROR_MESSAGE);
				usuarioTxt.setText("");
			}
			else {
				ConsolaRentaCar.crearCliente(nombre, username, tipo, password);
			}
			
		}
	}

}
