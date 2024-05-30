package controller;

import javax.swing.JFrame;
import java.awt.CardLayout;
import gui.*;

public class MainFrame extends JFrame{
    private CardLayout card;
    private Controller cont;

    public MainFrame(){
        this.setTitle("UBS");
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





    public Controller getCont() {
        return cont;
    }

    public static void main(String[] args) {
        MainFrame gui = new MainFrame();
    }
}
