package gui;

import javax.swing.JPanel;

import controller.MainFrame;
import data.Customer;
import data.Readings;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.event.TreeSelectionEvent;

public class ViewAllBills extends JPanel{
    MainFrame main; // Reference to the main application frame
	private JScrollPane scrollPane;
	private JTree tree;
	private JLabel lblMonth;
	private JComboBox comboMonth;
	private JLabel lblYear;
	private JComboBox comboYear;
	private DefaultTreeModel model;
	DefaultMutableTreeNode header = new DefaultMutableTreeNode("Date");
	private String[] date;
	private String[] month = {"All","January", "February", "March","April","May","June","July","August","September","October","November","December"};
	private String[] year;
	private JTextField textField;
	private JLabel lblSearchUser;
	private JTextArea txtrUnits;
	private JLabel lblcaseSensetive;
	private JLabel lblCustomerBills;
	private JButton btnExport;
	
	public ViewAllBills(MainFrame m){
        main = m; // Initialize main application frame
		this.setLayout(null);
		main.setSize(1020,720);
		
		// Initialize month/year array
        date = main.getCont().getSystemDate(); // Retrieve current system date
        Vector<String> temp = new Vector<>();
        temp.add("All"); // Add "All" option for year selection
        for (int i = Integer.valueOf(date[1]); i >= 2000; i--) {
            temp.add(String.valueOf(i)); // Add years from current to 2000
        }
        year = new String[temp.size()];
        temp.toArray(year); // Convert vector to array
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(12, 83, 650, 560);
		add(this.scrollPane);
		
		
		model = new DefaultTreeModel(header);
		this.tree = new JTree(model);
		this.tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getPath().getPathCount()==4){
					btnExport.show();
				}
				else {btnExport.hide();}
			}
		});
		tree.setRowHeight(30);
		this.tree.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.scrollPane.setViewportView(this.tree);
		
		this.lblCustomerBills = new JLabel("Customer Bills");
		this.lblCustomerBills.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.lblCustomerBills.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane.setColumnHeaderView(this.lblCustomerBills);
		
		this.lblMonth = new JLabel("Month:");
		this.lblMonth.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblMonth.setBounds(674, 210, 83, 37);
		add(this.lblMonth);
		
		this.comboMonth = new JComboBox(month);
		comboMonth.setSelectedIndex(0);
		this.comboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
		});
		this.comboMonth.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.comboMonth.setBounds(769, 205, 178, 45);
		add(this.comboMonth);
		
		this.lblYear = new JLabel("Year:");
		this.lblYear.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblYear.setBounds(674, 144, 83, 38);
		add(this.lblYear);
		
		// Initialize and configure year combo box
        this.comboYear = new JComboBox(year);
        comboYear.setSelectedItem("All"); // Set default selection

        this.comboYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selMonth = comboMonth.getSelectedIndex(); // Get selected month
                if (comboYear.getSelectedItem().equals(date[1])) { // If the selected year is the current year
                    Vector<String> temp = new Vector<>();
                    int c = 0;
                    for (String m : month) {
                        temp.add(m); // Add months up to the current month
                        if (c == Integer.parseInt(date[0])) {
                            break;
                        }
                        c++;
                    }
                    month = new String[temp.size()];
                    temp.toArray(month); // Update month array
                    comboMonth.setModel(new DefaultComboBoxModel<>(month)); // Update combo box model
                    try {
                        comboMonth.setSelectedIndex(selMonth); // Set selected month
                    } catch (Exception e2) {
                        comboMonth.setSelectedIndex(comboMonth.getModel().getSize() - 1); // Fallback if exception occurs
                    }
                } else {
                    String[] mon = {"All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                    month = mon; // Reset month options
                    comboMonth.setModel(new DefaultComboBoxModel<>(month)); // Update combo box model
                    comboMonth.setSelectedIndex(selMonth); // Set selected month
                }
                searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText()); // Search tree based on year selection
            }
        });
		this.comboYear.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		
		this.comboYear.setBounds(769, 140, 178, 45);
		add(this.comboYear);
		
		this.textField = new JTextField();
		this.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
			}
		});
		this.textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.textField.setBounds(668, 336, 279, 45);
		add(this.textField);
		this.textField.setColumns(10);
		
		this.lblSearchUser = new JLabel("Search User:");
		this.lblSearchUser.setFont(new Font("STXinwei", Font.PLAIN, 25));
		this.lblSearchUser.setBounds(668, 278, 150, 45);
		add(this.lblSearchUser);
		
		this.txtrUnits = new JTextArea();
		txtrUnits.setOpaque(false);
		txtrUnits.setEditable(false);
		this.txtrUnits.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		this.txtrUnits.setText("Units");
		this.setUnits();
		this.txtrUnits.setBounds(674, 409, 279, 235);
		add(this.txtrUnits);
		
		searchTree(comboMonth.getSelectedIndex(), comboYear.getSelectedItem(), textField.getText());
		
		main.addTaskBar(this);
		
		this.lblcaseSensetive = new JLabel("*Case Sensetive");
		this.lblcaseSensetive.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		this.lblcaseSensetive.setForeground(Color.BLUE);
		this.lblcaseSensetive.setBounds(817, 288, 136, 26);
		add(this.lblcaseSensetive);
		
		this.btnExport = new JButton("");
		btnExport.hide();
		this.btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tree.getSelectionPath().getLastPathComponent().toString().split(" : ")[0];
				String[] date = tree.getSelectionPath().getLastPathComponent().toString().split(" : ")[1].split("/");
				if (main.getCont().printBills(user, date[1], date[2])){
					JOptionPane.showMessageDialog(null, "Saved Bill");
				}
				else {JOptionPane.showMessageDialog(null, "Bill Saved failed");}
			}
		});
		this.btnExport.setBounds(889, 83, 58, 37);
		ImageIcon print = new ImageIcon(this.getClass().getResource("/images/print.png"));
		print.setImage(print.getImage().getScaledInstance(btnExport.getHeight(), btnExport.getHeight(), Image.SCALE_DEFAULT));
		btnExport.setIcon(print);
		add(this.btnExport);
	}

	// Method to search and filter tree data
	public void searchTree(Object M, Object Y, String user) { // Method to search and filter tree data
	    String month = String.format("%02d", M); // Format month as two digits
	    String year = (String) Y; // Convert year object to string
	    String[] monthList = {month}; // Initialize monthList with selected month
	    String[] yearList = {year}; // Initialize yearList with selected year
	    header.removeAllChildren(); // Clear all children from the root node

	    // Adjust monthList if month is 0 (to include all months)
	    if (M.equals(0)) {
	        String[] temp = new String[this.month.length - 1];
	        for (int i = 0; i < this.month.length - 1; i++) {
	            temp[i] = String.format("%02d", i + 1); // Format months as two digits
	        }
	        monthList = temp; // Update monthList with all months
	    }

	    // Adjust yearList if year is "All" (to include all years)
	    if (year.equals("All")) {
	        String[] temp = new String[this.year.length - 1];
	        for (int i = 0; i < this.year.length - 1; i++) {
	            temp[i] = this.year[i + 1]; // Exclude the first year from list
	        }
	        yearList = temp; // Update yearList with all years
	    }

	    // Iterate through each year in yearList
	    for (String y : yearList) {
	        if ((int) M > Integer.valueOf(date[0]) && date[1].equals(y)) {
	            continue; // Skip if month is beyond the current year
	        }
	        DefaultMutableTreeNode yr = new DefaultMutableTreeNode(y); // Create node for year
	        header.add(yr); // Add year node to header
	        double yearTotal = 0; // Initialize total for the year
	        boolean continuee = true; // Flag to control loop continuation

	        // Iterate through each month in monthList
	        for (String m : monthList) {
	            if (!continuee) {
	                continue; // Skip if loop continuation flag is false
	            }
	            if (y.equals(date[1]) && Integer.valueOf(date[0]) == Integer.valueOf(m)) {
	                continuee = false; // Stop processing if the month is the current month
	            }
	            DefaultMutableTreeNode mth = new DefaultMutableTreeNode(this.month[Integer.valueOf(m)]); // Create node for month
	            yr.add(mth); // Add month node to year node

	            Customer[] accts = main.getCont().getAllCustomers(); // Get all customers
	            Vector<String[][]> filtered = new Vector<>(); // Vector to hold filtered bills

	            // Get bills for the specified month and year
	            String[][][] bills = main.getCont().getUserReading(m, y);
	            double monthlyTotal = 0; // Initialize total for the month

	            // Filter bills based on user
	            for (String[][] b : bills) {
	                for (String[] i : b) {
	                    try {
	                        monthlyTotal += Double.parseDouble(i[2]); // Calculate monthly total
	                    } catch (Exception e) {
	                        continue; // Skip if parsing fails
	                    }
	                }
	                if (b[0][0].startsWith(user)) {
	                    filtered.add(b); // Add to filtered list if user matches
	                }
	            }

	            // Iterate through filtered bills
	            for (String[][] ur : filtered) {
	                DefaultMutableTreeNode u = new DefaultMutableTreeNode(ur[0][0] + " : " + ur[0][2]); // Create node for user bill
	                double userTotal = 0; // Initialize total for the user

	                // Add items to user node
	                for (String[] item : ur) {
	                    try {
	                        Double.valueOf(item[2]); // Validate item value
	                    } catch (Exception e) {
	                        continue; // Skip if parsing fails
	                    }
	                    userTotal += Double.valueOf(item[2]); // Calculate user total
	                    u.add(new DefaultMutableTreeNode(item[0] + " : " + item[1])); // Add item details
	                }
	                u.add(new DefaultMutableTreeNode("Bill Total: $" + String.format("%.2f", userTotal))); // Add total to user node
	                mth.add(u); // Add user node to month node
	            }
	            if (monthlyTotal==0.0 && (Integer.valueOf(m) == Integer.valueOf(main.getCont().getSystemDate()[0])&&Integer.valueOf(y) >= Integer.valueOf(main.getCont().getSystemDate()[1]))){
					mth.add(new DefaultMutableTreeNode(String.format("No Bill Generated this Month")));// adds this if month viewed is current and has no bills
				}
				else if (monthlyTotal==0.0 && (Integer.valueOf(m) < Integer.valueOf(main.getCont().getSystemDate()[0])||Integer.valueOf(y) < Integer.valueOf(main.getCont().getSystemDate()[1]))){
					mth.add(new DefaultMutableTreeNode(String.format("No Bill Generated")));// adds this if month is previous month and no bills
				}
				else {mth.add(new DefaultMutableTreeNode(String.format("Month Revenue: $"+"%.2f",monthlyTotal)));}
				yearTotal+=monthlyTotal;//show total if month has bills
	        }
	        // yr.add(new DefaultMutableTreeNode("Year Total: $" + String.format("%.2f", yearTotal))); // Optional: Add year total
	    }

	    model.reload(); // Reload the tree model
	    tree.setModel(model); // Set updated model to tree

	    // Expand rows if there's only one year or month in the list
	    if (yearList.length == 1) {
	        tree.expandRow(1);
	        if (monthList.length == 1) {
	            tree.expandRow(2);
	        }
	    }
	}

	public void setUnits() { // Method to set units data
	    Readings[] readings = main.getCont().getAllReadings(); // Get all readings
	    HashMap<String, String> organisedUnits = new HashMap<>(); // HashMap to store units
	    String text = "Reading Units: \n"; // Initialize text with header

	    // Populate HashMap with utility names and their units
	    for (Readings r : readings) {
	        organisedUnits.put(r.getUtilityName(), r.getUnit());
	    }

	    // Build the text to display units
	    for (Map.Entry<String, String> m : organisedUnits.entrySet()) {
	        text += m.getKey() + " : " + m.getValue() + '\n'; // Add utility and unit to text
	    }
	    txtrUnits.setText(text); // Update text area with units data
	}
}
