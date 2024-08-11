package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.MainFrame;
import data.Customer;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.PasswordAuthentication;
import javax.swing.JCheckBox;

public class ResetDetails extends JPanel{
	// Array of forgotten items for the combo box
	String[] forgottenItems = {"Password", "Username"};
	// Reference to the MainFrame object
	MainFrame main;
	private JLabel lblBackground;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private JLabel lblLogo;
	private JLabel lblPsGroup;
	private JLabel label;
	private JLabel lblReset;
	private JComboBox comboBox;
	private JTextField txtVerification;
	private JButton btnNext;
	private JButton btnBack;
	private JLabel lblIForgottenMy;
	private JLabel lblPleaseVerifyIts;
	private JLabel lblEmail;
	
	private Customer user;
	private boolean page2 = false;// Boolean flag to track if the user is on the second page
	private JLabel lblNewPassword;
	private JLabel lblConfirmPassword;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirm;
	private JButton btnReset;
	private JButton btnBack_1;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton btnLogin;
	private JLabel lblYourUsernameIs;
	private JLabel lblUsername;
	private JLabel lblError;
	private JLabel lblError_1;
	private JCheckBox chckbxShowPassword;
	private JLabel lblCaseSensitive;
    
    // Constructor to initialize the ResetDetails panel
    public ResetDetails(MainFrame m) {
        // Set the background color of the panel
        setBackground(new Color(135, 206, 250));
        
        // Set the main frame reference
        this.main = m;
		this.setLayout(null);
		main.setSize(1020, 720);
		
		
		
		
		
		
		
		
		// Initialize the all page of the form
		initPage1();
		init2();
		init3();
		
		
		
		
		
        this.lblBackground = new JLabel("Background");
        lblBackground.setSize(main.getWidth(), main.getHeight());
        // Resize and set the background image
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
		add(this.lblBackground);
	
		
	}
	
	//selection page
	public void initPage1(){
		this.panel1 = new JPanel();
		panel1.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
		this.panel1.setBounds(261, 83, 500, 550);
		add(this.panel1);
		this.panel1.setLayout(null);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(237, 40, 132, 33);
		this.panel1.add(this.lblPsGroup);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(237, 63, 141, 16);
		this.panel1.add(this.label);
		
		this.lblReset = new JLabel("Reset");
		this.lblReset.setVerticalAlignment(SwingConstants.TOP);
		this.lblReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblReset.setBounds(237, 85, 154, 34);
		this.panel1.add(this.lblReset);
		
		this.lblLogo = new JLabel("logo");
		this.lblLogo.setBounds(137, 29, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
		this.panel1.add(this.lblLogo);
		
		
		
		
		
		this.comboBox = new JComboBox(forgottenItems);
		this.comboBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.comboBox.setBounds(237, 212, 237, 43);
		this.panel1.add(this.comboBox);
		
		
		this.txtVerification = new JTextField();
		this.txtVerification.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
					user = main.getCont().getCustomerByEmail(txtVerification.getText());
					if (user ==null){lblError.setText("No Account Found");return;}
					if (comboBox.getSelectedIndex()==1){
						lblUsername.setText(user.getUsername());
						showPanel3();
					}
					else {showPanel2();}
				}
			}
		});
		this.txtVerification.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtVerification.setBounds(237, 332, 237, 43);
		this.panel1.add(this.txtVerification);
		this.txtVerification.setColumns(10);
		
		
		
		this.btnNext = new JButton("Next");
		this.btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user = main.getCont().getCustomerByEmail(txtVerification.getText());
				if (user ==null){lblError.setText("No Account Found");return;}
				if (comboBox.getSelectedIndex()==1){
					lblUsername.setText(user.getUsername());
					showPanel3();
				}
				else {showPanel2();}
			}
		});
		this.btnNext.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnNext.setBounds(342, 453, 132, 43);
		this.panel1.add(this.btnNext);
		
		
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
            // Action listener to handle the back button click
            public void actionPerformed(ActionEvent e) {
                main.showAllLogin(); // Show the main login screen
            }
        });
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(27, 453, 132, 43);
		this.panel1.add(this.btnBack);
		
		
		
		this.lblIForgottenMy = new JLabel("I forgotten my: ");
		this.lblIForgottenMy.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		this.lblIForgottenMy.setBounds(27, 213, 206, 43);
		this.panel1.add(this.lblIForgottenMy);
		
		
		
		this.lblPleaseVerifyIts = new JLabel("Please verify it's you!");
		this.lblPleaseVerifyIts.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		this.lblPleaseVerifyIts.setBounds(21, 332, 206, 43);
		this.panel1.add(this.lblPleaseVerifyIts);
		
		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.lblEmail.setBounds(237, 301, 80, 33);
		this.panel1.add(this.lblEmail);
		
		this.lblError = new JLabel();
		this.lblError.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		this.lblError.setForeground(Color.RED);
		this.lblError.setBounds(27, 144, 290, 33);
		this.panel1.add(this.lblError);
		
		this.lblCaseSensitive = new JLabel("* Case Sensitive");
		this.lblCaseSensitive.setHorizontalAlignment(SwingConstants.TRAILING);
		this.lblCaseSensitive.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		this.lblCaseSensitive.setForeground(Color.BLUE);
		this.lblCaseSensitive.setBounds(327, 310, 147, 16);
		this.panel1.add(this.lblCaseSensitive);
	}
	
	//forgot password
	public void init2(){
		
		this.panel2 = new JPanel();
		panel2.hide();
		panel2.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
		this.panel2.setBounds(261, 83, 500, 550);
		add(this.panel2);
		this.panel2.setLayout(null);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(237, 40, 132, 33);
		this.panel2.add(this.lblPsGroup);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(237, 63, 141, 16);
		this.panel2.add(this.label);
		
		this.lblReset = new JLabel("Reset Password");
		this.lblReset.setVerticalAlignment(SwingConstants.TOP);
		this.lblReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblReset.setBounds(234, 78, 169, 34);
		this.panel2.add(this.lblReset);
		
		this.lblLogo = new JLabel("logo");
		this.lblLogo.setBounds(137, 29, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
		this.panel2.add(this.lblLogo);
		
		
		
		this.lblNewPassword = new JLabel("New Password:");
		this.lblNewPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.lblNewPassword.setBounds(21, 174, 169, 33);
		this.panel2.add(this.lblNewPassword);
		
		this.lblConfirmPassword = new JLabel("Confirm Password:");
		this.lblConfirmPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.lblConfirmPassword.setBounds(20, 290, 217, 33);
		this.panel2.add(this.lblConfirmPassword);
		
		this.pwdPassword = new JPasswordField();
		this.pwdPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.pwdPassword.setBounds(137, 218, 331, 44);
		this.panel2.add(this.pwdPassword);
		
		this.pwdConfirm = new JPasswordField();
		this.pwdConfirm.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		this.pwdConfirm.setBounds(137, 334, 331, 44);
		this.panel2.add(this.pwdConfirm);
		
		this.btnReset = new JButton("Reset");
		this.btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // Check if the password and confirm password fields do not match
				if (!pwdPassword.getText().equals(pwdConfirm.getText())){lblError_1.setText("Passwords does not match");return;}
			    // Display a confirmation dialog for resetting the password
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Reset?", "Confirm", 0, 3, null, options, options[0]);
				if (sel!=0){return;}	 // Exit if the user selects "No"
				String pass = new String(pwdPassword.getPassword());
				main.getCont().editUser(user.getUsername(), user.getName(), user.getEmail(), pass, user.getAddress());
				JOptionPane.showMessageDialog(null, "Password Resetted", "Reset", JOptionPane.INFORMATION_MESSAGE);
				
				
				main.showAllLogin();	    // Show all login information and update the error label to indicate success
				lblError_1.setText("Success!");
			}
		});
		this.btnReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnReset.setBounds(335, 460, 141, 51);
		this.panel2.add(this.btnReset);
		
		this.btnBack_1 = new JButton("Back");
		this.btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = null;
				showPanel1();
			}
		});
		this.btnBack_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack_1.setBounds(21, 464, 132, 47);
		this.panel2.add(this.btnBack_1);
		
		this.lblError_1 = new JLabel("");
		this.lblError_1.setHorizontalAlignment(SwingConstants.TRAILING);
		this.lblError_1.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		this.lblError_1.setForeground(Color.RED);
		this.lblError_1.setBounds(21, 143, 447, 33);
		this.panel2.add(this.lblError_1);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()){
					pwdPassword.setEchoChar((char)0);
					pwdConfirm.setEchoChar((char)0);
				}
				else{pwdPassword.setEchoChar((char)0x2022);pwdConfirm.setEchoChar((char)0x2022);}
			}
		});
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.chckbxShowPassword.setBounds(137, 385, 157, 25);
		this.panel2.add(this.chckbxShowPassword);
		
	}
	
	//forgot Username
	public void init3(){
		this.panel3 = new JPanel();
		panel3.hide();
		panel3.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
		this.panel3.setBounds(261, 83, 500, 550);
		add(this.panel3);
		this.panel3.setLayout(null);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(237, 40, 132, 33);
		this.panel3.add(this.lblPsGroup);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(237, 63, 141, 16);
		this.panel3.add(this.label);
		
		this.lblReset = new JLabel("Reset");
		this.lblReset.setVerticalAlignment(SwingConstants.TOP);
		this.lblReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblReset.setBounds(237, 85, 154, 34);
		this.panel3.add(this.lblReset);
		
		this.lblLogo = new JLabel("logo");
		this.lblLogo.setBounds(137, 29, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
		this.panel3.add(this.lblLogo);
		
		this.btnLogin = new JButton("Login");
		this.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = null;
				main.showAllLogin();
			}
		});
		this.btnLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnLogin.setBounds(194, 454, 141, 49);
		this.panel3.add(this.btnLogin);
		
		this.lblYourUsernameIs = new JLabel("Your Username is: ");
		this.lblYourUsernameIs.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		this.lblYourUsernameIs.setBounds(54, 182, 385, 49);
		this.panel3.add(this.lblYourUsernameIs);
		
		this.lblUsername = new JLabel("username");
		this.lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 45));
		this.lblUsername.setBounds(54, 242, 385, 128);
		this.panel3.add(this.lblUsername);
		
		
		
		
		
		
		
	}
	
	public void showPanel1(){
		panel1.show();
		panel2.hide();
		panel3.hide();
	}
	public void showPanel2(){
		panel1.hide();
		panel2.show();
		panel3.hide();
	}
	public void showPanel3(){
		panel1.hide();
		panel2.hide();
		panel3.show();
	}
}
