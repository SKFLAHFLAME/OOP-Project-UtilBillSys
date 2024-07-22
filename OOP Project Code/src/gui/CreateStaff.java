package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateStaff extends JPanel{
	MainFrame main;
	private JLabel lblRegisterStaff;
	private JLabel lblStaffId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnRegister;
	private JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox chckbxShowPassword;
	private JLabel lblErrors;
	
	public CreateStaff(MainFrame m){
		this.main=m;
		this.setLayout(null);
		main.setSize(470,340);
		
		this.lblRegisterStaff = new JLabel("Register Staff");
		this.lblRegisterStaff.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.lblRegisterStaff.setBounds(145, 13, 161, 55);
		add(this.lblRegisterStaff);
		
		this.lblStaffId = new JLabel("Staff ID:");
		this.lblStaffId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblStaffId.setBounds(12, 92, 77, 19);
		add(this.lblStaffId);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblPassword.setBounds(12, 130, 77, 24);
		add(this.lblPassword);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.showAllStaff();
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.btnBack.setBounds(12, 262, 97, 25);
		add(this.btnBack);
		
		this.btnRegister = new JButton("Register");
		this.btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(main.getCurrentAcct()[0].equals("A")){
					if (main.getCont().isStaff(textField.getText())){lblErrors.setText("ID Already Used");return;}
					System.out.println("Successful");
					addStaff();
					main.showAllStaff();
				}
				else{lblErrors.setText("Not Admin");}
			}
		});
		this.btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.btnRegister.setBounds(341, 263, 97, 25);
		add(this.btnRegister);
		
		this.textField = new JTextField();
		this.textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textField.setBounds(101, 81, 324, 34);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.passwordField.setBounds(101, 125, 324, 34);
		add(this.passwordField);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxShowPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else{passwordField.setEchoChar((char)0x2022);}
			}
		});
		this.chckbxShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.chckbxShowPassword.setBounds(111, 168, 129, 34);
		add(this.chckbxShowPassword);
		
		this.lblErrors = new JLabel("");
		this.lblErrors.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblErrors.setForeground(Color.RED);
		this.lblErrors.setBounds(246, 170, 179, 34);
		add(this.lblErrors);
	}
	public void addStaff(){
		String id = textField.getText();
		String pass = new String(passwordField.getPassword());
		main.getCont().addStaff(id, pass);
	}
}
