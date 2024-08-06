package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.naming.InitialContext;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import controller.MainFrame;
import data.Readings;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AddFrame extends JDialog{
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
	private JButton btnUseFile;
	private JButton btnAddManually;
	private JButton btnCancel_1;
	private JButton btnAdd_1;
	private JButton btnUploadFile;
	private JLabel lblError;
	private JScrollPane scrollPane;
	private JList list;
	

	private String[][] fileContents= new String[0][0];
	private String[] lines = new String[0];
	private String fileSelected;
	private boolean Header=false;
	private boolean f;
	private JLabel lbldoNotUse;
	private JLabel lblFormat;
	
	public AddFrame(MainFrame m){
		main=m;
		this.f = main.flag;
		main.flag = false;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(310,420);
		
		this.setLocationRelativeTo(main);
		this.setResizable(false);
		
		this.InitPanel1();
		this.initPanel2();
		if (f==true){panel1.hide();panel2.show();}
		
		
	}
	
	
	
	//!Panel 1
	private void InitPanel1(){
		this.panel1 =  new JPanel();
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
				String name= textNField.getText();
				String price = textPField.getText();
				String unit = textUField.getText();
				String serCharge=textSCField.getText();
				addReading(name,price,unit,serCharge);
				main.getEu().redraw();
				dispose();
			}
		});
		this.btnAdd.setBounds(175, 310, 107, 42);
		panel1.add(this.btnAdd);


		this.btnUseFile = new JButton("Use File");
		this.btnUseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(true);
				panel1.setVisible(false);
			}
		});
		this.btnUseFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnUseFile.setBounds(93, 268, 106, 29);
		this.panel1.add(this.btnUseFile);

	}


	//!Panel 2
	private void initPanel2(){
		this.panel2 = new JPanel();
		this.panel2.setLocation(0, 0);
		panel2.setSize(304, 385);
		panel2.setLayout(null);
		getContentPane().add(panel2);
		panel2.setVisible(false);


		this.btnAdd_1 = new JButton("Add");
		this.btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileSelected==null){lblError.setText("No File Uploaded");return;}
				if (f == false){
					int c=0;
					for(String[] s : fileContents){
						if(c==0&&Header==true){c+=1;continue;}
						addReading(s[0], s[1], s[2], s[3].split("%")[0]);
						c+=1;
					}
					c=0;}
				else{
					if(updateReading()==false){return;}
					f = false;
				}
				main.getEu().redraw();
				dispose();
			}
		});
		this.btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnAdd_1.setBounds(178, 333, 103, 39);
		this.panel2.add(this.btnAdd_1);
		

		this.btnUploadFile = new JButton("Upload File");
		this.btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooser.setFileFilter(new FileNameExtensionFilter(".txt , .csv", "csv","txt"));
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
		this.btnUploadFile.setBounds(12, 13, 115, 25);
		this.panel2.add(this.btnUploadFile);
		

		this.lblError = new JLabel("");
		this.lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
		this.lblError.setBounds(59, 244, 152, 25);
		this.panel2.add(this.lblError);
		

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 51, 269, 193);
		this.panel2.add(this.scrollPane);
		

		this.list = new JList();
		this.scrollPane.setViewportView(this.list);


		this.btnAddManually = new JButton("Add Manually");
		if (f==true){btnAddManually.hide();}
		this.btnAddManually.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(false);
				panel1.setVisible(true);
			}
		});
		this.btnAddManually.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnAddManually.setBounds(82, 298, 152, 29);
		this.panel2.add(this.btnAddManually);


		this.btnCancel_1 = new JButton("Cancel");
		this.btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.flag = false;
				dispose();			
			}
		});
		this.btnCancel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.btnCancel_1.setBounds(12, 333, 103, 39);
		this.panel2.add(this.btnCancel_1);
		
		this.lbldoNotUse = new JLabel("*Do NOT put Units*");
		this.lbldoNotUse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.lbldoNotUse.setBounds(125, 13, 157, 25);
		this.panel2.add(this.lbldoNotUse);
		
		this.lblFormat = new JLabel("Format: \r\nNo file uploaded");
		this.lblFormat.setBounds(12, 271, 280, 25);
		this.panel2.add(this.lblFormat);



		
		

	}
	

	//! Populate List Method
	private void populateList() throws FileNotFoundException{
		if (fileSelected == null){System.out.println("No File to populate with");return;}
		fileContents = main.getCont().csvReader(fileSelected);
		lines=new String[fileContents.length];
		int c=0;
		for (String[] line : fileContents){
			System.out.println(line[0]);
			
			if (line[0].equals("Header")){Header = true;}
			String l="";
			for (String var: line){
				l=l+var+"  |  ";
			}
			lines[c]=l;
			
			if (line.length==4){lblFormat.setText("Format: \r\nUtil Name, Price($), Unit, Service Charge(%)");}
			else if (line.length==3&&f == true){lblFormat.setText("Format: \r\nUtil Name, Price($), Service Charge(%)");}
			else if (line.length==2&&f == true){lblFormat.setText("Format: \r\nUtil Name, Price($)");}
			else{lblFormat.setText("Format Read: \r\nUnReadable");}
			
			c+=1;
		}
		c=0;
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String line:lines){
			System.out.println(line);
			System.out.println(Header);
			if (c==0&&Header==true){

				model.addElement(line);
				c+=1;
				continue;
			}
			model.addElement("Reading "+c+"| "+line);
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
	public void addReading(String name, String priceString, String unit, String serviceCharge){
		Double price;
		Double serCharge;
		try {
			price = Double.parseDouble(priceString);
		} catch (Exception e) {
			price = 0.0;
		}
		try {
			serCharge = Double.parseDouble(serviceCharge);
		} catch (Exception e) {
			serCharge =0.0;
		}
		if(name.isEmpty()){name="-";}
		if(unit.isEmpty()){unit="-";}
		
		main.getCont().addReading(name, price, unit, serCharge);
	}
	
	public boolean updateReading(){
		Vector<String[]> avaliable = new Vector<>();
		Vector<String[]> unAvaliable = new Vector<>();
		Vector<String> notAvaRead = new Vector<>();
		int c=0;
		for (String[] line : fileContents){
			if (c==0&&Header == true){c+=1;continue;}
			try {
				main.getCont().getReading(line[0]).getUtilityName();
				avaliable.add(line);
			} catch (Exception e) {
				unAvaliable.add(line);
				notAvaRead.add(line[0]);
			}
			c+=1;
		}
		System.out.println(String.join(",", notAvaRead));
		if (!unAvaliable.isEmpty()){
			String[] options = {"Continue","Cancel"};
			int selection = JOptionPane.showOptionDialog(null, String.join(", ", notAvaRead)+" is not avaliable in Readings. Continue? Will change those avaliable", "Unavaliable Edits", 0,3,null,options,options[0]);
			if(selection != 0){return false;}
		}
		
		for (String[] line :avaliable){
			if (line.length ==4){main.getEu().editRow(line[0], line[1], line[2], line[3]);}// utilName, Price, Unit, serCharge
			else if (line.length ==3){main.getEu().editRow(line[0], line[1], "", line[2]);}//utilName, Price, serCharge
			else if (line.length ==2){main.getEu().editRow(line[0], line[1], "", "");}// utilName, Price
			else {JOptionPane.showMessageDialog(null, "Unable to read", "ERROR", ERROR); return false;}
		}
		
		
		
		return true;
	}


	

}
