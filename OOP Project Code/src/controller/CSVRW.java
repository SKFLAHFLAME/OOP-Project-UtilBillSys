package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


public class CSVRW {
	public String[][] csvReader(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(",");
		Vector<String[]> data = new Vector<>();
		int x=0;
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(line, ",");
			Vector<String> v = new Vector<>();
			int c=0;
			while (st.hasMoreTokens()){
				v.add(st.nextToken());
				c+=1;
			}
			String[] a = new String[c];
			v.toArray(a);
			c=0;
			data.add(a);
		}
		String[][] finaldata = new String[data.size()][2];
		data.toArray(finaldata);
		scanner.close();
		// //Testing method
		// for (String[]e:finaldata){
		// 	System.out.println(e.length);
		// 	for (String y :e){
		// 		System.out.print(y+"\t");
		// 	}
		// 	System.out.println();
		// }

		return finaldata;
		
	}
	
	public void csvWriter(String filepath, String[][] data) throws IOException {
		FileWriter writer = new FileWriter(filepath);
		for(String[] d : data) {
			for (String s:d){
				// if(s.isEmpty()){s="-";}
				writer.append(s+",");
			}
			writer.append("\n");
		}
		writer.close();
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// CSVRW csvrw = new CSVRW();
		// csvrw.csvReader("OOP Project Code/src/datafiles/Staff.csv");
		// String[][] x={{"JohnDoe", "password"},{"Sally", "abc"},{"Frank", "ABC123"}};
        // csvrw.csvWriter("OOP Project Code/src/datafiles/Customer.csv", x);
		// csvrw.csvReader("OOP Project Code/src/datafiles/Customer.csv");
		// csvrw.csvReader("OOP Project Code/src/datafiles/UserReadings.csv");
		// String[][] y = {{"Name", "Entry_Number", "Gas"+"(Cu_cM)", "Water"+"(Cu_M)"+"Electricity"+"(kWh)"}, 
		// {"John", "1", "2", "10", "30"}, 
		// {"Smith", "1", "-", "20", "80"}, 
		// {"Smith", "2", "-", "30", "75"}};
		// csvrw.csvWriter("OOP Project Code/src/datafiles/UserReadings.csv", y);
		// csvrw.csvReader("OOP Project Code/src/datafiles/UserReadings.csv");
		System.out.println(System.currentTimeMillis());
	}
}
