package gui;

import controller.MainFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SAccountPage extends JPanel{
	MainFrame main;
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField textField;
	private JLabel lblAccountPage;
	private JPasswordField passwordField;
	private boolean editing=false;
	public SAccountPage(MainFrame m){
		this.main=m;
		this.setLayout(null);
		main.setSize(500,500);


		
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
				if (main.getCurrentAcct()[0].equals("S")) {
					main.showStaffMenu();
				}else if (main.getCurrentAcct()[0].equals("A")) {main.showAdminMenu();}
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnBack.setBounds(12, 252, 131, 40);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String pass;
                if(editing==true) {//edit to not edit
					editing = false;

					showNormalScreen();
					
					btnEdit.setText("Edit");					
                } else {//not edit to edit
					editing = true;

					showEditScreen();


					btnEdit.setText("Finish");
                }
            }
		});
		this.btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnEdit.setBounds(348, 252, 131, 40);
		add(this.btnEdit);
		
		this.textField = new JTextField();
		this.textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.textField.setBounds(131, 69, 348, 40);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.lblAccountPage = new JLabel("Account Page");
		this.lblAccountPage.setFont(new Font("Tahoma", Font.PLAIN, 23));
		this.lblAccountPage.setBounds(12, 13, 280, 40);
		add(this.lblAccountPage);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.passwordField.setBounds(131, 139, 348, 40);
		add(this.passwordField);

	}

	public void showEditScreen(){

	}

	public void showNormalScreen(){

	}

}
