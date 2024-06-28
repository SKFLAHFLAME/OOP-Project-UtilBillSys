package controller;

import gui.*;
import java.awt.CardLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private CardLayout card;
    private Controller cont;
    private boolean prepage=false;

    public MainFrame(){
        
        this.setTitle("Utility Billing System");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
            
        this.cont = new Controller();
        this.getCont().init_Readings();
        
            
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
        EditUtility eu = new EditUtility(this);
        add(eu,"Util");
        card.show(this.getContentPane(), "Util");
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

    

    
}
