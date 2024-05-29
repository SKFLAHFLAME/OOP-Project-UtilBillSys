package controller;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class MainFrame extends JFrame{
    private CardLayout card;

    public MainFrame(){
        this.setTitle("Utility Bill System");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        card = new CardLayout();
        this.setLayout(card);

        this.setVisible(true);
    }

    public void showMenu(){
        Menu m = new Menu();

    }

    public static void main(String[] args) {
        MainFrame gui = new MainFrame();
    }

}
