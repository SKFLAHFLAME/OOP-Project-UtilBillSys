package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class EditSysDate extends JPanel{
	MainFrame main;
	private JButton btnBack;
	private JButton btnConfirm;
	private JLabel lblMonth;
	private JComboBox comboBox;
	private JLabel lblYear;
	private JTextField txtYear;
	private String[] months ={"January", "February", "March","April","May","June","July","August","September","October","November","December"};
	private JLabel lblError;
	private JButton btnSyncDate;
	
	public EditSysDate(MainFrame m){
		this.main = m;
		this.setLayout(null);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!main.getCurrentAcct()[0].equals("A")){main.showLogin();return;}
				main.showAdminMenu();
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnBack.setBounds(44, 205, 115, 40);
		add(this.btnBack);
		
		this.btnConfirm = new JButton("Confirm");
		this.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!main.getCurrentAcct()[0].equals("A")){return;}
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Change?", "Date Change", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
				if(txtYear.getText().length() !=4){lblError.setText("Invalid Year");refresh();return;}
				main.getCont().setSystemDate(String.valueOf(comboBox.getSelectedIndex()+1), txtYear.getText());
				refresh();
				
			}
		});
		this.btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnConfirm.setBounds(260, 205, 115, 40);
		add(this.btnConfirm);
		
		this.lblMonth = new JLabel("Month:");
		this.lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblMonth.setBounds(12, 58, 71, 40);
		add(this.lblMonth);
		
		this.comboBox = new JComboBox(months);
		this.comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.comboBox.setBounds(82, 61, 115, 40);
		add(this.comboBox);
		
		this.lblYear = new JLabel("Year:");
		this.lblYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblYear.setBounds(209, 63, 71, 30);
		add(this.lblYear);
		
		this.txtYear = new JTextField();
		this.txtYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				String year = txtYear.getText();
				if (Character.isDigit(key)&&!(year.length()>3)){return;}
				arg0.consume();
			}
		});
		this.txtYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtYear.setBounds(271, 61, 115, 40);
		add(this.txtYear);
		this.txtYear.setColumns(10);
		
		this.lblError = new JLabel("");
		this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
		this.lblError.setForeground(Color.RED);
		this.lblError.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblError.setBounds(39, 111, 336, 40);
		add(this.lblError);
		
		this.btnSyncDate = new JButton("Sync Date");
		this.btnSyncDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!main.getCurrentAcct()[0].equals("A")){return;}
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Sync? Will Set to current Date", "Sync", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
				main.getCont().syncDate();
				refresh();
			}
		});
		this.btnSyncDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnSyncDate.setBounds(258, 164, 117, 28);
		add(this.btnSyncDate);
		
		refresh();
		
	}
	
	public void refresh(){
		String[] date = main.getCont().getSystemDate();
		comboBox.setSelectedIndex(Integer.valueOf(date[0])-1);
		txtYear.setText(date[1]);
		
	}
}
