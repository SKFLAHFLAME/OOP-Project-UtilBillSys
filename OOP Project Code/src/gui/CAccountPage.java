package gui;

import data.Customer;
import data.Staff;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Class representing the account management page of the application
public class CAccountPage extends JPanel{
	PopupDialog window;
	String userName;
	Customer acct;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField txtUName;
	private JLabel lblAccountPage;
	private JPasswordField passwordField;
	private boolean editing=false;
	private JCheckBox chckbxShowPassword;
	private JLabel lblFullName;
	private JTextField txtFullname;
	private JLabel lblEmail;
	private JLabel lblPostalCode;
	private JLabel lblUnitNo;
	private JTextField txtPostal;
	private JTextField txtUnitno;
	private JLabel label;
	private JLabel lblAddress;
	private JTextField txtEmail;
	private JLabel lblErrors;
	private JLabel lblLogo;
	private JLabel lblPsGroup;
	
    // Constructor initializes the UI components and sets up the layout
	public CAccountPage(PopupDialog popupDialog, String userName){
		this.window=popupDialog;
		this.userName = userName;
		this.setLayout(null);
		window.setSize(500,400);


		
		this.lblId = new JLabel("Username: ");
		this.lblId.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblId.setBounds(29, 82, 139, 40);
		add(this.lblId);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblPassword.setBounds(29, 347, 112, 40);
		add(this.lblPassword);
		
		this.btnBack = new JButton("Close");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editing == true){showNormalScreen();showAccount();return;}
				window.dispose();
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(29, 452, 131, 40);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(passwordField.getPassword());
				String email = txtEmail.getText();
				String addr = String.join(":", txtPostal.getText(), txtUnitno.getText());
				String fullName = txtFullname.getText();
                if(editing==true) {//edit to not edit
                	if (fullName.isEmpty() ||email.isEmpty() ||txtPostal.getText().isEmpty()||txtUnitno.getText().isEmpty() ||pass.isEmpty()) {
            			lblErrors.setText("Fields are Blank"); return;
            		}
            		if(email.contains(" ")||pass.contains(" ")){
            			lblErrors.setText("Fields contains Spaces");
            			return;
            		}
            		if (!email.contains("@")||!email.contains(".")){
            			lblErrors.setText("Please Enter Proper Email");
            			return;
            		}
                	if (!(pass.equals(acct.getPassword())&&email.equals(acct.getEmail())&&fullName.equals(acct.getName())&&addr.equals(acct.getAddress()))){
                	String[] options = {"Yes", "No"};
    				int sel = JOptionPane.showOptionDialog(null, "Confirm Changes?", "Confirmation", 0, 3, null, options, options[0]);
    				if (sel != 0){return;}
    				else{saveChanges(acct.getUsername(), pass, fullName, email, addr);}}
					showNormalScreen();	
                } else {//not edit to edit
					showEditScreen();
                }
            }
		});
		this.btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnEdit.setBounds(422, 452, 131, 40);
		add(this.btnEdit);
		
		this.txtUName = new JTextField();
		this.txtUName.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		txtUName.setEditable(false);
		this.txtUName.setBounds(178, 83, 375, 40);
		add(this.txtUName);
		this.txtUName.setColumns(10);
		
		this.lblAccountPage = new JLabel("Account Page");
		this.lblAccountPage.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		this.lblAccountPage.setBounds(104, 31, 196, 40);
		add(this.lblAccountPage);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		passwordField.setEditable(false);
		this.passwordField.setBounds(178, 348, 375, 40);
		add(this.passwordField);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxShowPassword.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else{passwordField.setEchoChar((char)0x2022);}
			}
		});
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		this.chckbxShowPassword.setBounds(178, 395, 173, 28);
		add(this.chckbxShowPassword);
		
		this.lblFullName = new JLabel("Name:");
		this.lblFullName.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblFullName.setBounds(29, 150, 139, 28);
		add(this.lblFullName);
		
		this.txtFullname = new JTextField();
		this.txtFullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtFullname.setEditable(false);
		this.txtFullname.setBounds(178, 145, 375, 40);
		add(this.txtFullname);
		this.txtFullname.setColumns(10);
		
		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblEmail.setBounds(29, 208, 139, 40);
		add(this.lblEmail);
		
		this.lblPostalCode = new JLabel("Postal Code");
		this.lblPostalCode.setVerticalAlignment(SwingConstants.TOP);
		this.lblPostalCode.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		this.lblPostalCode.setBounds(178, 313, 84, 24);
		add(this.lblPostalCode);
		
		this.lblUnitNo = new JLabel("Unit No");
		this.lblUnitNo.setVerticalAlignment(SwingConstants.TOP);
		this.lblUnitNo.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		this.lblUnitNo.setBounds(385, 318, 100, 19);
		add(this.lblUnitNo);
		
		this.txtPostal = new JTextField();
		this.txtPostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if ((Character.isDigit(arg0.getKeyChar()))){return;}
				arg0.consume();
			}
		});
		this.txtPostal.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtPostal.setEditable(false);
		this.txtPostal.setBounds(178, 274, 139, 40);
		add(this.txtPostal);
		this.txtPostal.setColumns(10);
		
		this.txtUnitno = new JTextField();
		this.txtUnitno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (Character.isDigit(key) || (key=='-'&&!(txtUnitno.getText().contains("-")))){return;}
				e.consume();
			}
		});
		this.txtUnitno.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtUnitno.setEditable(false);
		this.txtUnitno.setBounds(385, 274, 168, 40);
		add(this.txtUnitno);
		this.txtUnitno.setColumns(10);
		
		this.label = new JLabel("#");
		this.label.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.label.setBounds(357, 280, 27, 28);
		add(this.label);
		
		this.lblAddress = new JLabel("Address:");
		this.lblAddress.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblAddress.setBounds(29, 273, 112, 40);
		add(this.lblAddress);
		
		this.txtEmail = new JTextField();
		this.txtEmail.setEditable(false);
		this.txtEmail.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtEmail.setBounds(178, 209, 375, 40);
		add(this.txtEmail);
		this.txtEmail.setColumns(10);
		
		this.lblErrors = new JLabel("");
		this.lblErrors.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
		this.lblErrors.setForeground(Color.RED);
		this.lblErrors.setBounds(330, 32, 223, 40);
		add(this.lblErrors);
		
		this.lblLogo = new JLabel("");
		this.lblLogo.setBounds(29, 11, 65, 60);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
        lblLogo.setIcon(logo);
		add(this.lblLogo);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		this.lblPsGroup.setBounds(104, 11, 112, 28);
		add(this.lblPsGroup);
		this.showAccount();
		this.showNormalScreen();
		
		window.setSize((txtUName.getLocation().x+txtUName.getSize().width+40),(btnEdit.getLocation().y+btnEdit.getSize().height+60));
		

	}
	

	// Switches the panel to editing mode, allowing the user to modify account details
	public void showEditScreen(){
	    // Set the editing flag to true
	    editing = true;
	    
	    // Change button labels to indicate the current mode
	    btnEdit.setText("Finish");
	    btnBack.setText("Cancel");
	    
	    // Enable text fields for editing
	    txtFullname.setEditable(true);
	    txtEmail.setEditable(true);
	    txtPostal.setEditable(true);
	    txtUnitno.setEditable(true);
	    passwordField.setEditable(true);
	    
	    // Show the password in plain text
	    passwordField.setEchoChar((char)0);
	    
	    // Hide the checkbox for showing/hiding password
	    chckbxShowPassword.hide();
	}

	// Switches the panel back to normal view mode, preventing changes to account details
	public void showNormalScreen(){
	    // Set the editing flag to false
	    editing = false;
	    
	    // Change button labels to indicate the current mode
	    btnEdit.setText("Edit");
	    btnBack.setText("Close");
	    
	    // Disable text fields to prevent editing
	    txtUName.setEditable(false);
	    txtFullname.setEditable(false);
	    txtEmail.setEditable(false);
	    txtPostal.setEditable(false);
	    txtUnitno.setEditable(false);
	    
	    // Disable password field editing and hide plain text
	    passwordField.setEditable(false);
	    chckbxShowPassword.show();
	    chckbxShowPassword.setSelected(false);
	    passwordField.setEchoChar((char)0x2022);
	    
	    // Refresh the displayed account details
	    showAccount();
	}

	// Displays the current account details in the text fields and password field
	public void showAccount(){
	    // Retrieve the Customer object for the given username
	    acct = window.main.getCont().getCustomer(userName);
	    
	    // Set the text fields with the account details
	    txtUName.setText(acct.getUsername());
	    txtFullname.setText(acct.getName());
	    txtEmail.setText(acct.getEmail());
	    
	    // Split the address into postal code and unit number
	    String[] addr = acct.getAddress().split(":");
	    txtPostal.setText(addr[0]);
	    
	    // Handle cases where the address might not contain a unit number
	    try {
	        txtUnitno.setText(addr[1]);
	    } catch (Exception e) {
	        txtUnitno.setText("");
	    }
	    
	    // Set the password field with the current password
	    passwordField.setText(acct.getPassword());
	}

	// Saves the updated account details
	public void saveChanges(String userName, String password, String newFName, String newEmail, String newAddress){
	    // Call the controller to update the user details
	    window.main.getCont().editUser(userName, newFName, newEmail, password, newAddress);
	    
	    // Update the local username and refresh the account details display
	    this.userName = userName;
	    showAccount();
	}
}