package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class ConsolaAdminG {
	private static JFrame f;
	private Usuario user;
	private static RentaCar rent;
	
	private JTabbedPane tp;
	
	private JLabel bienvenida;
	private ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	
	
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
		
		//JTextArea ta=new JTextArea(45,120);  
	    JPanel p1=new PanelInventario();  
	    //p1.add(ta);  
	    JPanel p2=new JPanel();  
	    JPanel p3=new JPanel();  
	    JTabbedPane tp=new JTabbedPane();
	    tp.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    tp.setSize(300,200);  
	    tp.add("Inventario",p1);  
	    tp.add("Reservas",p2);  
	    tp.add("Empleados",p3);

	    bienvenida = new JLabel("Bienvenido Admin General", SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(900,40));
		bienvenida.setBackground(new Color(0xFF5757));
		bienvenida.setOpaque(true);
	    
	    f.add(bienvenida,BorderLayout.NORTH);
		f.add(tp,BorderLayout.CENTER);
		
		
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
	}
	

	public static void showInventario(String tipo,String sede) {
		f.getContentPane().removeAll();
		f.repaint();
		f.setSize(700,500);
		f.setLayout(new BorderLayout());
        f.setResizable(false);
        String [][] inv = null;
        if (tipo.equals("Inventario General")) {
        	inv = rent.getTableInventario(rent.getInventarioGeneral());
        }
        else if (tipo.equals("Inventario Disponible")) {
        	inv = rent.getTableInventario(rent.getInventarioDisponible());
        }
        else if(tipo.equals("Inventario Sede")) {
        	inv = rent.getTableInventario(rent.getInventarioDispSede(sede));
        	tipo = "Inventario Disponible en " + sede;
        }
        
		PanelShowInv showInv = new PanelShowInv(inv,tipo);
		if (sede != null) {
			showInv.addChoice();
		}
		
		f.add(showInv,BorderLayout.CENTER);
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
		
	}
	
	public static void showAddCar() {
		f.getContentPane().removeAll();
		f.repaint();
		f.setSize(700,500);
		f.setLayout(new BorderLayout());
        f.setResizable(false);
        
        PanelAddCar showAdd = new PanelAddCar();
        
        f.add(showAdd,BorderLayout.CENTER);
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
	}
	
	public static void showRemCar() {
		f.getContentPane().removeAll();
		f.repaint();
		f.setSize(700,500);
		f.setLayout(new BorderLayout());
        f.setResizable(false);
        
        PanelRemoveCar showRem = new PanelRemoveCar();
        
        f.add(showRem,BorderLayout.CENTER);
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
	}
}
