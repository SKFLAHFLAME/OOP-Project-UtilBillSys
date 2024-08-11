package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.MainFrame;
import data.Readings;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SMenu extends JPanel{
	MainFrame main; // Reference to the MainFrame instance    
	private JButton btnLogOut;
    private JButton btnEditUtilities;
    private JButton btnViewCustomer;
    private JButton btnAccount;
    private JButton btnViewBills;
    private JPanel panel;
    private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private JLabel lblBackground;
	private JLabel lblLogo;
	private JLabel label;
	private JLabel lblStaffPage;
	private JLabel lblPsGroup;
	private JTextArea txtrDate;
	private JTextArea txtrAccount;
	private JTextArea txtrAverageReadings;
	private JScrollPane scrollPane;
	private String[] date;
	private DefaultTableModel priceModel;
	private JTable tablePrice;

    public SMenu(MainFrame m){
    	setBackground(new Color(135, 206, 250));
        this.main=m;
        this.setLayout(null);
        main.setSize(1020,720);
        main.addTaskBar(this);
        
        
        this.panel = new JPanel();
        panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
        this.panel.setBounds(148, 72, 689, 568);
        add(this.panel);
        this.panel.setLayout(null);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.setBounds(12, 13, 97, 25);
        this.panel.add(this.btnAccount);
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                main.setPrepage(false); // Set the previous page flag
        		main.showPopup("SAccount", main.getCurrentAcct()[1]);
        	}
        });
        btnAccount.hide(); // Hide the Account button
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        this.btnViewCustomer = new JButton("View Customer");
        this.btnViewCustomer.setBounds(30, 436, 152, 44);
        this.panel.add(this.btnViewCustomer);
        this.btnViewCustomer.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.showAllCustomers();
        	}
        });
        this.btnViewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        this.btnEditUtilities = new JButton("Edit Utilities");
        this.btnEditUtilities.setBounds(345, 436, 313, 44);
        this.panel.add(this.btnEditUtilities);
        this.btnEditUtilities.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.setPrepage(false);
        		main.showEditUtility();
        		
        	}
        });
        this.btnEditUtilities.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        this.btnLogOut = new JButton("Log Out");
        this.btnLogOut.setBounds(272, 511, 126, 44);
        this.panel.add(this.btnLogOut);
        this.btnLogOut.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
                main.clearCurrentAcct();
        		main.showAllLogin();
        		main.closeCurrentDialogs();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        this.btnViewBills = new JButton("View Bills");
        this.btnViewBills.setBounds(205, 436, 111, 44);
        this.panel.add(this.btnViewBills);
        this.btnViewBills.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showAllBills();        	}
        });
        this.btnViewBills.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        this.lblLogo = new JLabel("logo");
        this.lblLogo.setBounds(226, 57, 90, 90);
        logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
        this.panel.add(this.lblLogo);
        
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
        this.lblPsGroup.setBounds(328, 73, 128, 29);
        this.panel.add(this.lblPsGroup);
        
        this.lblStaffPage = new JLabel("Staff Page");
        this.lblStaffPage.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblStaffPage.setBounds(328, 115, 128, 25);
        this.panel.add(this.lblStaffPage);
        
        this.label = new JLabel("-----------------");
        this.label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.label.setBounds(328, 96, 128, 20);
        this.panel.add(this.label);
        
        this.txtrDate = new JTextArea();
        txtrDate.setBackground(Color.WHITE);
        this.txtrDate.setEditable(false);
        this.txtrDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrDate.setText("Date");
        this.txtrDate.setBounds(30, 80, 128, 51);
        this.panel.add(this.txtrDate);
        
        this.txtrAccount = new JTextArea();
        txtrAccount.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.txtrAccount.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        this.txtrAccount.setEditable(false); // Make text area non-editable
        //this.txtrAccount.setBackground(SystemColor.control); // Background color commented out
        this.txtrAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtrAccount.setBackground(SystemColor.control); // Change background on mouse enter
            }
            @Override
            public void mouseExited(MouseEvent e) {
                txtrAccount.setBackground(Color.WHITE); // Change background on mouse exit
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                main.setPrepage(false); // Set previous page flag
                main.showPopup("SAccount", main.getCurrentAcct()[1]); // Show account popup
            }
        });

        this.txtrAccount.setText("Account");
        this.txtrAccount.setBounds(517, 80, 141, 51);
        this.panel.add(this.txtrAccount);
        
        this.txtrAverageReadings = new JTextArea();
        this.txtrAverageReadings.setEditable(false);
        this.txtrAverageReadings.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrAverageReadings.setText("Average Meter Reading");
        this.txtrAverageReadings.setBounds(30, 186, 283, 242);
        this.panel.add(this.txtrAverageReadings);
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(345, 183, 313, 242);
        this.panel.add(this.scrollPane);
        
        String [] colName = {"Name","Price","Tax"};
    	priceModel = new DefaultTableModel(colName, 0){
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        this.tablePrice = new JTable(priceModel);
        this.tablePrice. getTableHeader(). setReorderingAllowed(false);
		this.tablePrice.getTableHeader().setResizingAllowed(false);
        tablePrice.getColumnModel().getColumn(1).setPreferredWidth(40);
        tablePrice.getColumnModel().getColumn(2).setPreferredWidth(10);
        tablePrice.setShowGrid(true);
        tablePrice.setRowHeight(35);
        this.tablePrice.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
        tablePrice.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        tablePrice.setEnabled(false);
        this.scrollPane.setViewportView(this.tablePrice);
        
        
        this.lblBackground = new JLabel("Background");
        this.lblBackground.setBounds(0, 0, main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
        add(this.lblBackground);
        
        init();
    }
    
    public void init(){
        showDate(); // Display the current date
        showAcctDetails(); // Display account details
        showAverageReadings(); // Display average meter readings
        fillCurrentPrices(); // Fill the price table with current prices
    }
    
    public void showAverageReadings(){
        // Build the text for average readings
        String text = "Average Usage: \nAs of: ";
        text += date[0] + "/" + date[1] + '\n'; // Append date
        for (Readings r : main.getCont().getAllReadings()){
            int ave = main.getCont().getAverageReading(r.getUtilityName()); // Get average reading
            text += r.getUtilityName() + " : " + ave + '\n'; // Append reading
        }
        
        txtrAverageReadings.setText(text); // Update text area with average readings
    }
    
    public void fillCurrentPrices(){
        // Clear the existing rows in the price table
        priceModel.setRowCount(0);
        Readings[] readings = main.getCont().getAllReadings(); // Get all readings
        for (Readings r : readings){
            String[] row = {r.getUtilityName(), "$" + r.getPrice() + "/" + r.getUnit(), "" + r.getServiceCharge() + "%"}; // Create row data
            priceModel.addRow(row); // Add row to table model
        }
        tablePrice.setModel(priceModel); // Set the table model
        tablePrice.repaint(); // Repaint table to reflect changes
    }
    
    public void showDate(){
        date = main.getCont().getSystemDate(); // Get system date
        String d = "Date: \n" + String.join(" / ", date); // Format date
        txtrDate.setText(d); // Update text area with date
    }
    
    public void showAcctDetails(){
        // Build text for account details
        String text = "ID : " + main.getCurrentAcct()[1] + '\n' 
                + "Access : Staff"; // Append account details
        txtrAccount.setText(text); // Update text area with account details
    }
}