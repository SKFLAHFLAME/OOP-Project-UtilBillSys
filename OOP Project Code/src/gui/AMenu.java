package gui;

import javax.swing.*;

import controller.MainFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AMenu extends JPanel{
    MainFrame main;
    private JLabel lblAdminPage;
    
    public AMenu(MainFrame m){
        this.main = m;
        this.setLayout(null);
        
        this.lblAdminPage = new JLabel("Admin Page");
        this.lblAdminPage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showLogin();
        	}
        });
        this.lblAdminPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.lblAdminPage.setBounds(122, 54, 204, 44);
        add(this.lblAdminPage);



    }
}
