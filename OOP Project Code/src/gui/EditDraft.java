package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.print.attribute.standard.MediaName;
import javax.sound.midi.VoiceStatus;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.Readings;
import controller.MainFrame;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.management.ManagementPermission;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditDraft extends JPanel {
    MainFrame main;
    private JButton btnReset;
    private JButton btnAddUtility;
    private JButton btnUpdateUtility;
    private JScrollPane scrollPane;
    private ImageIcon logo = new ImageIcon(EditDraft.class.getResource("/images/logo.png"));
    private ImageIcon background = new ImageIcon(EditDraft.class.getResource("/images/background.jpg"));
    private String[][] draft;
    private JTable table;
    private String[][] data;
    private boolean unsaved = false;
    private DefaultTableModel model;
    private DefaultTableModel priceModel;
    private Object[] columnNames = {"Utility Name", "Meter Reading", "Previous Reading", "Amount Used", "Cost"};
    private JLabel lblError;
    private JButton btnEdit;
    private JScrollPane scrollPane_1;
    private JTable tablePrice;
    private JLabel lblDate;
    private JTextArea txtrCurrentBill;
    private JLabel lblBillPrice;
    private JTextField txtTotal;
    private JLabel lblBackGround;
    private JLabel lblFilter;
    private JLabel lblLogo;
    private JLabel lblPsGroup;
    private String [][] lastBill;
    private String[] billDate;
    private String[] sysDate;
    private String userName;
    private JButton btnBack;

    public EditDraft(MainFrame main, String user) {
        this.main = main;
        main.setSize(1020,720);
        setLayout(null);
        main.addTaskBar(this);
        userName = user;
        lastBill = main.getCont().getLastUserReading(userName);
        
        sysDate = main.getCont().getSystemDate();
        
        
        try {
        	billDate = lastBill[0][2].split("/");
		} catch (Exception e) {
			// TODO: handle exception
		}
        

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(12, 120, 977, 292);
        add(this.scrollPane);

        
        
        this.model = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        this.table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if (arg0.getClickCount()==2){
        			int sel = table.getSelectedRow();
            		if (sel ==-1){return;}
            		String name = (String) table.getValueAt(sel, 0);
            		String mr= (String) table.getValueAt(sel, 1);
            		main.showPopup("EditMeterReading", String.join(",", userName,name, mr));
        		}
        	}
        });
        table.setRowHeight(25);
        table.setShowGrid(true);
        this.table. getTableHeader(). setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
        this.table.setRowHeight(table.getRowHeight() + 10);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        this.table.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.table.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
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
				int sel = JOptionPane.showOptionDialog(null, "Confirm Reset?", "Reset", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
                resetRow(edtRow);
            }
        });
        btnReset.setBounds(838, 482, 151, 50);
        add(btnReset);

        JButton btnSubmit = new JButton("Confirm");
        btnSubmit.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String[] options = {"Yes", "No"};
				int sel = JOptionPane.showOptionDialog(null, "Confirm Change? This will Change Bill according to changes", "Submit", 0, 3, null, options, options[1]);
				if(sel != 0){return;}
                main.getCont().updateUserReading(userName);
                main.showEditDraft(userName);
            }
        });
        
        this.btnBack = new JButton("Back");
        this.btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		main.closePopup();
        		if (main.getCurrentAcct()[0].equals("C")){
        			main.showCustMenu();
        		}
        		else{main.flag=true;main.showAllCustomers();}
        	}
        });
        this.btnBack.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.btnBack.setBounds(672, 482, 151, 50);
        add(this.btnBack);
        btnSubmit.setBounds(672, 608, 317, 47);
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
        		main.showPopup("EditMeterReading", String.join(",", userName,name, mr));
        	}
        });
        this.btnEdit.setBounds(672, 545, 317, 50);
        add(this.btnEdit);
        
        
        this.scrollPane_1 = new JScrollPane();
        this.scrollPane_1.setBounds(12, 425, 360, 230);
        add(this.scrollPane_1);
        
        
        String [] colName = {"Name","Price","Tax"};
    	priceModel = new DefaultTableModel(colName, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tablePrice = new JTable(priceModel);
        this.tablePrice. getTableHeader(). setReorderingAllowed(false);
		this.tablePrice.getTableHeader().setResizingAllowed(false);
        tablePrice.getColumnModel().getColumn(1).setPreferredWidth(40);
        tablePrice.getColumnModel().getColumn(2).setPreferredWidth(10);
        tablePrice.setShowGrid(true);
        tablePrice.setRowHeight(35);
        this.tablePrice.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        tablePrice.getTableHeader().setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        tablePrice.setEnabled(false);
        this.scrollPane_1.setViewportView(this.tablePrice);
        
        this.lblDate = new JLabel("Bill Date: ");
        this.lblDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblDate.setBounds(22, 81, 254, 26);
        add(this.lblDate);
        
        this.txtrCurrentBill = new JTextArea();
        this.txtrCurrentBill.setEditable(false);
        txtrCurrentBill.setOpaque(false);
        this.txtrCurrentBill.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtrCurrentBill.setText("Current Bill");
        this.txtrCurrentBill.setBounds(384, 425, 276, 230);
        add(this.txtrCurrentBill);
        
        this.lblBillPrice = new JLabel("Bill Price: ");
        this.lblBillPrice.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.lblBillPrice.setBounds(754, 423, 96, 35);
        add(this.lblBillPrice);
        
        this.txtTotal = new JTextField();
        this.txtTotal.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
        this.txtTotal.setEditable(false);
        this.txtTotal.setText("$");
        this.txtTotal.setBounds(860, 423, 129, 35);
        add(this.txtTotal);
        this.txtTotal.setColumns(10);
        
        this.lblLogo = new JLabel("logo");
        this.lblLogo.setBounds(401, 49, 67, 58);
        logo.setImage(logo.getImage().getScaledInstance(lblLogo.getHeight(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
        lblLogo.setIcon(logo);
        add(this.lblLogo);
        
        this.lblPsGroup = new JLabel("PS Group");
        this.lblPsGroup.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
        this.lblPsGroup.setBounds(480, 65, 113, 30);
        add(this.lblPsGroup);
        
        this.lblFilter = new JLabel("");
        lblFilter.setOpaque(true);
        this.lblFilter.setBackground(new Color(220,220,220, 230));
        this.lblFilter.setBounds(0, 0, main.getWidth(), main.getHeight());
        add(this.lblFilter);
        
        this.lblBackGround = new JLabel("");
        this.lblBackGround.setBounds(0, 0, 999, 699);
        lblBackGround.setSize(main.getWidth(), main.getHeight());
        background.setImage(background.getImage().getScaledInstance(lblBackGround.getWidth(), lblBackGround.getHeight(), Image.SCALE_DEFAULT));
        lblBackGround.setIcon(background);
        add(this.lblBackGround);
        
        
        
        
        init();
        
        
        
    }
    
    public void init(){
    	fillCurrentPrices();
    	redraw();
    	
    	// Tells program to run later
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	showCurrentBill();
            	if (lastBill==null){
                	
                	if (main.getCurrentAcct()[0].equals("C")) {
                		main.showCustMenu();
                		JOptionPane.showMessageDialog(null, "No History", "Error", JOptionPane.ERROR_MESSAGE);
                	} else {
                		String[] options = {"Yes", "No"};
        				int sel = JOptionPane.showOptionDialog(null, "No History, Generate Bill?", "No History", 0, 3, null, options, options[1]);
        				if(sel != 0){main.showAllCustomers();return;}
        				main.getCont().generateBills(userName);
        				init();
                	}
            	}
            }
        });
    	
    	
    	
        
    }
    public void showCurrentBill(){
    	//bill area
    	if (lastBill==null){txtrCurrentBill.setText("No History");return;}
    	String text = "Current Bill: "+lastBill[0][2]+ '\n';
    	double total =0;
    	for (int i =0; i<lastBill.length-1; i++){
    		text+= lastBill[i+1][0] +" : "+lastBill[i+1][1]+"\n";
    		total+=Double.valueOf(lastBill[i+1][2]);
    	}
    	text+="Total : $"+String.format("%.2f", total);
    	txtrCurrentBill.setText(text);
    	lblDate.setText("Bill Date: "+billDate[1]+"/"+billDate[2]);
    }
    
    public void fillCurrentPrices(){
    	//price Table
    	priceModel.setRowCount(0);
        Readings[] readings = main.getCont().getAllReadings();
        for(Readings r:readings){
        	String[] row = {r.getUtilityName(), "$"+r.getPrice()+"/"+r.getUnit(), ""+r.getServiceCharge()+"%"};
        	priceModel.addRow(row);
        }
        tablePrice.setModel(priceModel);
        tablePrice.repaint();
    }
    
    
	
	
	public void resetRow(int row){
		if(row ==-1){
			return;
		}
		String readingName=(String) table.getValueAt(row, 0);
		int currentReading = main.getCont().getCurrentTotalReading(userName, readingName);
		main.getCont().editMeterReading(userName, readingName, currentReading);
		
		main.showEditDraft(userName);

	}

    public void redraw() {
    	draft = main.getCont().getDraft(userName);
        this.model.setRowCount(0);
        double total=0;
        for (String[] d :draft) {
        	System.out.println(String.join(", ", d));
        	try {
        		Readings r = main.getCont().getReading(d[0]);
        		int currentTotal = Integer.valueOf(d[1]);
        		int pastTotal = main.getCont().getPastTotalReading(userName, r.getUtilityName());
        		int amtUsed = currentTotal - pastTotal;
        		
        		
                Object[] x = {d[0],d[1],pastTotal,amtUsed,"$"+String.format("%.2f", main.getCont().calculateReading(r.getUtilityName(),""+amtUsed))};
                model.addRow(x);
                total+=main.getCont().calculateReading(r.getUtilityName(),""+amtUsed);
			} catch (Exception e) {
				lblError.setText(d[0]+" Not Avaliable, Deleted");
				main.getCont().removeMeterReading(userName, d[0]);
				continue;

			}
        	txtTotal.setText(String.format("%.2f", total));
        	
        }
        table.setModel(model);
        table.repaint();
    }
    
    
    
}
