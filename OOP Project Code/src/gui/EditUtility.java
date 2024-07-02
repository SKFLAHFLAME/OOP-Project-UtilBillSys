package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.Readings;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.PrimitiveIterator.OfDouble;
import java.beans.PropertyChangeEvent;

public class EditUtility extends JPanel{
	MainFrame main;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnAddUtility;
	private JButton btnUpdateUtility;
	private Object[] coloumnames={"Utility Name","Price (S$)","Unit", "Service Charge (%)"};
	private JScrollPane scrollPane;
	private JTable table;
	private Vector<Object[]> changes = new Vector<>();
	private String[][]data;
//	private Vector<Object[]> finaldata=new Vector<>();
	private DefaultTableModel model;
	public EditUtility(MainFrame m){
		this.main = m;
		this.setLayout(null);
		
		this.btnBack = new JButton("Back");
		this.btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.closeAddFrame();
				if (main.getPrepage()==true){
					main.showAdminMenu();
				}
				else{main.showStaffMenu();}
			}
		});
		this.btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.btnBack.setBounds(187, 415, 97, 25);
		add(this.btnBack);
		
		this.btnDelete = new JButton("Delete");
		this.btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int edtRow= table.getSelectedRow();
				System.out.println(edtRow);
				deleteRow(edtRow);
				redraw();
			}
		});
		this.btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnDelete.setBounds(12, 318, 117, 52);
		add(this.btnDelete);
		
		this.btnAddUtility = new JButton("Add Utility");
		this.btnAddUtility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRow();
				
			}
		});
		this.btnAddUtility.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnAddUtility.setBounds(162, 319, 117, 50);
		add(this.btnAddUtility);
		
		this.btnUpdateUtility = new JButton("Update Utilities");
		this.btnUpdateUtility.addActionListener(new ActionListener() {
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
		this.btnUpdateUtility.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnUpdateUtility.setBounds(291, 318, 175, 52);
		add(this.btnUpdateUtility);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 13, 454, 292);
		add(this.scrollPane);
		
		this.model = new DefaultTableModel(coloumnames, 0);
		this.table = new JTable(model);
		this.table.setRowHeight(table.getRowHeight()+10);
		this.table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.table.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				int editedRow=table.getEditingRow();
				int editedCol=table.getEditingColumn();
				if (editedRow==-1||editedCol==-1){return;}
				Object item = table.getValueAt(editedRow, editedCol);
				Object[] i = {editedRow,editedCol,item};
				changes.add(i);
				System.out.println(item);
				System.out.println(i[0]+", "+i[1]+", "+i[2]);
			}
		});
		redraw();
		this.scrollPane.setViewportView(this.table);
		main.setSize(500,500);
	}
	
	public void addRow(){
//		main.getCont().addReading("i", 0.0, "i", 0.0);
//		redraw();
		main.showAddFrame();
//		redraw();
	}
	
	public void deleteRow(int row){
		if (row<0){return;}
		main.getCont().removeReading(row);
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
			data[(int) i[0]][(int) i[1]]=(String) i[2];
			model.removeRow((int) i[0]);
			model.insertRow((int) i[0], data[(int) i[0]]);
		}
		table.setModel(model);
		table.repaint();
		
		
	}
	
	public void updateItems(){
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
			main.getCont().updateReading(s[0].toString(), Double.valueOf(s[1].toString()), s[2].toString(), Double.valueOf(s[3].toString()), c);
			c+=1;
		}
		c=0;
		redraw();
	}
}
