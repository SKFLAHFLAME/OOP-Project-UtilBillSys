package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class SAccountPage extends JPanel{
	MainFrame main;
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField textField;
	private JLabel lblAccountPage;
	private JPasswordField passwordField;
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
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnBack.setBounds(12, 252, 131, 40);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
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
}
