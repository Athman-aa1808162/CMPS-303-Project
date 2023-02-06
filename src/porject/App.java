package porject;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * @author Athman Alibrhim - 201808162
 * @author Mohammad alashwal - 202003119
 * @author Mohamed abdelhamid - 202108484
 * @author Anas Madkoor - 202104114
 * @version 1.0
 * @date 05/12/2022
 */
public class App implements Serializable {

	public static void main(String[] args) {

		// An object of the HashTree
		HashTree table = new HashTree();

		Scanner input = new Scanner(System.in);

		/*
		 * Here table will call the method isFileExist(), to check whether there is old
		 * data already, so this table will contain the old data in addition to the new
		 * data that will be added, otherwise, it will be a new Object of the HashTree()
		 * class that will be used to save objects, and save them when the APP
		 * terminated
		 */
		table = isFileExist(table);

		// Menu act based on this choice
		int choice;

		// Main Menu
		do {
			System.out.println("\nchoose a number please:\n======================= \n1-Add new student "
					+ "\n2-Search for a student " + "\n3-Search for all students in specific years "
					+ "\n4-Delete a student " + "\n5-Print all students " + "\n6-Exit\n");

			choice = input.nextInt();

			switch (choice) {
			case 1:
				table.insert(newStudent()); // 1- Calling the newStudent method, which will ask the user for
				break; 						// the student information, and it will return an object of
			case 2:							// student with entered info after validating the inputs.
				Student temp =table.Search(searching()); // 2- Calling the method Search, that exist in the
				if(temp != null)						 // hashTree class, and pass the searching() method
					System.out.println(temp);			 // in this class, searching() will ask the user and validate inputs.
				else												
					System.out.println("Student Not Found");												
				break; 											
			case 3: 											
				table.searchByYear(searchingByYear());// 3- Calling the method searchByYear(), that exist in the
				break; 								  // hashTree class, and pass the searchingByYear() method
			case 4: 								  // in this class, searchByYear() will ask the user and validate inputs
				table.Delete(deleting());// 4- Calling the method deleting(), that exist in the HashTree class
				break; 					 // and pass the deleting(), that exist in this class, which will ask
			case 5: 					 // the user and validate inputs.
				table.print(); // 5- calling the print method of the HashTree class, which will call the
				break; 		   // pre-oreder traverse method of the Tree class to print all inserted data.
			case 6:
				table.saveData(table); // 6- Saving the table data before terminating the APP
				System.out.println("APP TERMINATED.\nThank You!");
				break;
			default:
				System.err.println("Choose a Number Between 1 and 6 Please!");
			}
		} while (choice != 6);

		input.close();
	}

	/*
	 * ***********************************Note*************************************
	 * |All methods below are just a callback methods, which will be passed to the|
	 * |method of the HashTree in the switch statements, also it used for inputs  |
	 * |validation. 															  |
	 * ***********************************Note*************************************
	 */

	/*
	 * Callback method will be used in the inserting method in the switch statement,
	 * also it will validate the user input
	 */
	public static Student newStudent() {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Student ID: ");
		String id = input.nextLine();
		int length = id.length();

		while (length != 9 || Integer.parseInt(id.substring(0, 4)) < 2000 || Integer.parseInt(id.substring(0, 4)) > 2023) {
			if(length != 9) {
				System.err.print("Enter Student ID (9-Digits Please): ");
				id = input.nextLine();
				length = id.length();
			}else if(Integer.parseInt(id.substring(0, 4)) < 2000 || Integer.parseInt(id.substring(0, 4)) > 2023) {
				System.err.print("Enter a Year From the Range (2000 to 2023 Please): ");
				id = input.nextLine();
				length = id.length();
			}
		}

		System.out.print("Enter Student Name: ");
		String name = input.nextLine();

		while (!name.matches("^[a-zA-Z ]+$") && !name.isEmpty()) {
			System.err.print("Enter Student Name (String Only Please): ");
			name = input.nextLine();
		}

		System.out.print("Enter Student GPA: ");
		double gpa = input.nextDouble();

		while (gpa < 0 || gpa > 4) {
			System.err.print("Enter Student GPA (0 to 4): ");
			gpa = input.nextDouble();
		}

		System.out.println("");
		return new Student(Integer.parseInt(id), name, gpa);
	}

	/*
	 * Callback method will be used in the searching method in the switch statement,
	 * also it will validate the user input
	 */
	public static int searching() {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Student ID: ");
		String id = input.nextLine();

		int length = id.length();

		while (length != 9) {
			System.err.print("Enter Student ID (9-Digits Please): ");
			id = input.nextLine();
			length = id.length();
		}

		return Integer.parseInt(id);
	}

	/*
	 * Callback method will be used in the searching by year method in the switch
	 * statement, also it will validate the user input
	 */
	public static int searchingByYear() {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Year: ");
		String year = input.nextLine();

		int length = year.length();

		while (length != 4 || Integer.parseInt(year) < 2000 || Integer.parseInt(year) > 2022) {
			if (length != 4) {
				System.err.print("Enter Valid Year (4-Digits please): ");
				year = input.nextLine();
				length = year.length();
			} else {
				System.err.print("Enter a Year From the Range (2000 to 2023 Please): ");
				year = input.nextLine();
			}
		}

		return Integer.parseInt(year);
	}

	/*
	 * Callback method will be used in the deleting method in the switch statement,
	 * also it will validate the user input
	 */
	public static int deleting() {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Student ID: ");
		String id = input.nextLine();

		int length = id.length();

		while (length != 9) {
			System.err.print("Enter Student ID (9-Digits Please): ");
			id = input.nextLine();
			length = id.length();
		}

		return Integer.parseInt(id);
	}

	/*
	 * Callback method will be used when APP start, to check if there is an old data
	 * exist already with a file named = "students.dat"
	 */
	private static HashTree isFileExist(HashTree table) {
		File file = new File("students.dat");
		if (file.exists() && !file.isDirectory()) {
			table = table.loadData();
		} else {
			table = new HashTree();
		}
		return table;
	}

}
