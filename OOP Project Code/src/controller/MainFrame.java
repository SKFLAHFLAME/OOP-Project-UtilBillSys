package controller;

import gui.*;
import java.awt.CardLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private CardLayout card;
    private Controller cont;

    public MainFrame(){
        this.setTitle("Utility Billing System");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.cont = new Controller();
        
        card = new CardLayout();    
        this.setLayout(card);
        
        this.showLogin();

        this.setVisible(true);
    }

    public void showMenu(){
        Menu m = new Menu(this);
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





    public Controller getCont() {
        return cont;
    }

    
}
