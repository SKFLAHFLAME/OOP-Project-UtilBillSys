package gui;

import javax.swing.JPanel;

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
	private JButton btnBack;
	private JButton btnConfirm;
	private JLabel lblMonth;
	private JComboBox comboBox;
	private JLabel lblYear;
	private JTextField txtYear;
	private String[] months ={"January", "February", "March","April","May","June","July","August","September","October","November","December"};
	private JLabel lblError;
	private JButton btnSyncDate;
	private JButton btnGenerateMonthsBills;
	private PopupDialog window;
	
	// Constructor initializes the panel and its components
	public EditSysDate(PopupDialog popupDialog){
		this.window = popupDialog;
		this.setLayout(null);
		
		this.btnBack = new JButton("Cancel");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check the account type and either show login screen or dispose the window
				if(!window.main.getCurrentAcct()[0].equals("A")){window.main.showAllLogin();return;}
				window.dispose();
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(12, 276, 454, 40);
		add(this.btnBack);
		
		this.btnConfirm = new JButton("Confirm");
		this.btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Only proceed if the account type is 'A'
				if(!window.main.getCurrentAcct()[0].equals("A")){return;}
				// Prompt the user for confirmation
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Change?", "Date Change", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
				// Validate the year input length
				if(txtYear.getText().length() !=4){lblError.setText("Invalid Year");refresh();return;}
				// Set the system date with the selected month and year
				window.main.getCont().setSystemDate(String.valueOf(comboBox.getSelectedIndex()+1), txtYear.getText());
				refresh();
				
			}
		});
		this.btnConfirm.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnConfirm.setBounds(243, 176, 223, 40);
		add(this.btnConfirm);
		
		this.lblMonth = new JLabel("Month:");
		this.lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblMonth.setBounds(12, 71, 71, 40);
		add(this.lblMonth);
		
		this.comboBox = new JComboBox(months);
		this.comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.comboBox.setBounds(93, 72, 142, 40);
		add(this.comboBox);
		
		this.lblYear = new JLabel("Year:");
		this.lblYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblYear.setBounds(245, 76, 71, 30);
		add(this.lblYear);
		
		this.txtYear = new JTextField();
		this.txtYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				String year = txtYear.getText();
				// Allow only digits and restrict input length to 4 characters
				if (Character.isDigit(key)&&!(year.length()>3)){return;}
				arg0.consume();	// Discard any invalid input
			}
		});
		this.txtYear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.txtYear.setBounds(326, 72, 140, 40);
		add(this.txtYear);
		this.txtYear.setColumns(10);
		
		this.lblError = new JLabel("");
		this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
		this.lblError.setForeground(Color.RED);
		this.lblError.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblError.setBounds(92, 123, 336, 40);
		add(this.lblError);
		
		this.btnSyncDate = new JButton("Sync Date");
		this.btnSyncDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Only allow syncing if the account type is 'A'
				if(!window.main.getCurrentAcct()[0].equals("A")){return;}
				// Prompt the user for confirmation before syncing
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Sync? Will Set to current Date", "Sync", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
				// Sync the system date with the current date
				window.main.getCont().syncDate();
				refresh();
			}
		});
		this.btnSyncDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnSyncDate.setBounds(12, 225, 454, 40);
		add(this.btnSyncDate);
		
		// Initialize and set up the Generate Bills button 
		this.btnGenerateMonthsBills = new JButton("Generate Bills");
		this.btnGenerateMonthsBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Generate bills for the selected month
				window.main.getCont().generateBills();
				// Show a success message after generating the bills
				JOptionPane.showMessageDialog(null, "Generated Bills", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.btnGenerateMonthsBills.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnGenerateMonthsBills.setBounds(12, 176, 223, 40);
		add(this.btnGenerateMonthsBills);
		// Refresh the panel to update the displayed date
		refresh();
		
	}
	
	// Method to refresh the displayed system date
		public void refresh() {
			// Retrieve the current system date
			String[] date = window.main.getCont().getSystemDate();
			// Update the dropdown and text field with the current month and year
			comboBox.setSelectedIndex(Integer.valueOf(date[0]) - 1);
			txtYear.setText(date[1]);
		}
}
