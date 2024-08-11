package controller;

import gui.*;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf;




public class MainFrame extends JFrame implements WindowListener{
    private CardLayout card;
    private Controller cont;
    private boolean prepage=false;
    private String[] currentAcct = new String[2];//( S/C , Username)
    public boolean flag;
    private AddFrame frame;
    private PopupDialog pop;
    private EditUtility eu;
	private EditDraft ed;
	private CMenu m;
	private ViewHistoryScreen vhs;
	private ImageIcon logo;
	private String backgroundFP = "/images/background.jpg";
	private String logoFP = "/images/logo.png";
	



	public MainFrame(){
        
        this.setTitle("Utility Billing System");
//        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1050,720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        
        this.cont = new Controller();
//    	getCont().setSystemDate("7", "2020");
        
            
        card = new CardLayout();
        this.setLayout(card);
        this.addWindowListener(this);
            
        this.showAllLogin();
        getCont().initialiseItems();
            
        this.setVisible(true);
        
    }
    
    //! Show Panels

	// Display customer menu
    public void showCustMenu(){
        m = new CMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }
    
    // Display admin menu
    public void showAdminMenu(){
        AMenu m = new AMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }
    
    // Display staff menu
    public void showStaffMenu(){
        SMenu m = new SMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }
    
    // Display all login panel
    public void showAllLogin(){
        AllLogin l = new AllLogin(this);
        add(l, "login");
        card.show(this.getContentPane(), "login");
    }
    
    // Display sign-up panel
    public void showSignUp(){
        SignUp su = new SignUp(this);
        add(su, "Signup");
        card.show(this.getContentPane(), "Signup");
    }
    
    // Display reset details panel
    public void showResetDetails(){
        ResetDetails rd = new ResetDetails(this);
        add(rd, "RD");
        card.show(this.getContentPane(), "RD");
    }
    
    // Display edit utility panel
    public void showEditUtility(){
        eu=new EditUtility(this);
        add(eu,"Util");
        card.show(this.getContentPane(), "Util");
    }
    
    // Display edit draft panel
    public void showEditDraft(String user){
    	ed=new EditDraft(this, user);
    	add(ed, "Edit");
    	card.show(this.getContentPane(), "Edit");
    }
    
    // Display all customers panel
    public void showAllCustomers() {
    	ViewAllCustomer vs = new ViewAllCustomer(this);
    	add(vs,"vs");
    	card.show(getContentPane(), "vs");
    }
    
    // Display view history screen
    public void showViewHistoryScreen(){
    	vhs = new ViewHistoryScreen(this);
    	add(vhs, "vhs");
    	card.show(getContentPane(), "vhs");
    }
    
    // Display all bills panel
	public void showAllBills() {
    	ViewAllBills vs = new ViewAllBills(this);
    	add(vs,"vs");
    	card.show(getContentPane(), "vs");
    }
    
	
	
	//!Frame section
    
    /**
     * 
     * @author samue
     * @param panel
     * @param parameters : for respective panels - parameters needed
     * @param Staff Account Page : "SAccount" - ID
     * @param Customer Account Page : "CAccount" - UserName
     * @param Edit Date: "Date" - 
     * @param Edit Meter Reading : "EditMeterReading" - Username, Readingname, new Value
     * @param Create Staff : "SSignUp" - ""
     * @return open Respective dialogs
     * 
     */
    public void showPopup(String panel, CharSequence parameters){
    	closeCurrentDialogs();
    	pop = new PopupDialog(this, panel,parameters);
    }
    
    // Display add frame dialog
    public void showAddFrame(){
    	closeCurrentDialogs();
    	frame = new AddFrame(this);
    	frame.setVisible(true);
    }
    
     // Close add frame dialog if it exists
    public void closeAddFrame(){
    	try {
    		frame.dispose();
		} catch (Exception e) {
			return;
		}
    	
    }
    
     // Close popup dialog if it exists
    public void closePopup(){
    	try {
    		pop.dispose();
		} catch (Exception e) {
			return;
		}
    }
    
    
    // Close both add frame and popup dialog
    public void closeCurrentDialogs(){
    	closeAddFrame();
    	closePopup();
    	
    }
    
    // Add a task bar to the specified panel
    public void addTaskBar(JPanel panel){
    	TaskBar bar = new TaskBar(panel, this);
    }




    // Get controller instance
    public Controller getCont() {
        return cont;
    }

    // Get the prepage flag
	public boolean getPrepage() {
		return prepage;
	}

	// Set the prepage flag
	public void setPrepage(boolean prepage) {
		this.prepage = prepage;
	}

    // Get current account information
	public String[] getCurrentAcct() {
		return currentAcct;
	}

    // Set current account information
	public void setCurrentAcct(String type, String Uname) {
		String[] x = {type,Uname};
		this.currentAcct = x;
	}
	
    // Clear current account information
	public void clearCurrentAcct(){
		this.currentAcct[0] =null;
		this.currentAcct[1] = null;
	}

    // Get EditUtility instance
	public EditUtility getEu() {
		return eu;
	}

    // Get logo icon
	public ImageIcon getLogo() {
		return logo;
	}

	// Set logo icon
	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}
    
    // Get background file path
	public String getBackgroundFP() {
		return backgroundFP;
	}

    // Get logo file path
	public String getLogoFP() {
		return logoFP;
	}
    // Get EditDraft instance
	public EditDraft getEd() {
		return ed;
	}
	
	

	@Override
	public void windowOpened(WindowEvent e) {
		getCont().syncData();
		
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/logo.png")).getImage());
		System.out.println("Added");
	}
 
	@Override
	public void windowClosing(WindowEvent e) {
        getCont().saveData();
        System.out.println("Saved");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Exited");
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowActivated(WindowEvent e) {

		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
		
	}
	
    // Main method to run the application
	public static void main(String[] args)
	{
		MainFrame ex = new MainFrame();
	}

    

    
}
