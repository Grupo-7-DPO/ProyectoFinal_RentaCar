package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ConsolaClientes.ConsolaClientesMain;
import Loader.RentaCar;
import Loader.RentaCliente;
import Model.Renta;
import Model.Usuario;

public class ConsolaCliente implements ActionListener{

	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCarros;
	JLabel bienvenida;
	JButton nuevaReserva = new JButton("Nueva Reserva");
	JButton consultarReserva = new JButton("Consultar Reserva");
	JButton consultarDisponibilidad = new JButton("Consultar Disponibilidad");
	JButton cerrarSesion  = new JButton();
	JPanel panel = new JPanel();
	ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	public ConsolaCliente(JFrame frameAnterior, Usuario usuario_actual, RentaCar renta_carros) {
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCarros = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		bienvenida = new JLabel("Bienvenido " + this.usuarioActual.getNombre(), SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(700,100));
		frame.add(bienvenida, BorderLayout.NORTH);
		
		panel.setLayout(new GridLayout(3,1,20,20));
		panel.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 30));
		
		nuevaReserva.setFocusable(false);
		nuevaReserva.setBackground(new Color(0xFF5757));
		nuevaReserva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		nuevaReserva.addActionListener(this);
		panel.add(nuevaReserva);
		
		consultarReserva.setFocusable(false);
		consultarReserva.setBackground(new Color(0xFAB0B9));
		consultarReserva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		consultarReserva.addActionListener(this);
		panel.add(consultarReserva);
		
		if (rentaCarros instanceof RentaCliente) {
			consultarDisponibilidad.setFocusable(false);
			consultarDisponibilidad.setBackground(new Color(0xFF5757));
			consultarDisponibilidad.setFont(new Font("Times New Roman", Font.BOLD, 20));
			consultarDisponibilidad.addActionListener(this);
			panel.add(consultarDisponibilidad);
		}
		else {
			JLabel label = new JLabel("¡¡Te invitamos a usar nuesta app para clientes!!", SwingConstants.CENTER);
			label.setFont(new Font("Times New Roman", Font.BOLD, 20));
			panel.add(label);
		}
		
		frame.add(panel, BorderLayout.CENTER);
		
		JPanel cerrar = new JPanel();
		cerrar.setLayout(new BorderLayout());
		ImageIcon imagenArreglada = new ImageIcon(imageCerrar.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		cerrarSesion.setIcon(imagenArreglada);
		cerrarSesion.setFocusable(false);
		cerrarSesion.setPreferredSize(new Dimension(70,70));
		cerrarSesion.addActionListener(this);
		cerrar.add(cerrarSesion, BorderLayout.EAST);
		frame.add(cerrar, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cerrarSesion) {
			ConsolaRentaCar.setUsuario(null);
			ConsolaRentaCar.login();
		}
		else if(e.getSource()==nuevaReserva){
			ConsolaRentaCar.crearReserva(false);
		}
		else if (e.getSource()==consultarReserva) {
			ConsolaRentaCar.consultarReserva();
		}
		else if (e.getSource() == consultarDisponibilidad) {
			ConsolaClientesMain.consultarDisponibilidad();
		}
	}

}
