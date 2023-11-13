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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class CambiarEstadoCarro implements ActionListener{

	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCar;
	
	JLabel titulo = new JLabel("Cambiar Estado de Carros", SwingConstants.CENTER);
	JLabel infoCarro = new JLabel("Escribe la Placa del Carro", SwingConstants.CENTER);
	JTextField placaCarro = new JTextField(10);
	JRadioButton disponibleButton = new JRadioButton("Disponible");
	JRadioButton mantenimientoButton = new JRadioButton("Mantenimiento");
	ButtonGroup grupoBotones = new ButtonGroup();
	JButton okButton = new JButton("Cambiar");
	
	JButton atrasButton = new JButton();
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	
	public CambiarEstadoCarro(JFrame frameAnterior, RentaCar renta_carros, Usuario usuario_actual) {
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCar = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		
		titulo.setBackground(new Color(0xF2465D));
		titulo.setForeground(Color.BLACK);
		titulo.setPreferredSize(new Dimension(700,90));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 35));
		frame.add(titulo, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 100));
		
		infoCarro.setFont(new Font("Times New Roman", Font.BOLD, 25));
		infoCarro.setOpaque(true);
		infoCarro.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(infoCarro);
		
		JPanel panelText = new JPanel(new FlowLayout());
		panelText.add(placaCarro);
		panelCentral.add(panelText);
		
		JPanel radioButtonsPanel = new JPanel(new FlowLayout());
		grupoBotones.add(disponibleButton);
		grupoBotones.add(mantenimientoButton);
		
		radioButtonsPanel.add(disponibleButton);
		radioButtonsPanel.add(mantenimientoButton);
		panelCentral.add(radioButtonsPanel);
		
		JPanel panelButton = new JPanel(new FlowLayout());
		okButton.setBackground(new Color(0xF2465D));
		okButton.setOpaque(true);
		okButton.setForeground(Color.WHITE);
		okButton.setPreferredSize(new Dimension(200,30));
		okButton.setFocusable(false);
		okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		okButton.addActionListener(this);
		panelButton.add(okButton);
		panelCentral.add(panelButton);
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new BorderLayout());
		panelSouth.setBackground(new Color(0xFAB0B9));
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		atrasButton.setIcon(imagenArreglada);
		atrasButton.setFocusable(false);
		atrasButton.setPreferredSize(new Dimension(70,70));
		atrasButton.addActionListener(this);
		panelSouth.add(atrasButton, BorderLayout.WEST);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == atrasButton) {
			if(usuarioActual.getTipo().equals("E")) {
				ConsolaRentaCar.consolaEmpleado();
			}
			if(usuarioActual.getTipo().equals("A")) {
				ConsolaRentaCar.consolaAdmin();
			}
			if(usuarioActual.getTipo().equals("AG")) {
				ConsolaRentaCar.consolaAdminG();
			}
		}
		else if (e.getSource() == okButton) {
			String placa = placaCarro.getText();
			String title = "Cambio de Estado";
			String message;
			if(disponibleButton.isSelected()) {
				rentaCar.cambiarEstadoCarro("disponible", placa);
				message = "Carro " + placa + " ahora\nestá disponible diponible";
				JOptionPane.showMessageDialog(null, message,
						title, JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if (mantenimientoButton.isSelected()) {
				rentaCar.cambiarEstadoCarro("mantenimiento", placa);
				message = "Carro " + placa + " puesto\nen mantenimeinto correctamente";
				JOptionPane.showMessageDialog(null, message,
						title, JOptionPane.INFORMATION_MESSAGE);
			}
			placaCarro.setText("");
		}
	}

}
