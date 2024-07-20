package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SLogin extends JPanel{
    MainFrame main;
    private JLabel lblNLogin;
    private JLabel lblSLogin;
    private JLabel lblStaffId;
    private JLabel lblPassword;
    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel lblErrors;
    private JCheckBox chckbxShowPassword;
    private JButton btnLogin;

    public SLogin(MainFrame m){
        this.main = m;
        this.setLayout(null);
        main.setSize(500,340);
        
        this.lblNLogin = new JLabel("Normal Login");
        this.lblNLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.lblNLogin.setForeground(Color.blue);
        this.lblNLogin.setFont(new Font("Dialog", Font.PLAIN, 10));
        this.lblNLogin.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblNLogin.setText("<HTML><U>Normal Login<U><HTML>");
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblNLogin.setText("Normal Login");
        	}
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		main.showLogin();
        	}
        });
        this.lblNLogin.setBounds(12, 273, 102, 15);
        add(this.lblNLogin);
        
        this.lblSLogin = new JLabel("Staff Login");
        this.lblSLogin.setFont(new Font("Dialog", Font.BOLD, 20));
        this.lblSLogin.setBounds(168, 12, 130, 37);
        add(this.lblSLogin);
        
        this.lblStaffId = new JLabel("Staff ID:");
        this.lblStaffId.setFont(new Font("Dialog", Font.BOLD, 15));
        this.lblStaffId.setBounds(12, 105, 79, 15);
        add(this.lblStaffId);
        
        this.lblPassword = new JLabel("Password:");
        this.lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        this.lblPassword.setBounds(12, 156, 94, 15);
        add(this.lblPassword);
        
        this.idField = new JTextField();
        this.idField.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.idField.setBounds(109, 94, 326, 37);
        add(this.idField);
        this.idField.setColumns(10);
        
        this.passwordField = new JPasswordField();
        this.passwordField.setFont(new Font("Dialog", Font.PLAIN, 15));
        passwordField.setEchoChar((char)0x2022);
        this.passwordField.setBounds(109, 145, 326, 37);
        add(this.passwordField);
        
        
        this.lblErrors = new JLabel("");
        this.lblErrors.setForeground(Color.RED);
        this.lblErrors.addPropertyChangeListener(new PropertyChangeListener() {
        	@Override
			public void propertyChange(PropertyChangeEvent evt) {
        		if(!lblErrors.getText().isEmpty()) {
        			chckbxShowPassword.setLocation(109,210);
        		}
       		    // else {chckbxShowPassword.setLocation(109,210);}
        	}
        });
        this.lblErrors.setBounds(109, 188, 326, 15);
        add(this.lblErrors);
        
        this.chckbxShowPassword = new JCheckBox("Show Password");
        this.chckbxShowPassword.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		if(chckbxShowPassword.isSelected()) {
        			passwordField.setEchoChar((char)0);
        		}
        		else {passwordField.setEchoChar((char)0x2022);}
        	}
        });
        this.chckbxShowPassword.setBounds(109, 191, 136, 25);
        add(this.chckbxShowPassword);
        
        this.btnLogin = new JButton("Login");
        this.btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = idField.getText();
        		String pass = new String(passwordField.getPassword());
                if (main.getCont().verifyStaff(id, pass)){
                    if (id.equals("admin")){
                        lblErrors.setText("Admin Login");
                        main.setCurrentAcct("A", "admin");
                        main.showAdminMenu();
                    }
                    else{lblErrors.setText("Success");main.setCurrentAcct("S", id);main.showStaffMenu();}
                }
                else {lblErrors.setText("ID or Password Incorrect! Try Again.");}
        	}
        });
        this.btnLogin.setBounds(333, 193, 102, 37);
        add(this.btnLogin);
        
    }
}
