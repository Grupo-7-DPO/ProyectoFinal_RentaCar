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
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import Loader.RentaCar;
import Model.Categoria;
import Model.Seguro;
import Model.Usuario;

public class CrearReserva implements ActionListener{

	JFrame frame;
	RentaCar rentaCar;
	Usuario usuarioActual;
	boolean especial;
	
	
	JLabel titulo = new JLabel("Crear Nueva Reserva", SwingConstants.CENTER);
	JComboBox opcionesCategoria;
	JComboBox opcionesSedeRecogida;
	JDateChooser fechaRecogida;
	JComboBox horaRecogida;
	JComboBox opcionesSedeEntrega;
	JDateChooser fechaEntrega;
	JComboBox horaEntrega;
	JComboBox seguro;
	JButton atrasButton = new JButton();
	JButton crearReservaButton = new JButton("Crear Reserva");
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	
	
	
	public CrearReserva(RentaCar renta_carros, Usuario usuario_actual, JFrame frameAntiguo, boolean especial) {
		frame = frameAntiguo;
		rentaCar = renta_carros;
		usuarioActual = usuario_actual;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		titulo.setBackground(new Color(0xFAB0B9));
		titulo.setPreferredSize(new Dimension(700,90));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		frame.add(titulo, BorderLayout.NORTH);
		
		
		JPanel panelPrincipal = new JPanel(new GridLayout(8,2,5,5));
		panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 30));
		
		JLabel categoria = crearLabelTabla("Categoria");
		opcionesCategoria = setBoxCategorias();
		panelPrincipal.add(categoria);
		panelPrincipal.add(opcionesCategoria);
		
		JLabel sedeRecogida = crearLabelTabla("Sede Recogida");
		opcionesSedeRecogida = setBoxSedes();
		panelPrincipal.add(sedeRecogida);
		panelPrincipal.add(opcionesSedeRecogida);
		
		JLabel fechaRecogidaLabel = crearLabelTabla("Fecha Recogida");
		fechaRecogida = new JDateChooser();
		panelPrincipal.add(fechaRecogidaLabel);
		panelPrincipal.add(fechaRecogida);
		
		JLabel horaRecogidaLabel = crearLabelTabla("Hora Recogida");
		horaRecogida = setBoxHora();
		panelPrincipal.add(horaRecogidaLabel);
		panelPrincipal.add(horaRecogida);
		
		JLabel sedeEntrega = crearLabelTabla("Sede Entrega");
		opcionesSedeEntrega = setBoxSedes();
		panelPrincipal.add(sedeEntrega);
		panelPrincipal.add(opcionesSedeEntrega);
		
		JLabel fechaEntregaLabel = crearLabelTabla("Fecha Entrega");
		fechaEntrega = new JDateChooser();
		panelPrincipal.add(fechaEntregaLabel);
		panelPrincipal.add(fechaEntrega);
		
		JLabel horaEntregaLabel = crearLabelTabla("Hora Entrega");
		horaEntrega = setBoxHora();
		panelPrincipal.add(horaEntregaLabel);
		panelPrincipal.add(horaEntrega);
		
		JLabel seguroLabel = crearLabelTabla("Seguro");
		seguro = setBoxSeguros();
		panelPrincipal.add(seguroLabel);
		panelPrincipal.add(seguro);
		
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
		crearReservaButton.setBackground(new Color(0xF2465D));
		crearReservaButton.setOpaque(true);
		crearReservaButton.setForeground(Color.WHITE);
		crearReservaButton.setPreferredSize(new Dimension(200,30));
		crearReservaButton.setFocusable(false);
		crearReservaButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		crearReservaButton.addActionListener(this);
		panelBuscar.add(crearReservaButton);
		panelSouth.add(panelBuscar, BorderLayout.CENTER);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == atrasButton) {
			if(usuarioActual.getTipo().equals("C")) {
				ConsolaRentaCar.consolaCliente();
			}
		}
		else if (e.getSource() == crearReservaButton) {
			String id = rentaCar.crearId();
			String usuario = usuarioActual.getUser();
			String tipoStr = opcionesCategoria.getSelectedItem().toString();
			Categoria tipo = rentaCar.encontrarCategoria(tipoStr);
			String sedeRecogida = opcionesSedeRecogida.getSelectedItem().toString();
			String fechaRecogidaStr = DateFormat.getDateInstance().format(fechaRecogida.getDate());
			String horaRecogidaStr = horaRecogida.getSelectedItem().toString();
			String sedeEntrega = opcionesSedeEntrega.getSelectedItem().toString();
			String fechaEntregaStr = DateFormat.getDateInstance().format(fechaEntrega.getDate());
			String horaEntregaStr = horaEntrega.getSelectedItem().toString();
			String seguroStr = seguro.getSelectedItem().toString();
			Seguro seguro = rentaCar.encontrarSeguro(seguroStr);
			long precio = rentaCar.calcularTarifaReserva(fechaRecogidaStr, fechaEntregaStr, tipo, seguro);
			
			String message = "El id de tu reserva es " + id + "\nSu precio es de " + precio + "$";
			String title = "Precio de la reserva " + id;
			JOptionPane.showMessageDialog(null, message,
					title, JOptionPane.INFORMATION_MESSAGE);
			
			rentaCar.crearReserva(id, usuario, tipo, sedeRecogida, fechaRecogidaStr, horaRecogidaStr, sedeEntrega, fechaEntregaStr, horaEntregaStr, "pagado", seguro);
			String message2 = "Reserva con id " + id + " a nombre de " + usuarioActual.getNombre() + " creada correctamente";
			String title2 = "Reserva " + id + " creada";
			JOptionPane.showMessageDialog(null, message2,
					title2, JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private JComboBox setBoxSeguros() {
		
		List<Seguro> lista_seguros = rentaCar.getSeguros();
		String[] seguros = new String[lista_seguros.size()];
		int i = 0;
		for(Seguro seguro_iteracion : lista_seguros) {
			if(i==0) {
				seguros[i] = "Ninguno";
			}
			else {
				seguros[i] = seguro_iteracion.getNombre();
			}
			i++;
		}
		JComboBox boxSeguros = new JComboBox(seguros);
		return boxSeguros;
	}

	private JComboBox setBoxHora() {
		String[] horasDelDia = {
			    "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
			    "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
			    "18:00"};
		JComboBox boxHoras = new JComboBox(horasDelDia);
		return boxHoras;
	}

	private JComboBox setBoxSedes() {
		List<String> lista_sedes = rentaCar.getNombreSedes();
		String[] sedes = new String[lista_sedes.size()];
		int i = 0;
		for (String nombre_sede : lista_sedes) {
			sedes[i] = nombre_sede;
			i++;
		}
		JComboBox sedesBox = new JComboBox(sedes);
		return sedesBox;
	}

	private JComboBox setBoxCategorias() {
		
		List<Categoria> lista_categorias = rentaCar.getCategorias();
		String[] categorias = new String[lista_categorias.size()];
		int i = 0;
		for (Categoria categoria_iteracion : lista_categorias) {
			String nombre = categoria_iteracion.getNombre();
			categorias[i] = nombre;
			i++;
		}
		JComboBox categoriasBox = new JComboBox(categorias);
		return categoriasBox;
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

	

}
