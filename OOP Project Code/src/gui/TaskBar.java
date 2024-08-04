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

import controller.MainFrame;

public class TaskBar {
	MainFrame main;
	private JPanel panel;
	private Font font = new Font("Trebuchet MS", Font.PLAIN, 24);
	private Font selected = new Font("Trebuchet MS", Font.BOLD, 25);
	private String currentPanel;
	
	public TaskBar(JPanel panel, MainFrame m){
		main=m;
		this.panel = panel;
		currentPanel = panel.getClass().getSimpleName();
		switch (main.getCurrentAcct()[0]) {
		case "A":
			adminBar();
			break;
			
		case "S":
			staffBar();
			break;
			
		case "C":
			customerBar();
			break;

		default:
			main.showAllLogin();
			break;
		}
		
		
	}
	
	
	
	public void adminBar(){
    	String spacer = "  ";
    	int gap = 24;
 
    	JPanel bar = new JPanel();
    	bar.setLayout(new FlowLayout(FlowLayout.LEADING));
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(255,50,50, 200));
    	panel.add(bar);
    	
    	JLabel editUtil = new JLabel("Edit Utility");
    	editUtil.setToolTipText("Edit Utility Details");
    	editUtil.setForeground(Color.WHITE);
    	editUtil.setFont(font);
    	editUtil.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showEditUtility();
    		}
    	});
    	bar.add(editUtil);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel editStaff = new JLabel("Edit Staff");
    	editStaff.setToolTipText("Add/Edit Staff Accounts");
    	editStaff.setForeground(Color.WHITE);
    	editStaff.setFont(font);
    	editStaff.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showAllStaff();
    		}
    	});
    	bar.add(editStaff);
    	
    	
    	bar.add(new JLabel(spacer));
    	
    	
    	JLabel viewCust = new JLabel("View Customers");
    	viewCust.setToolTipText("View All Customer Accounts");
    	viewCust.setForeground(Color.WHITE);
    	viewCust.setFont(font);
    	viewCust.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showAllCustomers();
    		}
    	});
    	bar.add(viewCust);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel viewBiils = new JLabel("View Bills");
    	viewBiils.setToolTipText("View All Bills by Date");
    	viewBiils.setForeground(Color.WHITE);
    	viewBiils.setFont(font);
    	viewBiils.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showAllBills();
    		}
    	});
    	bar.add(viewBiils);
    	

    	
    	//spacer
    	JLabel[] labels = new JLabel[gap];
    	for (JLabel x: labels){
    		x = new JLabel(spacer);
    		bar.add(x);
    	}
    	bar.add(new JLabel("  "));
    	
    	
    	
    	JLabel calendar = new JLabel();
    	calendar.setToolTipText("Edit System Date");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showEditDate();
			}
		});
    	ImageIcon calicon = new ImageIcon(this.getClass().getResource("/data/calendar.png"));
    	calicon.setImage(calicon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    	calendar.setIcon(calicon);
    	bar.add(calendar);

    	
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showStaffAccount(main.getCurrentAcct()[1]);
			}
		});
    	ImageIcon proicon = new ImageIcon(this.getClass().getResource("/data/profile.png"));
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	bar.add(profile);
    	
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct();
    			main.showAllLogin();
    			main.setPrepage(false);
    			main.flag = false;
			}
		});
    	ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("/data/logout.png"));
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	bar.add(exit);
    	
    }
	
	
	
	
	
	public void staffBar(){
    	String spacer = "  ";
    	int gap = 37;
    	
    	JPanel bar = new JPanel();
    	bar.setLayout(new FlowLayout(FlowLayout.LEADING));
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(100,60,200, 200));
    	panel.add(bar);
    	
    	JLabel editUtil = new JLabel("Edit Utility");
    	editUtil.setToolTipText("Edit Utility Details");
    	editUtil.setForeground(Color.WHITE);
    	editUtil.setFont(font);
    	editUtil.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showEditUtility();
    		}
    	});
    	bar.add(editUtil);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel viewCust = new JLabel("View Customers");
    	viewCust.setToolTipText("View All Customer Accounts");
    	viewCust.setForeground(Color.WHITE);
    	viewCust.setFont(font);
    	viewCust.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showAllCustomers();
    		}
    	});
    	bar.add(viewCust);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel viewBiils = new JLabel("View Bills");
    	viewBiils.setToolTipText("View All Bills by Months");
    	viewBiils.setForeground(Color.WHITE);
    	viewBiils.setFont(font);
    	viewBiils.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			main.showAllBills();
    		}
    	});
    	bar.add(viewBiils);
    	

    	
    	//spacer
    	JLabel[] labels = new JLabel[gap];
    	for (JLabel x: labels){
    		x = new JLabel(spacer);
    		bar.add(x);
    	}
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.showStaffAccount(main.getCurrentAcct()[1]);
			}
		});
    	ImageIcon proicon = new ImageIcon(this.getClass().getResource("/data/profile.png"));
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	bar.add(profile);
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct();
    			main.showAllLogin();
    			main.setPrepage(false);
    			main.flag = false;
			}
		});
    	ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("/data/logout.png"));
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	bar.add(exit);
    	
    }
	
	
	
	
	
	public void customerBar(){
    	String spacer = "  ";
    	int gap = 44;
    	
    	
    	JPanel bar = new JPanel();
    	bar.setLayout(new FlowLayout(FlowLayout.LEADING));
    	bar.setBounds(0, 0, main.getWidth(), 40);
    	bar.setBackground(new Color(0,75,200, 200));
    	panel.add(bar);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel home = new JLabel("Home");
    	home.setToolTipText("View Bill History");
    	home.setForeground(Color.WHITE);
    	home.setFont(font);
    	if(currentPanel.equals("CMenu")){
    		home.setFont(selected);
    		gap = gap-1;
    	}
    	else{
    		home.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showCustMenu();
        		}
        	});
    	}
    	bar.add(home);
    	
    	bar.add(new JLabel(spacer));
    	
    	
    	JLabel viewHist = new JLabel("View History");
    	viewHist.setToolTipText("View Bill History");
    	viewHist.setForeground(Color.WHITE);
    	viewHist.setFont(font);
    	if(currentPanel.equals("ViewHistoryScreen")){
    		viewHist.setFont(selected);
    		gap = gap-2;
    	}
    	else{
    		viewHist.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			main.showViewHistoryScreen();
        		}
        	});
    	}
    	bar.add(viewHist);
    	
    	bar.add(new JLabel(spacer));
    	
    	JLabel editDraft = new JLabel("Edit Draft");
    	editDraft.setToolTipText("Edit Month's Bill");
    	editDraft.setForeground(Color.WHITE);
    	editDraft.setFont(font);
    	if (currentPanel.equals("EditDraft")){
    		editDraft.setFont(selected);
    		gap = gap-2;
    	}
    	else {
    		editDraft.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e){
    			if (!main.getCont().hasDraft(main.getCurrentAcct()[1])){
            		main.setPrepage(true);
            		main.showAddMeterReading();
            	}
        		else {
        			main.setPrepage(false);
        			main.showEditDraft();
        		}
    		}
    	});}
    	bar.add(editDraft);
    	
    	
    	

    	
    	//spacer
    	JLabel[] labels = new JLabel[gap];
    	for (JLabel x: labels){
    		x = new JLabel(spacer);
    		bar.add(x);
    	}

    	
    	
    	
    	JLabel profile = new JLabel();
    	profile.setToolTipText("View/Edit Current Account");
//    	profile.setBounds(bar.getWidth()-40, 0, 25, 25);
    	profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//show Customer Account
			}
		});
    	ImageIcon proicon = new ImageIcon(this.getClass().getResource("/data/profile.png"));
    	proicon.setImage(proicon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    	profile.setIcon(proicon);
    	bar.add(profile);
    	
    	
    	
    	JLabel exit = new JLabel();
    	exit.setToolTipText("Log Out");
//    	exit.setBounds(bar.getWidth()-40, 0, 25, 25);
    	exit.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
				main.clearCurrentAcct();
    			main.showAllLogin();
    			main.setPrepage(false);
    			main.flag = false;
			}
		});
    	ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("/data/logout.png"));
    	exitIcon.setImage(exitIcon.getImage().getScaledInstance(40,35, Image.SCALE_DEFAULT));
    	exit.setIcon(exitIcon);
    	bar.add(exit);
    	
    }

}
