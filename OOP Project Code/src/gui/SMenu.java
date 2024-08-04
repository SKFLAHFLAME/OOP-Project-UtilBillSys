package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.event.AncestorListener;

public class SMenu extends JPanel{
    MainFrame main;
    private JLabel lblStaffPage;
    private JButton btnLogOut;
    private JButton btnEditUtilities;
    private JButton btnViewCustomer;
    private JButton btnAccount;
    private JButton btnViewBills;

    public SMenu(MainFrame m){
        this.main=m;
        this.setLayout(null);
        main.setSize(1020,720);
        
        this.lblStaffPage = new JLabel("Staff Page");
        this.lblStaffPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.lblStaffPage.setBounds(478, 135, 152, 61);
        add(this.lblStaffPage);
        
        this.btnLogOut = new JButton("Log Out");
        this.btnLogOut.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
                main.clearCurrentAcct();
        		main.showAllLogin();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnLogOut.setBounds(518, 522, 110, 33);
        add(this.btnLogOut);
        
        this.btnEditUtilities = new JButton("Edit Utilities");
        this.btnEditUtilities.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.setPrepage(false);
        		main.showEditUtility();
        		
        	}
        });
        this.btnEditUtilities.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnEditUtilities.setBounds(266, 334, 152, 61);
        add(this.btnEditUtilities);
        
        this.btnViewCustomer = new JButton("View Customer");
        this.btnViewCustomer.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.showAllCustomers();
        	}
        });
        this.btnViewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewCustomer.setBounds(498, 334, 152, 61);
        add(this.btnViewCustomer);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.setPrepage(false);
        		main.showStaffAccount(main.getCurrentAcct()[1]);
        	}
        });
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnAccount.setBounds(690, 159, 97, 25);
        add(this.btnAccount);
        
        this.btnViewBills = new JButton("View Bills");
        this.btnViewBills.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showAllBills();        	}
        });
        this.btnViewBills.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewBills.setBounds(734, 334, 110, 61);
        add(this.btnViewBills);
        TaskBar bar = new TaskBar(this, main);
    }
    
    
    
    
}
