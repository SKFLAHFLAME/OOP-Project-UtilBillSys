package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel{
    MainFrame main;
    private JPasswordField passwordField;
    private JTextField textField;

    public Login(MainFrame m){
        this.main = m;
        this.setLayout(null);
        main.setSize(500,340);
        
        JLabel lblSLogin = new JLabel("Staff Login");
        lblSLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSLogin.setFont(new Font("Dialog", Font.PLAIN, 9));
        lblSLogin.setForeground(Color.blue);
        lblSLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblSLogin.setText("<HTML><U>Staff Login</U></HTML>");
        		
        		lblSLogin.setFont(new Font("Dialog",Font.PLAIN,9));        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblSLogin.setText("Staff Login");
        		lblSLogin.setFont(new Font("Dialog", Font.PLAIN, 9));
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showMenu();
        	}
        	
        });
        lblSLogin.setBounds(12, 269, 75, 17);
        add(lblSLogin);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
        lblUsername.setBounds(12, 90, 114, 37);
        add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        lblPassword.setBounds(12, 144, 114, 38);
        add(lblPassword);
        
        
        JLabel lblForgotPassword = new JLabel("Forgot Password");
        lblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblForgotPassword.setForeground(Color.BLUE);
        lblForgotPassword.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblForgotPassword.setText("<HTML><U>Forgot Password</U></HTML>");
        		lblForgotPassword.setFont(new Font("Dialog",Font.PLAIN,12));   
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblForgotPassword.setText("Forgot Password");
        		lblForgotPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        lblForgotPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblForgotPassword.setBounds(334, 230, 105, 37);
        add(lblForgotPassword);
        
        JLabel lblSignUp = new JLabel("No account? Sign Up");
        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSignUp.setForeground(Color.DARK_GRAY);
        lblSignUp.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblSignUp.setText("<HTML><U>No account? Sign Up</U></HTML>");
        		lblSignUp.setFont(new Font("Dialog",Font.PLAIN,12));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblSignUp.setText("No account? Sign Up");
        		lblSignUp.setFont(new Font("Dialog", Font.PLAIN, 12));
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        lblSignUp.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblSignUp.setBounds(132, 194, 130, 26);
        add(lblSignUp);
        
        
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
        passwordField.setBounds(132, 144, 307, 38);
        add(passwordField);
        
        textField = new JTextField();
        textField.setFont(new Font("Dialog", Font.PLAIN, 15));
        textField.setBounds(132, 89, 307, 38);
        add(textField);
        textField.setColumns(10);
        
        Button btnLogin = new Button("Login");
        btnLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnLogin.setSize(105, 37);
        btnLogin.setLocation(334, 194);
        add(btnLogin);
        
        
        JLabel lblSuccess = new JLabel();
        lblSuccess.setBounds(142, 243, 70, 15);
        add(lblSuccess);
        
        

        
    }
}