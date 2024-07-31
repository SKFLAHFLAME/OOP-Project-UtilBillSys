package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Vector;
import controller.MainFrame;

public class ViewHistoryScreen extends JPanel {
    MainFrame main;
    private DefaultTreeModel model;
    private DefaultMutableTreeNode userName;

    private JScrollPane scrollPane;
    private JLabel lblCustomerDetails;
    private JTree tree;
    private JButton btnBack;
    private JButton btnSearch;
    private JComboBox<String> comboYear;
    private JLabel lblYear;
    private JComboBox<String> comboMonth;
    private JLabel lblMonth;

    private String[] date;
    private String[] month = { "All", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    private String[] year;

    public ViewHistoryScreen(MainFrame m) {
        main = m;
        main.setSize(400, 715);
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
        this.scrollPane.setBounds(12, 45, 366, 259);
        add(this.scrollPane);

        model = new DefaultTreeModel(userName);
        this.tree = new JTree(model);
        this.tree.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.scrollPane.setViewportView(this.tree);
        populateTree();

        this.lblCustomerDetails = new JLabel("Customer Details");
        this.lblCustomerDetails.setFont(new Font("Dialog", Font.BOLD, 20));
        this.lblCustomerDetails.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblCustomerDetails.setBounds(12, 12, 366, 40);
        add(this.lblCustomerDetails);

        this.lblMonth = new JLabel("Month:");
        this.lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.lblMonth.setBounds(12, 317, 60, 32);
        add(this.lblMonth);

        this.comboMonth = new JComboBox<>(month);
        this.comboMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.comboMonth.setBounds(74, 317, 179, 32);
        add(this.comboMonth);

        this.lblYear = new JLabel("Year:");
        this.lblYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.lblYear.setBounds(12, 362, 54, 38);
        add(this.lblYear);

        this.comboYear = new JComboBox<>(year);
        this.comboYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.comboYear.setBounds(74, 362, 179, 32);
        add(this.comboYear);

        this.btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                filterTree();
            }
        });
        this.btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
        this.btnSearch.setBounds(12, 416, 170, 51);
        add(this.btnSearch);

        this.btnBack = new JButton("Back");
        this.btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showCustMenu();
            }
        });
        this.btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
        this.btnBack.setBounds(158, 474, 117, 25);
        add(this.btnBack);
        main.setSize(400, 500);
    }

    public void populateTree() {
        userName.removeAllChildren();
        String[][][] userReadings = main.getCont().getUserReading(main.getCurrentAcct()[1]); // get all userReadings of user
        double utotal = 0;

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
            
            
            DefaultMutableTreeNode bill = new DefaultMutableTreeNode(ur[0][2]); // use the date as the node label
            for (int i = 0; i < ur.length - 1; i++) { // go through each reading
                if (ur[i][0] == null) {
                    continue;
                }
                bill.add(new DefaultMutableTreeNode(ur[i + 1][0] + ": " + ur[i + 1][1]));
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

        String selectedYear = comboYear.getSelectedItem().toString();
        String selectedMonth = comboMonth.getSelectedItem().toString();

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
                String billYear = dateParts[2];

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
                bill.add(new DefaultMutableTreeNode(ur[i + 1][0] + ": " + ur[i + 1][1]));
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
