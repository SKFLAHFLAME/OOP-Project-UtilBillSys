package gui;

import data.Staff;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class SAccountPage extends JPanel {
	PopupDialog window;  // Reference to the PopupDialog window
	String id;  // Staff ID
	String[] acct;  // Array to store account details
	
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField textField;
	private JLabel lblAccountPage;
	private JPasswordField passwordField;
	private boolean editing=false;
	private JCheckBox chckbxShowPassword;
	
	public SAccountPage(PopupDialog popupDialog, String id){
		this.window=popupDialog;
		this.id = id;
		this.setLayout(null);
		window.setSize(500,400);


		
		this.lblId = new JLabel("Staff ID:");
		this.lblId.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblId.setBounds(29, 69, 114, 28);
		add(this.lblId);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.lblPassword.setBounds(29, 139, 120, 40);
		add(this.lblPassword);
		
		this.btnBack = new JButton("Close");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editing == true) {
					showNormalScreen();  // If in editing mode, revert to normal screen
					showAccount();  // Refresh account details
					return;
				}
				window.dispose();  // Close the window
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(12, 252, 131, 40);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();  // Get the ID from the text field
				String pass = new String(passwordField.getPassword());  // Get the password from the password field
                if (editing == true) { // If already editing
                	if (!(id.equals(acct[0]) && pass.equals(acct[1]))) {
                	String[] options = {"Yes", "No"};
    				int sel = JOptionPane.showOptionDialog(null, "Confirm Changes?", "Confirmation", 0, 3, null, options, options[0]);
    				if (sel == 1) { return; }  // If user selects "No", return
    				else { saveChanges(id, pass); }  // Save changes if "Yes" is selected
					}
					showNormalScreen();  // Revert to normal screen after editing
                } else {  // If not in editing mode
					showEditScreen();  // Switch to editing mode
                }
            }
		});
		this.btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnEdit.setBounds(348, 252, 131, 40);
		add(this.btnEdit);
		
		this.textField = new JTextField();
		this.textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		textField.setEditable(false);
		this.textField.setBounds(159, 69, 320, 40);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.lblAccountPage = new JLabel("Account Page");
		this.lblAccountPage.setFont(new Font("Tahoma", Font.PLAIN, 23));
		this.lblAccountPage.setBounds(12, 13, 280, 40);
		add(this.lblAccountPage);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		passwordField.setEditable(false);
		this.passwordField.setBounds(159, 139, 320, 40);
		add(this.passwordField);
		
		this.chckbxShowPassword = new JCheckBox("Show Password");
		this.chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);  // Show password
				} else {
					passwordField.setEchoChar((char) 0x2022);  // Hide password
				}
			}
		});
		this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		this.chckbxShowPassword.setBounds(159, 189, 173, 28);
		add(this.chckbxShowPassword);
		this.showAccount();
		this.showNormalScreen();
		
		window.setSize((textField.getLocation().x+textField.getSize().width+40),(btnEdit.getLocation().y+btnEdit.getSize().height+60));
		

	}
	
	// Method to switch to the editing screen
	public void showEditScreen(){
		editing=true;
		btnEdit.setText("Finish");
		btnBack.setText("Cancel");
		if (window.main.getCurrentAcct()[0].equals("A")){
			textField.setEditable(true);}
		passwordField.setEditable(true);
		passwordField.setEchoChar((char)0);
		chckbxShowPassword.hide();
		
	}

	// Method to switch back to the normal screen
	public void showNormalScreen(){
		editing = false;
		btnEdit.setText("Edit");
		btnBack.setText("Close");
		textField.setEditable(false);
		passwordField.setEditable(false);
		chckbxShowPassword.show();
		chckbxShowPassword.setSelected(false);
		passwordField.setEchoChar((char)0x2022);
		if (this.id.equals("admin")){
			btnEdit.hide();
			btnBack.setLocation((window.getWidth()/2)-(btnBack.getSize().width/2), 252);
		}
		
	}
	
	// Method to display the account details on the screen
	public void showAccount(){
		acct = window.main.getCont().getStaff(id);
		textField.setText(acct[0]);
		passwordField.setText(acct[1]);
	}
	
	// Method to save changes to the account details
	public void saveChanges(String id, String password){
		window.main.getCont().editStaff(acct[0], id, password);
		this.id = id;
		showAccount();
	}
	
}
