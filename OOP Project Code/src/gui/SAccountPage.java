package gui;

import controller.MainFrame;
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

public class SAccountPage extends JPanel{
	MainFrame main;
	String id;
	String[] acct;
	
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField textField;
	private JLabel lblAccountPage;
	private JPasswordField passwordField;
	private boolean editing=false;
	private JCheckBox chckbxShowPassword;
	
	public SAccountPage(MainFrame m, String id){
		this.main=m;
		this.id = id;
		this.setLayout(null);
		main.setSize(500,400);


		
		this.lblId = new JLabel("ID:");
		this.lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblId.setBounds(29, 69, 90, 28);
		add(this.lblId);
		
		this.lblPassword = new JLabel("Password:");
		this.lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblPassword.setBounds(29, 139, 97, 40);
		add(this.lblPassword);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editing == true){showNormalScreen();showAccount();return;}
				if (main.getCurrentAcct()[0].equals("S")) {
					main.showStaffMenu();
				}else if (main.getCurrentAcct()[0].equals("A")) {
					if (main.getCurrentAcct()[1].equals(id)){
						main.showAdminMenu();}
					else {main.showAllStaff();}
				}
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnBack.setBounds(12, 252, 131, 40);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String pass = new String(passwordField.getPassword());
                if(editing==true) {//edit to not edit
                	if (!(id.equals(acct[0])&&pass.equals(acct[1]))){
                	String[] options = {"Yes", "No"};
    				int sel = JOptionPane.showOptionDialog(null, "Confirm Changes?", "Confirmation", 0, 3, null, options, options[0]);
    				if (sel == 1){return;}
    				else{saveChanges(id, pass);}}
					showNormalScreen();	
                } else {//not edit to edit
					showEditScreen();
                }
            }
		});
		this.btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnEdit.setBounds(348, 252, 131, 40);
		add(this.btnEdit);
		
		this.textField = new JTextField();
		this.textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setEditable(false);
		this.textField.setBounds(131, 69, 348, 40);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.lblAccountPage = new JLabel("Account Page");
		this.lblAccountPage.setFont(new Font("Tahoma", Font.PLAIN, 23));
		this.lblAccountPage.setBounds(12, 13, 280, 40);
		add(this.lblAccountPage);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setEditable(false);
		this.passwordField.setBounds(131, 139, 348, 40);
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
		this.chckbxShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.chckbxShowPassword.setBounds(131, 186, 124, 28);
		add(this.chckbxShowPassword);
		this.showAccount();
		this.showNormalScreen();
		
		main.setSize((textField.getLocation().x+textField.getSize().width+40),(btnEdit.getLocation().y+btnEdit.getSize().height+60));
		

	}
	

	public void showEditScreen(){
		editing=true;
		btnEdit.setText("Finish");
		btnBack.setText("Cancel");
		if (main.getCurrentAcct()[0].equals("A")){
			textField.setEditable(true);}
		passwordField.setEditable(true);
		passwordField.setEchoChar((char)0);
		chckbxShowPassword.hide();
		
	}

	public void showNormalScreen(){
		editing = false;
		btnEdit.setText("Edit");
		btnBack.setText("Back");
		textField.setEditable(false);
		passwordField.setEditable(false);
		chckbxShowPassword.show();
		chckbxShowPassword.setSelected(false);
		passwordField.setEchoChar((char)0x2022);
		if (this.id.equals("admin")){
			btnEdit.hide();
			btnBack.setLocation((main.getWidth()/2)-(btnBack.getSize().width/2), 252);
		}
		
	}
	
	public void showAccount(){
		acct = main.getCont().getStaff(id);
		textField.setText(acct[0]);
		passwordField.setText(acct[1]);
	}
	public void saveChanges(String id, String password){
		main.getCont().editStaff(acct[0], id, password);
		this.id = id;
		showAccount();
	}
	
}
