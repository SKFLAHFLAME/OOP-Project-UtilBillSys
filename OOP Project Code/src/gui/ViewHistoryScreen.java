package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Vector;
import controller.MainFrame;
import java.awt.Color;

public class ViewHistoryScreen extends JPanel {
    MainFrame main;
    private DefaultTreeModel model;
    private DefaultMutableTreeNode userName;

    private JScrollPane scrollPane;
    private JLabel lblCustomerDetails;
    private JTree tree;
    private JButton btnSearch;
    private JComboBox comboYear;
    private JLabel lblYear;
    private JComboBox comboMonth;
    private JLabel lblMonth;

    private String[] date;
    private String[] month = { "All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    private String[] year;
    private JLabel lblErrors;

    public ViewHistoryScreen(MainFrame m) {
        main = m;
        this.setLayout(null);
        userName = new DefaultMutableTreeNode(main.getCurrentAcct()[1]);

        date = main.getCont().getSystemDate();
        Vector<String> temp = new Vector<>();
        temp.add("All");
        for (int i = Integer.valueOf(date[1]); i >= 2000; i--) {
            temp.add("" + i);
        }
        year = new String[temp.size()];
        temp.toArray(year);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(24, 77, 621, 570);
        add(this.scrollPane);

        model = new DefaultTreeModel(userName);
        this.tree = new JTree(model);
        this.tree.setRowHeight(30);
        this.tree.setFont(new Font("Tw Cen MT", Font.PLAIN, 27));
        this.scrollPane.setViewportView(this.tree);
        
        this.lblCustomerDetails = new JLabel("History");
        this.scrollPane.setColumnHeaderView(this.lblCustomerDetails);
        this.lblCustomerDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
        this.lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);

        this.lblMonth = new JLabel("Month:");
        this.lblMonth.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
        this.lblMonth.setBounds(657, 165, 108, 51);
        add(this.lblMonth);

        this.comboMonth = new JComboBox(month);
        this.comboMonth.setFont(new Font("Tw Cen MT", Font.PLAIN, 27));
        this.comboMonth.setBounds(657, 215, 294, 51);
        add(this.comboMonth);

        this.lblYear = new JLabel("Year:");
        this.lblYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 28));
        this.lblYear.setBounds(657, 307, 86, 44);
        add(this.lblYear);

        this.comboYear = new JComboBox(year);
        this.comboYear.setFont(new Font("Tw Cen MT", Font.PLAIN, 27));
        this.comboYear.setBounds(657, 352, 294, 51);
        add(this.comboYear);

        this.btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                filterTree();
            }
        });
        this.btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.btnSearch.setBounds(657, 433, 170, 51);
        add(this.btnSearch);
        
        
        TaskBar bar = new TaskBar(this, main);
        
        this.lblErrors = new JLabel("");
        this.lblErrors.setForeground(Color.RED);
        this.lblErrors.setFont(new Font("Yu Gothic", Font.BOLD, 25));
        this.lblErrors.setBounds(657, 77, 294, 51);
        add(this.lblErrors);
        

        populateTree();
    }

    public void populateTree() {
        userName.removeAllChildren();
        String[][][] userReadings = main.getCont().getUserReading(main.getCurrentAcct()[1]); // get all userReadings of user
        double utotal = 0;
        if (userReadings.length == 0){
        	lblErrors.setText("No History Found");
        }

        // Add readings to the corresponding month nodes
        for (String[][] ur : userReadings) { // go through each bill
            double total = 0;
            if (ur == null) {
                continue;
            }
            if (ur[0][0] == null) {
                continue;
            }
            String[] dateParts = ur[0][2].split("/"); //  ur[0][2] is the date in dd/MM/yyyy format
            int monthIndex = Integer.parseInt(dateParts[1]) - 1; // get month index (0-based)
            int billYear = Integer.parseInt(dateParts[2]);
            // stops loop if bill is in the "future"
            if (monthIndex+1 > Integer.parseInt(date[0])&& billYear == Integer.parseInt(date[1])){
            	continue;
            }
            if (billYear>Integer.parseInt(date[1])){
            	continue;
            }
            
            
            DefaultMutableTreeNode bill = new DefaultMutableTreeNode(ur[0][2]); // use the date as the node label
            for (int i = 0; i < ur.length - 1; i++) { // go through each reading
                if (ur[i][0] == null) {
                    continue;
                }
                bill.add(new DefaultMutableTreeNode(ur[i + 1][0] + " used: " + ur[i + 1][1]));
                total += Double.valueOf(ur[i + 1][2]);
            }
            String t = String.format("%.2f", total);
            utotal += total;
            bill.add(new DefaultMutableTreeNode("Total: $" + t)); // total of bill
//            monthNodes[monthIndex].add(bill);
            userName.add(bill);
        }

        this.model.reload();
        tree.setModel(model);
    }


    private void filterTree() {
        userName.removeAllChildren(); // Clear the existing tree
        String[][][] userReadings = main.getCont().getUserReading(main.getCurrentAcct()[1]); // get all userReadings of user
        
        int monthI = comboMonth.getSelectedIndex();
        String selectedYear = comboYear.getSelectedItem().toString();
        String selectedMonth = comboMonth.getSelectedItem().toString();
        // stops loop if bill is in the "future"
        if(!selectedYear.equals("All")){
	        if (monthI > Integer.parseInt(date[0])&& Integer.parseInt(selectedYear) == Integer.parseInt(date[1])){
	        	selectedMonth = month[Integer.parseInt(date[0])];
        }}
        
        // Handle "All" cases, filtering section
        boolean filterByYear = !selectedYear.equals("All");
        boolean filterByMonth = !selectedMonth.equals("All");
        Vector<String[][]> uniTemp = new Vector<>(Arrays.asList(userReadings));
        if (filterByMonth) {
            // Filter by the selected month
        	Vector<String[][]> temp = new Vector<>();
            for (String[][] ur : uniTemp) {
                if (ur == null || ur[0][0] == null) {
                    continue;
                }
                String[] dateParts = ur[0][2].split("/");
                String billMonth = dateParts[1];
				String billYear = dateParts[2];
                if (Integer.parseInt(billYear) > Integer.parseInt(date[1])){
                	continue;
                }
				if (Integer.valueOf(billMonth) > Integer.parseInt(date[0])&& Integer.parseInt(billYear) == Integer.parseInt(date[1])){
                	continue;
                }

                // Skip bills not in the selected month
                if (!month[Integer.valueOf(billMonth)].equals(selectedMonth)) {
                    continue;
                }
                temp.add(ur);

            }
            uniTemp = temp;
        } 
        if (filterByYear) {
        	Vector<String[][]> temp = new Vector<>();
            // Filter by the selected year
            for (String[][] ur : uniTemp) {
                if (ur == null || ur[0][0] == null) {
                    continue;
                }
                String[] dateParts = ur[0][2].split("/");
                String billMonth = dateParts[1];
                String billYear = dateParts[2];
                if (Integer.valueOf(billMonth) > Integer.parseInt(date[0])&& Integer.parseInt(billYear) == Integer.parseInt(date[1])){
                	continue;
                }

                // Skip bills not in the selected year
                if (!billYear.equals(selectedYear)) {
                    continue;
                }
                temp.add(ur);

            }
            uniTemp= temp;
        } 
        if(filterByMonth==false && filterByYear==false){
        	populateTree();
        	return;
            // No filtering, show all bills
        }
        
        // populateList
        for (String[][] ur : uniTemp) { // go through each bill
            double total = 0;
            if (ur == null) {
                continue;
            }
            if (ur[0][0] == null) {
                continue;
            }
            DefaultMutableTreeNode bill = new DefaultMutableTreeNode(ur[0][2]); // use the date as the node label
            for (int i = 0; i < ur.length - 1; i++) { // go through each reading
                if (ur[i][0] == null) {
                    continue;
                }
                bill.add(new DefaultMutableTreeNode(ur[i + 1][0] + " used: " + ur[i + 1][1]));
                total += Double.valueOf(ur[i + 1][2]);
            }
            String t = String.format("%.2f", total);
            bill.add(new DefaultMutableTreeNode("Total: $" + t)); // total of bill
//            monthNodes[monthIndex].add(bill);
            userName.add(bill);
        }
        

        this.model.reload(); // Reload the model to update the tree
        tree.setModel(model); // Set the new model to the tree
    }

}
