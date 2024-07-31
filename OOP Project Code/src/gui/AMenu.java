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
    private JButton btnEditUtilities;
    private JButton btnViewBills;
    private JButton btnEditStaff;
    private JButton btnAccount;
    private JButton btnEditDate;
    private JButton btnViewBills_1;
    
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
        		main.showAllLogin();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnLogOut.setBounds(170, 262, 97, 25);
        add(this.btnLogOut);
        
        this.btnEditUtilities = new JButton("Edit Utilities");
        this.btnEditUtilities.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.setPrepage(true);
        		main.showEditUtility();
        		
        	}
        });
        this.btnEditUtilities.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnEditUtilities.setBounds(135, 105, 128, 59);
        add(this.btnEditUtilities);
        
        this.btnViewBills = new JButton("View Customers");
        this.btnViewBills.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.showAllCustomers();
        	}
        });
        this.btnViewBills.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewBills.setBounds(273, 106, 165, 29);
        add(this.btnViewBills);
        
        this.btnEditStaff = new JButton("Edit Staff");
        this.btnEditStaff.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showAllStaff();
        	}
        });
        this.btnEditStaff.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnEditStaff.setBounds(12, 105, 113, 56);
        add(this.btnEditStaff);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.setPrepage(true);
        		main.showStaffAccount(main.getCurrentAcct()[1]);
        	}
        });
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnAccount.setBounds(321, 57, 97, 25);
        add(this.btnAccount);
        
        this.btnEditDate = new JButton("Edit Date");
        this.btnEditDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showEditDate();
        	}
        });
        this.btnEditDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.btnEditDate.setBounds(12, 57, 97, 25);
        add(this.btnEditDate);
        
        this.btnViewBills_1 = new JButton("View Bills");
        this.btnViewBills_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		main.showAllBills();
        	}
        });
        this.btnViewBills_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewBills_1.setBounds(273, 141, 167, 23);
        add(this.btnViewBills_1);



    }
}
