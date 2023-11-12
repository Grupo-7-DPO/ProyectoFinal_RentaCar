package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class ConsolaAdminG {
	private JFrame f;
	private Usuario user;
	private RentaCar rent;
	
	JTabbedPane tp;
	
	JLabel bienvenida;
	JButton nuevaReserva = new JButton("Nueva Reserva");
	JButton consultarReserva = new JButton("Consultar Reserva");
	JButton cerrarSesion  = new JButton("Cerrar Sesion");
	JPanel panel = new JPanel();
	ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	
	
	public ConsolaAdminG(JFrame frame, Usuario usuario_actual, RentaCar renta_carros)
	{
		this.f = frame;
		this.user = usuario_actual;
		this.rent = renta_carros;
		
		f.getContentPane().removeAll();
		f.repaint();
		f.setSize(700,500);
		f.setLayout(new BorderLayout());
        f.setResizable(false);
		
		JTextArea ta=new JTextArea(200,200);  
	    JPanel p1=new JPanel();  
	    p1.add(ta);  
	    JPanel p2=new JPanel();  
	    JPanel p3=new JPanel();  
	    JTabbedPane tp=new JTabbedPane();
	    tp.setSize(300,200);  
	    tp.add("Inventario",p1);  
	    tp.add("Reservas",p2);  
	    tp.add("Empleados",p3);
	    
	    bienvenida = new JLabel("Bienvenido Admin General", SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(300,20));
	    
	    f.add(bienvenida,BorderLayout.NORTH);
		f.add(tp,BorderLayout.CENTER);
		
		
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
	}
}
