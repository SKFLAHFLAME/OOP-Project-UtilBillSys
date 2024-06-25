package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SMenu extends JPanel{
    MainFrame main;
    private JLabel lblStaffPage;

    public SMenu(MainFrame m){
        this.main=m;
        this.setLayout(null);
        
        this.lblStaffPage = new JLabel("Staff Page");
        this.lblStaffPage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		main.showLogin();
        	}
        });
        this.lblStaffPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.lblStaffPage.setBounds(139, 59, 152, 61);
        add(this.lblStaffPage);
    }

}
