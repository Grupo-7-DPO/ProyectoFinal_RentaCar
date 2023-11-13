package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Model.Usuario;

public class PanelShowInv extends JPanel implements ActionListener{
	
	private JLabel bienvenida;
	private JButton back = new JButton("Back");
	String[][] data;
	
	
	public PanelShowInv(String[][] data, String tipo) {
		JPanel top = new JPanel();
		JPanel inv = new JPanel();
		
		bienvenida = new JLabel(tipo, SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(900,40));
		bienvenida.setBackground(new Color(0xFAB0B9));
		bienvenida.setOpaque(true);
		
		this.data = data;
		String column[] = {"PLACA",  "TIPO",  "CAPACIDAD",  "MODELO",  "MARCA",  "ESTADO"};
		JTable invTable = new JTable(data,column);
		invTable.setEnabled(false);
		
		setLayout(new BorderLayout());
		top.add(bienvenida);
		add(top,BorderLayout.NORTH);
		inv.add(new JScrollPane(invTable));
		add(inv,BorderLayout.CENTER);
		
		back.setSize(60, 30);
		back.setFont(new Font("Times New Roman", Font.BOLD, 20));
		back.setBackground(new Color(0xFF5757));
		back.addActionListener(this);
		add(back,BorderLayout.WEST);
		
	}
	
	public void addChoice() {
		String[] sedes = {"Sede norte", "Sede sur", "Sede occidente"};
    	JComboBox choice = new JComboBox(sedes);
    	JPanel panelChoice = new JPanel();
    	panelChoice.setLayout(new BoxLayout(panelChoice,BoxLayout.Y_AXIS));
    	add(panelChoice,BorderLayout.EAST);
    	panelChoice.add(choice);
    	JButton button = new JButton("Buscar");
    	panelChoice.add(button,BorderLayout.EAST);
    	button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 
    			String sede = ""+choice.getItemAt(choice.getSelectedIndex());
    			ConsolaAdminG.showInventario("Inventario Sede", sede);
    		}
    	});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			Usuario user = ConsolaRentaCar.getUsuario();
			String tipo = user.getTipo();
			if (tipo.equals("AG")) {
				ConsolaRentaCar.consolaAdminG();
			}
			else if(tipo.equals("A")) {
				ConsolaRentaCar.consolaAdmin();
			}
		}
		
	}
	
	
}
