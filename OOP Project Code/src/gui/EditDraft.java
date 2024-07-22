package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
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

public class EditDraft extends JPanel {
    MainFrame main;
    private JButton btnBack;
    private JButton btnDelete;
    private JButton btnAddUtility;
    private JButton btnUpdateUtility;
    private JScrollPane scrollPane;
    
    private String[][] draft;
    private JTable table;
    private Vector<Object[]> changes = new Vector<>();
    private String[][] data;
    private boolean unsaved = false;
    private DefaultTableModel model;
    private Object[] columnNames = {"Utility Name", "Meter Reading", "Unit", "Price (S$)", "Service Charge", "Total Price(S$)"};

    public EditDraft(MainFrame main) {
    	System.out.println(main.getCurrentAcct()[1]);
        this.main = main;
        setLayout(null);
        draft = main.getCont().getDraft(main.getCurrentAcct()[1]);

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
        redraw();
        this.scrollPane.setViewportView(this.table);
        main.setSize(500, 500);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int edtRow = table.getSelectedRow();
                try {
                    table.getCellEditor().cancelCellEditing();
                } catch (Exception e) {
                    // Handle exception if necessary
                }

                System.out.println(edtRow);
                deleteRow(edtRow);
                redraw();
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
        btnAdd.setBounds(218, 344, 115, 29);
        add(btnAdd);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
    }

	public void addRow(){
//		main.getCont().addReading("i", 0.0, "i", 0.0);
//		redraw();
		main.showAddFrame();
//		redraw();
	}
	
	public void deleteRow(int row){
		

	}

    public void redraw() {
        this.model.setRowCount(0);
        int c = 0;
        data = new String[main.getCont().allReadings().length][6];
        for (String[] d :draft) {
        	System.out.println(String.join(", ", d));
        	Readings r = main.getCont().getReading(d[0]);
            Object[] x = {d[0], d[1], r.getUnit(), String.format("%.2f", r.getPrice()), String.format("%.2f",r.getServiceCharge()), String.format("%.2f", main.getCont().calculateReading(r.getUtilityName(),String.valueOf(d[1])))};
            model.addRow(x);
            c += 1;
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
