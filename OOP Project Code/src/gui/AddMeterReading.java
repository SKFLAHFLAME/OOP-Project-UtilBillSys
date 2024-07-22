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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddMeterReading extends JPanel {
	MainFrame main;
	private JTextField textField;
	private JComboBox mrBox;
	private String[] valueArr;
	private JLabel lblUnit;
	private String previous;
	private boolean noUnit=false;
	public AddMeterReading(MainFrame m) {
		main =m;
		setLayout(null);
		main.setSize(460, 350);
		initReadingNames();
		
		JLabel lblAddMeterReading = new JLabel("Add Meter Reading");
		lblAddMeterReading.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAddMeterReading.setBounds(15, 16, 337, 43);
		add(lblAddMeterReading);
		
		JLabel lblMeterReading = new JLabel("Meter Reading:");
		lblMeterReading.setBounds(15, 161, 114, 20);
		add(lblMeterReading);
		
		this.mrBox = new JComboBox(this.valueArr);
		this.mrBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String unit = main.getCont().getReading((String) mrBox.getSelectedItem()).getUnit();
				lblUnit.setText(unit);
				if(unit.equals("-")){
					textField.setEditable(false);
					previous = textField.getText();
					noUnit = true;
					textField.setText("1");
					return;
				}
				if (noUnit==false){previous = textField.getText();}
				textField.setEditable(true);
				textField.setText(previous);
				noUnit = false;
				
			}
			
		});
		mrBox.setBounds(172, 72, 240, 26);
		add(mrBox);
		
		JLabel lblUtility = new JLabel("Utility:");
		lblUtility.setBounds(15, 75, 69, 20);
		add(lblUtility);
		
		textField = new JTextField();
		this.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				if(Character.isDigit(key)||(key==KeyEvent.VK_PERIOD&&!textField.getText().contains("."))){
		            return;}
				arg0.consume();
			}
		});
		textField.setBounds(172, 158, 188, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.getPrepage()==true){
					main.showCustMenu();
					return;
				}
				main.showEditDraft();
			}
		});
		btnBack.setBounds(15, 255, 115, 29);
		add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String readingName = (String) mrBox.getSelectedItem();
                double meterReading = Double.parseDouble(textField.getText()); 
                
                main.getCont().addMeterReading(main.getCurrentAcct()[1],readingName, meterReading);
                main.setPrepage(false);
                main.showEditDraft();
			}
		});
		btnAdd.setBounds(320, 255, 115, 29);
		add(btnAdd);
		
		this.lblUnit = new JLabel("Unit");
		this.lblUnit.setBounds(370, 161, 42, 20);
		add(this.lblUnit);
		lblUnit.setText(main.getCont().getReading((String) mrBox.getSelectedItem()).getUnit());
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
