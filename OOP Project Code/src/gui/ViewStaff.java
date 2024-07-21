package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.Staff;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewStaff extends JPanel{
	MainFrame main;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnBack;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnAdd;
	
	public ViewStaff(MainFrame m) {
		this.main=m;
		this.setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 68, 366, 256);
		add(this.scrollPane);
		
		this.list = new JList();
		this.list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.scrollPane.setViewportView(this.list);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showAdminMenu();
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.btnBack.setBounds(149, 380, 104, 34);
		add(this.btnBack);
		
		this.btnEdit = new JButton("Edit");
		this.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex()==-1){return;}
				String ID = (String) list.getSelectedValue();
				main.showStaffAccount(ID);
			}
		});
		this.btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.btnEdit.setBounds(274, 335, 104, 34);
		add(this.btnEdit);
		
		this.btnRemove = new JButton("Remove");
		this.btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Delete?", "Delete", 0, 3, null, options, options[0]);
				if (sel == 1){return;}
				if (list.getSelectedIndex()==-1){return;}
				String selectedID = (String) list.getSelectedValue();
				main.getCont().removeStaff(selectedID);
				populateList();
			}
		});
		this.btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.btnRemove.setBounds(12, 335, 104, 34);
		add(this.btnRemove);
		
		this.btnAdd = new JButton("Add");
		this.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showSSignUp();
			}
		});
		this.btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.btnAdd.setBounds(149, 335, 104, 34);
		add(this.btnAdd);
		main.setSize(400, 500);
		populateList();
	}
	
	public void populateList(){
		DefaultListModel<String> model = new DefaultListModel<>();
		Staff[] allStaff = main.getCont().getAllStaff();
		for(Staff s: allStaff){
			if(s.getUsername().equals("admin")){continue;}
			model.addElement(s.getUsername());
		}
		list.setModel(model);
		
	}
}
