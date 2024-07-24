package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ViewAllBills extends JPanel{
	MainFrame main;
	private JScrollPane scrollPane;
	private JTree tree;
	private JLabel lblMonth;
	private JComboBox comboMonth;
	private JLabel lblYear;
	private JComboBox comboYear;
	private JButton btnSearch;
	private JButton btnBack;
	private JButton btnSearchByName;
	
	private String[] date;
	private String[] month = {"January", "February", "March","April","May","June","July","August","September","October","November","December"};
	private String[] year;
	
	public ViewAllBills(MainFrame m){
		main = m;
		this.setLayout(null);
		
		//init month/year array
		date = main.getCont().getSystemDate();
		Vector<String> temp = new Vector<>();
		for (int i=2000; i<=Integer.valueOf(date[1]);i++){
			temp.add(""+i);
		}
		year = new String[temp.size()];
		temp.toArray(year);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 45, 366, 259);
		add(this.scrollPane);
		
		this.tree = new JTree();
		this.scrollPane.setViewportView(this.tree);
		
		this.lblMonth = new JLabel("Month:");
		this.lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.lblMonth.setBounds(12, 317, 60, 32);
		add(this.lblMonth);
		
		this.comboMonth = new JComboBox(month);
		this.comboMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.comboMonth.setBounds(74, 317, 117, 32);
		add(this.comboMonth);
		
		this.lblYear = new JLabel("Year:");
		this.lblYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.lblYear.setBounds(12, 362, 54, 38);
		add(this.lblYear);
		
		this.comboYear = new JComboBox(year);
		this.comboYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.comboYear.setBounds(74, 362, 117, 32);
		add(this.comboYear);
		
		this.btnSearch = new JButton("Search");
		this.btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnSearch.setBounds(74, 419, 93, 27);
		add(this.btnSearch);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.getCurrentAcct()[0].equals("A")){
					main.showAdminMenu();
					return;
				}
				else{main.showStaffMenu();}
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnBack.setBounds(218, 412, 117, 40);
		add(this.btnBack);
		
		this.btnSearchByName = new JButton("Search by Name");
		this.btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showAllCustomers();
			}
		});
		this.btnSearchByName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnSearchByName.setBounds(218, 359, 160, 44);
		add(this.btnSearchByName);
	}
	
	public void populateTree(){
		
	}
	
	public void searchTree(String search){
		
	}
}
