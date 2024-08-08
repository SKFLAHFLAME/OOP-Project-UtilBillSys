package gui;

import controller.MainFrame;
import data.Readings;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;

public class EditUtility extends JPanel{
	MainFrame main;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnAddUtility;
	private JButton btnUpdateUtility;
	private Object[] coloumnames={"Utility Name","Price (S$)","Unit", "Service Charge (%)"};
	private JScrollPane scrollPane;
	private JTable table;
	private ImageIcon logo = new ImageIcon(this.getClass().getResource("/images/logo.png"));
    private ImageIcon background = new ImageIcon(this.getClass().getResource("/images/background.jpg"));
	private Vector<Object[]> changes = new Vector<>();//Row, Col, Val
	private String[][]data;
	private boolean unsaved = false;
//	private Vector<Object[]> finaldata=new Vector<>();
	private DefaultTableModel model;
	private JButton btnEditWithFile;
	private JButton btnClearRow;
	private JLabel lblBackground;
	private JLabel lblFilter;
	public EditUtility(MainFrame m){
		setBackground(new Color(135, 206, 250));
		this.main = m;
		this.setLayout(null);
		
		this.btnBack = new JButton("<");
		btnBack.setBorderPainted(false);
//		btnBack.setBackground(Color.LIGHT_GRAY);
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!back()){return;}
				if (main.getCurrentAcct()[0].equals("A")){
					main.showAdminMenu();
				}
				else{main.showStaffMenu();}
			}
		});
		this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnBack.setBounds(28, 47, 49, 44);
		add(this.btnBack);
		
		this.btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		this.btnDelete.setBackground(new Color(255,50,50, 200));
		this.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int edtRow= table.getSelectedRow();
				if(edtRow == -1){return;}
				try {
					table.getCellEditor().cancelCellEditing();
				} catch (Exception e) {
					
				}
				String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Delete?", "Delete", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
				System.out.println(edtRow);
				deleteRow(edtRow);
				redraw();
			}
		});
		this.btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnDelete.setBounds(756, 170, 202, 51);
		add(this.btnDelete);
		
		this.btnAddUtility = new JButton("Add Utility");
		this.btnAddUtility.setForeground(Color.WHITE);
		this.btnAddUtility.setBackground(new Color(255,50,50, 200));
		this.btnAddUtility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addRow();
				
			}
		});
		this.btnAddUtility.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnAddUtility.setBounds(756, 531, 202, 50);
		add(this.btnAddUtility);
		
		this.btnUpdateUtility = new JButton("Update Utilities");
		this.btnUpdateUtility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int c=0;
				int c2=0;
//				for (Object[] x : finaldata){
//					for(Object y:x){System.out.print(y+":"+c2+":"+c+" , ");c2+=1;}
//					System.out.println();
//					c2=0;
//					c+=1;
//				}
				c2=0;c=0;
				updateItems();
			}
		});
		this.btnUpdateUtility.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnUpdateUtility.setBounds(756, 433, 202, 52);
		add(this.btnUpdateUtility);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(28, 102, 718, 549);
		add(this.scrollPane);
		
		this.model = new DefaultTableModel(coloumnames, 0)
		{
		    @Override 
		    public boolean isCellEditable(int row, int column)
		    {
		        if (main.getCurrentAcct()[0].equals("A")){return true;}
		        return column==0 ? false:true;
		    }
		};
		this.table = new JTable(model);
		table.setShowGrid(true);
		Font tableFont = new Font("Tahoma", Font.PLAIN, 20);
		table.getTableHeader().setFont(tableFont);
		table. getTableHeader(). setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		this.table.setRowHeight(table.getRowHeight()+10);
		this.table.setFont(tableFont);
		this.table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				int editedRow=table.getEditingRow();
				int editedCol=table.getEditingColumn();
				if (editedRow==-1||editedCol==-1){return;}
				unsaved = true;
				Object item = table.getValueAt(editedRow, editedCol);
				if (editedCol==1||editedCol==3){
					if(item.equals("")){
						item = 0.0;
					}
				}
				Object[] i = {editedRow,editedCol,item};
				changes.add(i);
				System.out.println(item);
				System.out.println(i[0]+", "+i[1]+", "+i[2]);
			}
		});
		redraw();
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(2);
		table.getColumnModel().getColumn(2).setPreferredWidth(2);
		this.scrollPane.setViewportView(this.table);
		
		this.btnEditWithFile = new JButton("Edit with File");
		this.btnEditWithFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.flag=true;
				main.showAddFrame();
			}
		});
		this.btnEditWithFile.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnEditWithFile.setBounds(756, 347, 202, 50);
		add(this.btnEditWithFile);
		
		this.btnClearRow = new JButton("Clear Row");
		this.btnClearRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRow();
			}
		});
		this.btnClearRow.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.btnClearRow.setBounds(756, 264, 202, 50);
		add(this.btnClearRow);
		main.setSize(1020,720);
		
		main.addTaskBar(this);
		
        this.lblFilter = new JLabel("");
        lblFilter.setOpaque(true);
        this.lblFilter.setBackground(new Color(220,220,220, 230));
        this.lblFilter.setBounds(0, 0, main.getWidth(), main.getHeight());
        add(this.lblFilter);
        
        this.lblBackground = new JLabel("Background");
		this.lblBackground.setBounds(0, 0, 1009, 690);
		lblBackground.setSize(main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackground.getWidth(), lblBackground.getHeight(), Image.SCALE_DEFAULT));
        lblBackground.setIcon(background);
        add(this.lblBackground);
		
		
		
		init();
	}
	
	public void init(){
		if(main.getCurrentAcct()[0].equals("A")){
//			btnUpdateUtility.setLocation(291,318);
			return;}
		btnAddUtility.hide();
		btnDelete.hide();
//		btnUpdateUtility.setLocation(291,318);
//		btnClearRow.setLocation(12,324);
	}
	

	public void addRow(){
//		main.getCont().addReading("i", 0.0, "i", 0.0);
//		redraw();
		main.showAddFrame();
//		redraw();
	}
	
	public void deleteRow(int row){
		if (row<0){return;}
		int c=0;
		int v=0;
		Vector<Object[]> temp = new Vector<>();
		Iterator<Object[]> iterator = changes.iterator();
		while(iterator.hasNext()){
			Object[] x = iterator.next();
			if (x[0].equals(row)){
//				changes.remove(c);
				v+=1;
				c+=1;
				continue;
			}
			if((int)x[0]>row){
				x[0]=(int)x[0]-1;
			}
			System.out.println(c+"   "+v+"    "+x[0]);
//			changes.setElementAt(x, c-v);
			temp.add(x);
			c+=1;
		}
		changes = new Vector<>();
		changes = (Vector<Object[]>) temp.clone();
		System.out.println(changes.isEmpty());
		if (changes.isEmpty()){unsaved = false;}

		main.getCont().removeReading(row);

	}
	public boolean isEqual(String[][] data2){
		if (data.length == data2.length){return false;}
		for (int i =0; i<data.length; i++){
			String[] d1 = data[i];
			String[] d2 = data2[i];
			if(!Arrays.equals(d1, d2)){return false;}
		}
		return true;
	}
	
	public void redraw(){
		this.model.setRowCount(0);
		int c=0;
		data= new String[main.getCont().allReadings().length][4];
		for (Readings r:main.getCont().allReadings()){
			Object[] x = {r.getUtilityName(),String.format("%.2f", r.getPrice()),r.getUnit(),r.getServiceCharge()};
//			finaldata.insertElementAt(x, c);
			System.out.println(x[0]+", "+x[1]+", "+x[2]+", "+x[3]+": "+c);
			data[c][0]=r.getUtilityName();
			data[c][1]=String.valueOf(r.getPrice());
			data[c][2]=r.getUnit();
			data[c][3]=String.valueOf(r.getServiceCharge());
			model.addRow(x);
			c+=1;
		}
		c=0;
		for(Object[] i:changes){
//			System.out.println(changes.size());
			System.out.println(String.join(","+i));
			// try {
				data[(int) i[0]][(int) i[1]]=String.valueOf(i[2]);
			// } catch (Exception e) {
			// }
			model.removeRow((int) i[0]);
			model.insertRow((int) i[0], data[(int) i[0]]);
		}
		table.setModel(model);
		table.repaint();
		
		
	}
	
	public void updateItems(){
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
		}
		redraw();
//		change = new Object[main.getCont().allReadings().length+1][4];
		int c=0;
//		for(Object[] i:changes){
////			System.out.println(i[0]+", "+i[1]+", "+i[2]);
//			Object[] y =finaldata.get((int) i[0]);
//			y[(int) i[1]]=i[2];
//			finaldata.set((int)i[0], y);
//			
//		}
//		c=0;
		for(Object[] s: data){
//			System.out.println(s[0].toString()+","+ Double.valueOf(s[1].toString())+", "+ s[2].toString()+", "+ Double.valueOf(s[3].toString())+", "+ c);
			try {
				main.getCont().updateReading(s[0].toString(), Double.valueOf(s[1].toString()), s[2].toString(), Double.valueOf(s[3].toString()), c);
			} catch (Exception e){

			}
			c+=1;
		}
		c=0;
		this.unsaved = false;
		redraw();
	}
	
	public void clearRow(){
		if (!(main.getCurrentAcct()[0].equals("A") || main.getCurrentAcct()[0].equals("S"))){return;}
		int selRow = table.getSelectedRow();
		if (selRow == -1){return;}
		Object[] r1 = {selRow, 1, 0.0};
		Object[] r2 = {selRow, 3, 0.0};
		changes.add(r1);
		changes.add(r2);
		unsaved = true;
		redraw();
	}
	
	public void editRow(String utilName, String price, String unit, String serCharge){
		int row=0;
		for (int i=0; i<data.length;i++){
			if(data[i][0].equals(utilName)){row=i;break;}
		}
		if(!price.equals("")){
			try {
				Double.valueOf(price);
			} catch (Exception e) {
				price = String.valueOf(0.0);
			}
			Object[] temp = {row, 1, price};
			changes.add(temp);
		}
		if(!unit.equals("")){
			Object[] temp = {row, 2, unit};
			changes.add(temp);
		}
		if(!serCharge.equals("")){
			try {
				Double.valueOf(serCharge);
			} catch (Exception e) {
				serCharge = String.valueOf(0.0);
			}
			Object[] temp = {row, 3, serCharge};
			String[] t = {String.valueOf(row), String.valueOf(3), String.valueOf(serCharge)};
			System.out.println(String.join(":", t));
			changes.add(temp);
		}
		unsaved = true;
		redraw();
	}
	
	public boolean back(){
		try {
			table.getCellEditor().stopCellEditing();
		} catch (Exception e) {
		}
		main.closeAddFrame();
		if (unsaved == true){
			String[] options = {"Save", "No","Cancel"};
			int selection = JOptionPane.showOptionDialog(null, "You have unsaved changes Save?", "Unsaved changes", 0,3,null,options,options[0]);
			if(!(selection == 1|| selection== 0)){return false;}
			else if (selection ==0){updateItems();}
		}
		
		return true;
	}
}
