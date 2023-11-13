package Consola;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelInventario extends JPanel implements ActionListener{
	private JButton invGeneral = new JButton("Inventario General");
	private JButton invDisp = new JButton ("Inventario Disponible"); 
	private JButton invDispSede = new JButton ("Inventario Disponible en Sede");
	private JButton addCar = new JButton("Añadir Carro");
	private JButton elimCar = new JButton("Eliminar Carro");
	private JButton addInsurance = new JButton("Crear seguro");
	private JButton cerrarSesion  = new JButton();
	private ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	private int fontSize = 25;
	private int widthB = 800;
	private int heightB = 50;
	private int padding = 50;
	
	public PanelInventario() {
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		add(Box.createVerticalStrut(padding));
		
		invGeneral.setSize(new Dimension(widthB,heightB));
		invGeneral.setAlignmentX(CENTER_ALIGNMENT);
		invGeneral.setBackground(new Color(0xFF5757));
		invGeneral.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		invGeneral.addActionListener(this);
		add(invGeneral);
		
		add(Box.createVerticalStrut(padding));
		
		invDisp.setSize(new Dimension(widthB,heightB));
		invDisp.setAlignmentX(CENTER_ALIGNMENT);
		invDisp.setBackground(new Color(0xFAB0B9));
		invDisp.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		invDisp.addActionListener(this);
		add(invDisp);
		
		add(Box.createVerticalStrut(padding));
		
		invDispSede.setPreferredSize(new Dimension(widthB,heightB));
		invDispSede.setAlignmentX(CENTER_ALIGNMENT);
		invDispSede.setBackground(new Color(0xFF5757));
		invDispSede.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		invDispSede.addActionListener(this);
		add(invDispSede);
		
		add(Box.createVerticalStrut(padding));
		
		addCar.setPreferredSize(new Dimension(widthB,heightB));
		addCar.setAlignmentX(CENTER_ALIGNMENT);
		addCar.setBackground(new Color(0xFAB0B9));
		addCar.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		addCar.addActionListener(this);
		add(addCar);
		
		add(Box.createVerticalStrut(padding));
		
		elimCar.setPreferredSize(new Dimension(widthB,heightB));
		elimCar.setAlignmentX(CENTER_ALIGNMENT);
		elimCar.setBackground(new Color(0xFF5757));
		elimCar.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		elimCar.addActionListener(this);
		add(elimCar);
		
		add(Box.createVerticalStrut(padding));
		
		addInsurance.setPreferredSize(new Dimension(widthB,heightB));
		addInsurance.setAlignmentX(CENTER_ALIGNMENT);
		addInsurance.setBackground(new Color(0xFAB0B9));
		addInsurance.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		addInsurance.addActionListener(this);
		add(addInsurance);
		
		add(Box.createVerticalStrut(padding));
		
		ImageIcon imagenArreglada = new ImageIcon(imageCerrar.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		cerrarSesion.setIcon(imagenArreglada);
		cerrarSesion.setPreferredSize(new Dimension(70,70));
		cerrarSesion.addActionListener(this);
		cerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		add(cerrarSesion);

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == invGeneral) {
			ConsolaAdminG.showInventario("Inventario General", null);
		}
		else if(e.getSource() == invDisp) {
			ConsolaAdminG.showInventario("Inventario Disponible",null);
		}
		else if(e.getSource() == invDispSede) {
			ConsolaAdminG.showInventario("Inventario Sede","Sede norte");
		}
		else if(e.getSource() == addCar) {
			ConsolaAdminG.showAddCar();
		}
		else if(e.getSource() == elimCar) {
			ConsolaAdminG.showRemCar();
		}
		
		else if(e.getSource() == addInsurance) {
			ConsolaAdminG.showAddInsurance();
		}
		
		else if(e.getSource() == cerrarSesion) {
			ConsolaRentaCar.setUsuario(null);
			ConsolaRentaCar.login();
		}
		
	}
	
	
}
