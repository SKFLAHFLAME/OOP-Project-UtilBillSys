/**
 * 
 */
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;

import controller.MainFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author HSIEN ZHENG
 *
 */
public class ViewDraft extends JPanel{
	MainFrame main;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnEdit;
	private JButton btnBack;
	private JButton btnSave;
	private JButton btnSubmit;
	private JLabel lblDraft;
	public ViewDraft(MainFrame main) {
		this.main=main;
		private Object[] coloumnames={"Utility Name","Price (S$)","Unit"};
		setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 39, 372, 160);
		add(scrollPane);
		
		this.table = new JTable();
		scrollPane.setViewportView(table);
		
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

}
