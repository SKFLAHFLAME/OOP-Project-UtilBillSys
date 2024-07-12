package gui;

import controller.MainFrame;
import data.Readings;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMeterReading extends JPanel {
	MainFrame main;
	private JTextField textField;
	private JComboBox mrBox;
	private String[] valueArr;
	public AddMeterReading(MainFrame m) {
		main =m;
		setLayout(null);
		initReadingNames();
		
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
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name= (String)mrBox.getSelectedItem();
				double MR = Double.parseDouble(textField.getText());
				main.getCont().addMeterReading(main.getCurrentAcct()[1],name, MR);
				main.showEditDraft();
			}
		});
		btnAdd.setBounds(320, 255, 115, 29);
		add(btnAdd);
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
