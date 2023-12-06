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
import java.text.DateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import Loader.RentaCar;
import Loader.RentaCliente;
import Model.Usuario;

public class ConsultarDisponibilidad implements ActionListener{
	JFrame frame;
	Usuario usuarioActual;
	RentaCar rentaCarros;
	JLabel bienvenida = new JLabel("Consultar Disponibilidad de Vehiculos", SwingConstants.CENTER);
	JComboBox sedesBox;
	JDateChooser fechaInicial = new JDateChooser();
	JDateChooser fechaFinal = new JDateChooser();
	JButton buscarButton = new JButton("Buscar");
	JButton atrasButton = new JButton();
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	JPanel panelSouth = new JPanel(new BorderLayout());

	public ConsultarDisponibilidad(JFrame frameAnterior, Usuario usuario_actual, RentaCar renta_carros) {
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCarros = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(700,100));
		frame.add(bienvenida, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel(new GridLayout(3,2,20,20));
		panelCentral.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 70));
		
		JLabel sedeLabel = crearLabelTabla("Sede");
		setBoxSedes();
		panelCentral.add(sedeLabel);
		panelCentral.add(sedesBox);
		
		JLabel fechaInicialLbl = crearLabelTabla("Fecha Inicial");
		panelCentral.add(fechaInicialLbl);
		panelCentral.add(fechaInicial);
		
		JLabel fechaFinalLbl = crearLabelTabla("Fecha Final");
		panelCentral.add(fechaFinalLbl);
		panelCentral.add(fechaFinal);
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		atrasButton.setIcon(imagenArreglada);
		atrasButton.setFocusable(false);
		atrasButton.setPreferredSize(new Dimension(70,70));
		atrasButton.addActionListener(this);
		panelSouth.add(atrasButton, BorderLayout.WEST);
		
		JPanel panelBuscar = new JPanel(new FlowLayout());
		panelBuscar.setBackground(new Color(0xFAB0B9));
		panelBuscar.setOpaque(true);
		buscarButton.setBackground(new Color(0xF2465D));
		buscarButton.setOpaque(true);
		buscarButton.setForeground(Color.WHITE);
		buscarButton.setPreferredSize(new Dimension(200,30));
		buscarButton.setFocusable(false);
		buscarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		buscarButton.addActionListener(this);
		panelBuscar.add(buscarButton);
		panelSouth.add(panelBuscar, BorderLayout.CENTER);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}

	private JComboBox setBoxSedes() {
		List<String> lista_sedes = rentaCarros.getNombreSedes();
		String[] sedes = new String[lista_sedes.size()];
		int i = 0;
		for (String nombre_sede : lista_sedes) {
			sedes[i] = nombre_sede;
			i++;
		}
		sedesBox = new JComboBox(sedes);
		return sedesBox;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == atrasButton) {
			ConsolaRentaCar.consolaCliente();
		}
		else if(e.getSource() == buscarButton) {
			String sedeElegida = sedesBox.getSelectedItem().toString();
			String fechaInicialstr = DateFormat.getDateInstance().format(fechaInicial.getDate());
			String fechaFinalstr = DateFormat.getDateInstance().format(fechaFinal.getDate());
			
			showInventario(sedeElegida, fechaInicialstr, fechaFinalstr);
		}
		
	}
	
	private void showInventario(String sede, String fechaInicial, String fechaFinal) {
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setSize(700,500);
		frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        
        JPanel panelCentral = new JPanel(new BorderLayout());
        JPanel panelInv = new JPanel();
        panelInv.setBackground(new Color(0xFAB0B9));
        panelInv.setOpaque(true);
        
        frame.add(bienvenida, BorderLayout.NORTH);
        String [][] inv = null;
        
        inv = rentaCarros.getTableInventario(rentaCarros.getInventarioDispSede(sede));
        String tipo = "Inventario Disponible en " + sede + " entre " + fechaInicial + " y " + fechaFinal;
        JLabel info = new JLabel(tipo, SwingConstants.CENTER);
		info.setFont(new Font("Times New Roman", Font.BOLD, 20));
		info.setPreferredSize(new Dimension(900,40));
		info.setBackground(new Color(0xFAB0B9));
		info.setOpaque(true);
        
        String column[] = {"PLACA",  "TIPO",  "CAPACIDAD",  "MODELO",  "MARCA",  "ESTADO"};
		JTable invTable = new JTable(inv,column);
		invTable.setEnabled(false);
		panelInv.add(new JScrollPane(invTable));
		
		panelCentral.add(info, BorderLayout.NORTH);
		panelCentral.add(panelInv, BorderLayout.CENTER);
		
		frame.add(panelCentral,BorderLayout.CENTER);
		
		JPanel panelAtras = new JPanel(new BorderLayout());
		panelAtras.add(atrasButton, BorderLayout.WEST);
			
		JPanel panelBuscar = new JPanel(new FlowLayout());
		panelBuscar.setBackground(new Color(0xFAB0B9));
		panelBuscar.setOpaque(true);
		panelAtras.add(panelBuscar, BorderLayout.CENTER);
			
		frame.add(panelAtras, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate(); 
		frame.repaint();
		frame.pack();
	    frame.setVisible(true);
		
	}
}
