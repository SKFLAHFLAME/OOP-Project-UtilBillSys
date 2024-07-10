package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.MainFrame;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMeterReading extends JPanel {
	private JTextField textField;
	private JComboBox mrBox;
	private String[] valueArr = {"Electricity", "Gas", "Water"};
	public AddMeterReading(MainFrame main) {
		setLayout(null);
		
		JLabel lblAddMeterReading = new JLabel("Add Meter Reading");
		lblAddMeterReading.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAddMeterReading.setBounds(15, 16, 337, 43);
		add(lblAddMeterReading);
		
		JLabel lblMeterReading = new JLabel("Meter Reading:");
		lblMeterReading.setBounds(15, 161, 114, 20);
		add(lblMeterReading);
		
		this.mrBox = new JComboBox(this.valueArr);
		mrBox.setBounds(172, 72, 240, 26);
		add(mrBox);
		
		JLabel lblUtility = new JLabel("Utility:");
		lblUtility.setBounds(15, 75, 69, 20);
		add(lblUtility);
		
		textField = new JTextField();
		textField.setBounds(172, 158, 240, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showEditDraft();
			}
		});
		btnBack.setBounds(15, 255, 115, 29);
		add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(320, 255, 115, 29);
		add(btnAdd);
	}
}
