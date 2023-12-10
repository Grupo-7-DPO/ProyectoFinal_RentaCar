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
import java.io.File;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import Loader.RentaCar;
import Model.Usuario;

public class CrearCliente implements ActionListener{

	JFrame frame;
	RentaCar rentaCar;
	String nombre;
	String username;
	String tipo;
	String password;
	
	JPanel panelPrincipal = new JPanel(new GridLayout(11,2,5,5));
	
	JLabel titulo = new JLabel("Crear Nueva Cuenta", SwingConstants.CENTER);
	
	JTextField contactoTxt = new JTextField(15);
	JDateChooser fechaNacimiento = new JDateChooser();
	JTextField nacionalidadTxt = new JTextField();
	JButton cedulaButton = new JButton("Select File");
	JFileChooser fileCedula = new JFileChooser();
	File imagenCedula = null ;
	
	JTextField paisLicenciaTxt = new JTextField();
	JTextField numeroLicenciaTxt = new JTextField();
	JDateChooser fechaVencimientoPase = new JDateChooser();
	JButton paseButton = new JButton("Select File");
	JFileChooser filePase = new JFileChooser();
	File imagenPase = null;
	
	JComboBox tipoTarjeta;
	JTextField numeroTarjetaTxt = new JTextField();
	JTextField fechaVencimientoTarjeta = new JTextField();
	
	JButton cargarButton = new JButton("Cargar Datos");
	
	
	public CrearCliente(JFrame frame2, RentaCar renta_carros, String nombre2, String username2, String tipo2, String password2) {
		frame = frame2;
		rentaCar = renta_carros;
		nombre = nombre2;
		username = username2;
		tipo = tipo2;
		password = password2;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0x266099));
		titulo.setPreferredSize(new Dimension(700,70));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 35));
		titulo.setForeground(Color.WHITE);
		frame.add(titulo, BorderLayout.NORTH);
		
		
		panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 30));
		
		agregarTabla("Contacto", contactoTxt);
		agregarTabla("Fecha de Nacimiento", fechaNacimiento);
		agregarTabla("Nacionalidad", nacionalidadTxt);
		cedulaButton.addActionListener(this);
		agregarTabla( "Imagen de Documento", cedulaButton);
		agregarTabla( "Numero Licencia", numeroLicenciaTxt);
		agregarTabla( "Pais de Licencia", paisLicenciaTxt);
		agregarTabla( "Fecha Vencimiento de Licencia", fechaVencimientoPase);
		paseButton.addActionListener(this);
		agregarTabla( "Imagen de Licencia", paseButton);
		tipoTarjeta = setBoxPagos();
		agregarTabla( "Tipo de Tarjeta", tipoTarjeta);
		agregarTabla( "Numero de Tarjeta", numeroTarjetaTxt);
		agregarTabla( "Fecha Vencimiento de Tarjeta", fechaVencimientoTarjeta);
		
		frame.add(panelPrincipal, BorderLayout.CENTER);
		
		JPanel panelBuscar = new JPanel(new FlowLayout());
		panelBuscar.setBackground(new Color(0xFAB0B9));
		panelBuscar.setOpaque(true);
		cargarButton.setBackground(new Color(0xF2465D));
		cargarButton.setOpaque(true);
		cargarButton.setForeground(Color.WHITE);
		cargarButton.setPreferredSize(new Dimension(200,30));
		cargarButton.setFocusable(false);
		cargarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cargarButton.addActionListener(this);
		panelBuscar.add(cargarButton);
		frame.add(panelBuscar, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cedulaButton) {
			fileCedula.setCurrentDirectory(new File("./imagenes"));
			int response = fileCedula.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION){
				imagenCedula = new File(fileCedula.getSelectedFile().getName());
			}
		}
		else if (e.getSource() == paseButton) {
			filePase.setCurrentDirectory(new File("./imagenes"));
			int response = filePase.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION){
				imagenPase = new File(filePase.getSelectedFile().getName());
			}
		}
		else if(e.getSource() == cargarButton) {
			String contacto = contactoTxt.getText();
			String fecha_nacimiento = DateFormat.getDateInstance().format(fechaNacimiento.getDate());
			String nacionalidad = nacionalidadTxt.getText();
			String numero_licencia = numeroLicenciaTxt.getText();
			String pais_licencia = paisLicenciaTxt.getText();
			String fecha_vencimiento_pase = DateFormat.getDateInstance().format(fechaVencimientoPase.getDate());
			String tipo_tarjeta = tipoTarjeta.getSelectedItem().toString();
			String numero_tarjeta = numeroTarjetaTxt.getText();
			String fecha_vencimiento_tarjeta = fechaVencimientoTarjeta.getText();
			
			if(imagenCedula != null && imagenPase != null) {
				
				String imagen_documento = imagenCedula.toString();
				String imagen_pase = imagenPase.toString();
				
				rentaCar.crearUsuarioCliente(nombre, tipo, username, password);
				rentaCar.crearCliente(nombre, contacto, fecha_nacimiento, nacionalidad, imagen_documento, numero_licencia, pais_licencia,
						fecha_vencimiento_pase, imagen_pase, tipo_tarjeta, numero_tarjeta, fecha_vencimiento_tarjeta, username);
				
				JOptionPane.showMessageDialog(null, "Usuario creado correctamente",
						"Creacion de Usuario", JOptionPane.INFORMATION_MESSAGE);
				
				Usuario usuario = rentaCar.encontrarUsuario(username, password);
				ConsolaRentaCar.setUsuario(usuario);
				ConsolaRentaCar.consolaCliente();
			}
			else {
				String message = "Debes seleccionar los archivos correspondientes";
				JOptionPane.showMessageDialog(null, message,
						"Error, Archivos no Seleccionados", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void agregarTabla(String topic, Component elemento) {
		JLabel label = crearLabelTabla(topic);
		panelPrincipal.add(label);
		panelPrincipal.add(elemento);
	}
	
	private JLabel crearLabelTabla(String topic) {
		Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
		JLabel label = new JLabel(topic, SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBackground(new Color(0xF2465D));
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBorder(border);
		
		return label;
	}
	
	private JComboBox setBoxPagos() {
		String[] tipos = {
			    "Visa", "MasterCard", "PayPal", "PayU"};
		JComboBox box = new JComboBox (tipos);
		return box;
	}

}
