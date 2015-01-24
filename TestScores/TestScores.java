/**
	 * 
	 * @author Scott Killeen
	 * @SDate April 3, 2013
	 */
import java.io.*;
import java.util.*;

public class finalprogram1 {
	/**
	 * The main method will open a file stream to file named "names.txt". It will
	 * then ask the user to write a list of first and last names. Those names will 
	 * then be saved to the file.
	 */
	public static void main(String args[]) throws IOException {
		switch(Menu()){ 
		case 1: 				
		score1();
			
			
		break;
		case 2: 
			score2();	
		
		break;
		case 3:
			average1();
		break;
		case 4:
			average2();
		break;
		case 5: // Quit
			System.out.println("Thanks for stoppinng by!");
			System.exit(0);
		break;
		
		default:
		}
		
		
	}
	public static String score1() throws IOException {
		java.util.Scanner Myscan = new java.util.Scanner(System.in);
		int[] scores=new int[26];
		String shane="";
		PrintWriter FileOut; // variable for the writer to the file
		FileWriter Out; // Variable to create the stream to the file
		Out = new FileWriter("Scores.txt"); // creating the output stream object
		FileOut = new PrintWriter( Out); // creating the print to stream object
		System.out.println("Enter the 25 scores for the first team!");	
		for (int i = 1; i < 26; i++) {
			System.out.println(i+") ");	
			scores[i] = Myscan.nextInt(); //Allows user to enter names.
			FileOut.println(scores[i]);// writing to the file
		}
		Myscan.close();
		FileOut.close(); // close the output stream
		Runtime Execute = Runtime.getRuntime();
		Execute.exec("Notepad Scores.txt");
		return(shane);
		
	}
	
	public static String score2() throws IOException {
		java.util.Scanner Myscan = new java.util.Scanner(System.in);
		int[] scores=new int[26];
		int[] scores2=new int[26];
		String shane1="";
		PrintWriter FileOut; // variable for the writer to the file
		FileWriter Out; // Variable to create the stream to the file
		Out = new FileWriter("Scores1.txt"); // creating the output stream object
		FileOut = new PrintWriter( Out); // creating the print to stream object
		System.out.println("Enter the 25 scores for the second team!");	
		for (int i = 1; i < 26; i++) {
			scores2[i] = Myscan.nextInt(); //Allows user to enter names.
			System.out.println(i+") ");	
			FileOut.println(scores2[i]);// writing to the file
		}
		Myscan.close();
		FileOut.close(); // close the output stream
		Runtime Execute = Runtime.getRuntime();
		Execute.exec(" Notepad Scores1.txt");
		return(shane1);
		
	}
	
	
	public static int average1() throws IOException {
		BufferedReader FileIn; // Variable for a buffer to read the file contents
		FileReader In;// Variable for a read stream to a file
		In = new FileReader("Scores.txt"); // Creates a read stream object
		FileIn = new BufferedReader(In); // Creates the buffered reader object
		String line = ""; // variable to read data from the file
		String shane3="";
		int[] average=new int[26];
		int t=0;
		int h=0;
		while (line != null) { // read until end of file
			line = FileIn.readLine(); // read file contents
			if (line !=null) { 
				int i=0;
				average[i]=Integer.parseInt(line);
				t+=average[i];
				
				
			}
		}
		t=(t/25);
		System.out.println(t);
		
		FileIn.close(); // close the file.
		return(t);
		
	}
	public static int average2() throws IOException {
		BufferedReader FileIn; // Variable for a buffer to read the file contents
		FileReader In;// Variable for a read stream to a file
		In = new FileReader("Scores1.txt"); // Creates a read stream object
		FileIn = new BufferedReader(In); // Creates the buffered reader object
		String line = ""; // variable to read data from the file
		int[] average2=new int[26];
		int h=0;
		while (line != null) { // read until end of file
			line = FileIn.readLine(); // read file contents
			if (line !=null) { 
				int i=0;
				average2[i]=Integer.parseInt(line);
				h+=average2[i];			
			}
		}
		h=(h/25);
		System.out.println(h);
		
		FileIn.close(); // close the file.
		return(h);
		
	}
	public static int Menu() {
		int select;
		// creating the Scanner object for keyboard input.
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("Enter the number of your selection");
		System.out.println("1. Enter Team One's Score");
		System.out.println("2. Enter Team Two's Score");
		System.out.println("3. Get Team One's average");
		System.out.println("4. Get Team Twos's average");
		System.out.println("5. Quit");
		System.out.println("-------------------------------");
		select = s.nextInt();
		
		
		return (select);

	}

}
