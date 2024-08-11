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
import java.awt.Image;

import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.swing.ImageIcon;
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
	private JToggleButton tglbtnSearchMethod;
	private JLabel lblPostalCode;
	private JLabel lblUnitNo;
	private JButton btnSearch;
	private JTextField txtPostal;
	private JTextField txtxUnitNo;
	private JLabel label;
	private JButton btnExport;
	
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
				if (arg0.getPath().getPathCount()==4){
					btnExport.show();
				}
				else{btnExport.hide();}
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
		this.txtSearch.setBounds(674, 277, 293, 44);
		add(this.txtSearch);
		this.txtSearch.setColumns(10);
		
		this.lblSearch = new JLabel("Search User:");
		this.lblSearch.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblSearch.setBounds(674, 230, 166, 34);
		add(this.lblSearch);
		
		this.lblcaseSensetive = new JLabel("*Case Sensetive");
		this.lblcaseSensetive.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		this.lblcaseSensetive.setForeground(Color.BLUE);
		this.lblcaseSensetive.setBounds(841, 239, 139, 25);
		add(this.lblcaseSensetive);
		
		
		this.btnEditUser = new JButton("Edit User");
		this.btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tree.getSelectionPath().getLastPathComponent().toString();
				if (tglbtnSearchMethod.isSelected()){
					main.showEditDraft(user);
				}
				else {main.showPopup("CAccount", user);}
			}
		});
		btnEditUser.hide();
		this.btnEditUser.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnEditUser.setBounds(672, 342, 295, 56);
		add(this.btnEditUser);
		main.setSize(1020,720);
		
		
		this.txtrUnits = new JTextArea();
		txtrUnits.setOpaque(false);
		txtrUnits.setEditable(false);
		this.txtrUnits.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtrUnits.setText("Units");
		this.setUnits();
		this.txtrUnits.setBounds(674, 409, 291, 235);
		add(this.txtrUnits);
		
		main.addTaskBar(this);
		
		this.tglbtnSearchMethod = new JToggleButton("Search Address");
		this.tglbtnSearchMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnSearchMethod.isSelected()){
					showAddressSearch();
					tglbtnSearchMethod.setText("Search Username");
					return;
				}
				else {
					showUserSearch();
					tglbtnSearchMethod.setText("Search Address");
				}
			}
		});
		this.tglbtnSearchMethod.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.tglbtnSearchMethod.setBounds(674, 154, 293, 54);
		add(this.tglbtnSearchMethod);
		
		this.lblPostalCode = new JLabel("Postal:");
		this.lblPostalCode.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblPostalCode.setBounds(684, 219, 79, 25);
		add(this.lblPostalCode);
		
		this.lblUnitNo = new JLabel("Unit No:");
		this.lblUnitNo.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblUnitNo.setBounds(684, 257, 80, 25);
		add(this.lblUnitNo);
		
		this.btnSearch = new JButton("Search");
		this.btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String postal = txtPostal.getText();
				String unitNo = txtxUnitNo.getText();
				searchTree(postal, unitNo);
			}
		});
		this.btnSearch.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.btnSearch.setBounds(674, 302, 293, 34);
		add(this.btnSearch);
		
		this.txtPostal = new JTextField();
		this.txtPostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (Character.isDigit(arg0.getKeyChar())){return;}
				arg0.consume();
			}
		});
		this.txtPostal.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtPostal.setBounds(776, 219, 191, 34);
		add(this.txtPostal);
		this.txtPostal.setColumns(10);
		
		this.txtxUnitNo = new JTextField();
		this.txtxUnitNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())|| (e.getKeyChar()=='-'&&!(txtxUnitNo.getText().contains("-")))){return;}
				e.consume();
			}
		});
		this.txtxUnitNo.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtxUnitNo.setBounds(799, 255, 168, 34);
		add(this.txtxUnitNo);
		this.txtxUnitNo.setColumns(10);
		
		this.label = new JLabel("#");
		this.label.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.label.setBounds(776, 260, 19, 25);
		add(this.label);
		
		this.btnExport = new JButton("");
		btnExport.hide();
		this.btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tree.getSelectionPath().getParentPath().getParentPath().getLastPathComponent().toString();
				String[] date = tree.getSelectionPath().getLastPathComponent().toString().split(":")[1].split("/");
				boolean completed = main.getCont().printBills(user, date[1], date[2]);
				if (completed){
					JOptionPane.showMessageDialog(null, "Saved Bill");
				}
				else {JOptionPane.showMessageDialog(null, "Bill Saved failed");}	
				
			}
		});
		this.btnExport.setBounds(674, 342, 295, 56);
		ImageIcon print = new ImageIcon(this.getClass().getResource("/images/print.png"));
		print.setImage(print.getImage().getScaledInstance(btnExport.getHeight(), btnExport.getHeight(), Image.SCALE_DEFAULT));
		btnExport.setIcon(print);
		add(this.btnExport);
		
		lblPostalCode.hide();
		lblUnitNo.hide();
		label.hide();
		txtPostal.hide();
		txtxUnitNo.hide();
		btnSearch.hide();
		if (main.flag==true){
			main.flag=false;
			tglbtnSearchMethod.setSelected(true);
			showAddressSearch();
		}
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
	
	public void showAddressSearch(){
		btnEditUser.setText("Edit Latest Bill");
		txtSearch.hide();
		lblcaseSensetive.hide();
		lblSearch.hide();
		lblPostalCode.show();
		lblUnitNo.show();
		label.show();
		txtPostal.show();
		txtxUnitNo.show();
		btnSearch.show();
		
	}
	public void showUserSearch(){
		btnEditUser.setText("Edit User");
		txtSearch.show();
		lblcaseSensetive.show();
		lblSearch.show();
		
		lblPostalCode.hide();
		lblUnitNo.hide();
		label.hide();
		txtPostal.hide();
		txtxUnitNo.hide();
		btnSearch.hide();
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
				bill.add(new DefaultMutableTreeNode("Total: $"+t));
				userR.add(bill);
				
			}
			if (userReadings.length==0){
				userR.add(new DefaultMutableTreeNode("No History"));//total of bill
				System.out.println("No Hist");
			}
			else{userR.add(new DefaultMutableTreeNode("User Total: $"+String.format("%.2f", utotal)));}//total of bill
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
				bill.add(new DefaultMutableTreeNode("Total: $"+t));
			}
			if (userReadings.length==0){
				userR.add(new DefaultMutableTreeNode("No History"));//total of bill
				System.out.println("No Hist");
			}
			else{userR.add(new DefaultMutableTreeNode("User Total: $"+String.format("%.2f", utotal)));}//total of bill
			username.add(userR);
		}
		model.reload();
		tree.setModel(model);
	}
	
	
	public void searchTree(String postal, String unitNo){
		if (postal.equals("")){populateTree();return;}
		Customer[] accts = main.getCont().getAllCustomers();
//		Vector<Customer> filtered = new Vector<>();
		// Filter accts
		Customer[] filtered = main.getCont().getCustomer(postal, unitNo);
		

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
				bill.add(new DefaultMutableTreeNode("Total: $"+t));
				userR.add(bill);
			}
			if (userReadings.length==0){
				userR.add(new DefaultMutableTreeNode("No History"));//total of bill
				System.out.println("No Hist");
			}
			else{userR.add(new DefaultMutableTreeNode("User Total: $"+String.format("%.2f", utotal)));}//total of bill
			username.add(userR);
		}
		model.reload();
		tree.setModel(model);
	}
}
