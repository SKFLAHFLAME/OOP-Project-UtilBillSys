   package gui;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.MainFrame;
import data.Readings;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMeterReading extends JPanel{
	MainFrame main;
	private JTextField textField;
	private JComboBox mrBox;
	private String[] valueArr;
	public EditMeterReading(MainFrame m) {
		main=m;
		setLayout(null);
		initReadingNames();
		
		this.mrBox = new JComboBox(this.valueArr);
		mrBox.setBounds(161, 50, 260, 26);
		add(mrBox);
		
		JLabel lblMeterReading = new JLabel("Meter Reading:");
		lblMeterReading.setBounds(15, 145, 107, 20);
		add(lblMeterReading);
		
		JLabel lblUtility = new JLabel("Utility:");
		lblUtility.setBounds(15, 53, 69, 20);
		add(lblUtility);
		
		textField = new JTextField();
		textField.setBounds(161, 142, 260, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showEditDraft();
			}
		});
		btnBack.setBounds(7, 255, 115, 29);
		add(btnBack);
		
		JLabel lblEditmr = new JLabel("EditMeterReading");
		lblEditmr.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditmr.setBounds(15, 16, 107, 20);
		add(lblEditmr);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnUpdate.setBounds(320, 255, 115, 29);
		add(btnUpdate);
	}
	
	private void initReadingNames(){
		Readings[] r=main.getCont().allReadings();
		valueArr=new String[r.length];
		int c=0;
		for(Readings x:r){
			String n = x.getUtilityName();
			valueArr[c]=n;
			c+=1;
		}
	}
}
