package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSysDate extends JPanel{
	MainFrame main;
	private JButton btnBack;
	private JButton btnConfirm;
	
	public EditSysDate(MainFrame m){
		this.main = m;
		this.setLayout(null);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!main.getCurrentAcct()[0].equals("A")){main.showLogin();return;}
				main.showAdminMenu();
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnBack.setBounds(44, 205, 115, 40);
		add(this.btnBack);
		
		this.btnConfirm = new JButton("Confirm");
		this.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!main.getCurrentAcct()[0].equals("A")){return;}
			}
		});
		this.btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnConfirm.setBounds(260, 205, 115, 40);
		add(this.btnConfirm);
		
	}
}
