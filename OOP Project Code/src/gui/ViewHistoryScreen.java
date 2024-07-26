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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;
import java.awt.Component;

public class ViewHistoryScreen extends JPanel{
	MainFrame main;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode userName;
	
	private JScrollPane scrollPane;
	private JLabel lblCustomerDetails;
	private JTree tree;
	private JButton btnBack;
	private JTextField txtSearch;
	private JLabel lblSearch;
	private JLabel lblcaseSensetive;
	private JButton btnSearchByDate;
	
	public ViewHistoryScreen(MainFrame m) {
		main=m;
		main.setSize(480,580);
		this.setLayout(null);
		userName = new DefaultMutableTreeNode(main.getCurrentAcct()[1]);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 59, 366, 288);
		add(this.scrollPane);
		
		model = new DefaultTreeModel(userName);
		this.tree = new JTree(model);
		this.tree.setFont(new Font("Tahoma", Font.PLAIN, 16));
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

/*		this.txtSearch = new JTextField();
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
		
		this.btnSearchByDate = new JButton("Search By Date");
		this.btnSearchByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.showAllBills();
			}
		});
		this.btnSearchByDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.btnSearchByDate.setBounds(239, 411, 139, 40);
		add(this.btnSearchByDate);
		main.setSize(400,500);
		*/
	}

	
	public void populateTree() {
		String[][][] userReadings = main.getCont().getUserReading(main.getCurrentAcct()[1]);//get all userReadings of user
		double utotal = 0;
		for (String[][] ur: userReadings){//go thru each bill
			double total=0;
			if(ur==null){continue;}
			if (ur[0][0]==null){continue;}
			DefaultMutableTreeNode bill= new DefaultMutableTreeNode("Bill "+ur[0][1]+":"+ur[0][2]);
			for (int i =0; i<ur.length-1; i++){// go thru each reading
				if (ur[i][0]==null){continue;}
				bill.add(new DefaultMutableTreeNode(ur[i+1][0]+": "+ ur[i+1][1]));
				total+=Double.valueOf(ur[i+1][2]);
			}
			String t = String.format("%.2f", total);
			utotal+= total;
			bill.add(new DefaultMutableTreeNode("Total: $"+t));//total of bill	
			userName.add(bill);
		}
	
		this.model.reload();
		tree.setModel(model);
	}
}
