package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SMenu extends JPanel{
    MainFrame main;
    private JLabel lblStaffPage;
    private JButton btnLogOut;
    private JButton btnEditUtilities;
    private JButton btnViewCustomer;
    private JButton btnAccount;

    public SMenu(MainFrame m){
        this.main=m;
        this.setLayout(null);
        main.setSize(500,340);
        
        this.lblStaffPage = new JLabel("Staff Page");
        this.lblStaffPage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		main.showLogin();
        	}
        });
        this.lblStaffPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
        this.lblStaffPage.setBounds(164, 13, 152, 61);
        add(this.lblStaffPage);
        
        this.btnLogOut = new JButton("Log Out");
        this.btnLogOut.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
                main.clearCurrentAcct();
        		main.showStaffLogin();
        	}
        });
        this.btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnLogOut.setBounds(195, 256, 110, 33);
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
        this.btnEditUtilities.setBounds(26, 131, 152, 61);
        add(this.btnEditUtilities);
        
        this.btnViewCustomer = new JButton("View Customer");
        this.btnViewCustomer.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		main.showAllCustomers();
        	}
        });
        this.btnViewCustomer.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.btnViewCustomer.setBounds(307, 131, 152, 61);
        add(this.btnViewCustomer);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.setPrepage(false);
        		main.showStaffAccount(main.getCurrentAcct()[1]);
        	}
        });
        this.btnAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.btnAccount.setBounds(351, 37, 97, 25);
        add(this.btnAccount);
    }
}
