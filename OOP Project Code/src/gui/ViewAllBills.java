package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.Customer;
import data.Readings;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ViewAllBills extends JPanel{
	MainFrame main;
	private JScrollPane scrollPane;
	private JTree tree;
	private JLabel lblMonth;
	private JComboBox comboMonth;
	private JLabel lblYear;
	private JComboBox comboYear;
	private DefaultTreeModel model;
	DefaultMutableTreeNode header = new DefaultMutableTreeNode("Date");
	private String[] date;
	private String[] month = {"All","January", "February", "March","April","May","June","July","August","September","October","November","December"};
	private String[] year;
	private JTextField textField;
	private JLabel lblSearchUser;
	private JTextArea txtrUnits;
	private JLabel lblcaseSensetive;
	private JLabel lblCustomerBills;
	
	public ViewAllBills(MainFrame m){
		main = m;
		this.setLayout(null);
		main.setSize(1020,720);
		
		//init month/year array
		date = main.getCont().getSystemDate();
		Vector<String> temp = new Vector<>();
		temp.add("All");
		for (int i=Integer.valueOf(date[1]); i>=2000;i--){
			temp.add(String.valueOf(i));
		}
		year = new String[temp.size()];
		temp.toArray(year);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 83, 650, 560);
		add(this.scrollPane);
		
		
		model = new DefaultTreeModel(header);
		this.tree = new JTree(model);
		tree.setRowHeight(30);
		this.tree.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.scrollPane.setViewportView(this.tree);
		
		this.lblCustomerBills = new JLabel("Customer Bills");
		this.lblCustomerBills.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.lblCustomerBills.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane.setColumnHeaderView(this.lblCustomerBills);
		
		this.lblMonth = new JLabel("Month:");
		this.lblMonth.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblMonth.setBounds(674, 210, 83, 37);
		add(this.lblMonth);
		
		this.comboMonth = new JComboBox(month);
		comboMonth.setSelectedIndex(0);
		this.comboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
		});
		this.comboMonth.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.comboMonth.setBounds(769, 205, 178, 45);
		add(this.comboMonth);
		
		this.lblYear = new JLabel("Year:");
		this.lblYear.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblYear.setBounds(668, 129, 83, 38);
		add(this.lblYear);
		
		this.comboYear = new JComboBox(year);
		comboYear.setSelectedItem("All");
		
		this.comboYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selMonth = comboMonth.getSelectedIndex();
				if (comboYear.getSelectedItem().equals(date[1])){
					Vector<String> temp = new Vector<>();
					int c=0;
					for (String m:month){
						temp.add(m);
						if (c==Integer.parseInt(date[0])){break;}
						c++;
					}
					month = new String[temp.size()];
					temp.toArray(month);
					comboMonth.setModel(new DefaultComboBoxModel<>(month));
					try {
						comboMonth.setSelectedIndex(selMonth);
					} catch (Exception e2) {
						comboMonth.setSelectedIndex(comboMonth.getModel().getSize()-1);
					}
				}
				else {String[] mon = {"All","January", "February", "March","April","May","June","July","August","September","October","November","December"};
					month  = mon;
					comboMonth.setModel(new DefaultComboBoxModel<>(month));
					comboMonth.setSelectedIndex(selMonth);}
				
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
		});
		this.comboYear.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		
		this.comboYear.setBounds(769, 125, 178, 45);
		add(this.comboYear);
		
		this.textField = new JTextField();
		this.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
		});
		this.textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.textField.setBounds(668, 336, 279, 45);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.lblSearchUser = new JLabel("Search User:");
		this.lblSearchUser.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblSearchUser.setBounds(668, 278, 150, 45);
		add(this.lblSearchUser);
		
		this.txtrUnits = new JTextArea();
		txtrUnits.setOpaque(false);
		txtrUnits.setEditable(false);
		this.txtrUnits.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtrUnits.setText("Units");
		this.setUnits();
		this.txtrUnits.setBounds(674, 409, 279, 235);
		add(this.txtrUnits);
		
		searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
		
		TaskBar bar = new TaskBar(this, main);
		
		this.lblcaseSensetive = new JLabel("*Case Sensetive");
		this.lblcaseSensetive.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		this.lblcaseSensetive.setForeground(Color.BLUE);
		this.lblcaseSensetive.setBounds(817, 288, 136, 26);
		add(this.lblcaseSensetive);
	}

	
	public void searchTree(Object M, Object Y, String user){
		String month = String.format("%02d",M);
		String year = (String) Y;
		String[] monthList = {month};
		String[] yearList = {year};
		header.removeAllChildren();

		if (M.equals(0)){
			String[] temp = new String[this.month.length-1];
			for (int i=0 ; i<this.month.length-1 ;i++){
				temp[i] = String.format("%02d",i+1);
			}
			monthList = temp;
		}

		if (year.equals("All")){
			String[] temp = new String[this.year.length-1];
			for (int i=0; i<this.year.length-1 ;i++){
				temp[i] = this.year[i+1];
			}
			yearList = temp;
		}

		for (String y:yearList){
			if((int)M>Integer.valueOf(date[0]) && date[1].equals(y)){continue;}
			DefaultMutableTreeNode yr = new DefaultMutableTreeNode(y);
			header.add(yr);
			double yearTotal=0;
			boolean continuee = true;
			for (String m :monthList){
				if (continuee == false){continue;}
				if (y.equals(date[1])&& Integer.valueOf(date[0])==Integer.valueOf(m)){
					continuee = false;
				}
				DefaultMutableTreeNode mth = new DefaultMutableTreeNode(this.month[Integer.valueOf(m)]);
				yr.add(mth);

				Customer[] accts = main.getCont().getAllCustomers();
				Vector<String[][]> filtered = new Vector<>();
				// Filter accts
				String[][][] bills = main.getCont().getUserReading(m, y);
				double monthlyTotal = 0;
				for (String[][] b:bills){
					for (String[] i:b){
						try {
							monthlyTotal+=Double.parseDouble(i[2]);
						}
						catch (Exception e) {continue;}
						
					}
					if (b[0][0].startsWith(user)){
						filtered.add(b);
					}
				}
				
				for (String [][] ur : filtered){
					DefaultMutableTreeNode u = new DefaultMutableTreeNode(ur[0][0]+" : "+ur[0][2]);
					double userTotal=0;
					for(String[] item:ur){
						try {
							Double.valueOf(item[2]);
						}
						catch (Exception e) {
							continue;
						}
						userTotal+= Double.valueOf(item[2]);
						u.add(new DefaultMutableTreeNode(item[0]+" : " + item[1]));
					}
					u.add(new DefaultMutableTreeNode("Bill Total: $"+String.format("%.2f",userTotal)));
					mth.add(u);
				}
				mth.add(new DefaultMutableTreeNode(String.format("Month Revenue: $"+"%.2f",monthlyTotal)));
				yearTotal+=monthlyTotal;
			}
//			yr.add(new DefaultMutableTreeNode("Year Total: $"+String.format("%.2f", yearTotal)));
		}
		
		model.reload();
		tree.setModel(model);
		if (yearList.length == 1){
			tree.expandRow(1);
			if (monthList.length==1){
				tree.expandRow(2);
			}
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
}
