package gui;

import javax.swing.*;

import controller.MainFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AMenu extends JPanel{
    MainFrame main;
    private JLabel lblAdminPage;
    private JButton btnLogOut;
    private JButton btnAddStaff;
    private JButton btnEditUtilities;
    private JButton btnViewBills;
    private JButton btnEditStaff;
    private JButton btnAccount;
    
    public AMenu(MainFrame m){
        this.main = m;
        this.setLayout(null);
        main.setSize(500,340);
        
        this.lblAdminPage = new JLabel("Admin Page");
        this.lblAdminPage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showLogin();
        	}
        });
        this.lblAdminPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.lblAdminPage.setBounds(125, 40, 204, 44);
        add(this.lblAdminPage);
        
        this.btnLogOut = new JButton("Log Out");
        this.btnLogOut.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
                main.clearCurrentAcct();
        		main.showStaffLogin();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnLogOut.setBounds(170, 262, 97, 25);
        add(this.btnLogOut);
        
        this.btnAddStaff = new JButton("Add Staff");
        this.btnAddStaff.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
        		main.showSSignUp();
        	}
        });
        this.btnAddStaff.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnAddStaff.setBounds(12, 105, 140, 25);
        add(this.btnAddStaff);
        
        this.btnEditUtilities = new JButton("Edit Utilities");
        this.btnEditUtilities.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.setPrepage(true);
        		main.showEditUtility();
        		
        	}
        });
        this.btnEditUtilities.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnEditUtilities.setBounds(170, 105, 140, 59);
        add(this.btnEditUtilities);
        
        this.btnViewBills = new JButton("View Bills");
        this.btnViewBills.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        	}
        });
        this.btnViewBills.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewBills.setBounds(321, 106, 117, 57);
        add(this.btnViewBills);
        
        this.btnEditStaff = new JButton("Edit Staff");
        this.btnEditStaff.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnEditStaff.setBounds(12, 136, 140, 25);
        add(this.btnEditStaff);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.setPrepage(true);
        		main.showStaffAccount();
        	}
        });
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnAccount.setBounds(321, 57, 97, 25);
        add(this.btnAccount);



    }
}
