package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Usuario;

public class PanelAddCar extends JPanel implements ActionListener{

	private JLabel bienvenida;
	private JButton back = new JButton("Back");
	private JButton send = new JButton("Send");
	
	private JTextField placaTF;
	private JTextField modelTF;
	private JTextField marcaTF;
	private JTextField capacidadTF;
	private JTextField transmisionTF;
	private JTextField tipoTF;
	private JTextField colorTF;
	private JTextField sedeTF;
	private JTextField estadoTF;
	
	public PanelAddCar() {
		setLayout(new BorderLayout());
		
		bienvenida = new JLabel("Agregar Vehiculo", SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(900,40));
		bienvenida.setBackground(new Color(0xFAB0B9));
		bienvenida.setOpaque(true);
		add(bienvenida,BorderLayout.NORTH);
		
		JPanel options = new JPanel();
		JLabel placa = new JLabel("Placa");
		placa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		placaTF = new JTextField();
		JLabel model = new JLabel("Modelo");
		model.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		modelTF = new JTextField();
		JLabel marca = new JLabel("Marca");
		marca.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		marcaTF = new JTextField();
		JLabel capacidad = new JLabel("Capacidad");
		capacidad.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		capacidadTF = new JTextField();
		JLabel transmision = new JLabel("Transmisión");
		transmision.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		transmisionTF = new JTextField();
		JLabel tipo = new JLabel("Tipo");
		tipo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		tipoTF = new JTextField();
		JLabel color = new JLabel("Color");
		color.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		colorTF = new JTextField();
		JLabel sede = new JLabel("Sede");
		sede.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		sedeTF = new JTextField();
		JLabel estado = new JLabel("Estado");
		estado.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		estadoTF = new JTextField();
		
		
		options.add(placa);
		options.add(placaTF);
		options.add(model);
		options.add(modelTF);
		options.add(marca);
		options.add(marcaTF);
		options.add(capacidad);
		options.add(capacidadTF);
		options.add(transmision);
		options.add(transmisionTF);
		options.add(tipo);
		options.add(tipoTF);
		options.add(color);
		options.add(colorTF);
		options.add(sede);
		options.add(sedeTF);
		options.add(estado);
		options.add(estadoTF);
		
		
		options.setLayout(new GridLayout(0,2,0,20));
		
		add(options, BorderLayout.CENTER);
		
		send.setFont(new Font("Times New Roman", Font.BOLD, 20));
		send.setBackground(new Color(0xFF5757));
		send.addActionListener(this);
		
		back.setSize(60, 30);
		back.setFont(new Font("Times New Roman", Font.BOLD, 20));
		back.setBackground(new Color(0xFAB0B9));
		back.addActionListener(this);

		options.add(back);
		options.add(send);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			ConsolaRentaCar.consolaAdminG();
		}
		if(e.getSource()==send) {
			String placa = placaTF.getText();
			String marca = marcaTF.getText();
			String modelo = modelTF.getText();
			String capacidad = capacidadTF.getText();
			String trans = transmisionTF.getText();
			String tipo = tipoTF.getText();
			String color = colorTF.getText();
			String estado = estadoTF.getText();
			String sede = sedeTF.getText();
			
			try {
				boolean ret =ConsolaRentaCar.crearVehiculo(placa,marca,modelo,tipo,color,trans,capacidad,estado,sede);
				if (ret) {
					placaTF.setText("");
					marcaTF.setText("");
					modelTF.setText("");
					capacidadTF.setText("");
					transmisionTF.setText("");
					tipoTF.setText("");
					colorTF.setText("");
					estadoTF.setText("");
					sedeTF.setText("");
					JOptionPane.showMessageDialog(this, "Car added succesfully", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this, "Error Please Verify Information", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Error Please Verify Information");
			}
		}
	}

}
