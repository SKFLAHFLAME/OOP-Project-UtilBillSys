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
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class EditDraft extends JPanel {
    MainFrame main;
    private JButton btnDelete;
    private JButton btnAddUtility;
    private JButton btnUpdateUtility;
    private JScrollPane scrollPane;
    
    private String[][] draft;
    private JTable table;
    private String[][] data;
    private boolean unsaved = false;
    private DefaultTableModel model;
    private Object[] columnNames = {"Utility Name", "Meter Reading", "Previous Reading", "Price", "Tax", "Amount Used"};
    private JLabel lblError;
    private JButton btnEdit;
    private JComboBox comboBox;
    private JTextField txtReading;

    public EditDraft(MainFrame main) {
        this.main = main;
        main.setSize(1020,720);
        setLayout(null);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(22, 81, 954, 292);
        add(this.scrollPane);

        
        
        this.model = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        table.setRowHeight(25);
        this.table. getTableHeader(). setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
        this.table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
        table.getColumnModel().getColumn(5).setPreferredWidth(90);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        this.table.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.table.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
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
        
        
        JLabel lblEditDraft = new JLabel("Edit Draft");
        this.scrollPane.setColumnHeaderView(lblEditDraft);
        lblEditDraft.setFont(new Font("Tahoma", Font.PLAIN, 20));

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int edtRow = table.getSelectedRow();
                if (edtRow == -1){return;}
                String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Deletion?", "Delete", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
                deleteRow(edtRow);
                if (!main.getCont().hasDraft(main.getCurrentAcct()[1])){
                	main.showAddMeterReading();
                }
            }
        });
        btnDelete.setBounds(405, 575, 129, 50);
        add(btnDelete);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showAddMeterReading();
            }
        });
        btnAdd.setBounds(546, 576, 129, 50);
        add(btnAdd);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Submission? It will remove this draft", "Submit", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
                main.getCont().submitUserReading(main.getCurrentAcct()[1]);
                main.getCont().clearDraft(main.getCurrentAcct()[1]);;
                main.showCustMenu();
            }
        });
        btnSubmit.setBounds(835, 576, 129, 47);
        add(btnSubmit);
        
        this.lblError = new JLabel("");
        this.lblError.setFont(new Font("Tahoma", Font.ITALIC, 15));
        this.lblError.setForeground(Color.RED);
        this.lblError.setBounds(12, 386, 182, 35);
        add(this.lblError);
        
        this.btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int sel = table.getSelectedRow();
        		if (sel ==-1||sel==model.getRowCount()-1){return;}
        		String name = (String) table.getValueAt(sel, 0);
        		String mr= (String) table.getValueAt(sel, 1);
        		main.showEditMeterReading(name, mr);
        	}
        });
        this.btnEdit.setBounds(687, 574, 129, 50);
        add(this.btnEdit);
        
        TaskBar bar = new TaskBar(this, main);
        
        this.comboBox = new JComboBox();
        this.comboBox.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.comboBox.setBounds(130, 406, 213, 50);
        add(this.comboBox);
        
        this.txtReading = new JTextField();
        this.txtReading.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtReading.setBounds(130, 499, 213, 50);
        add(this.txtReading);
        this.txtReading.setColumns(10);
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
        double total=0;
        for (String[] d :draft) {
        	System.out.println(String.join(", ", d));
        	try {
        		Readings r = main.getCont().getReading(d[0]);
                Object[] x = {d[0], d[1], "", "$"+String.format("%.2f", r.getPrice())+"/"+r.getUnit(), String.format("%.2f",r.getServiceCharge())+"%", Integer.valueOf(d[1])-Integer.valueOf("0")};
                model.addRow(x);
                total+=main.getCont().calculateReading(r.getUtilityName(),String.valueOf(d[1]));
			} catch (Exception e) {
				lblError.setText(d[0]+" Not Avaliable, Deleted");
				main.getCont().removeMeterReading(main.getCurrentAcct()[1], d[0]);
				continue;
				
				
			}
        	
        }
//        String.format("%.2f", main.getCont().calculateReading(r.getUtilityName(),String.valueOf(d[1])))
//        Object[] tot= {"Total","","","","",String.format("%.2f", total)};
//        model.addRow(tot);
//        for (Object[] i : changes) {
//            data[(int) i[0]][(int) i[1]] = String.valueOf(i[2]);
//            model.removeRow((int) i[0]);
//            model.insertRow((int) i[0], data[(int) i[0]]);
//        }
        table.setModel(model);
        table.repaint();
    }
}
