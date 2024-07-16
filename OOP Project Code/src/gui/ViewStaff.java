package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class ViewStaff extends JPanel{
	MainFrame main;
	private JScrollPane scrollPane;
	private JTree tree;
	
	public ViewStaff(MainFrame m) {
		this.main=m;
		this.setLayout(null);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 68, 366, 256);
		add(this.scrollPane);
		
		this.tree = new JTree();
		this.scrollPane.setViewportView(this.tree);
		main.setSize(400, 500);
	}
}
