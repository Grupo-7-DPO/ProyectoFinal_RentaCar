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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Loader.RentaCar;
import Model.Usuario;

public class ConsolaOcupacion implements ActionListener{

	JFrame frame;
	RentaCar rentaCar;
	Usuario usuarioActual;
	
	JLabel titulo = new JLabel("Ocupación por Meses", SwingConstants.CENTER);
	
	JButton atrasButton = new JButton();
	ImageIcon imageAtras = new ImageIcon("./imagenes/boton_atras.png");
	
	public ConsolaOcupacion(RentaCar renta_carros, JFrame frameAnterior, Usuario usuario_actual) {
		frame = frameAnterior;
		usuarioActual = usuario_actual;
		rentaCar = renta_carros;
		
		frame.getContentPane().removeAll();
		frame.repaint();
		frame.setPreferredSize(new Dimension(700,500));
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		
		titulo.setBackground(new Color(0xF2465D));
		titulo.setForeground(Color.BLACK);
		titulo.setPreferredSize(new Dimension(700,90));
		titulo.setOpaque(true);
		titulo.setFont(new Font("Times New Roman", Font.BOLD, 35));
		frame.add(titulo, BorderLayout.NORTH);
		
		Integer[][] matriz = rentaCar.creacionMatrizOcupacion();
		
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(new Color(0xFAB0B9));
		
		JLabel dias = new JLabel("DÍAS", SwingConstants.CENTER);
		dias.setFont(new Font("Times New Roman", Font.BOLD, 23));
		dias.setForeground(Color.BLACK);
		dias.setPreferredSize(new Dimension(700,50));
		JLabel meses = new JLabel(" MESES");
		meses.setFont(new Font("Times New Roman", Font.BOLD, 20));
		meses.setForeground(Color.BLACK);
		JLabel vacio = new JLabel();
		vacio.setPreferredSize(new Dimension(50,400));
		
		JPanel panelTabla = new JPanel(new GridLayout(12,30,5,5));
		panelTabla.setBorder(BorderFactory.createLineBorder(new Color(0xFAB0B9), 20));
		panelTabla.setBackground(Color.BLACK);
		panelTabla.setOpaque(true);
		for(int i = 0;i<matriz.length;i++) {
			for(int j = 0; j<matriz[i].length; j++) {
				int valorMatriz = matriz[i][j];
				int valor = 255 - valorMatriz*70;
				if (valor < 0) {
					valor = 0;
				}
				JLabel cuadrado = new JLabel();
				cuadrado.setBackground(new Color(255,valor,valor));
				cuadrado.setOpaque(true);
				panelTabla.add(cuadrado);
			}
		}
		
		panelCentral.add(panelTabla, BorderLayout.CENTER);
		panelCentral.add(dias, BorderLayout.NORTH);
		panelCentral.add(meses, BorderLayout.WEST);
		panelCentral.add(vacio, BorderLayout.EAST);
		
		
		frame.add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel(new BorderLayout());
		panelSouth.setBackground(new Color(0xFAB0B9));
        ImageIcon imagenArreglada = new ImageIcon(imageAtras.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
		atrasButton.setIcon(imagenArreglada);
		atrasButton.setFocusable(false);
		atrasButton.setPreferredSize(new Dimension(70,70));
		atrasButton.addActionListener(this);
		panelSouth.add(atrasButton, BorderLayout.WEST);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		frame.getContentPane().revalidate();
		frame.pack();
	    frame.repaint(); 
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == atrasButton) {
			if(usuarioActual.getTipo().equals("A")) {
				ConsolaRentaCar.consolaAdmin();
			}
			else if(usuarioActual.getTipo().equals("AG")) {
				ConsolaRentaCar.consolaAdminG();
			}
		}
	}

}
