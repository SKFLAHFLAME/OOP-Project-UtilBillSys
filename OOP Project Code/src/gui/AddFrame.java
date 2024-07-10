package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.naming.InitialContext;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import controller.MainFrame;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class AddFrame extends JFrame{
	MainFrame main;
	private JPanel panel1;//Add Manually
	private JPanel panel2;//Add by File
	private JFileChooser fileChooser;
	
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
	private JButton btnAddFile;
	private JButton btnAddManually;
	private JButton btnCancel_1;
	private JButton btnAdd_1;
	private JButton btnUploadFile;
	private JLabel lblError;
	private JScrollPane scrollPane;
	private JList list;
	

	private String fileSelected;
	
	public AddFrame(MainFrame m){
		main=m;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().setLayout(null);
		this.setSize(310,420);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setLocationRelativeTo(main);
		this.setResizable(false);
		
		this.InitPanel1();
		this.initPanel2();
		
		
	}
	
	//!Panel 1
	private void InitPanel1(){
		this.panel1 = new JPanel();
		this.panel1.setLocation(0, 0);
		panel1.setSize(294, 365);
		panel1.setLayout(null);
		getContentPane().add(panel1);


		this.lblAddUtility = new JLabel("Add Utility");
		this.lblAddUtility.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblAddUtility.setFont(new Font("Tahoma", Font.PLAIN, 23));
		this.lblAddUtility.setBounds(12, 13, 258, 52);
		panel1.add(this.lblAddUtility);
		

		this.lblUtilityName = new JLabel("Utility Name:");
		this.lblUtilityName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblUtilityName.setBounds(12, 78, 93, 27);
		panel1.add(this.lblUtilityName);
		

		this.lblUtilityPrice = new JLabel("Utility Price($):");
		this.lblUtilityPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblUtilityPrice.setBounds(12, 118, 115, 27);
		panel1.add(this.lblUtilityPrice);
		

		this.lblReadingUnit = new JLabel("Reading Unit:");
		this.lblReadingUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblReadingUnit.setBounds(12, 158, 106, 27);
		panel1.add(this.lblReadingUnit);
		

		this.lblServiceCharge = new JLabel("Service Charge(%):");
		this.lblServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lblServiceCharge.setBounds(12, 198, 139, 27);
		panel1.add(this.lblServiceCharge);
		

		this.textSCField = new JTextField();
		this.textSCField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textSCField.setBounds(163, 198, 107, 27);
		panel1.add(this.textSCField);
		this.textSCField.setColumns(10);
		

		this.textUField = new JTextField();
		this.textUField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textUField.setBounds(117, 158, 153, 27);
		panel1.add(this.textUField);
		this.textUField.setColumns(10);
		

		this.textNField = new JTextField();
		this.textNField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.textNField.setBounds(117, 78, 153, 27);
		panel1.add(this.textNField);
		this.textNField.setColumns(10);


		this.textPField = new JTextField();
		this.textPField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.textPField.setBounds(127, 119, 143, 27);
		panel1.add(this.textPField);
		this.textPField.setColumns(10);
		

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		this.btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.btnCancel.setBounds(12, 310, 107, 42);
		panel1.add(this.btnCancel);


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
		this.btnAdd.setBounds(175, 310, 107, 42);
		panel1.add(this.btnAdd);


		this.btnAddFile = new JButton("Add File");
		this.btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(true);
				panel1.setVisible(false);
			}
		});
		this.btnAddFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnAddFile.setBounds(93, 268, 106, 29);
		this.panel1.add(this.btnAddFile);

	}


	//!Panel 2
	private void initPanel2(){
		this.panel2 = new JPanel();
		this.panel2.setLocation(0, 0);
		panel2.setSize(294, 365);
		panel2.setLayout(null);
		getContentPane().add(panel2);
		panel2.setVisible(false);


		this.btnAdd_1 = new JButton("Add");
		this.btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileSelected==null){
					lblError.setText("No File Uploaded");
					return;
				}
			}
		});
		this.btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnAdd_1.setBounds(179, 313, 103, 39);
		this.panel2.add(this.btnAdd_1);
		

		this.btnUploadFile = new JButton("Upload File");
		this.btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				panel2.add(fileChooser);
				int r = fileChooser.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION){
					fileSelected = fileChooser.getSelectedFile().getAbsolutePath();
				}
				else {
					System.out.println("Cancelled");
				}
				
				try {
					populateList();
				} catch (FileNotFoundException e) {
					System.out.println("No file found");
				}
			}
		});
		this.btnUploadFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnUploadFile.setBounds(76, 13, 141, 25);
		this.panel2.add(this.btnUploadFile);
		

		this.lblError = new JLabel("");
		this.lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
		this.lblError.setBounds(59, 244, 152, 25);
		this.panel2.add(this.lblError);
		

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 51, 258, 193);
		this.panel2.add(this.scrollPane);
		

		this.list = new JList();
		this.scrollPane.setViewportView(this.list);


		this.btnAddManually = new JButton("Add Manually");
		this.btnAddManually.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(false);
				panel1.setVisible(true);
			}
		});
		this.btnAddManually.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnAddManually.setBounds(59, 270, 152, 29);
		this.panel2.add(this.btnAddManually);


		this.btnCancel_1 = new JButton("Cancel");
		this.btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();			
			}
		});
		this.btnCancel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnCancel_1.setBounds(12, 313, 103, 39);
		this.panel2.add(this.btnCancel_1);



		
		

	}
	

	//! Populate List Method
	private void populateList() throws FileNotFoundException{
		if (fileSelected == null){System.out.println("No File to populate with");return;}
		String[][] fileContents = main.getCont().readCSV(fileSelected);
		String[] lines=new String[fileContents.length];
		int c=0;
		for (String[] line : fileContents){
			String l="";
			for (String var: line){
				l=l+var+"     ";
			}
			lines[c]=l;
			c+=1;
		}
		c=0;
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String line:lines){
			System.out.println(line);
			if (c==0){
				model.addElement("Header: "+line);
				c+=1;
				continue;
			}
			model.addElement("Reading "+c+": "+line);
			c+=1;
		}
		list.setModel(model);
		
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
