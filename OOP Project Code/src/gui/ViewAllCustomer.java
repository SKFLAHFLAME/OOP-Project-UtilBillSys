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

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JToggleButton;

public class ViewAllCustomer extends JPanel{
	MainFrame main;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode customer = new DefaultMutableTreeNode("Customers");
	
	private JScrollPane scrollPane;
	private JLabel lblCustomerDetails;
	private JTree tree;
	private JTextField txtSearch;
	private JLabel lblSearch;
	private JLabel lblcaseSensetive;
	private JButton btnEditUser;
	private JTextArea txtrUnits;
	
	public ViewAllCustomer(MainFrame m) {
		main=m;
		this.setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 83, 650, 560);
		add(this.scrollPane);
		
		model = new DefaultTreeModel(customer);
		this.tree = new JTree(model);
		tree.setRowHeight(30);
		this.tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if (arg0.getPath().getPathCount()==2){
					btnEditUser.show();
				}
				else{btnEditUser.hide();}
			}
		});
		this.tree.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.scrollPane.setViewportView(this.tree);
		
		this.lblCustomerDetails = new JLabel("Customer Details");
		this.scrollPane.setColumnHeaderView(this.lblCustomerDetails);
		this.lblCustomerDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);
		populateTree();
		
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

		this.txtSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		this.txtSearch.setBounds(674, 285, 291, 44);
		add(this.txtSearch);
		this.txtSearch.setColumns(10);
		
		this.lblSearch = new JLabel("Search User:");
		this.lblSearch.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblSearch.setBounds(674, 238, 166, 34);
		add(this.lblSearch);
		
		this.lblcaseSensetive = new JLabel("*Case Sensetive");
		this.lblcaseSensetive.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		this.lblcaseSensetive.setForeground(Color.BLUE);
		this.lblcaseSensetive.setBounds(836, 247, 139, 25);
		add(this.lblcaseSensetive);
		
		this.btnEditUser = new JButton("Edit User");
		this.btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnEditUser.hide();
		this.btnEditUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnEditUser.setBounds(674, 342, 166, 54);
		add(this.btnEditUser);
		main.setSize(1020,720);
		
		TaskBar bar = new TaskBar(this, main);
		
		this.txtrUnits = new JTextArea();
		txtrUnits.setOpaque(false);
		txtrUnits.setEditable(false);
		this.txtrUnits.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtrUnits.setText("Units");
		this.setUnits();
		this.txtrUnits.setBounds(674, 409, 291, 235);
		add(this.txtrUnits);
		
	}
	
	public void setUnits(){
		Readings[] readings = main.getCont().getAllReadings();
		HashMap<String, String> organisedUnits = new HashMap<>();
		String text = "";
		for (Readings r:readings){
			organisedUnits.put(r.getUtilityName(), r.getUnit());
		}
//		int gap = 9-organisedUnits.size();
//		for (int i=0; i<gap; i++){text+="\n";}
		text += "Reading Units: \n";
		for (Map.Entry<String, String> m : organisedUnits.entrySet()){
			text += m.getKey()+" : " + m.getValue()+'\n';
		}
		txtrUnits.setText(text);
	}
	
	public void populateTree() {
		Customer[] accts = main.getCont().getAllCustomers();
		customer.removeAllChildren();
		double allTotal=0;
		for (Customer a : accts) {
			DefaultMutableTreeNode username = new DefaultMutableTreeNode(a.getUsername());
			customer.add(username);
			
			username.add(new DefaultMutableTreeNode("Full Name: "+a.getName()));
			username.add(new DefaultMutableTreeNode("Email: "+a.getEmail()));
			username.add(new DefaultMutableTreeNode("Address: "+a.getAddress()));
			
			DefaultMutableTreeNode userR = new DefaultMutableTreeNode("View All Bills");
			String[][][] userReadings = main.getCont().getUserReading(a.getUsername());//get all userReadings of user
			double utotal = 0;
			for (String[][] ur: userReadings){//go thru each bill
				double total=0;
				if(ur==null){continue;}
				if (ur[0][0]==null){continue;}
				DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill "+ur[0][1]+":"+ur[0][2]);
				for (int i =0; i<ur.length-1; i++){// go thru each reading
					if (ur[i][0]==null){continue;}
					bill.add(new DefaultMutableTreeNode(ur[i+1][0]+" used: "+ ur[i+1][1]));
					total+=Double.valueOf(ur[i+1][2]);
					allTotal+=Double.valueOf(ur[i+1][2]);
				}
				String t = String.format("%.2f", total);
				utotal+= total;
				bill.add(new DefaultMutableTreeNode("Total: $"+t));//total of bill
				userR.add(bill);
				
			}
			userR.add(new DefaultMutableTreeNode("User Total: $"+String.format("%.2f", utotal)));
			username.add(userR);
		}
		String t=String.format("%.2f", allTotal);
		customer.add(new DefaultMutableTreeNode("Total Customers Bills: $"+t));
		
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
			double utotal = 0;
			for (String[][] ur: userReadings){//go thru each bill
				double total=0;
				if(ur==null){continue;}
				if (ur[0][0]==null){continue;}
				DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill "+ur[0][1]+":"+ur[0][2]);
				for (int i =0; i<ur.length-1; i++){// go thru each reading
					if (ur[i][0]==null){continue;}
					bill.add(new DefaultMutableTreeNode(ur[i+1][0]+" used: "+ ur[i+1][1]));
					total+=Double.valueOf(ur[i+1][2]);
				}
				String t = String.format("%.2f", total);
				utotal+= total;
				bill.add(new DefaultMutableTreeNode("Total: $"+t));//total of bill
				userR.add(bill);
			}
			userR.add(new DefaultMutableTreeNode("User Total: $"+String.format("%.2f", utotal)));
			username.add(userR);
		}
		model.reload();
		tree.setModel(model);
	}
}
