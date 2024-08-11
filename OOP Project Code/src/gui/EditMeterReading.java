package gui;

import data.Readings;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditMeterReading extends JPanel {
	// Declare UI components and variables
	private JTextField textField;
	private JComboBox mrBox;
	private String[] valueArr;
	private JLabel lblUnit;
	private String previous;
	private boolean noUnit=false;
	private PopupDialog window;
    // Constructor to initialize the panel and its components
	public EditMeterReading(PopupDialog popupDialog, String CurrentName, String Reading, String user) {
		window =popupDialog;
		setLayout(null);
		window.setSize(460, 350);
		initReadingNames();
		
		JLabel lblEditMeterReading = new JLabel("Edit Meter Reading");
		lblEditMeterReading.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditMeterReading.setBounds(15, 16, 337, 43);
		add(lblEditMeterReading);
		
		JLabel lblMeterReading = new JLabel("Meter Reading:");
		lblMeterReading.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblMeterReading.setBounds(15, 161, 147, 34);
		add(lblMeterReading);
		
		this.mrBox = new JComboBox(this.valueArr);
		this.mrBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.mrBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                // Update the unit label based on the selected utility
				String unit = window.main.getCont().getReading((String) mrBox.getSelectedItem()).getUnit();
				lblUnit.setText(unit);
				if(unit.equals("-")){
					textField.setEditable(false);
					previous = textField.getText();
					noUnit = true;
					return;
				}
				 // Handle cases where the unit is not applicable
				if (noUnit==false){previous = textField.getText();}
				textField.setEditable(true);
				textField.setText(previous);
				noUnit = false;
				
			}
			
		});
		mrBox.setBounds(172, 72, 263, 43);
		mrBox.setEnabled(false);	// Disable the combo box until necessary
		add(mrBox);

		
		JLabel lblUtility = new JLabel("Utility:");
		lblUtility.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblUtility.setBounds(15, 75, 147, 34);
		add(lblUtility);
		
		textField = new JTextField();
		this.textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				// Allow only numeric input
				if(Character.isDigit(key)){
		            return;}
				arg0.consume();	// Discard non-numeric input
			}
		});
		textField.setBounds(172, 158, 188, 37);
		add(textField);
		textField.setColumns(10);
		textField.setText(Reading);	// Set the initial value for the meter reading
		
		JButton btnBack = new JButton("Cancel");
		btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(window.main.getPrepage()==true){
					window.main.showCustMenu();
					return;
				}
				window.dispose();	// Close the popup  
			}
		});
		btnBack.setBounds(15, 241, 147, 43);
		add(btnBack);
		
		JButton btnAdd = new JButton("Update");
		btnAdd.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String readingName = (String) mrBox.getSelectedItem();
                int meterReading = Integer.valueOf(textField.getText()); 
                
                // Remove the old meter reading and add the updated reading
                window.main.getCont().removeMeterReading(user, CurrentName);
                window.main.getCont().addMeterReading(user,readingName, meterReading);
                window.main.setPrepage(false);
                window.main.showEditDraft(user);// Show the updated draft
                window.dispose(); // Close the popup
            }
		});
		btnAdd.setBounds(288, 241, 147, 43);
		add(btnAdd);
		
		this.lblUnit = new JLabel("Unit");
		this.lblUnit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		this.lblUnit.setBounds(370, 161, 65, 34);
		add(this.lblUnit);
		
        // Set the initial unit and selected utility in the combo box
		lblUnit.setText(window.main.getCont().getReading((String) mrBox.getSelectedItem()).getUnit());
		mrBox.setSelectedItem(CurrentName);
	}
	
    // Initialize the combo box with the names of available readings
	private void initReadingNames(){
        Readings[] r = window.main.getCont().allReadings(); // Get all readings
        valueArr = new String[r.length]; // Initialize the array with appropriate size
        int c = 0;
        for (Readings x : r) {
            String n = x.getUtilityName();
            valueArr[c] = n; // Populate the array with utility names
            c += 1;
		}
	}
}
