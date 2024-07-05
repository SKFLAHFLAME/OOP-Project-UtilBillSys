package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.MainFrame;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame{
	MainFrame main;
	private JLabel lblAddUtility;
	private JLabel lblUtilityName;
	private JLabel lblUtilityPrice;
	private JLabel lblReadingUnit;
	private JLabel lblServiceCharge;
	private JTextField textSCField;
	private JTextField textUField;
	private JTextField textNField;
	private JTextField textPField;
	private JButton btnCancel;
	private JButton btnAdd;
	public AddFrame(MainFrame m){
		main=m;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().setLayout(null);
		this.setSize(300,375);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setLocationRelativeTo(main);
		
		this.lblAddUtility = new JLabel("Add Utility");
		this.lblAddUtility.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblAddUtility.setFont(new Font("Tahoma", Font.PLAIN, 23));
		this.lblAddUtility.setBounds(12, 13, 258, 52);
		getContentPane().add(this.lblAddUtility);
		
		this.lblUtilityName = new JLabel("Utility Name:");
		this.lblUtilityName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblUtilityName.setBounds(12, 78, 93, 27);
		getContentPane().add(this.lblUtilityName);
		
		this.lblUtilityPrice = new JLabel("Utility Price($):");
		this.lblUtilityPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblUtilityPrice.setBounds(12, 118, 115, 27);
		getContentPane().add(this.lblUtilityPrice);
		
		this.lblReadingUnit = new JLabel("Reading Unit:");
		this.lblReadingUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblReadingUnit.setBounds(12, 158, 106, 27);
		getContentPane().add(this.lblReadingUnit);
		
		this.lblServiceCharge = new JLabel("Service Charge(%):");
		this.lblServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblServiceCharge.setBounds(12, 198, 139, 27);
		getContentPane().add(this.lblServiceCharge);
		
		this.textSCField = new JTextField();
		this.textSCField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textSCField.setBounds(163, 198, 107, 27);
		getContentPane().add(this.textSCField);
		this.textSCField.setColumns(10);
		
		this.textUField = new JTextField();
		this.textUField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textUField.setBounds(117, 158, 153, 27);
		getContentPane().add(this.textUField);
		this.textUField.setColumns(10);
		
		this.textNField = new JTextField();
		this.textNField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textNField.setBounds(117, 78, 153, 27);
		getContentPane().add(this.textNField);
		this.textNField.setColumns(10);
		
		this.textPField = new JTextField();
		this.textPField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textPField.setBounds(127, 119, 143, 27);
		getContentPane().add(this.textPField);
		this.textPField.setColumns(10);
		
		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		this.btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnCancel.setBounds(12, 265, 107, 42);
		getContentPane().add(this.btnCancel);
		
		this.btnAdd = new JButton("Add");
		this.btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addReading();
				main.getEu().redraw();
				dispose();
			}
		});
		this.btnAdd.setBounds(163, 265, 107, 42);
		getContentPane().add(this.btnAdd);
		
		
	}
	
//	public boolean check(){
//		String name = textNField.getText();
//		Double price = Double.valueOf(textPField.getText());
//		String unit = textUField.getText();
//		Double serCharge = Double.valueOf(textSCField.getText());
//		if (price.isNaN()||serCharge.isNaN()){
//			return false;
//		}
//		return true;
//	}
	public void addReading(){
		String name = textNField.getText();
		String unit = textUField.getText(); 
		Double price;
		Double serCharge;
		try {
			price = Double.valueOf(textPField.getText());
		} catch (Exception e) {
			price = 0.0;
		}
		try {
			serCharge = Double.valueOf(textSCField.getText());
		} catch (Exception e) {
			serCharge =0.0;
		}
		
		main.getCont().addReading(name, price, unit, serCharge);
	}
}
