package gui;

import javax.swing.JPanel;

import controller.MainFrame;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class AllLogin extends JPanel{
	MainFrame main;
	private ImageIcon logo ;
	private ImageIcon background;
	private JPanel panel;
	private JLabel lblIcon;
	private JLabel lblLogin;
	private JButton btnLogin;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField textField;
	private JPasswordField passwordField;
	private JCheckBox chckbxShowPassword;
	private JLabel lblError;
	private JLabel lblPsGroup;
	private JLabel lblForgotPassword;
	private JLabel lblNoAccountSign;
	private JLabel lblBackGround;
	private JLabel label;
	
	public AllLogin(MainFrame m){
		main = m;
		setBackground(new Color(135, 206, 250));
		setLayout(null);
		main.setSize(1020,720);
		
		logo = new ImageIcon(AllLogin.class.getResource(main.getLogoFP()));
		background = new ImageIcon(AllLogin.class.getResource(main.getBackgroundFP()));
		
		this.panel = new JPanel();
//		this.panel.setBackground(SystemColor.menu);
		panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
		this.panel.setBounds(299, 82, 450, 550);
//		panel.setLocation(main.getWidth()/2-panel.getWidth()/2, main.getHeight()/2 - panel.getHeight()/2);
		add(this.panel);
		this.panel.setLayout(null);
		
		this.lblIcon = new JLabel();
		this.lblIcon.setBounds(110, 29, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblIcon.getHeight(), lblIcon.getHeight(), Image.SCALE_DEFAULT));
		lblIcon.setIcon(logo);
		this.panel.add(this.lblIcon);
		
		this.lblLogin = new JLabel("Login");
		this.lblLogin.setVerticalAlignment(SwingConstants.TOP);
		this.lblLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblLogin.setBounds(207, 84, 87, 34);
		this.panel.add(this.lblLogin);
		
		this.btnLogin = new JButton("Login");
		this.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextPage();
			}
		});
		this.btnLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnLogin.setBounds(154, 488, 150, 49);
		btnLogin.setLocation(panel.getWidth()/2-btnLogin.getWidth()/2, panel.getHeight()-btnLogin.getHeight()-20);
		this.panel.add(this.btnLogin);
		
		this.lblUsername = new JLabel("Username: ");
		this.lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblUsername.setBounds(10, 169, 111, 49);
		this.panel.add(this.lblUsername);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblPassword.setBounds(10, 239, 105, 43);
		this.panel.add(this.lblPassword);
		
		this.textField = new JTextField();
		this.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char key=arg0.getKeyChar();
				if(key == KeyEvent.VK_ENTER){
					nextPage();
				}
			}
		});
		this.textField.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.textField.setBounds(125, 169, 304, 49);
		this.panel.add(this.textField);
		this.textField.setColumns(10);
		
		this.passwordField = new JPasswordField();
		passwordField.setEchoChar((char)0x2022);
		this.passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char key=e.getKeyChar();
				if(key == KeyEvent.VK_ENTER){
					nextPage();
				}
			}
		});
		this.passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.passwordField.setBounds(125, 239, 304, 49);
		this.panel.add(this.passwordField);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setOpaque(false);
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxShowPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else {passwordField.setEchoChar((char)0x2022);}
			}
		});
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.chckbxShowPassword.setBounds(125, 299, 156, 23);
		this.panel.add(this.chckbxShowPassword);
		
		this.lblError = new JLabel("");
		this.lblError.setForeground(new Color(255, 0, 0));
		this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 16));
		this.lblError.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
//				if (!lblError.getText().isEmpty()){
//					chckbxShowPassword.setLocation(138, chckbxShowPassword.getLocation().y+lblError.getHeight());
//				}
//				else {chckbxShowPassword.setLocation(138,300);}
			}
		});
		this.lblError.setBounds(125, 135, 304, 23);
		this.panel.add(this.lblError);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(206, 40, 132, 33);
		this.panel.add(this.lblPsGroup);
		
		this.lblForgotPassword = new JLabel("Forgot Password");
		lblForgotPassword.setOpaque(false);
		this.lblForgotPassword.setForeground(SystemColor.textInactiveText);
		this.lblForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblForgotPassword.setForeground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblForgotPassword.setForeground(SystemColor.textInactiveText);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showResetDetails();
			}
		});
		this.lblForgotPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		this.lblForgotPassword.setFont(new Font("Yet R", Font.PLAIN, 17));
		this.lblForgotPassword.setBounds(297, 299, 132, 23);
		this.panel.add(this.lblForgotPassword);
		
		this.lblNoAccountSign = new JLabel("No Account? Sign Up!");
		this.lblNoAccountSign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showSignUp();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNoAccountSign.setForeground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNoAccountSign.setForeground(SystemColor.textInactiveText);
			}
		});
		this.lblNoAccountSign.setForeground(SystemColor.textInactiveText);
		this.lblNoAccountSign.setFont(new Font("Yet R", Font.PLAIN, 17));
		this.lblNoAccountSign.setBounds(125, 359, 176, 23);
		this.panel.add(this.lblNoAccountSign);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(207, 71, 141, 16);
		this.panel.add(this.label);
		
		this.lblBackGround = new JLabel("");
		this.lblBackGround.setBounds(0,0, main.getWidth(), main.getHeight());
		background.setImage(background.getImage().getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), Image.SCALE_DEFAULT));
		lblBackGround.setIcon(background);
		add(this.lblBackGround);
		
		
	}
	
	public void nextPage(){
        String name = textField.getText();
        String pass = new String(passwordField.getPassword());
        if (main.getCont().verifyUser(name, pass)) {
            lblError.setText("Success!");
            main.setCurrentAcct("C", name);
            main.showCustMenu();
        }
        else if (main.getCont().verifyStaff(name, pass)){
            if (name.equals(main.getCont().getAllStaff()[0].getUsername())){
                lblError.setText("Admin Login");
                main.setCurrentAcct("A", main.getCont().getAllStaff()[0].getUsername());
                main.showAdminMenu();
            }
            else{lblError.setText("Success");main.setCurrentAcct("S", name);main.showStaffMenu();}
        }
        else{
            lblError.setText("Email or Password incorrect. Try Again.");
            } 
    }
}
