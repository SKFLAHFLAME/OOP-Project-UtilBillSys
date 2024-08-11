package gui;

import java.awt.CardLayout;

import javax.swing.JDialog;

import controller.MainFrame;

public class PopupDialog extends JDialog{
    // Reference to the MainFrame object
	MainFrame main;
	private CardLayout card;
	
    // Constructor to initialize the dialog with the main frame, panel type, and parameters
	public PopupDialog(MainFrame m, String panel, CharSequence param) {
		main = m;
		this.setSize(500,400);
		this.setVisible(true);
		card = new CardLayout();
		this.setLayout(card);
		String[] items = param.toString().split(",");
		this.setLocationRelativeTo(main);
		this.setResizable(false);
		
		
		// Switch-case to determine which panel to display based on the 'panel' parameter
        switch (panel) {
            // If the panel is "CAccount", show the customer account
            case "CAccount":
                showCustAccount(items[0]); // Pass the first item as an argument
                break;
                
            // If the panel is "SAccount", show the staff account
            case "SAccount":
                showStaffAccount(items[0]); // Pass the first item as an argument
                break;
                
            // If the panel is "Date", show the edit date screen
            case "Date":
                showEditDate();
                break;
                
            // If the panel is "EditMeterReading", show the edit meter reading screen
            case "EditMeterReading":
                showEditMeterReading(items[0], items[1], items[2]); // Pass three items as arguments
                break;
            
            // If the panel is "SSignUp", show the staff sign-up screen
            case "SSignUp":
                showSSignUp();
                break;

            // Default case to handle any other values (no action is taken)
            default:
                break;
        }
    }


	 // Method to show the staff account page
    public void showStaffAccount(String id) {
        SAccountPage ap = new SAccountPage(this, id); // Create a new SAccountPage with the given ID
        this.add(ap, "AP"); // Add the page to the CardLayout with the identifier "AP"
        card.show(getContentPane(), "AP"); // Display the page
    }
    
    // Method to show the customer account page
    public void showCustAccount(String id) {
        CAccountPage ap = new CAccountPage(this, id); // Create a new CAccountPage with the given ID
        this.add(ap, "AP"); // Add the page to the CardLayout with the identifier "AP"
        card.show(getContentPane(), "AP"); // Display the page
    }
    
    // Method to show the edit system date page
    public void showEditDate() {
        EditSysDate vs = new EditSysDate(this); // Create a new EditSysDate page
        add(vs, "vs"); // Add the page to the CardLayout with the identifier "vs"
        card.show(getContentPane(), "vs"); // Display the page
    }
    
    // Method to show the edit meter reading page
    public void showEditMeterReading(String user, String preName, String mr) {
        EditMeterReading emr = new EditMeterReading(this, preName, mr, user); // Create a new EditMeterReading page with the provided parameters
        add(emr, "EditMeterReading"); // Add the page to the CardLayout with the identifier "EditMeterReading"
        card.show(this.getContentPane(), "EditMeterReading"); // Display the page
    }
    
    // Method to show the staff sign-up page
    public void showSSignUp() {
        CreateStaff su = new CreateStaff(this); // Create a new CreateStaff page
        add(su, "SSignup"); // Add the page to the CardLayout with the identifier "SSignup"
        card.show(this.getContentPane(), "SSignup"); // Display the page
    }
}