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
import java.awt.Checkbox;

public class SLogin extends JPanel{
    MainFrame main;
    private JLabel lblNLogin;
    private JLabel lblSLogin;
    private JLabel lblStaffId;
    private JLabel lblPassword;
    private JTextField idField;
    private JPasswordField passwordField;
    private Button btnLogin;
    private Checkbox cbSP;
    private JLabel label;

    public SLogin(MainFrame m){
        this.main = m;
        this.setLayout(null);
        
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
        this.lblSLogin.setBounds(157, 12, 130, 37);
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
        this.passwordField.setBounds(109, 145, 326, 37);
        add(this.passwordField);
        
        this.btnLogin = new Button("Login");
        this.btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = idField.getText();
        		String pass = new String(passwordField.getPassword());
        	}
        });
        this.btnLogin.setBounds(320, 208, 115, 31);
        add(this.btnLogin);
        
        this.cbSP = new Checkbox("Show Password");
        this.cbSP.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(cbSP.getState()) {
        			passwordField.setEchoChar((char)0);
        		}
        		else {passwordField.setEchoChar((char)0x2022);}
        	}
        });
        this.cbSP.setBounds(109, 188, 115, 23);
        add(this.cbSP);
        
        this.label = new JLabel("");
        this.label.setBounds(109, 188, 326, 15);
        add(this.label);
        
    }
}
