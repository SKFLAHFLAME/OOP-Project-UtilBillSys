package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.Readings;
import controller.MainFrame;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditDraft extends JPanel {
    MainFrame main;
    private JButton btnBack;
    private JButton btnDelete;
    private JButton btnAddUtility;
    private JButton btnUpdateUtility;
    private JScrollPane scrollPane;
    
    private String[][] draft;
    private JTable table;
    private String[][] data;
    private boolean unsaved = false;
    private DefaultTableModel model;
    private Object[] columnNames = {"Utility Name", "Meter Reading", "Unit", "Price (S$)", "Service Charge", "Total Price(S$)"};
    private JLabel lblError;
    private JButton btnEdit;

    public EditDraft(MainFrame main) {
        this.main = main;
        main.setSize(580,480);
        setLayout(null);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(15, 36, 536, 292);
        add(this.scrollPane);

        this.model = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        this.table.setRowHeight(table.getRowHeight() + 10);
        this.table.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.table.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                int editedRow = table.getEditingRow();
                int editedCol = table.getEditingColumn();
                if (editedRow == -1 || editedCol == -1) {
                    return;
                }
                unsaved = true;
                Object item = table.getValueAt(editedRow, editedCol);
                if (editedCol == 1 || editedCol == 3) {
                    if (item.equals("")) {
                        item = 0.0;
                    }
                }
                Object[] i = {editedRow, editedCol, item};
                System.out.println(item);
                System.out.println(i[0] + ", " + i[1] + ", " + i[2]);
            }
        });
        this.scrollPane.setViewportView(this.table);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int edtRow = table.getSelectedRow();
                
                String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Deletion?", "Delete", 0, 3, null, options, options[1]);
				if(sel == 1){return;}
                
                deleteRow(edtRow);
                if (main.getCont().hasDraft(main.getCurrentAcct()[1])){
                	main.showAddMeterReading();
                }
            }
        });
        btnDelete.setBounds(15, 344, 115, 29);
        add(btnDelete);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showAddMeterReading();
            }
        });
        btnAdd.setBounds(159, 344, 115, 29);
        add(btnAdd);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Submission? It will remove this draft", "Submit", 0, 3, null, options, options[1]);
				if(sel == 1){return;}
                main.getCont().submitUserReading(main.getCurrentAcct()[1]);
                main.getCont().clearDraft(main.getCurrentAcct()[1]);;
                main.showCustMenu();
            }
        });
        btnSubmit.setBounds(436, 344, 115, 29);
        add(btnSubmit);

        JLabel lblEditDraft = new JLabel("Edit Draft");
        lblEditDraft.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEditDraft.setBounds(32, 16, 93, 20);
        add(lblEditDraft);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showCustMenu();
            }
        });
        btnBack.setBounds(218, 390, 115, 29);
        add(btnBack);
        
        this.lblError = new JLabel("");
        this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
        this.lblError.setForeground(Color.RED);
        this.lblError.setBounds(12, 386, 182, 35);
        add(this.lblError);
        
        this.btnEdit = new JButton("Edit");
        this.btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int sel = table.getSelectedRow();
        		if (sel ==-1){return;}
        		String name = (String) table.getValueAt(sel, 0);
        		String mr= (String) table.getValueAt(sel, 1);
        		main.showEditMeterReading(name, mr);
        	}
        });
        this.btnEdit.setBounds(306, 344, 115, 29);
        add(this.btnEdit);
        

        redraw();
    }

	public void addRow(){
//		main.getCont().addReading("i", 0.0, "i", 0.0);
//		redraw();
		main.showAddFrame();
//		redraw();
	}
	
	public void deleteRow(int row){
		if(row ==-1){
			return;
		}
		String readingName=(String) table.getValueAt(row, 0);
		main.getCont().removeMeterReading(main.getCurrentAcct()[1], readingName);
		redraw();

	}

    public void redraw() {
    	draft = main.getCont().getDraft(main.getCurrentAcct()[1]);
        this.model.setRowCount(0);
        for (String[] d :draft) {
        	System.out.println(String.join(", ", d));
        	try {
        		Readings r = main.getCont().getReading(d[0]);
                Object[] x = {d[0], d[1], r.getUnit(), String.format("%.2f", r.getPrice()), String.format("%.2f",r.getServiceCharge()), String.format("%.2f", main.getCont().calculateReading(r.getUtilityName(),String.valueOf(d[1])))};
                model.addRow(x);
			} catch (Exception e) {
				lblError.setText(d[0]+" Not Avaliable, Deleted");
				main.getCont().removeMeterReading(main.getCurrentAcct()[1], d[0]);
				continue;
				
				
			}
        	
        }
//        for (Object[] i : changes) {
//            data[(int) i[0]][(int) i[1]] = String.valueOf(i[2]);
//            model.removeRow((int) i[0]);
//            model.insertRow((int) i[0], data[(int) i[0]]);
//        }
        table.setModel(model);
        table.repaint();
    }
}
