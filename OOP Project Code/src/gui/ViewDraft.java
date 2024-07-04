/**
 * 
 */
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.MainFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * @author HSIEN ZHENG
 *
 */
public class ViewDraft extends JPanel{
	MainFrame main;
	private JTable table;
	private Object[] coloumnames={"Utility Name","Price (S$)","Unit"};
	private Vector<Object[]> changes = new Vector<>();
	private JScrollPane scrollPane;
	private JButton btnEdit;
	private JButton btnBack;
	private JButton btnSave;
	private JButton btnSubmit;
	private JLabel lblDraft;
	private DefaultTableModel model;
	public ViewDraft(MainFrame main) {
		this.main=main;
		setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 39, 372, 160);
		add(scrollPane);
		
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
		
		this.btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.showEditDraft();
			}
		});
		btnEdit.setBounds(15, 203, 115, 29);
		add(btnEdit);
		
		this.btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.showCustMenu();
			}
		});
		btnBack.setBounds(15, 255, 115, 29);
		add(btnBack);
		
		this.btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(173, 255, 115, 29);
		add(btnSave);
		
		this.btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(335, 255, 115, 29);
		add(btnSubmit);
		
		this.lblDraft = new JLabel("Draft");
		lblDraft.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDraft.setBounds(36, 16, 69, 20);
		add(lblDraft);
	}
	private void redraw() {
		// TODO Auto-generated method stub
		
	}

}
