package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


public class CSVRW {
	public String[][] csvReader(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(", ");
		Vector<String[]> data = new Vector<>();
		while(scanner.hasNext()){
			StringTokenizer st = new StringTokenizer(scanner.nextLine(), ", ");
			int c =0;
			String[] a=new String[2];
			while (st.hasMoreTokens()){
				a[c] = st.nextToken();
				c+=1;
			}
			c=0;
			data.add(a);
		}
		for (String[] x :data){
			for (String y:x){
				System.out.print(y+",");
			}
			System.out.println();

		}
		scanner.close();

		return null;
		
	}
	
	public void csvWriter(String filepath, String[][] data) {
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		CSVRW csvrw = new CSVRW();
		csvrw.csvReader("OOP Project Code/src/datafiles/Staff.csv");
	}

}
