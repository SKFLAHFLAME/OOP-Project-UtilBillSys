   package gui;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.MainFrame;

import javax.swing.JButton;
import java.awt.Font;

public class EditMeterReading extends JPanel{
	private JTextField textField;
	public EditMeterReading(MainFrame main) {
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(161, 50, 260, 26);
		add(comboBox);
		
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
		btnBack.setBounds(7, 255, 115, 29);
		add(btnBack);
		
		JLabel lblEditmr = new JLabel("EditMeterReading");
		lblEditmr.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditmr.setBounds(15, 16, 107, 20);
		add(lblEditmr);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(320, 255, 115, 29);
		add(btnUpdate);
	}
}
