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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PanelReservas extends JPanel implements ActionListener{
	
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
	
	public PanelReservas() {
		panelCentral.setLayout(new GridLayout(7,1,10,20));
		panelCentral.setBorder(BorderFactory.createLineBorder(new Color(0xEEEEEE), 30));
		
		ConfigurarButton(nuevaReserva,0xFF5757);
		panelCentral.add(nuevaReserva);
		
		ConfigurarButton(nuevaReservaEspecial,0xFAB0B9);
		panelCentral.add(nuevaReservaEspecial);
		
		ConfigurarButton(comenzarReserva,0xFF5757);
		panelCentral.add(comenzarReserva);
		
		ConfigurarButton(recibirCarro,0xFAB0B9);
		panelCentral.add(recibirCarro);
		
		ConfigurarButton(recibirCarroLimpieza,0xFF5757);
		panelCentral.add(recibirCarroLimpieza);
		
		ConfigurarButton(carroMantenimiento,0xFAB0B9);
		panelCentral.add(carroMantenimiento);
		
		ImageIcon imagenArreglada = new ImageIcon(imageCerrar.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		cerrarSesion.setIcon(imagenArreglada);
		cerrarSesion.setPreferredSize(new Dimension(70,70));
		cerrarSesion.addActionListener(this);
		cerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		panelCentral.add(cerrarSesion);
		
		add(panelCentral, BorderLayout.CENTER);
	}
	
	private void ConfigurarButton(JButton boton, int color) {
		boton.setBackground(new Color(color));
		boton.setOpaque(true);
		boton.setForeground(Color.BLACK);
		boton.setPreferredSize(new Dimension(600,35));
		boton.setFocusable(false);
		boton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		boton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
			ConsolaRentaCar.comenzarReserva("Sede norte");
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
