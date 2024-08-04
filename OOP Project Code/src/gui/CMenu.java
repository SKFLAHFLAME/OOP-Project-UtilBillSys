package gui;

import javax.swing.*;

import controller.MainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CMenu extends JPanel{
    MainFrame main;
	private JButton btnShowHistoryScreen;
	private JButton btnViewCurrentDraft;
	private JPanel panel;
	private JLabel lblBackground;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/data/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/data/background.jpg"));
	private JLabel lblLogo;
	private JTextArea txtrCurrentPrices;
	private JLabel lblPsGroup;
	private JLabel lblHome;
	private JLabel label;
	private JTextArea txtrCurrentBill;
	private JTextArea txtrDate;

    public CMenu(MainFrame main){
    	setBackground(new Color(135, 206, 250));
        this.main = main;
        this.setLayout(null);
        main.setSize(1020,720);
        
        TaskBar bar = new TaskBar(this, main);
        
        this.panel = new JPanel();
        panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
        this.panel.setBounds(229, 57, 552, 599);
        add(this.panel);
        this.panel.setLayout(null);
        
        this.lblLogo = new JLabel("logo");
        this.lblLogo.setBounds(162, 39, 90, 90);
        logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
        this.panel.add(this.lblLogo);
        
        this.txtrCurrentPrices = new JTextArea();
        txtrCurrentPrices.setEditable(false);
//        txtrCurrentPrices.setOpaque(false);
        this.txtrCurrentPrices.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentPrices.setText("Current Prices");
        this.txtrCurrentPrices.setBounds(12, 162, 300, 296);
        this.panel.add(this.txtrCurrentPrices);
        
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
        this.lblPsGroup.setBounds(264, 39, 128, 39);
        this.panel.add(this.lblPsGroup);
        
        this.lblHome = new JLabel("Home");
        this.lblHome.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblHome.setBounds(264, 91, 113, 31);
        this.panel.add(this.lblHome);
        
        this.label = new JLabel("-----------------");
        this.label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        this.label.setBounds(264, 76, 128, 16);
        this.panel.add(this.label);
        
        this.txtrCurrentBill = new JTextArea();
        this.txtrCurrentBill.setEditable(false);
        this.txtrCurrentBill.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentBill.setText("Current Bill : ");
        this.txtrCurrentBill.setBounds(324, 165, 216, 235);
        this.panel.add(this.txtrCurrentBill);
        
        this.txtrDate = new JTextArea();
        this.txtrDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtrDate.setText("Date:");
        this.txtrDate.setBounds(12, 13, 113, 90);
        this.panel.add(this.txtrDate);
        
        JButton btnLogOut = new JButton("Log out");
        btnLogOut.setBounds(211, 546, 115, 29);
        this.panel.add(btnLogOut);
        
        this.btnViewCurrentDraft = new JButton("Edit");
        btnViewCurrentDraft.setContentAreaFilled(false);
        btnViewCurrentDraft.setOpaque(true);
        this.btnViewCurrentDraft.setBackground(new Color(30, 144, 255));
        this.btnViewCurrentDraft.setBounds(456, 413, 84, 45);
        this.panel.add(this.btnViewCurrentDraft);
        btnViewCurrentDraft.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		if (!main.getCont().hasDraft(main.getCurrentAcct()[1])){
            		main.setPrepage(true);
            		main.showAddMeterReading();
            	}
        		else {
        			main.setPrepage(false);
        			main.showEditDraft();
        		}
        		
        	}
        });
        btnViewCurrentDraft.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        
        this.btnShowHistoryScreen = new JButton("View");
        this.btnShowHistoryScreen.setBounds(324, 413, 84, 45);
        this.panel.add(this.btnShowHistoryScreen);
        btnShowHistoryScreen.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[][][] ur = main.getCont().getUserReading(main.getCurrentAcct()[1]);
        		if (ur.length!=0){
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
        btnShowHistoryScreen.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnLogOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.clearCurrentAcct();
        		main.showAllLogin();
        	}
        });
        
        this.lblBackground = new JLabel("");
        this.lblBackground.setBounds(0, 0, 1000, 700);
        lblBackground.setSize(main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
        add(this.lblBackground);
    }
}
