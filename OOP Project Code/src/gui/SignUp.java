package gui;

import controller.MainFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;

public class SignUp extends JPanel{
	MainFrame main;
	private JScrollBar scrollBar;
	private JLabel lblFullName;
	private JLabel lblEmail;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtUName;
	private JPasswordField passwordField;
	private Checkbox cbShPa;
	private JLabel lblErrors;
	private JLabel lblGoLogin;
	private JCheckBox chckbxShowPassword;
	private JButton btnSignUp;
	
	public SignUp(MainFrame m) {
		this.main = m;
		this.setLayout(null);
		main.setSize(525, 390);
		
		this.lblFullName = new JLabel("Full Name:");
		this.lblFullName.setFont(new Font("Dialog", Font.BOLD, 15));
		this.lblFullName.setBounds(12, 83, 90, 15);
		add(this.lblFullName);
		
		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
		this.lblEmail.setBounds(12, 130, 70, 15);
		add(this.lblEmail);
		
		this.lblUsername = new JLabel("Username:");
		this.lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		this.lblUsername.setBounds(12, 177, 90, 15);
		add(this.lblUsername);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		this.lblPassword.setBounds(12, 224, 90, 15);
		add(this.lblPassword);
		
		this.txtName = new JTextField();
		this.txtName.setFont(new Font("Dialog", Font.PLAIN, 15));
		this.txtName.setBounds(114, 73, 340, 35);
		add(this.txtName);
		this.txtName.setColumns(10);
		
		this.txtEmail = new JTextField();
		this.txtEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		this.txtEmail.setBounds(114, 120, 340, 35);
		add(this.txtEmail);
		this.txtEmail.setColumns(10);
		
		this.txtUName = new JTextField();
		this.txtUName.setFont(new Font("Dialog", Font.PLAIN, 15));
		this.txtUName.setBounds(114, 167, 340, 35);
		add(this.txtUName);
		this.txtUName.setColumns(10);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwordField.setEchoChar((char)0x2022);
		this.passwordField.setBounds(114, 214, 340, 35);
		add(this.passwordField);
		
		this.lblErrors = new JLabel();
		this.lblErrors.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!lblErrors.getText().isEmpty()) {
				chckbxShowPassword.setLocation(114, 280);}
//				else {chckbxShowPassword.setLocation(114,261);}
				
			}
		});
		lblErrors.setFont(new Font("Dialog",Font.ITALIC,12));
		lblErrors.setForeground(Color.red);
		this.lblErrors.setBounds(114, 250, 249, 23);
		add(this.lblErrors);
		
		
		this.lblGoLogin = new JLabel("Have an Account Already? Login");
		lblGoLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.lblGoLogin.setForeground(Color.blue);
		this.lblGoLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGoLogin.setText("<HTML><U>Have an Account Already? Login<U><HTML>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGoLogin.setText("Have an Account Already? Login");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showLogin();
			}
		});
		this.lblGoLogin.setFont(new Font("Dialog", Font.BOLD, 11));
		this.lblGoLogin.setBounds(12, 323, 239, 15);
		add(this.lblGoLogin);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char)0);
				}
				else {passwordField.setEchoChar((char)0x2022);}
				
			}
		});
		this.chckbxShowPassword.setBounds(114, 256, 133, 25);
		add(this.chckbxShowPassword);
		
		this.btnSignUp = new JButton("Sign Up");
		this.btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String email = txtEmail.getText();
				String uName = txtUName.getText();
				String pass = new String(passwordField.getPassword());
				if (!uName.isEmpty() && !pass.isEmpty()) {
					if(!uName.contains(" ")&&!pass.contains(" ")){
					main.getCont().addUser(uName, pass);
					main.showLogin();}
					else{lblErrors.setText("Fields contains Spaces");}
				}
				else {lblErrors.setText("Fields are Blank");}
			}
		});
		this.btnSignUp.setBounds(337, 260, 117, 35);
		add(this.btnSignUp);
		
		
		
		
	}
}
