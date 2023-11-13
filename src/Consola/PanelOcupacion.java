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

public class PanelOcupacion extends JPanel implements ActionListener{

	JLabel bienvenida;
	JPanel panelCentral = new JPanel();
	
	JButton cerrarSesion = new JButton();
	ImageIcon imageCerrar = new ImageIcon("./imagenes/cerrarSesion.png");
	
	JButton sedeSur = new JButton("Sede sur");
	JButton sedeNorte = new JButton("Sede norte");
	JButton sedeOccidente = new JButton("Sede occidente");
	
	public PanelOcupacion() {
		panelCentral.setLayout(new GridLayout(7,1,10,20));
		panelCentral.setBorder(BorderFactory.createLineBorder(new Color(0xEEEEEE), 30));
		
		ConfigurarButton(sedeSur,0xFF5757);
		panelCentral.add(sedeSur);
		
		ConfigurarButton(sedeNorte,0xFAB0B9);
		panelCentral.add(sedeNorte);
		
		ConfigurarButton(sedeOccidente,0xFF5757);
		panelCentral.add(sedeOccidente);
		
		
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
		else if(e.getSource() == sedeSur) {
			ConsolaRentaCar.verOcupacion("Sede sur");
		}
		else if(e.getSource() == sedeNorte) {
			ConsolaRentaCar.verOcupacion("Sede norte");
		}
		else if(e.getSource() == sedeOccidente) {
			ConsolaRentaCar.verOcupacion("Sede occidente");
		}
	}

}
