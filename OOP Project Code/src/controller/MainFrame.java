package controller;

import gui.*;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame implements WindowListener{
    private CardLayout card;
    private Controller cont;
    private boolean prepage=false;
    private String[] currentAcct = new String[2];//( S/C , Username)
    public boolean flag;
    private AddFrame frame;
    private EditUtility eu;
	private EditDraft ed;
	private EditMeterReading emr;
	private AddMeterReading amr;

    public MainFrame(){
        
        this.setTitle("Utility Billing System");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
            
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.cont = new Controller();
        
            
        card = new CardLayout();
        this.setLayout(card);
        this.addWindowListener(this);
            
        this.showLogin();
        getCont().initialiseItems();
            
        this.setVisible(true);
        
    }
    
    //! Show Panels

    public void showCustMenu(){
        CMenu m = new CMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }
    public void showAdminMenu(){
        AMenu m = new AMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }
    public void showStaffMenu(){
        SMenu m = new SMenu(this);
        add(m, "menu");
        card.show(this.getContentPane(), "menu");
    }

    public void showLogin(){
        Login l = new Login(this);
        add(l, "login");
        card.show(this.getContentPane(), "login");
    }

    public void showStaffLogin(){
        SLogin sl = new SLogin(this);
        add(sl, "Slogin");
        card.show(this.getContentPane(), "Slogin");
    }
    
    public void showSignUp(){
        SignUp su = new SignUp(this);
        add(su, "Signup");
        card.show(this.getContentPane(), "Signup");
    }
    public void showSSignUp(){
        CreateStaff su = new CreateStaff(this);
        add(su, "SSignup");
        card.show(this.getContentPane(), "SSignup");
    }
    public void showEditUtility(){
        eu=new EditUtility(this);
        add(eu,"Util");
        card.show(this.getContentPane(), "Util");
    }
    public void showEditDraft(){
    	ed=new EditDraft(this);
    	add(ed, "Edit");
    	card.show(this.getContentPane(), "Edit");
    }
    public void showEditMeterReading(){
    	emr=new EditMeterReading(this);
    	add(emr, "EditMeterReading");
    	card.show(this.getContentPane(), "EditMeterReading");
    }
    public void showAddMeterReading(){
    	amr=new AddMeterReading(this);
    	add(amr, "AddMeterReading");
    	card.show(this.getContentPane(), "AddMeterReading");
    }
    public void showStaffAccount(String id){
    	SAccountPage ap=new SAccountPage(this, id);
    	this.add(ap,"AP");
    	card.show(getContentPane(), "AP");
    }
    public void showAllStaff() {
    	ViewStaff vs = new ViewStaff(this);
    	add(vs,"vs");
    	card.show(getContentPane(), "vs");
    }
    public void showAllCustomers() {
    	ViewAllCustomer vs = new ViewAllCustomer(this);
    	add(vs,"vs");
    	card.show(getContentPane(), "vs");
    }
    
    public void showAddFrame(){
    	frame = new AddFrame(this);
    	frame.setVisible(true);
    }
    public void closeAddFrame(){
    	try {
    		frame.dispose();
		} catch (Exception e) {
			return;
		}
    	
    }





    public Controller getCont() {
        return cont;
    }

	public boolean getPrepage() {
		return prepage;
	}

	public void setPrepage(boolean prepage) {
		this.prepage = prepage;
	}

	public String[] getCurrentAcct() {
		return currentAcct;
	}

	public void setCurrentAcct(String type, String Uname) {
		String[] x = {type,Uname};
		this.currentAcct = x;
	}
	public void clearCurrentAcct(){
		this.currentAcct[0] =null;
		this.currentAcct[1] = null;
	}

	public EditUtility getEu() {
		return eu;
	}
	
	
	
	
	
	

	@Override
	public void windowOpened(WindowEvent e) {
		getCont().syncData();
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
	

    

    
}
