package Consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelAddEmpleado extends JPanel implements ActionListener{

	
	private JLabel bienvenida;
	private JButton back = new JButton("Back");
	private JButton send = new JButton("Send");
	private JTextField nameTF = new JTextField();
	private JTextField usernameTF = new JTextField();
	private JTextField passTF = new JTextField();
	private JComboBox sedeCB = new JComboBox();
	
	public PanelAddEmpleado() {
		
		String[] sedes = {"Sede norte","Sede sur","Sede occidente"};
		
		setLayout(new BorderLayout());
		
		bienvenida = new JLabel("Agregar Empleado", SwingConstants.CENTER);
		bienvenida.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bienvenida.setPreferredSize(new Dimension(900,40));
		bienvenida.setBackground(new Color(0xFAB0B9));
		bienvenida.setOpaque(true);
		add(bienvenida,BorderLayout.NORTH);
		
		JPanel options = new JPanel();
		
		JLabel name = new JLabel("Nombre");
		name.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		JLabel pass = new JLabel("Password");
		pass.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		JLabel sede = new JLabel("Sede");
		sede.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		sedeCB = new JComboBox(sedes);
		
		send.setFont(new Font("Times New Roman", Font.BOLD, 20));
		send.setBackground(new Color(0xFF5757));
		send.addActionListener(this);
		

		back.setFont(new Font("Times New Roman", Font.BOLD, 20));
		back.setBackground(new Color(0xFAB0B9));
		back.addActionListener(this);
		
		options.setLayout(new GridLayout(0,2,0,20));
		options.add(name);
		options.add(nameTF);
		options.add(username);
		options.add(usernameTF);
		options.add(pass);
		options.add(passTF);
		options.add(sede);
		options.add(sedeCB);
		options.add(back);
		options.add(send);
		
		add(options);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {
			ConsolaRentaCar.consolaAdminG();
		}
		else if (e.getSource()==send) {
			String name = nameTF.getText();
			String user = usernameTF.getText();
			String pass = passTF.getText();
			String sede = sedeCB.getSelectedItem().toString();
			boolean ret = ConsolaRentaCar.crearEmpleado(name, user, pass, sede);
			if (ret) {
				nameTF.setText("");
				usernameTF.setText("");
				passTF.setText("");
				JOptionPane.showMessageDialog(this, "Empleado creado correctamente", "Success", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "Error Please Verify Information", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}
