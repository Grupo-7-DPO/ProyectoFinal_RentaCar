package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Loader.RentaCar;
import Model.Reserva;
import Model.Seguro;
import Model.Usuario;

public class ConsultarReserva implements ActionListener{

	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCar;
	Reserva reserva;
	
	JLabel titulo = new JLabel("Consultar Reserva", SwingConstants.CENTER);
	JLabel instruccion = new JLabel("Escribe el id de la Reserva", SwingConstants.CENTER);
	JTextField idReservaText = new JTextField(15);
	JButton buscarButton = new JButton("Buscar");
	JButton botonAtras = new JButton();
	JButton botonAtrasConsulta = new JButton();
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	
	
	public ConsultarReserva(JFrame frameAnterior, Usuario usuario_actual, RentaCar renta_carros) {
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCar = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0xFAB0B9));
		titulo.setPreferredSize(new Dimension(700,70));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		frame.add(titulo, BorderLayout.NORTH);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		instruccion.setFont(new Font("Times New Roman", Font.BOLD, 25));
		instruccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelPrincipal.add(Box.createVerticalStrut(100));
		panelPrincipal.add(instruccion);
		
		idReservaText.setMaximumSize(new Dimension(200,30));
		idReservaText.setHorizontalAlignment(JTextField.CENTER);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(idReservaText);
		
        buscarButton.setBackground(new Color(0xF2465D));
        buscarButton.setMaximumSize(new Dimension(200,30));
        buscarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buscarButton.setFocusable(false);
        buscarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        buscarButton.setForeground(Color.BLACK);
        buscarButton.addActionListener(this);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(buscarButton);
        
        JPanel atrasPanel = new JPanel(new BorderLayout());
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		botonAtras.setIcon(imagenArreglada);
		botonAtras.setFocusable(false);
		botonAtras.setPreferredSize(new Dimension(70,70));
		botonAtras.addActionListener(this);
		atrasPanel.add(botonAtras, BorderLayout.WEST);
		frame.add(atrasPanel, BorderLayout.SOUTH);
        
		frame.add(panelPrincipal, BorderLayout.CENTER);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buscarButton) {
			String id = idReservaText.getText().toString();
			reserva = rentaCar.encontrarReserva(id);
			
			if(reserva == null) {
				String message = "Lo sentimos, tu reserva con id " + id + " no fue encontrada";
				String title = "Reserva " + id + " No Encontrada";
				JOptionPane.showMessageDialog(null, message,
						title, JOptionPane.ERROR_MESSAGE);
				idReservaText.setText("");
			}
			else {
				if(reserva.getUsername().toString().equals(usuarioActual.getUser().toString())) {
					reservaEncontrada();
				}
				else {
					String message = "Lo sentimos, no pudimos encontrar una reserva con id " + id + " asociada a tu cuenta";
					String title = "Reserva " + id + " No Encontrada";
					JOptionPane.showMessageDialog(null, message,
							title, JOptionPane.ERROR_MESSAGE);
					idReservaText.setText("");
				}
				
			}
			
		}
		else if(e.getSource()==botonAtras) {
			ConsolaRentaCar.consolaCliente();
		}
		else if(e.getSource()==botonAtrasConsulta) {
			ConsolaRentaCar.consultarReserva();
		}
		
	}
	
	private void reservaEncontrada() {
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0xFAB0B9));
		titulo.setPreferredSize(new Dimension(700,70));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		titulo.setText("Reserva a nombre de " + usuarioActual.getNombre() + " con id " + reserva.getId());
		frame.add(titulo, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel(new GridLayout(8,2,10,10));
		panelCentral.setBorder(BorderFactory.createLineBorder(Color.WHITE, 20));
		
		
		JLabel categoriaLabel = crearLabelTabla("Categoria");
		String categoriaStr = reserva.getTipo().getNombre();
		JLabel categoriaInfo = crearInfoTabla(categoriaStr);
		panelCentral.add(categoriaLabel);
		panelCentral.add(categoriaInfo);
		
		JLabel sedeRecogidaLabel = crearLabelTabla("Sede Recogida");
		String sedeRecogidaStr = reserva.getSedeRecogida();
		JLabel sedeRecogidaInfo = crearInfoTabla(sedeRecogidaStr);
		panelCentral.add(sedeRecogidaLabel);
		panelCentral.add(sedeRecogidaInfo);
		
		
		JLabel fechaRecogidaLabel = crearLabelTabla("Fecha Recogida");
		String fechaRecogidaStr = reserva.getDiaRecogida();
		JLabel fechaRecogidaInfo = crearInfoTabla(fechaRecogidaStr);
		panelCentral.add(fechaRecogidaLabel);
		panelCentral.add(fechaRecogidaInfo);
		
		
		JLabel horaRecogidaLabel = crearLabelTabla("Hora Recogida");
		String horaRecogidaStr = reserva.getHoraRecogida();
		JLabel horaRecogidaInfo = crearInfoTabla(horaRecogidaStr);
		panelCentral.add(horaRecogidaLabel);
		panelCentral.add(horaRecogidaInfo);
		
		JLabel sedeEntregaLabel = crearLabelTabla("Sede Entrega");
		String sedeEntregaStr = reserva.getSedeEntrega();
		JLabel sedeEntregaInfo = crearInfoTabla(sedeEntregaStr);
		panelCentral.add(sedeEntregaLabel);
		panelCentral.add(sedeEntregaInfo);
		
		JLabel fechaEntregaLabel = crearLabelTabla("Fecha Entrega");
		String fechaEntregaStr = reserva.getDiaEntrega();
		JLabel fechaEntregaInfo = crearInfoTabla(fechaEntregaStr);
		panelCentral.add(fechaEntregaLabel);
		panelCentral.add(fechaEntregaInfo);
		
		JLabel horaEntregaLabel = crearLabelTabla("Hora Entrega");
		String horaEntregaStr = reserva.getHoraEntrega();
		JLabel horaEntregaInfo = crearInfoTabla(horaEntregaStr);
		panelCentral.add(horaEntregaLabel);
		panelCentral.add(horaEntregaInfo);
		
		JLabel seguroLabel = crearLabelTabla("Seguro");
		Seguro seguro = reserva.getSeguro();
		String seguroStr;
		if(seguro == null) {
			seguroStr = "ninguno";
		}
		else {
			seguroStr = reserva.getSeguro().getNombre();
		}
		JLabel seguroInfo = crearInfoTabla(seguroStr);
		panelCentral.add(seguroLabel);
		panelCentral.add(seguroInfo);
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		JPanel atrasPanel = new JPanel(new BorderLayout());
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		botonAtrasConsulta.setIcon(imagenArreglada);
		botonAtrasConsulta.setFocusable(false);
		botonAtrasConsulta.setPreferredSize(new Dimension(70,70));
		botonAtrasConsulta.addActionListener(this);
		atrasPanel.add(botonAtrasConsulta, BorderLayout.WEST);
		frame.add(atrasPanel, BorderLayout.SOUTH);
        
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}
	
	private JLabel crearLabelTabla(String topic) {
		Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
		JLabel label = new JLabel(topic, SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBackground(new Color(0xF2465D));
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBorder(border);
		
		return label;
	}
	
	private JLabel crearInfoTabla(String info) {
		Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
		JLabel infoLabel = new JLabel(info, SwingConstants.CENTER);
		infoLabel.setBackground(new Color(0xFAB0B9));
		infoLabel.setForeground(Color.BLACK);
		infoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		infoLabel.setBorder(border);
		
		return infoLabel;
	}
}
