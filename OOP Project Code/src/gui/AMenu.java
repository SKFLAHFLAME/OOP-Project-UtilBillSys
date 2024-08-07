package gui;

import javax.swing.*;

import controller.MainFrame;
import data.Readings;
import data.Staff;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AMenu extends JPanel{
    MainFrame main;
    String[] date;
    private JButton btnLogOut;
    private JButton btnEditUtilities;
    private JButton btnViewBills;
    private JButton btnAccount;
    private JButton btnEditDate;
    private JButton btnViewBills_1;
    private JPanel panel;
    private JLabel lblBackground;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private JLabel lblLogo;
	private JLabel lblPsGroup;
	private JLabel lblAdminPage;
	private JLabel label;
	private JTextArea txtrDate;
	private JScrollPane scrollPane;
	private JButton btnAdd;
	private JButton btnDelete;
	private JList list;
	private JLabel lblCurrentStaffAccounts;
	private JTextField txtSearch;
	private JScrollPane scrollPane_1;
	private JTextArea txtrAverageReadings;
    
    public AMenu(MainFrame m){
    	setBackground(new Color(135, 206, 250));
        this.main = m;
        date  = main.getCont().getSystemDate();
        this.setLayout(null);
        main.setSize(1020,720);

        main.addTaskBar(this);
        
        this.panel = new JPanel();
        this.panel.setBounds(150, 70, 689, 568);
        panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
        add(this.panel);
        this.panel.setLayout(null);
        
        this.btnEditDate = new JButton("Edit Date");
        this.btnEditDate.setBounds(12, 13, 97, 25);
        this.panel.add(this.btnEditDate);
        this.btnEditDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showPopup("Date", "");
        	}
        });
        this.btnEditDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEditDate.hide();
        
        this.btnEditUtilities = new JButton("Edit Utilities");
        this.btnEditUtilities.setBounds(12, 433, 346, 38);
        this.panel.add(this.btnEditUtilities);
        this.btnEditUtilities.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.setPrepage(true);
        		main.showEditUtility();
        		
        	}
        });
        this.btnEditUtilities.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        
        this.btnViewBills = new JButton("View Customers");
        this.btnViewBills.setBounds(12, 482, 166, 38);
        this.panel.add(this.btnViewBills);
        this.btnViewBills.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.showAllCustomers();
        	}
        });
        this.btnViewBills.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        
        this.btnViewBills_1 = new JButton("View Bills");
        this.btnViewBills_1.setBounds(192, 482, 166, 38);
        this.panel.add(this.btnViewBills_1);
        this.btnViewBills_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showAllBills();
        	}
        });
        this.btnViewBills_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.setBounds(538, 74, 97, 25);
        this.panel.add(this.btnAccount);
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.setPrepage(true);
        		main.showPopup("SAccount", main.getCurrentAcct()[1]);
        	}
        });
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        this.btnLogOut = new JButton("Log Out");
        this.btnLogOut.setBounds(538, 110, 97, 25);
        this.panel.add(this.btnLogOut);
        this.btnLogOut.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
                main.clearCurrentAcct();
        		main.showAllLogin();
        		main.closeCurrentDialogs();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        this.lblLogo = new JLabel("logo");
        this.lblLogo.setBounds(228, 30, 90, 90);
        logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
        this.panel.add(this.lblLogo);
        
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
        this.lblPsGroup.setBounds(328, 40, 128, 29);
        this.panel.add(this.lblPsGroup);
        
        this.lblAdminPage = new JLabel("Admin Page");
        this.lblAdminPage.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblAdminPage.setBounds(328, 87, 128, 25);
        this.panel.add(this.lblAdminPage);
        
        this.label = new JLabel("-----------------");
        this.label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.label.setBounds(328, 62, 128, 20);
        this.panel.add(this.label);
        
        this.txtrDate = new JTextArea();
        txtrDate.setBackground(Color.WHITE);
        this.txtrDate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showPopup("Date", "");
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		txtrDate.setBackground(SystemColor.control);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		txtrDate.setBackground(Color.WHITE);
        	}
        });
        this.txtrDate.setEditable(false);
        this.txtrDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrDate.setText("Date");
        this.txtrDate.setBounds(30, 77, 128, 56);
        this.panel.add(this.txtrDate);
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(368, 166, 309, 283);
        this.panel.add(this.scrollPane);
        
        this.list = new JList();
        this.list.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.list.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() ==2){
					if (list.getSelectedIndex()==-1){return;}
					String ID = (String) list.getSelectedValue();
					main.showPopup("SAccount", ID);
				}
        	}
        });
        this.scrollPane.setViewportView(this.list);
        
        this.lblCurrentStaffAccounts = new JLabel("Current Staff Accounts");
        this.lblCurrentStaffAccounts.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.lblCurrentStaffAccounts.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblCurrentStaffAccounts.setVerticalAlignment(SwingConstants.BOTTOM);
        this.scrollPane.setColumnHeaderView(this.lblCurrentStaffAccounts);
        
        this.btnAdd = new JButton("Add");
        this.btnAdd.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showPopup("SSignUp", "");
        	}
        });
        this.btnAdd.setBounds(580, 489, 97, 25);
        this.panel.add(this.btnAdd);
        
        this.btnDelete = new JButton("Delete");
        this.btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (list.getSelectedIndex()==-1){return;}
        		String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Delete?", "Delete", 0, 3, null, options, options[1]);
				if (sel !=0){return;}
				if (list.getSelectedIndex()==-1){return;}
				String selectedID = (String) list.getSelectedValue();
				main.getCont().removeStaff(selectedID);
				populateList();
        	}
        });
        this.btnDelete.setBounds(368, 489, 97, 25);
        this.panel.add(this.btnDelete);
        
        this.txtSearch = new JTextField();
        this.txtSearch.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		searchList(txtSearch.getText());
        	}
        	@Override
        	public void keyReleased(KeyEvent e) {
        		searchList(txtSearch.getText());
        	}
        });
        this.txtSearch.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtSearch.setBounds(368, 449, 309, 29);
        this.panel.add(this.txtSearch);
        this.txtSearch.setColumns(10);
        
        this.scrollPane_1 = new JScrollPane();
        this.scrollPane_1.setBounds(12, 167, 346, 255);
        this.panel.add(this.scrollPane_1);
        
        this.txtrAverageReadings = new JTextArea();
        this.txtrAverageReadings.setEditable(false);
        this.txtrAverageReadings.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrAverageReadings.setText("Average Readings");
        this.scrollPane_1.setViewportView(this.txtrAverageReadings);
        
        
        this.lblBackground = new JLabel("Background");
        this.lblBackground.setBounds(0, 0, main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
        add(this.lblBackground);
        
        init();

    }
    public void init(){
    	showDate();
    	showAverageReadings();
    	populateList();
    }
    
    public void showDate(){
    	
    	String d = "Date: \n"+String.join(" / ", date);
    	txtrDate.setText(d);
    }
    
    public void showAverageReadings(){
    	String text = "Average Usage: \nAs of: ";
    	text += date[0]+"/"+date[1]+'\n';
    	for (Readings r:main.getCont().getAllReadings()){
    		int ave=main.getCont().getAverageReading(r.getUtilityName());
    		text+= r.getUtilityName()+" : "+ave+'\n';
    	}
    	
    	txtrAverageReadings.setText(text);
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
    public void searchList(String id){
		DefaultListModel<String> model = new DefaultListModel<>();
		Staff[] allStaff = main.getCont().getAllStaff();
		Vector<Staff> filtered = new Vector<>();
		for (Staff s:allStaff){
			if (s.getUsername().startsWith(id)){
				filtered.add(s);
			}
		}
		for(Staff s: filtered){
			if(s.getUsername().equals("admin")){continue;}
			model.addElement(s.getUsername());
		}
		list.setModel(model);
		
	}
}
