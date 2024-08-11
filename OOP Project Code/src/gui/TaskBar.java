package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.MainFrame;

public class TaskBar {
	MainFrame main; // Reference to the main frame controller
	private JPanel panel;
	private Font font = new Font("Trebuchet MS", Font.PLAIN, 24);
	private Font selected = new Font("Trebuchet MS", Font.BOLD, 24);
	private String currentPanel;
	
	
	ImageIcon calicon = new ImageIcon(this.getClass().getResource("/images/calendar.png"));
	ImageIcon proicon = new ImageIcon(this.getClass().getResource("/images/profile.png"));
	ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("/images/logout.png"));
	
	// Constructor for TaskBar
		public TaskBar(JPanel panel, MainFrame m){
			main = m; // Initialize main frame reference
			this.panel = panel; // Initialize panel reference
			currentPanel = panel.getClass().getSimpleName(); // Get the name of the current panel
			// Display the appropriate task bar based on account type
			switch (main.getCurrentAcct()[0]) {
			case "A":
				adminBar(); // Admin task bar
				break;
				
			case "S":
				staffBar(); // Staff task bar
				break;
				
			case "C":
				customerBar(); // Customer task bar
				break;

			default:
				main.showAllLogin(); // Show login if account type is not recognized
				break;
			}
		}
	
	
	
	// Method to create the admin task bar
	public void adminBar(){
		int xPos = 0; // Initial x position for the labels
		
    	JPanel bar = new JPanel();
    	bar.setLayout(null);
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(255,90,90));
    	panel.add(bar);
    	
    	//!admin Home Header
    	JLabel adminpg = new JLabel("Home");
    	adminpg.setToolTipText("Admin Page");
    	adminpg.setForeground(Color.WHITE);
    	adminpg.setFont(font);
    	if(currentPanel.equals("AMenu")){
    		adminpg.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		adminpg.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showAdminMenu(); // Show admin menu on click
        		}
        	});
    	}
    	adminpg.setHorizontalAlignment(SwingConstants.CENTER);
    	adminpg.setBounds(xPos, 5, 80, 30);
    	xPos+=adminpg.getWidth();
    	bar.add(adminpg);
    	
    	
    	//!Edit Utilities Header
    	JLabel editUtil = new JLabel("Edit Utility");
    	editUtil.setToolTipText("Edit Utility Details");
    	editUtil.setForeground(Color.WHITE);
    	editUtil.setFont(font);
    	if(currentPanel.equals("EditUtility")){
    		editUtil.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		editUtil.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showEditUtility(); // Show edit utility page on click
        		}
        	});
    	}
    	editUtil.setHorizontalAlignment(SwingConstants.CENTER);
    	editUtil.setBounds(xPos, 5, 150, 30);
    	xPos+=editUtil.getWidth();
    	bar.add(editUtil);
    	
    	
    	//!view Customer Header
    	JLabel viewCust = new JLabel("View Customers");
    	viewCust.setToolTipText("View All Customer Accounts");
    	viewCust.setForeground(Color.WHITE);
    	viewCust.setFont(font);
    	if(currentPanel.equals("ViewAllCustomer")){
    		viewCust.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		viewCust.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showAllCustomers(); // Show all customers page on click
        		}
        	});
    	}
    	viewCust.setHorizontalAlignment(SwingConstants.CENTER);
    	viewCust.setBounds(xPos, 5, 190, 30);
    	xPos+=viewCust.getWidth();
    	bar.add(viewCust);
    	
    	
    	//!view Bill Header
    	JLabel viewBiils = new JLabel("View Bills");
    	viewBiils.setToolTipText("View All Bills by Date");
    	viewBiils.setForeground(Color.WHITE);
    	viewBiils.setFont(font);
    	if(currentPanel.equals("ViewAllBills")){
    		viewBiils.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		viewBiils.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showAllBills(); // Show all bills page on click
        		}
        	});
    	}
    	viewBiils.setHorizontalAlignment(SwingConstants.CENTER);
    	viewBiils.setBounds(xPos, 5, 140, 30);
    	xPos+=viewBiils.getWidth();
    	bar.add(viewBiils);
    	

    	
    	
    	
    	
    	
    	
    	JLabel calendar = new JLabel();
    	calendar.setToolTipText("Edit System Date");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showPopup("Date", ""); // Show date popup on click
			}
		});
    	
    	calicon.setImage(calicon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    	calendar.setIcon(calicon);
    	calendar.setSize(30, 30);
    	calendar.setLocation(main.getWidth()-6*calendar.getWidth(), 5);
    	calendar.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(calendar);

    	
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showPopup("SAccount", main.getCurrentAcct()[1]); // Show account popup on click
			}
		});
    	
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	profile.setSize(30, 30);
    	profile.setLocation(main.getWidth()-4*profile.getWidth(), 5);
    	profile.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(profile);
    	
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct(); // Clear current account
    			main.showAllLogin(); // Show login page
    			main.setPrepage(false); // Reset prepage flag
    			main.flag = false; // Reset flag
    			main.closeCurrentDialogs(); // Close any open dialogs
			}
		});
    	
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	exit.setSize(30, 30);
    	exit.setLocation(main.getWidth()-2*exit.getWidth(), 5);
    	exit.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(exit);
    	
    }
	
	
	
	
	
	// Method to create the staff task bar
	public void staffBar(){
	    int xPos = 5; // Initial x position for the labels
			
    	JPanel bar = new JPanel();
    	bar.setLayout(null);
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(135,100,210));
    	panel.add(bar);
    	
    	JLabel staffpg = new JLabel("Home");
    	staffpg.setToolTipText("Staff Page");
    	staffpg.setForeground(Color.WHITE);
    	staffpg.setFont(font);
    	if(currentPanel.equals("SMenu")){
    		staffpg.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		staffpg.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showStaffMenu(); // Show staff menu on click
        		}
        	});
    	}
    	staffpg.setHorizontalAlignment(SwingConstants.CENTER);
    	staffpg.setBounds(xPos, 5, 80, 30);
    	xPos+=staffpg.getWidth();
    	bar.add(staffpg);
    	
    	
    	JLabel editUtil = new JLabel("Edit Utility");
    	editUtil.setToolTipText("Edit Utility Details");
    	editUtil.setForeground(Color.WHITE);
    	editUtil.setFont(font);
    	if(currentPanel.equals("EditUtility")){
    		editUtil.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		editUtil.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showEditUtility(); // Show utility page on click
        		}
        	});
    	}
    	editUtil.setHorizontalAlignment(SwingConstants.CENTER);
    	editUtil.setBounds(xPos, 5, 150, 30);
    	xPos+=editUtil.getWidth();
    	bar.add(editUtil);
    	
    	
    	JLabel viewCust = new JLabel("View Customers");
    	viewCust.setToolTipText("View All Customer Accounts");
    	viewCust.setForeground(Color.WHITE);
    	viewCust.setFont(font);
    	if(currentPanel.equals("ViewAllCustomer")){
    		viewCust.setFont(selected);	// Highlight the selected panel
    	}
    	else{
    		viewCust.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showAllCustomers();	// Show customer page on click
        		}
        	});
    	}
    	viewCust.setHorizontalAlignment(SwingConstants.CENTER);
    	viewCust.setBounds(xPos, 5, 190, 30);
    	xPos+=viewCust.getWidth();
    	bar.add(viewCust);
    	
    	
    	JLabel viewBiils = new JLabel("View Bills");
    	viewBiils.setToolTipText("View All Bills by Months");
    	viewBiils.setForeground(Color.WHITE);
    	viewBiils.setFont(font);
    	if(currentPanel.equals("ViewAllBills")){
    		viewBiils.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		viewBiils.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showAllBills(); // Show all bills page on click
        		}
        	});
    	}
    	viewBiils.setHorizontalAlignment(SwingConstants.CENTER);
    	viewBiils.setBounds(xPos, 5, 140, 30);
    	xPos+=viewBiils.getWidth();
    	bar.add(viewBiils);
    	

    	
    	
    	
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account"); // Tooltip text
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showPopup("SAccount", main.getCurrentAcct()[1]); // Show account popup on click
			}
		});
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	profile.setSize(30, 30);
    	profile.setLocation(main.getWidth()-4*profile.getWidth(), 5);
    	profile.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(profile);
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct(); // Clear current account
    			main.showAllLogin(); // Show login page
    			main.setPrepage(false); // Reset prepage flag
    			main.flag = false; // Reset flag
    			main.closeCurrentDialogs(); // Close any open dialogs
			}
		});
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	exit.setSize(30, 30);
    	exit.setLocation(main.getWidth()-2*exit.getWidth(), 5);
    	exit.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(exit);
    	
    }
	
	
	
	
	// Method to create the customer task bar
	public void customerBar(){
    	int xPos = 5; // Initial x position for the labels
    	
    	
    	JPanel bar = new JPanel();
    	bar.setLayout(null);
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(0,75,200, 200));
    	panel.add(bar);
    	
    	
    	JLabel home = new JLabel("Home");
    	home.setToolTipText("View Bill History");
    	home.setForeground(Color.WHITE);
    	home.setFont(font);
    	if(currentPanel.equals("CMenu")){
    		home.setFont(selected); // Highlight the selected panel
    	}
    	else{
    		home.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showCustMenu(); // Show customer menu on click
        		}
        	});
    	}
    	home.setHorizontalAlignment(SwingConstants.CENTER);
    	home.setBounds(xPos, 5, 80, 30);
    	xPos+=home.getWidth();
    	bar.add(home);
    	
    	
    	
    	JLabel viewHist = new JLabel("View History");
    	viewHist.setToolTipText("View Bill History");
    	viewHist.setForeground(Color.WHITE);
    	viewHist.setFont(font);
    	if(currentPanel.equals("ViewHistoryScreen")){
    		viewHist.setFont(selected);	// Highlight the selected panel
    	}
    	else{
    		viewHist.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showViewHistoryScreen();	// Show History Screen on click
        		}
        	});
    	}
    	viewHist.setHorizontalAlignment(SwingConstants.CENTER);
    	viewHist.setBounds(xPos, 5, 150, 30);
    	xPos+=viewHist.getWidth();
    	bar.add(viewHist);
    	
    	
    	JLabel editDraft = new JLabel("Edit Draft");
    	editDraft.setToolTipText("Edit Month's Bill");
    	editDraft.setForeground(Color.WHITE);
    	editDraft.setFont(font);
    	if (currentPanel.equals("EditDraft")){
    		editDraft.setFont(selected);	// Highlight the selected panel
    	}
    	else {
    		editDraft.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			
        			main.setPrepage(false);
        			main.showEditDraft(main.getCurrentAcct()[1]);	// Show Edit Draft
        		
    		}
    	});}
    	editDraft.setHorizontalAlignment(SwingConstants.CENTER);
    	editDraft.setBounds(xPos, 5, 130, 30);
    	xPos+=editDraft.getWidth();
    	bar.add(editDraft);
    	
    	if (!main.getCont().checkEditStatus(main.getCurrentAcct()[1])){
    		editDraft.hide();
    	}
    	
    	

    	
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showPopup("CAccount", main.getCurrentAcct()[1]);
			}
		});
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	profile.setSize(30, 30);
    	profile.setLocation(main.getWidth()-4*profile.getWidth(), 5);
    	profile.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(profile);
    	
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct(); // Clear current account
    			main.showAllLogin(); // Show login page
    			main.setPrepage(false); // Reset prepage flag
    			main.flag = false; // Reset flag
    			main.closeCurrentDialogs(); // Close any open dialogs
			}
		});
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	exit.setSize(30, 30);
    	exit.setLocation(main.getWidth()-2*exit.getWidth(), 5);
    	exit.setHorizontalAlignment(SwingConstants.CENTER);
    	bar.add(exit);
    	
    }

}
