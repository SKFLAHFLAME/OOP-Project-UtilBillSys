package gui;

import javax.swing.*;

import controller.MainFrame;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CMenu extends JPanel{
    MainFrame main;
    JLabel loc;
	private JButton btnShowHistoryScreen;
	private JButton btnViewCurrentDraft;
	private JButton btnAccount;

    public CMenu(MainFrame main){
        this.main = main;
        this.setLayout(null);
        main.setSize(500,360);
        
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
        
        this.btnShowHistoryScreen = new JButton("Show History Screen");
        btnShowHistoryScreen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (main.getCont().hasDraft(main.getCurrentAcct()[1])){
        			main.showViewHistoryScreen();
        		}
        		else{
        			JOptionPane.showMessageDialog(null, 
                            "No History", 
                            "History", 
                            JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
        btnShowHistoryScreen.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnShowHistoryScreen.setBounds(109, 83, 194, 54);
        add(btnShowHistoryScreen);
        
        this.btnViewCurrentDraft = new JButton("View Current Draft");
        btnViewCurrentDraft.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		if (main.getCont().hasDraft(main.getCurrentAcct()[1])){
            		main.setPrepage(true);
            		main.showAddMeterReading();
            	}
        		else {
        			main.setPrepage(false);
        			main.showEditDraft();
        		}
        		
        	}
        });
        btnViewCurrentDraft.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnViewCurrentDraft.setBounds(109, 176, 194, 54);
        add(btnViewCurrentDraft);
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.clearCurrentAcct();
        		main.showLogin();
        	}
        });
        btnLogOut.setBounds(152, 271, 115, 29);
        add(btnLogOut);
        
        this.btnAccount = new JButton("Account");
        this.btnAccount.setBounds(341, 35, 97, 25);
        add(this.btnAccount);
    }
}
