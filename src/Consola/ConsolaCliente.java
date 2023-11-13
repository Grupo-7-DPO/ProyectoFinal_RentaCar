package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class ConsolaCliente {

	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCarros;
	JLabel bienvenida;
	JButton nuevaReserva = new JButton("Nueva Reserva");
	JButton consultarReserva = new JButton("Consultar Reserva");
	JButton cerrarSesion  = new JButton("Cerrar Sesion");
	JPanel panel = new JPanel();
	ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	public ConsolaCliente(JFrame frame, Usuario usuario_actual, RentaCar renta_carros) {
		this.frame = frame;
		this.usuarioActual = usuario_actual;
		this.rentaCarros = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setLayout(new BorderLayout());
		frame.setSize(700, 500);
		frame.setResizable(false);
		
		bienvenida = new JLabel("Bienvenido", SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(900,70));
		frame.add(bienvenida, BorderLayout.NORTH);
		
		panel.setLayout(new FlowLayout());
		
		JPanel reserva = new JPanel();
		reserva.setPreferredSize(new Dimension(900,70));
		
		nuevaReserva.setPreferredSize(new Dimension(600,40));
		nuevaReserva.setBackground(new Color(0xFF5757));
		nuevaReserva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		reserva.add(nuevaReserva);
		panel.add(reserva);
		
		JPanel consultar = new JPanel();
		consultar.setPreferredSize(new Dimension(600,40));
		consultarReserva.setPreferredSize(new Dimension(600,40));
		consultarReserva.setBackground(new Color(0xFAB0B9));
		consultarReserva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		consultar.add(consultarReserva);
		panel.add(consultar);
		
		frame.add(panel, BorderLayout.CENTER);
		
		
		cerrarSesion.setPreferredSize(new Dimension(100,100));
		//frame.add(cerrarSesion, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate(); 
	    frame.repaint(); 
	    frame.pack(); 
	    frame.setVisible(true);
	}

}
