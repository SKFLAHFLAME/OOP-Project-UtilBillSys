package gui;

import controller.MainFrame;
import data.Readings;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	private boolean unsaved = false;
//	private Vector<Object[]> finaldata=new Vector<>();
	private DefaultTableModel model;
	public EditUtility(MainFrame m){
		this.main = m;
		this.setLayout(null);
		
		this.btnBack = new JButton("Back");
		btnBack.setBorderPainted(false);
//		btnBack.setBackground(Color.LIGHT_GRAY);
		this.btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				main.closeAddFrame();
				if (unsaved == true){
					String[] options = {"Save", "No","Cancel"};
					int selection = JOptionPane.showOptionDialog(null, "You have unsaved changes Save?", "Unsaved changes", 0,3,null,options,options[0]);
					if(selection == 2){return;}
					else if (selection ==0){updateItems();}
				}
				

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
				if(sel == 1){return;}
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addRow();
				
			}
		});
		this.btnAddUtility.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.btnAddUtility.setBounds(162, 319, 117, 50);
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
			System.out.println(changes.size());
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
}
