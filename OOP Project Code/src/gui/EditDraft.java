package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.sound.midi.VoiceStatus;
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
import javax.swing.JTextArea;

public class EditDraft extends JPanel {
    MainFrame main;
    private JButton btnReset;
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
    private JScrollPane scrollPane_1;
    private JTable tablePrice;
    private JLabel lblDate;
    private JTextArea txtrCurrentBill;
    private JLabel lblBillPrice;
    private JTextField txtTotal;

    public EditDraft(MainFrame main) {
        this.main = main;
        main.setSize(1020,720);
        setLayout(null);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(22, 104, 954, 292);
        add(this.scrollPane);

        
        
        this.model = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        table.setRowHeight(25);
        table.setShowGrid(true);
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

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnReset.addActionListener(new ActionListener() {
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
        btnReset.setBounds(672, 491, 140, 50);
        add(btnReset);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showAddMeterReading();
            }
        });
        btnAdd.setBounds(790, 18, 129, 50);
        add(btnAdd);

        JButton btnSubmit = new JButton("Confirm");
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
        btnSubmit.setBounds(672, 569, 315, 47);
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
        		if (sel ==-1){return;}
        		String name = (String) table.getValueAt(sel, 0);
        		String mr= (String) table.getValueAt(sel, 1);
        		main.showEditMeterReading(name, mr);
        	}
        });
        this.btnEdit.setBounds(847, 491, 140, 50);
        add(this.btnEdit);
        
        TaskBar bar = new TaskBar(this, main);
        
        this.scrollPane_1 = new JScrollPane();
        this.scrollPane_1.setBounds(22, 414, 333, 241);
        add(this.scrollPane_1);
        
        this.tablePrice = new JTable();
        this.tablePrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        this.scrollPane_1.setViewportView(this.tablePrice);
        
        this.lblDate = new JLabel("Bill Date: ");
        this.lblDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblDate.setBounds(22, 65, 254, 26);
        add(this.lblDate);
        
        this.txtrCurrentBill = new JTextArea();
        this.txtrCurrentBill.setEditable(false);
        this.txtrCurrentBill.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentBill.setText("Current Bill");
        this.txtrCurrentBill.setBounds(367, 414, 276, 241);
        add(this.txtrCurrentBill);
        
        this.lblBillPrice = new JLabel("Bill Price: ");
        this.lblBillPrice.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.lblBillPrice.setBounds(760, 414, 83, 35);
        add(this.lblBillPrice);
        
        this.txtTotal = new JTextField();
        this.txtTotal.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
        this.txtTotal.setEditable(false);
        this.txtTotal.setText("$");
        this.txtTotal.setBounds(847, 414, 129, 35);
        add(this.txtTotal);
        this.txtTotal.setColumns(10);
        redraw();
        init();
        
    }
    
    public void init(){
    	String [][] lastBill = main.getCont().getLastUserReading(main.getCurrentAcct()[1]);
    	String text = "Current Bill: "+lastBill[0][2]+ '\n';
    	int total =0;
    	for (int i =0; i<lastBill.length-1; i++){
    		text+= lastBill[i+1][0] +" : "+lastBill[i+1][1]+"\n";
    		total+=Double.valueOf(lastBill[i+1][2]);
    	}
    	text+="Total : $"+total;
    	txtrCurrentBill.setText(text);
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
