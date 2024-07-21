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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;

public class ViewAllCustomer extends JPanel{
	MainFrame main;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode customer = new DefaultMutableTreeNode("Customers");
	
	private JScrollPane scrollPane;
	private JLabel lblCustomerDetails;
	private JTree tree;
	private JButton btnBack;
	private JTextField txtSearch;
	private JLabel lblSearch;
	private JLabel lblcaseSensetive;
	
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
		
		this.txtSearch = new JTextField();
		this.txtSearch.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String text = txtSearch.getText();
				searchTree(text);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtSearch.getText();
				searchTree(text);
			}
		});

		this.txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.txtSearch.setBounds(87, 358, 291, 31);
		add(this.txtSearch);
		this.txtSearch.setColumns(10);
		
		this.lblSearch = new JLabel("Search:");
		this.lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.lblSearch.setBounds(22, 358, 62, 31);
		add(this.lblSearch);
		
		this.lblcaseSensetive = new JLabel("*Case Sensetive");
		this.lblcaseSensetive.setForeground(Color.BLUE);
		this.lblcaseSensetive.setBounds(87, 388, 132, 25);
		add(this.lblcaseSensetive);
		main.setSize(400,500);
		
	}
	
	public void populateTree() {
		Customer[] accts = main.getCont().getAllCustomers();
		customer.removeAllChildren();
		int allTotal=0;
		for (Customer a : accts) {
			DefaultMutableTreeNode username = new DefaultMutableTreeNode(a.getUsername());
			customer.add(username);
			
			username.add(new DefaultMutableTreeNode("Full Name: "+a.getName()));
			username.add(new DefaultMutableTreeNode("Email: "+a.getEmail()));
			username.add(new DefaultMutableTreeNode("Address: "+a.getAddress()));
			
			DefaultMutableTreeNode userR = new DefaultMutableTreeNode("View All Bills");
			String[][][] userReadings = main.getCont().getUserReading(a.getUsername());//get all userReadings of user
			for (String[][] ur: userReadings){//go thru each bill
				int total=0;
				if(ur==null){continue;}
				if (ur[0][0]==null){continue;}
				DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill "+ur[0][1]+":"+ur[0][2]);
				for (int i =0; i<ur.length-1; i++){// go thru each reading
					if (ur[i][0]==null){continue;}
					bill.add(new DefaultMutableTreeNode(ur[i+1][0]+": "+ ur[i+1][1]));
					total+=Integer.valueOf(ur[i+1][2]);
					allTotal+=Integer.valueOf(ur[i+1][2]);
				}
				bill.add(new DefaultMutableTreeNode("Total: $"+total));//total of bill
				userR.add(bill);
				
			}
			username.add(userR);
		}
		customer.add(new DefaultMutableTreeNode("Total Customers Bills: $"+allTotal));
		
		model.reload();
		tree.setModel(model);
	}

	public void searchTree(String search){
		if (search.equals("")){populateTree();return;}
		Customer[] accts = main.getCont().getAllCustomers();
		Vector<Customer> filtered = new Vector<>();
		// Filter accts
		for (Customer a : accts){
			if (a.getUsername().startsWith(search)){
				filtered.add(a);
			}
		}

		customer.removeAllChildren();
		for (Customer a : filtered) {
			DefaultMutableTreeNode username = new DefaultMutableTreeNode(a.getUsername());
			customer.add(username);
			
			username.add(new DefaultMutableTreeNode("Full Name: "+a.getName()));
			username.add(new DefaultMutableTreeNode("Email: "+a.getEmail()));
			username.add(new DefaultMutableTreeNode("Address: "+a.getAddress()));
			
			DefaultMutableTreeNode userR = new DefaultMutableTreeNode("View All Bills");
			String[][][] userReadings = main.getCont().getUserReading(a.getUsername());//get all userReadings of user
			for (String[][] ur: userReadings){//go thru each bill
				int total=0;
				if(ur==null){continue;}
				if (ur[0][0]==null){continue;}
				DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill "+ur[0][1]+":"+ur[0][2]);
				for (int i =0; i<ur.length-1; i++){// go thru each reading
					if (ur[i][0]==null){continue;}
					bill.add(new DefaultMutableTreeNode(ur[i+1][0]+": "+ ur[i+1][1]));
					total+=Integer.valueOf(ur[i+1][2]);
				}
				bill.add(new DefaultMutableTreeNode("Total: "+total));//total of bill
				userR.add(bill);
			}
			
			username.add(userR);
		}
		model.reload();
		tree.setModel(model);
	}
}
