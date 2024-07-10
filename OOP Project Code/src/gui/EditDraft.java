/**
 * 
 */
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.MainFrame;
import data.Readings;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * @author HSIEN ZHENG
 *
 */
public class EditDraft extends JPanel{
	MainFrame main;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnAddUtility;
	private JButton btnUpdateUtility;
	private JScrollPane scrollPane;
	private JTable table;
	private Vector<Object[]> changes = new Vector<>();
	private String[][]data;
//	private Vector<Object[]> finaldata=new Vector<>();
	private DefaultTableModel model;
	private Object[] coloumnames={"Utility Name","Price (S$)","Unit"};
	public EditDraft(MainFrame main) {
		this.main=main;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 54, 382, 175);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int edtRow= table.getSelectedRow();
					try {
						table.getCellEditor().cancelCellEditing();
					} catch (Exception e) {
						
					}
					
					System.out.println(edtRow);
					deleteRow(edtRow);
					redraw();
				}
			});
		btnDelete.setBounds(33, 234, 115, 29);
		add(btnDelete);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showAddMeterReading();
			}
		});
		btnAdd.setBounds(170, 234, 115, 29);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showEditMeterReading();
			}
		});
		btnUpdate.setBounds(300, 234, 115, 29);
		add(btnUpdate);
		
		JLabel lblEditDraft = new JLabel("Edit Draft");
		lblEditDraft.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEditDraft.setBounds(32, 16, 93, 20);
		add(lblEditDraft);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showViewDraft();
			}
		});
		btnBack.setBounds(0, 271, 115, 29);
		add(btnBack);
	}
		public void deleteRow(int row){
			if (row<0){return;}
			main.getCont().removeReading(row);
			int c=0;
			int v=0;
			for (Object[] x:changes){
				if (x[0].equals(row)){
					changes.remove(c);
					v+=1;
					c+=1;
					return;
				}
				if((int)x[0]>row){
					x[0]=(int)x[0]-1;
				}
				System.out.println(c+"   "+v+"    "+x[0]);
				changes.setElementAt(x, c-v);
				c+=1;
			}
			v=0;
			c=0;
		}
		
		public void redraw(){
			this.model.setRowCount(0);
			int c=0;
			data= new String[main.getCont().allReadings().length][4];
			for (Readings r:main.getCont().allReadings()){
				Object[] x = {r.getUtilityName(),String.format("%.2f", r.getPrice()),r.getUnit(),r.getServiceCharge()};
//				finaldata.insertElementAt(x, c);
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
				data[(int) i[0]][(int) i[1]]=String.valueOf(i[2]);
				model.removeRow((int) i[0]);
				model.insertRow((int) i[0], data[(int) i[0]]);
			}
			table.setModel(model);
			table.repaint();
		}
}
