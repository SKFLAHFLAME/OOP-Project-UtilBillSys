package gui;

import controller.MainFrame;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Login extends JPanel{
    MainFrame main;
    private boolean p = false;
    private JPasswordField passwordField;
    private JTextField textField;
    private JLabel lblEye;
    private Checkbox chkbShowPass;
    private JLabel lblSuccess;

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
        		main.showStaffLogin();
        	}
        	
        });
        lblSLogin.setBounds(12, 271, 75, 17);
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
        lblForgotPassword.setBounds(334, 238, 105, 37);
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
        		main.showSignUp();
        	}
        });
        lblSignUp.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblSignUp.setBounds(132, 243, 130, 26);
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
        
        this.chkbShowPass = new Checkbox("Show Password");
        this.chkbShowPass.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(chkbShowPass.getState()) {
                	passwordField.setEchoChar((char)0);
                }
                else {
                    passwordField.setEchoChar((char)0x2022);
                }
        	}
        });
        
        this.chkbShowPass.setBounds(132, 188, 115, 23);
        add(this.chkbShowPass);

        Button btnLogin = new Button("Login");
        btnLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name = textField.getText();
                String pass = new String(passwordField.getPassword());
                if (main.getCont().verifyUser(name, pass)) {
                    lblSuccess.setText("Success!");
                }
                else{
                	lblSuccess.setText("Email or Password incorrect. Try Again.");
                	}

        	}
        });
        btnLogin.setSize(105, 32);
        btnLogin.setLocation(334, 203);
        add(btnLogin);
        
        
        this.lblSuccess = new JLabel();
        this.lblSuccess.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
        		if (!lblSuccess.getText().isBlank()) {
    				chkbShowPass.setLocation(132,210);}
    			else {chkbShowPass.setLocation(132,188);}
        	}
        });
        this.lblSuccess.setForeground(Color.RED);
        this.lblSuccess.setFont(new Font("Dialog", Font.ITALIC, 12));
        this.lblSuccess.setBounds(132, 188, 307, 15);
        add(this.lblSuccess);
        
        // this.lblEye = new JLabel(new ImageIcon(Login.class.getResource("/files/eyeO.jpg")));
        // this.lblEye.addMouseListener(new MouseAdapter() {
        // 	@Override
        // 	public void mouseClicked(MouseEvent e) {
        // 		if(p == false) {
        // 			lblEye.setIcon(new ImageIcon(Login.class.getResource("/files/eyeC.jpg")));
        //             p =true;
        // 		}
        // 		else {lblEye.setIcon(new ImageIcon(Login.class.getResource("/files/eyeO.jpg")));p=false;}
        		
        // 		if(p == true) {
        // 			passwordField.setEchoChar((char)0);
        // 		}
        // 		else {
        //             passwordField.setEchoChar((char)0x2022);
        //         }
        		
        //     }
        // });
        // this.lblEye.setBounds(443, 144, 45, 38);
        // add(this.lblEye);
        
        
        
        

        
    }
}