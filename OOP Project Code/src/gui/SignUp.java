package gui;

import controller.MainFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Font;
import java.awt.Image;

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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JPanel panel;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblUsernameCannotBe;
	private JLabel lblLogo;
	private JLabel lblPsGroup;
	private JLabel lblRegistration;
	private JLabel lblBackground;
	
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/data/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/data/background.jpg"));
	private JLabel label;
	
	public SignUp(MainFrame m) {
		setBackground(new Color(135, 206, 250));
		this.main = m;
		this.setLayout(null);
		main.setSize(1020,720);
		
		this.panel = new JPanel();
		this.panel.setBounds(77, 82, 862, 515);
		panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
//		panel.setLocation(main.getWidth()/2-panel.getWidth()/2, main.getHeight()/2 - panel.getHeight()/2);
		add(this.panel);
		this.panel.setLayout(null);
		
		
		this.lblFullName = new JLabel("Full Name:");
		this.lblFullName.setBounds(12, 169, 128, 35);
		this.panel.add(this.lblFullName);
		this.lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 25));

		this.txtName = new JTextField();
		this.txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					registration();
				}
			}
		});
		this.txtName.setBounds(141, 164, 278, 45);
		this.panel.add(this.txtName);
		this.txtName.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.txtName.setColumns(10);
		
		this.txtEmail = new JTextField();
		this.txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					registration();
				}
			}
		});
		this.txtEmail.setBounds(141, 233, 278, 45);
		this.panel.add(this.txtEmail);
		this.txtEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.txtEmail.setColumns(10);
		
		this.txtAddress = new JTextField();
		this.txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				if (Character.isDigit(key)||
						(key==KeyEvent.VK_COMMA&&!txtAddress.getText().contains(","))||
						(key==KeyEvent.VK_MINUS&&!txtAddress.getText().contains("-"))||
						(key==KeyEvent.VK_SPACE&&!txtAddress.getText().contains(" "))){
					return;
				}
				arg0.consume();
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					registration();
				}
			}
		});
		this.txtAddress.setToolTipText("Postal Code, Unit No.\r\n");
		this.txtAddress.setBounds(141, 301, 278, 45);
		this.txtAddress.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.panel.add(this.txtAddress);
		this.txtAddress.setColumns(10);
		
		this.txtUName = new JTextField();
		this.txtUName.setBounds(572, 194, 278, 45);
		this.txtUName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					registration();
				}
			}
		});
		this.panel.add(this.txtUName);
		this.txtUName.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.txtUName.setColumns(10);
		
		this.passwordField = new JPasswordField();
		this.passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					registration();
				}
			}
		});
		this.passwordField.setBounds(572, 263, 278, 45);
		this.panel.add(this.passwordField);
		this.passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		passwordField.setEchoChar((char)0x2022);
		
		
		this.lblGoLogin = new JLabel("Have an Account Already? Login");
		this.lblGoLogin.setBounds(141, 375, 263, 25);
		lblGoLogin.setOpaque(false);
		this.panel.add(this.lblGoLogin);
		this.lblGoLogin.setForeground(SystemColor.textInactiveText);
		this.lblGoLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGoLogin.setForeground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblGoLogin.setForeground(SystemColor.textInactiveText);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showAllLogin();
			}
		});
		this.lblGoLogin.setFont(new Font("Yet R", Font.PLAIN, 17));
		
		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setBounds(12, 238, 117, 35);
		this.panel.add(this.lblEmail);
		this.lblEmail.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		
		this.lblUsername = new JLabel("Username:");
		this.lblUsername.setBounds(449, 199, 117, 35);
		this.panel.add(this.lblUsername);
		this.lblUsername.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setBounds(449, 274, 111, 35);
		this.panel.add(this.lblPassword);
		this.lblPassword.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		
		this.lblErrors = new JLabel();
		this.lblErrors.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblErrors.setBounds(151, 413, 571, 20);
		this.panel.add(this.lblErrors);
		this.lblErrors.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!lblErrors.getText().isEmpty()) {}
//				else {chckbxShowPassword.setLocation(114,261);}
				
			}
		});
		lblErrors.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblErrors.setForeground(Color.red);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.setBounds(572, 321, 159, 25);
		chckbxShowPassword.setOpaque(false);
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char)0);
				}
				else {passwordField.setEchoChar((char)0x2022);}
				
			}
		});
		this.panel.add(this.chckbxShowPassword);
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		
		this.btnSignUp = new JButton("Sign Up");
		this.btnSignUp.setBounds(152, 480, 150, 49);
		this.btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration();
			}
		});
		this.btnSignUp.setLocation(panel.getWidth()/2-btnSignUp.getWidth()/2, panel.getHeight()-btnSignUp.getHeight() - 20);
		this.panel.add(this.btnSignUp);
		this.btnSignUp.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		
		this.lblAddress = new JLabel("Address: ");
		this.lblAddress.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		this.lblAddress.setBounds(12, 301, 117, 45);
		this.panel.add(this.lblAddress);
		
		this.lblUsernameCannotBe = new JLabel("*Username cannot be changed");
		this.lblUsernameCannotBe.setFont(new Font("Tw Cen MT", Font.ITALIC, 20));
		this.lblUsernameCannotBe.setBounds(574, 164, 276, 25);
		this.panel.add(this.lblUsernameCannotBe);
		
		this.lblLogo = new JLabel("New label");
		this.lblLogo.setBounds(352, 34, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
		this.panel.add(this.lblLogo);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(446, 39, 150, 35);
		this.panel.add(this.lblPsGroup);
		
		this.lblRegistration = new JLabel("Registration");
		this.lblRegistration.setVerticalAlignment(SwingConstants.TOP);
		this.lblRegistration.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblRegistration.setBounds(449, 85, 128, 35);
		this.panel.add(this.lblRegistration);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(449, 72, 124, 16);
		this.panel.add(this.label);
		
		this.lblBackground = new JLabel();
		this.lblBackground.setBounds(0, 0, main.getWidth(), main.getHeight());
		background.setImage(background.getImage().getScaledInstance(main.getWidth(), main.getHeight(), Image.SCALE_DEFAULT));
		lblBackground.setIcon(background);
		add(this.lblBackground);
		
		
		
		
	}
	
	public void registration(){
		String name = txtName.getText();
		String email = txtEmail.getText();
		String addr = txtAddress.getText();
		String uName = txtUName.getText();
		String pass = new String(passwordField.getPassword());
		if (name.isEmpty() ||email.isEmpty() ||addr.isEmpty() ||uName.isEmpty() ||pass.isEmpty()) {
			lblErrors.setText("Fields are Blank"); return;
		}
		if(uName.contains(" ")||pass.contains(" ")){
			lblErrors.setText("Fields contains Spaces");
			return;
		}
		if (!email.contains("@")||!email.contains(".")){
			lblErrors.setText("Please Enter Proper Email");
			return;
		}
		if (!addr.contains(",")){
			lblErrors.setText("Address format in \"Postal Code, Unit No. \" e.g.(512345, 0x-xxx) ");
			return;
		}
		
		if(main.getCont().isUser(uName)||main.getCont().isStaff(uName)){
			lblErrors.setText("Username Already in Use");
			return;
		}
		
		main.getCont().addUser(uName, pass, name, email, addr);
		main.showAllLogin();
			
	}
}
