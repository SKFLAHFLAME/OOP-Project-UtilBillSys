package gui;

import javax.swing.JPanel;
import controller.MainFrame;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class AllLogin extends JPanel{
    // MainFrame object to access application logic
    MainFrame main;
    
    // UI components
    private ImageIcon logo;
    private ImageIcon background;
    private JPanel panel;
    private JLabel lblIcon;
    private JLabel lblLogin;
    private JButton btnLogin;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField textField;
    private JPasswordField passwordField;
    private JCheckBox chckbxShowPassword;
    private JLabel lblError;
    private JLabel lblPsGroup;
    private JLabel lblForgotPassword;
    private JLabel lblNoAccountSign;
    private JLabel lblBackGround;
    private JLabel label;
    
    // Constructor to initialize the login panel
    public AllLogin(MainFrame m){
        main = m;
        setBackground(new Color(135, 206, 250)); // Set background color for the panel
        setLayout(null); // Use null layout for custom positioning
        main.setSize(1020,720); // Set size of the main frame
        
        // Load and set icons for the logo and background
        logo = new ImageIcon(AllLogin.class.getResource(main.getLogoFP()));
        background = new ImageIcon(AllLogin.class.getResource(main.getBackgroundFP()));
        
        // Initialize and configure the panel
        this.panel = new JPanel();
        panel.setBackground(new Color((224.0f/255.0f),(224.0f/255.0f),(224.0f/255.0f), 0.95f));
        this.panel.setBounds(299, 82, 450, 550);
        add(this.panel);
        this.panel.setLayout(null);
        
        // Configure logo label
        this.lblIcon = new JLabel();
        this.lblIcon.setBounds(110, 29, 90, 90);
        logo.setImage(logo.getImage().getScaledInstance(lblIcon.getHeight(), lblIcon.getHeight(), Image.SCALE_DEFAULT));
        lblIcon.setIcon(logo);
        this.panel.add(this.lblIcon);
        
        // Configure login label
        this.lblLogin = new JLabel("Login");
        this.lblLogin.setVerticalAlignment(SwingConstants.TOP);
        this.lblLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblLogin.setBounds(207, 84, 87, 34);
        this.panel.add(this.lblLogin);
        
        // Configure login button
        this.btnLogin = new JButton("Login");
        this.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPage(); // Handle login button click
            }
        });
        this.btnLogin.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.btnLogin.setBounds(154, 488, 150, 49);
        btnLogin.setLocation(panel.getWidth()/2-btnLogin.getWidth()/2, panel.getHeight()-btnLogin.getHeight()-20);
        this.panel.add(this.btnLogin);
        
        // Configure username label
        this.lblUsername = new JLabel("Username: ");
        this.lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblUsername.setBounds(10, 169, 111, 49);
        this.panel.add(this.lblUsername);
        
        // Configure password label
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblPassword.setBounds(10, 239, 105, 43);
        this.panel.add(this.lblPassword);
        
        // Configure username text field
        this.textField = new JTextField();
        this.textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                char key=arg0.getKeyChar();
                if(key == KeyEvent.VK_ENTER){
                    nextPage(); // Handle Enter key press in username field
                }
            }
        });
        this.textField.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
        this.textField.setBounds(125, 169, 304, 49);
        this.panel.add(this.textField);
        this.textField.setColumns(10);
        
        // Configure password field
        this.passwordField = new JPasswordField();
        passwordField.setEchoChar((char)0x2022); // Set default echo character for password
        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char key=e.getKeyChar();
                if(key == KeyEvent.VK_ENTER){
                    nextPage(); // Handle Enter key press in password field
                }
            }
        });
        this.passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
        this.passwordField.setBounds(125, 239, 304, 49);
        this.panel.add(this.passwordField);
        
        // Configure show password checkbox
        this.chckbxShowPassword = new JCheckBox("Show Password");
        chckbxShowPassword.setOpaque(false);
        this.chckbxShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(chckbxShowPassword.isSelected()){
                    passwordField.setEchoChar((char)0); // Show password
                }
                else {passwordField.setEchoChar((char)0x2022);} // Hide password
            }
        });
        this.chckbxShowPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.chckbxShowPassword.setBounds(125, 299, 156, 23);
        this.panel.add(this.chckbxShowPassword);
        
        // Configure error label
        this.lblError = new JLabel("");
        this.lblError.setForeground(new Color(255, 0, 0));
        this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 16));
        this.lblError.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                // Add any behavior needed on property change here
            }
        });
        this.lblError.setBounds(125, 135, 304, 23);
        this.panel.add(this.lblError);
        
        // Configure PS Group label
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
        this.lblPsGroup.setBounds(206, 40, 132, 33);
        this.panel.add(this.lblPsGroup);
        
        // Configure forgot password label
        this.lblForgotPassword = new JLabel("Forgot Password");
        lblForgotPassword.setOpaque(false);
        this.lblForgotPassword.setForeground(SystemColor.textInactiveText);
        this.lblForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                lblForgotPassword.setForeground(SystemColor.textHighlight); // Change color on hover
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblForgotPassword.setForeground(SystemColor.textInactiveText); // Revert color on exit
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showResetDetails(); // Handle click event
            }
        });
        this.lblForgotPassword.setHorizontalAlignment(SwingConstants.TRAILING);
        this.lblForgotPassword.setFont(new Font("Yet R", Font.PLAIN, 17));
        this.lblForgotPassword.setBounds(297, 299, 132, 23);
        this.panel.add(this.lblForgotPassword);
        
        // Configure sign up label
        this.lblNoAccountSign = new JLabel("No Account? Sign Up!");
        this.lblNoAccountSign.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.showSignUp(); // Handle click event
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblNoAccountSign.setForeground(SystemColor.textHighlight); // Change color on hover
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblNoAccountSign.setForeground(SystemColor.textInactiveText); // Revert color on exit
            }
        });
        this.lblNoAccountSign.setForeground(SystemColor.textInactiveText);
        this.lblNoAccountSign.setFont(new Font("Yet R", Font.PLAIN, 17));
        this.lblNoAccountSign.setBounds(125, 359, 176, 23);
        this.panel.add(this.lblNoAccountSign);
        
        // Configure separator label
        this.label = new JLabel("-----------------");
        this.label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        this.label.setBounds(207, 71, 141, 16);
        this.panel.add(this.label);
        
        // Configure background label
        this.lblBackGround = new JLabel("");
        this.lblBackGround.setBounds(0,0, main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), Image.SCALE_DEFAULT));
        lblBackGround.setIcon(background);
        add(this.lblBackGround);
    }
    
    // Method to handle login and navigate to the next page
    public void nextPage(){
        String name = textField.getText();
        String pass = new String(passwordField.getPassword());
        if (main.getCont().verifyUser(name, pass)) {
            lblError.setText("Success!");
            main.setCurrentAcct("C", name);
            main.showCustMenu();
        }
        else if (main.getCont().verifyStaff(name, pass)){
            if (name.equals(main.getCont().getAllStaff()[0].getUsername())){
                lblError.setText("Admin Login");
                main.setCurrentAcct("A", main.getCont().getAllStaff()[0].getUsername());
                main.showAdminMenu();
            }
            else{
                lblError.setText("Success");
                main.setCurrentAcct("S", name);
                main.showStaffMenu();
            }
        }
        else{
            lblError.setText("Email or Password incorrect. Try Again.");
        } 
    }
}
