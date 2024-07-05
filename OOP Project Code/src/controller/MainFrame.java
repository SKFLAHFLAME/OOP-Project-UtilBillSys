package controller;

import gui.*;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;

// public class MainFrame extends JFrame{
//     private CardLayout card;
//     private Controller cont;
//     private boolean prepage=false;
//     private String[] currentAcct = new String[2];//( S/C , Username)
//     public boolean flag;
//     private AddFrame frame;
//     private EditUtility eu;
// 	private ViewDraft vd;
// 	private EditDraft ed;
// 	private EditMeterReading emr;

//     public MainFrame(){
        
//         this.setTitle("Utility Billing System");
//         this.setSize(400,300);
//         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//         this.setLocationRelativeTo(null);
            
//         this.cont = new Controller();
        
            
//         card = new CardLayout();
//         this.setLayout(card);
            
//         this.showLogin();
//         getCont().initialiseUsers();
            
//         this.setVisible(true);
        
//     }

//     public void showCustMenu(){
//         CMenu m = new CMenu(this);
//         add(m, "menu");
//         card.show(this.getContentPane(), "menu");
//     }
//     public void showAdminMenu(){
//         AMenu m = new AMenu(this);
//         add(m, "menu");
//         card.show(this.getContentPane(), "menu");
//     }
//     public void showStaffMenu(){
//         SMenu m = new SMenu(this);
//         add(m, "menu");
//         card.show(this.getContentPane(), "menu");
//     }

//     public void showLogin(){
//         Login l = new Login(this);
//         add(l, "login");
//         card.show(this.getContentPane(), "login");
//     }

//     public void showStaffLogin(){
//         SLogin sl = new SLogin(this);
//         add(sl, "Slogin");
//         card.show(this.getContentPane(), "Slogin");
//     }
    
//     public void showSignUp(){
//         SignUp su = new SignUp(this);
//         add(su, "Signup");
//         card.show(this.getContentPane(), "Signup");
//     }
//     public void showSSignUp(){
//         CreateStaff su = new CreateStaff(this);
//         add(su, "SSignup");
//         card.show(this.getContentPane(), "SSignup");
//     }
//     public void showEditUtility(){
//         eu=new EditUtility(this);
//         add(eu,"Util");
//         card.show(this.getContentPane(), "Util");
//     }
//     public void showViewDraft(){
// 		vd=new ViewDraft(this);
// 		add(vd, "Draft");
// 		card.show(this.getContentPane(), "Draft");
// 	}
//     public void showEditDraft(){
//     	ed=new EditDraft(this);
//     	add(ed, "Edit");
//     	card.show(this.getContentPane(), "Edit");
//     }
//     public void showEditMeterReading(){
//     	emr=new EditMeterReading(this);
//     	add(emr, "EditMeterReading");
//     	card.show(this.getContentPane(), "EditMeterReading");
//     }
//     public void showAddFrame(){
//     	frame = new AddFrame(this);
//     	frame.setVisible(true);
//     }
//     public void closeAddFrame(){
//     	try {
//     		frame.dispose();
// 		} catch (Exception e) {
// 			return;
// 		}
    	
//     }





//     public Controller getCont() {
//         return cont;
//     }

// 	public boolean getPrepage() {
// 		return prepage;
// 	}

// 	public void setPrepage(boolean prepage) {
// 		this.prepage = prepage;
// 	}

// 	public String[] getCurrentAcct() {
// 		return currentAcct;
// 	}

// 	public void setCurrentAcct(String type, String Uname) {
// 		String[] x = {type,Uname};
// 		this.currentAcct = x;
// 	}
// 	public void clearCurrentAcct(){
// 		this.currentAcct[0] =null;
// 		this.currentAcct[1] = null;
// 	}

// 	public EditUtility getEu() {
// 		return eu;
// 	}
	

    

    
// }

public class MainFrame extends JFrame{
    private CardLayout card;
    private Controller cont;
    private boolean prepage=false;
    private String[] currentAcct = new String[2];//( S/C , Username)
    public boolean flag;
    private AddFrame frame;
    private EditUtility eu;
	private ViewDraft vd;
	private EditDraft ed;
	private EditMeterReading emr;

    public MainFrame(){
        
        this.setTitle("Utility Billing System");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
            
        this.cont = new Controller();
        
            
        card = new CardLayout();
        this.setLayout(card);
            
        this.showLogin();
        getCont().initialiseUsers();
            
        this.setVisible(true);
        
    }

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
    public void showViewDraft(){
		vd=new ViewDraft(this);
		add(vd, "Draft");
		card.show(this.getContentPane(), "Draft");
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
    public void showStaffAccount(){
    	SAccountPage ap=new SAccountPage(this);
    	this.add(ap,"AP");
    	card.show(getContentPane(), "AP");
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
	

    

    
}
