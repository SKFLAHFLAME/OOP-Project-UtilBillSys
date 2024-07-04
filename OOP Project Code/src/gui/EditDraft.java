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

import controller.MainFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author HSIEN ZHENG
 *
 */
public class EditDraft extends JPanel{
	MainFrame main;
	private JTable table;
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
		btnDelete.setBounds(33, 234, 115, 29);
		add(btnDelete);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.showEditMeterReading();
			}
		});
		btnAdd.setBounds(170, 234, 115, 29);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
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
}
