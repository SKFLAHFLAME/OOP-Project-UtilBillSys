package gui;

import javax.swing.*;

import controller.MainFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CMenu extends JPanel{
    MainFrame main;
    JLabel loc;

    public CMenu(MainFrame m){
        this.main = m;
        this.setLayout(null);
        
        this.loc = new JLabel("Customer");
        this.loc.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showLogin();
        	}
        });
        this.loc.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.loc.setBounds(152, 13, 151, 54);
        this.add(loc);
    }

}
