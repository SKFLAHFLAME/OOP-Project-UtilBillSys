package gui;

import javax.sound.midi.VoiceStatus;
import javax.swing.*;

import controller.MainFrame;
import data.Readings;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.FlowLayout;

//Panel class for the main menu interface of the application
public class CMenu extends JPanel{
    MainFrame main;
    private String[] date;
	private JButton btnShowHistoryScreen;
	private JButton btnViewCurrentDraft;
	private JPanel panel;
	private JLabel lblBackground;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private JLabel lblLogo;
	private JTextArea txtrCurrentPrices;
	private JLabel lblPsGroup;
	private JLabel lblHome;
	private JLabel label;
	private JTextArea txtrCurrentBill;
	private JTextArea txtrDate;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblAccessCustomer;
	private JLabel lblAcct;
	private JPanel panel_1;

    // Constructor initializing the CMenu panel
    public CMenu(MainFrame main){
    	setBackground(new Color(135, 206, 250));
        this.main = main;
        this.setLayout(null);
        main.setSize(1020,720);
        main.addTaskBar(this);
        
        this.panel = new JPanel();
        panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
        this.panel.setBounds(204, 57, 606, 600);
        add(this.panel);
        this.panel.setLayout(null);
        
        this.lblLogo = new JLabel("logo");
        this.lblLogo.setBounds(189, 39, 90, 90);
        logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
        this.panel.add(this.lblLogo);
        
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
        this.lblPsGroup.setBounds(291, 39, 128, 39);
        this.panel.add(this.lblPsGroup);
        
        this.lblHome = new JLabel("Home");
        this.lblHome.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblHome.setBounds(291, 91, 113, 31);
        this.panel.add(this.lblHome);
        
        this.label = new JLabel("-----------------");
        this.label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.label.setBounds(291, 74, 128, 16);
        this.panel.add(this.label);
        
        this.txtrDate = new JTextArea();
        txtrDate.setEditable(false);
        this.txtrDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrDate.setText("Date:");
        this.txtrDate.setBounds(12, 39, 113, 63);
        this.panel.add(this.txtrDate);
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.clearCurrentAcct();
        		main.showAllLogin();
        		main.closeCurrentDialogs();
        	} 
        });
        btnLogOut.setBounds(248, 536, 113, 39);
        this.panel.add(btnLogOut);
        
        this.btnViewCurrentDraft = new JButton("Edit");
        this.btnViewCurrentDraft.setBackground(new Color(219, 112, 147));
        this.btnViewCurrentDraft.setBounds(510, 413, 84, 45);
        this.panel.add(this.btnViewCurrentDraft);
        btnViewCurrentDraft.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
//        		if (!main.getCont().hasDraft(main.getCurrentAcct()[1])){
//            		main.setPrepage(true);
//            		main.showAddMeterReading();
//            	}
//        		else {
        			main.setPrepage(false);
        			main.showEditDraft(main.getCurrentAcct()[1]);      // Show draft editing screen
//        		}
        		
        	}
        });
        btnViewCurrentDraft.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        
        this.btnShowHistoryScreen = new JButton("View");
        this.btnShowHistoryScreen.setBounds(332, 413, 101, 45);
        this.panel.add(this.btnShowHistoryScreen);
        btnShowHistoryScreen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Show history screen or display warning if no history
        		String[][][] ur = main.getCont().getUserReading(main.getCurrentAcct()[1]);
        		if (ur.length!=0){
        			main.showViewHistoryScreen();
        		}
        		else{
        			JOptionPane.showMessageDialog(null, 
                            "No History", 
                            "History", 
                            JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
        btnShowHistoryScreen.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(332, 161, 262, 239);
        this.panel.add(this.scrollPane);
        
        this.txtrCurrentBill = new JTextArea();
        this.scrollPane.setViewportView(this.txtrCurrentBill);
        txtrCurrentBill.setEditable(false);
        this.txtrCurrentBill.setEditable(false);
        this.txtrCurrentBill.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentBill.setText("Current Bill : ");
        
        this.scrollPane_1 = new JScrollPane();
        this.scrollPane_1.setBounds(12, 161, 314, 297);
        this.panel.add(this.scrollPane_1);
        
        this.txtrCurrentPrices = new JTextArea();
        this.scrollPane_1.setViewportView(this.txtrCurrentPrices);
        txtrCurrentPrices.setEditable(false);
        //        txtrCurrentPrices.setOpaque(false);
        this.txtrCurrentPrices.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentPrices.setText("Current Prices");
        
        this.panel_1 = new JPanel();
        this.panel_1.setBackground(Color.WHITE);
        this.panel_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		panel_1.setBackground(SystemColor.control);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		panel_1.setBackground(Color.WHITE);
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
                // Show popup with account details
        		main.showPopup("CAccount", main.getCurrentAcct()[1]);
        	}
        });
        this.panel_1.setBounds(443, 39, 151, 55);
        panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.panel.add(this.panel_1);
        this.panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        this.lblAcct = new JLabel("Acct");
        this.panel_1.add(this.lblAcct);
        this.lblAcct.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        this.lblAcct.setHorizontalAlignment(SwingConstants.TRAILING);
        
        this.lblAccessCustomer = new JLabel("Access: Customer");
        this.panel_1.add(this.lblAccessCustomer);
        this.lblAccessCustomer.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        this.lblAccessCustomer.setHorizontalAlignment(SwingConstants.TRAILING);
                
                
        
        
        this.lblBackground = new JLabel("");
        this.lblBackground.setBounds(0, 0, 1000, 700);
        lblBackground.setSize(main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
        add(this.lblBackground);
        
        
        
        init();
    }
    
 // Method to initialize the data displayed on the menu
    public void init(){
        showDate(); // Display the current date
        showPrices(); // Display current utility prices
        showCurrentBill(); // Display the latest bill
        showAcctDetails(); // Display account details
        checkEditStatus(); // Check if editing is allowed
    }
    
    // Method to check if the current draft can be edited
    public void checkEditStatus(){
        if (!main.getCont().checkEditStatus(main.getCurrentAcct()[1])){
            btnViewCurrentDraft.setEnabled(false); // Disable the edit button if editing is not allowed
        }
    }
    
    // Method to display current utility prices
    public void showPrices(){
        Readings[] readings = main.getCont().getAllReadings();
        String text = "Utility Prices: \n";
        
        // Build the text for utility prices
        for (Readings r : readings){
            text += r.getUtilityName() + " : $" + r.getPrice() + "/" + r.getUnit() + '\n';
        }
        txtrCurrentPrices.setText(text); // Set the text in the current prices text area
    }
    
    // Method to display the latest bill
    public void showCurrentBill(){
        String[][] lastBill = main.getCont().getLastUserReading(main.getCurrentAcct()[1]);
        if (lastBill != null){
            String text = "Latest Bill: " + lastBill[0][2] + '\n';
            double total = 0;
            
            // Build the text for the current bill
            for (int i = 0; i < lastBill.length - 1; i++){
                text += lastBill[i + 1][0] + " : " + lastBill[i + 1][1] + "\n";
                total += Double.valueOf(lastBill[i + 1][2]);
            }
            text += "Total : $" + String.format("%.2f", total);
            txtrCurrentBill.setText(text); // Set the text in the current bill text area
        } else {
            txtrCurrentBill.setText("No History"); // Display message if no bill history
        }
    }
    
    // Method to display the current date
    public void showDate(){
        date = main.getCont().getSystemDate();
        String d = "Date: \n" + String.join(" / ", date);
        txtrDate.setText(d); // Set the text in the date text area
    }
    
    // Method to display account details
    public void showAcctDetails(){
        String text = "Welcome " + main.getCurrentAcct()[1];
        lblAcct.setText(text); // Set the text in the account label
    }
}