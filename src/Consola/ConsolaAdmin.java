package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Loader.RentaCar;
import Model.Usuario;

public class ConsolaAdmin {
	private static JFrame f;
	private Usuario user;
	private static RentaCar rent;
	
	private JTabbedPane tp;
	
	private JLabel bienvenida;
	private ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	
	
	public ConsolaAdmin(JFrame frame, Usuario usuario_actual, RentaCar renta_carros)
	{
		this.f = frame;
		this.user = usuario_actual;
		this.rent = renta_carros;
		
		f.getContentPane().removeAll();
		f.repaint();
		f.setPreferredSize(new Dimension(900,765));
		f.setLayout(new BorderLayout());
        f.setResizable(false);
		
		//JTextArea ta=new JTextArea(45,120);  
	    JPanel p1=new PanelInventario(true);  
	    JPanel p2=new PanelReservas();  
	    JPanel p4 = new PanelOcupacion();
	    //JPanel p4 
	    JTabbedPane tp=new JTabbedPane();
	    tp.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    tp.setSize(700,500);  
	    tp.add("Inventario",p1);  
	    tp.add("Reservas",p2);  
	    tp.add("Ocupacion",p4);

	    bienvenida = new JLabel("Bienvenido Admin", SwingConstants.CENTER);
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
	
	
	
	public static void showAddEmpleado() {
		f.getContentPane().removeAll();
		f.repaint();
		f.setPreferredSize(new Dimension(700,500));
		f.setLayout(new BorderLayout());
        f.setResizable(false);
        
        PanelAddEmpleado addEmp = new PanelAddEmpleado();

        f.add(addEmp,BorderLayout.CENTER);
		f.getContentPane().revalidate(); 
		f.repaint();
		f.pack();
	    f.setVisible(true);
	}
	
	
}
