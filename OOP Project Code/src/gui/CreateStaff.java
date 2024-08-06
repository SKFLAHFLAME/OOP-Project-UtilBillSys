package gui;

import javax.swing.JPanel;

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
	private JLabel lblRegisterStaff;
	private JLabel lblStaffId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnRegister;
	private JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox chckbxShowPassword;
	private JLabel lblErrors;
	private PopupDialog window;
	
	public CreateStaff(PopupDialog popupDialog){
		this.window=popupDialog;
		this.setLayout(null);
		window.setSize(470,340);
		
		this.lblRegisterStaff = new JLabel("Register Staff");
		this.lblRegisterStaff.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.lblRegisterStaff.setBounds(145, 13, 161, 55);
		add(this.lblRegisterStaff);
		
		this.lblStaffId = new JLabel("Staff ID:");
		this.lblStaffId.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblStaffId.setBounds(12, 78, 106, 47);
		add(this.lblStaffId);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblPassword.setBounds(12, 136, 112, 51);
		add(this.lblPassword);
		
		this.btnBack = new JButton("Cancel");
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(12, 246, 129, 41);
		add(this.btnBack);
		
		this.btnRegister = new JButton("Register");
		this.btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(window.main.getCurrentAcct()[0].equals("A")){
					if (window.main.getCont().isStaff(textField.getText())||window.main.getCont().isUser(textField.getText())){lblErrors.setText("ID Already Used");return;}
					System.out.println("Successful");
					addStaff();
					window.dispose();
				}
				else{lblErrors.setText("Not Admin");}
			}
		});
		this.btnRegister.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnRegister.setBounds(309, 246, 129, 42);
		add(this.btnRegister);
		
		this.textField = new JTextField();
		this.textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		this.textField.setBounds(128, 78, 295, 47);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		this.passwordField.setBounds(128, 139, 295, 48);
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
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.chckbxShowPassword.setBounds(128, 194, 149, 34);
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
		window.main.getCont().addStaff(id, pass);
	}
}
