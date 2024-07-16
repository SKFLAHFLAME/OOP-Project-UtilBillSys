package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.Customer;
import data.Readings;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAllCustomer extends JPanel{
	MainFrame main;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode customer = new DefaultMutableTreeNode("Customers");
	
	private JScrollPane scrollPane;
	private JLabel lblCustomerDetails;
	private JTree tree;
	private JButton btnBack;
	
	public ViewAllCustomer(MainFrame m) {
		main=m;
		this.setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 59, 366, 288);
		add(this.scrollPane);
		
		model = new DefaultTreeModel(customer);
		this.tree = new JTree(model);
		this.scrollPane.setViewportView(this.tree);
		populateTree();
		
		this.lblCustomerDetails = new JLabel("Customer Details");
		this.lblCustomerDetails.setFont(new Font("Dialog", Font.BOLD, 20));
		this.lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCustomerDetails.setBounds(12, 12, 366, 40);
		add(this.lblCustomerDetails);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (main.getCurrentAcct()[0].equals("A")) {
					main.showAdminMenu();
				}
				else {main.showStaffMenu();}
			}
		});
		this.btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
		this.btnBack.setBounds(135, 423, 117, 25);
		add(this.btnBack);
		main.setSize(400,500);
		
	}
	
	public void populateTree() {
		Customer[] accts = main.getCont().getAllCustomers();
		customer.removeAllChildren();
		for (Customer a : accts) {
			DefaultMutableTreeNode username = new DefaultMutableTreeNode(a.getUsername());
			customer.add(username);
			
			username.add(new DefaultMutableTreeNode("Full Name: "+a.getName()));
			username.add(new DefaultMutableTreeNode("Email: "+a.getEmail()));
			username.add(new DefaultMutableTreeNode("Address: "+a.getAddress()));
			
			DefaultMutableTreeNode userR = new DefaultMutableTreeNode("View All Bills");
			String[][][] userReadings = main.getCont().getUserReading(a.getUsername());//get all userReadings of user
			for (String[][] ur: userReadings){
				if(ur==null){continue;}
				DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill"+ur[0][1]+":"+ur[0][2]);
				for (int i =0; i<ur.length-1; i++){
					bill.add(new DefaultMutableTreeNode(ur[i+1][0]+": "+ ur[i+1][1]));
				}
				userR.add(bill);
			}
			
			username.add(userR);
			
		}
		
		
		model.reload();
		tree.setModel(model);
	}
}
