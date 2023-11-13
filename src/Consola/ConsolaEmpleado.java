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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Empleado;
import Model.Usuario;

public class ConsolaEmpleado implements ActionListener{

	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCar;
	Empleado empleadoActual;
	
	JLabel bienvenida;
	JPanel panelCentral = new JPanel();
	
	JButton cerrarSesion = new JButton();
	JButton nuevaReserva = new JButton("Nueva Reserva");
	JButton nuevaReservaEspecial = new JButton("Nueva Reserva Especial");
	JButton comenzarReserva = new JButton("Comenzar Reserva");
	JButton recibirCarro = new JButton("Recibir Carro Reservado");
	JButton recibirCarroLimpieza = new JButton("Recibir Carro de Limpieza");
	JButton carroMantenimiento = new JButton("Poner Carro en Mantenimiento");
	ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	public ConsolaEmpleado(JFrame frameAnterior, Usuario usuario_actual, RentaCar renta_carros) {
		
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCar = renta_carros;
		empleadoActual = rentaCar.encontrarEmpleado(usuarioActual.getUser());
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		bienvenida = new JLabel("Bienvenido " + usuarioActual.getNombre(), SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setBackground(new Color(0xEEEEEE));
		bienvenida.setOpaque(true);
		bienvenida.setPreferredSize(new Dimension(700,100));
		frame.add(bienvenida, BorderLayout.NORTH);
		
		panelCentral.setLayout(new GridLayout(6,1,10,10));
		panelCentral.setBorder(BorderFactory.createLineBorder(new Color(0xEEEEEE), 30));
		
		ConfigurarButton(nuevaReserva);
		panelCentral.add(nuevaReserva);
		
		ConfigurarButton(nuevaReservaEspecial);
		panelCentral.add(nuevaReservaEspecial);
		
		ConfigurarButton(comenzarReserva);
		panelCentral.add(comenzarReserva);
		
		ConfigurarButton(recibirCarro);
		panelCentral.add(recibirCarro);
		
		ConfigurarButton(recibirCarroLimpieza);
		panelCentral.add(recibirCarroLimpieza);
		
		ConfigurarButton(carroMantenimiento);
		panelCentral.add(carroMantenimiento);
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		JPanel cerrar = new JPanel();
		cerrar.setLayout(new BorderLayout());
		cerrar.setBackground(new Color(0xEEEEEE));
		cerrar.setOpaque(true);
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
	
	private void ConfigurarButton(JButton boton) {
		boton.setBackground(new Color(0xF2465D));
		boton.setOpaque(true);
		boton.setForeground(Color.WHITE);
		boton.setPreferredSize(new Dimension(200,30));
		boton.setFocusable(false);
		boton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		boton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cerrarSesion) {
			ConsolaRentaCar.setUsuario(null);
			ConsolaRentaCar.login();
		}
		else if(e.getSource() == nuevaReserva) {
			ConsolaRentaCar.crearReserva(false);
		}
		else if(e.getSource() == nuevaReservaEspecial) {
			ConsolaRentaCar.crearReserva(true);
		}
		else if(e.getSource() == comenzarReserva) {
			ConsolaRentaCar.comenzarReserva(empleadoActual.getSede());
		}
		else if(e.getSource() == recibirCarro) {
			ConsolaRentaCar.recibirCarroReservado();
		}
		else if (e.getSource() == recibirCarroLimpieza) {
			ConsolaRentaCar.cambiarEstadoCarro();
		}
		else if (e.getSource() == carroMantenimiento) {
			ConsolaRentaCar.cambiarEstadoCarro();
		}
		
	}

}
