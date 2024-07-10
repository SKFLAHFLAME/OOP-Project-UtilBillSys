package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class FileChooser extends JFrame implements ActionListener{
	private JButton save;
	private JFileChooser fc;
	
	
	public FileChooser() {
		this.setTitle("File Chooser");
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		this.add(fc);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		FileChooser fChooser = new FileChooser();
	}

}
