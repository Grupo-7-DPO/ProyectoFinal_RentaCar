package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Categoria;
import Model.Reserva;
import Model.Usuario;
import Model.Vehiculo;

public class ComenzarReserva implements ActionListener {

	JFrame frame;
	RentaCar rentaCar;
	String sedeEmpleado;
	Usuario usuarioActual;
	
	JLabel titulo = new JLabel("Comenzar Reserva",SwingConstants.CENTER);
	JLabel infoReserva = new JLabel("Escribe los Datos de la Reserva",SwingConstants.CENTER);
	JLabel idLabel = new JLabel("ID reserva:");
	JLabel nombreLabel = new JLabel("Nombre Usuario:");
	JTextField idReserva = new JTextField(15);
	JTextField nombreReserva = new JTextField(15);
	JButton comenzarButton = new JButton("Comenzar");
	JButton atrasButton = new JButton();
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	
	
	
	public ComenzarReserva(String sede_empleado, JFrame frameAnterior, RentaCar renta_carros, Usuario usuario_actual) {
		frame = frameAnterior;
		rentaCar = renta_carros;
		sedeEmpleado = sede_empleado;
		usuarioActual = usuario_actual;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0xFAB0B9));
		titulo.setForeground(Color.BLACK);
		titulo.setPreferredSize(new Dimension(700,90));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 35));
		frame.add(titulo, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		
	
		infoReserva.setFont(new Font("Times New Roman", Font.BOLD, 25));
		infoReserva.setOpaque(true);
		infoReserva.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(Box.createVerticalStrut(50));
		panelCentral.add(infoReserva);
		
		panelCentral.add(Box.createVerticalGlue());
		
		idLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nombreLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JPanel panelID = new JPanel(new FlowLayout());
		panelID.add(idLabel);
		panelID.add(idReserva);
		
		JPanel panelNombre = new JPanel(new FlowLayout());
		panelNombre.add(nombreLabel);
		panelNombre.add(nombreReserva);
		
		panelCentral.add(panelID);
		panelCentral.add(panelNombre);
		
		panelCentral.add(Box.createVerticalGlue());
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new BorderLayout());
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		atrasButton.setIcon(imagenArreglada);
		atrasButton.setFocusable(false);
		atrasButton.setPreferredSize(new Dimension(70,70));
		atrasButton.addActionListener(this);
		panelSouth.add(atrasButton, BorderLayout.WEST);
		
		JPanel panelBuscar = new JPanel(new FlowLayout());
		panelBuscar.setOpaque(true);
		comenzarButton.setBackground(new Color(0xF2465D));
		comenzarButton.setOpaque(true);
		comenzarButton.setForeground(Color.WHITE);
		comenzarButton.setPreferredSize(new Dimension(200,30));
		comenzarButton.setFocusable(false);
		comenzarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comenzarButton.addActionListener(this);
		panelBuscar.add(comenzarButton);
		panelSouth.add(panelBuscar, BorderLayout.CENTER);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == atrasButton) {
			if(usuarioActual.getTipo().equals("E")) {
				ConsolaRentaCar.consolaEmpleado();
			}
			else if(usuarioActual.getTipo().equals("A")) {
				ConsolaRentaCar.consolaAdmin();
			}
			else if(usuarioActual.getTipo().equals("AG")) {
				ConsolaRentaCar.consolaAdminG();
			}
			
		}
		else if(e.getSource() == comenzarButton) {
			String id = idReserva.getText();
			String nombre = nombreReserva.getText();
			
			Usuario usuarioReserva = rentaCar.encontrarUsuarioConNombre(nombre);
			Reserva reserva = rentaCar.encontrarReserva(id);
			
			if (reserva != null && usuarioReserva != null && usuarioReserva.getUser().equals(reserva.getUsername())) {
				if (reserva.getSedeRecogida().equals(sedeEmpleado)) {
					String sede = reserva.getSedeRecogida();
					Categoria tipo = reserva.getTipo();
					long precio = rentaCar.calcularTarifaReserva(reserva.getDiaRecogida(), reserva.getDiaEntrega() , reserva.getTipo(), reserva.getSeguro());
					String title = "Pago restante";
					String message = "Debe pagar el saldo restante de " + Math.round(precio*0.7 * 100.0) / 100.0 + "$";
					
					JOptionPane.showMessageDialog(null, message,
							title, JOptionPane.INFORMATION_MESSAGE);
					
					Vehiculo carro_asignado = rentaCar.comenzarReserva(sede, tipo, reserva);
					
					if (carro_asignado != null) {
						String message2 = "Carro asignado: "+ carro_asignado.getMarca() + " " + carro_asignado.getModelo() + 
								"\nPlaca: " + carro_asignado.getPlaca() + 
								"\nColor: " + carro_asignado.getColor() +
								"\nTipo: " + carro_asignado.getTipo();
						String title2 = "Carro Asignado a la reserva " + id;
						JOptionPane.showMessageDialog(null, message2,
								title2, JOptionPane.INFORMATION_MESSAGE);
						
						rentaCar.cambiarEstadoCarro("reservado", carro_asignado.getPlaca());
					}
					else {
						JOptionPane.showMessageDialog(null, "Lo sentimos, no tenemos carros disponibles por el momento",
								"Carros No Encontrados", JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				else if (!reserva.getSedeRecogida().equals(sedeEmpleado)) {
					String message3 = "El carro debe ser recogido en la sede " + reserva.getSedeRecogida() + 
										"\nNo en la sede " + sedeEmpleado;
					
					JOptionPane.showMessageDialog(null, message3,
							"Sede Incorrecta", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				String message4 = "ERROR: No existe una reserva con id " + id + " a nombre de " + nombre;
				JOptionPane.showMessageDialog(null, message4,
						"Reserva No Encontrada", JOptionPane.ERROR_MESSAGE);
			}
			
			idReserva.setText("");
			nombreReserva.setText("");
			
		}
		
	}

}
