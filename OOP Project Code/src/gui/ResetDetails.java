package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.MainFrame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetDetails extends JPanel{
	String[] forgottenItems = {"Username", "Password"};
	MainFrame main;
	private JLabel lblBackground;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
	private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblPsGroup;
	private JLabel label;
	private JLabel lblReset;
	private JComboBox comboBox;
	private JTextField txtVerification;
	private JButton btnNext;
	private JButton btnBack;
	private JLabel lblIForgottenMy;
	private JLabel lblPleaseVerifyIts;
	private JLabel lblEmail;
	
	private boolean page2 = false;
	
	public ResetDetails(MainFrame m) {
		setBackground(new Color(135, 206, 250));
		this.main=m;
		this.setLayout(null);
		main.setSize(1020, 720);
		
		this.panel = new JPanel();
		panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
		this.panel.setBounds(261, 83, 500, 550);
		add(this.panel);
		this.panel.setLayout(null);
		
		this.lblPsGroup = new JLabel("PS Group");
		this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		this.lblPsGroup.setBounds(237, 40, 132, 33);
		this.panel.add(this.lblPsGroup);
		
		this.label = new JLabel("-----------------");
		this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		this.label.setBounds(237, 63, 141, 16);
		this.panel.add(this.label);
		
		this.lblReset = new JLabel("Reset");
		this.lblReset.setVerticalAlignment(SwingConstants.TOP);
		this.lblReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.lblReset.setBounds(237, 85, 87, 34);
		this.panel.add(this.lblReset);
		
		this.lblLogo = new JLabel("logo");
		this.lblLogo.setBounds(137, 29, 90, 90);
		logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(logo);
		this.panel.add(this.lblLogo);
		
		
		
		
		
		
		
		
		
		
		
		this.lblBackground = new JLabel("Background");
        lblBackground.setSize(main.getWidth(), main.getHeight());
		background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
		add(this.lblBackground);
		
		
		initPage1();
		
	}
	
	
	public void initPage1(){
		this.comboBox = new JComboBox(forgottenItems);
		this.comboBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.comboBox.setBounds(237, 233, 237, 43);
		this.panel.add(this.comboBox);
		
		
		this.txtVerification = new JTextField();
		this.txtVerification.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtVerification.setBounds(237, 332, 237, 43);
		this.panel.add(this.txtVerification);
		this.txtVerification.setColumns(10);
		
		
		
		this.btnNext = new JButton("Next");
		this.btnNext.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnNext.setBounds(342, 453, 132, 43);
		this.panel.add(this.btnNext);
		
		
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showAllLogin();
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(27, 453, 132, 43);
		this.panel.add(this.btnBack);
		
		
		
		this.lblIForgottenMy = new JLabel("I forgotten my: ");
		this.lblIForgottenMy.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		this.lblIForgottenMy.setBounds(27, 233, 206, 43);
		this.panel.add(this.lblIForgottenMy);
		
		
		
		this.lblPleaseVerifyIts = new JLabel("Please verify it's you!");
		this.lblPleaseVerifyIts.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		this.lblPleaseVerifyIts.setBounds(21, 332, 206, 43);
		this.panel.add(this.lblPleaseVerifyIts);
		
		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.lblEmail.setBounds(237, 301, 80, 33);
		this.panel.add(this.lblEmail);
	}
	
	
	
	
	
	
}
