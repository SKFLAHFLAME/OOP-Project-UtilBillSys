package controller;

import javax.swing.JFrame;
import gui.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        this.setTitle("UBS");
        this.setSize(400,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }


    public static void main(String[] args) {
        MainFrame gui = new MainFrame();
    }
}
