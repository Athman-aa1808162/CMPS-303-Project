package porject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HashTree implements Serializable {

	private final int SIZE = 24;
	private Tree[] hashArray;

	public HashTree() {
		this.hashArray = new Tree[SIZE];
		for (int i = 0; i < SIZE; i++)
			hashArray[i] = new Tree<>();
	}

	/*
	 * Hash function will receive the key, then it will use the MOD to generate a
	 * number,that will be the index where the Object will be stored in the
	 * hashArray.
	 */
	public int hashFunc(int key) {
		Integer Key = key;
		int hashValue = Integer.parseInt(Key.toString().substring(0, 4)) - 2000;
		return hashValue;
	}

	/*
	 * Method will receive an object of type student, and it will call the method
	 * Search() to check whether student exist or not, then it will save it in the
	 * the hashArray of Trees if it's not exist
	 */
	public void insert(Student student) {
		if (Search(student.getId()) != null) {
			System.err.println("Student With ID: " + student.getId() + " Already Exist!");
			return;
		} else {
			int key = student.getId();
			int hashVal = hashFunc(key);
			hashArray[hashVal].insert(key, student);
			System.out.println("Student Has Been Inserted Successfully.\n");
		}
	}

	/*
	 * Same implementation of the Tree search method, I tried to call the search
	 * method of Tree class, it gives me error if the ID not exist
	 */
	public Student Search(int key) {
		int hashVal = hashFunc(key);
		Node<Student> current = hashArray[hashVal].getRoot();

		try {
			while (current.key != key) {
				if (key < current.key)
					current = current.leftChild;
				else
					current = current.rightChild;
				if (current == null)
					return null;
			}
			return current.getData();
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * Method receive specific year, and using the same implementation of the
	 * print() method, but here it will call the preorder1() method, which is a new
	 * method created in the Tree class with same implementation to the old one, in
	 * addition to check year while traversing the tree
	 */
	public Student searchByYear(int year) {
		Integer Year = year;
		int hashValue = Integer.parseInt(Year.toString().substring(0, 4)) - 2000;
		Node<Student> current = hashArray[hashValue].getRoot();

		if (current == null)
			System.out.println("No Student Are in this Year");
		else
			hashArray[hashValue].preorder1(current, year);

		return null;
	}

	/*
	 * Method will receive the student ID as a key, and it will use the search
	 * method of this class, if student exist it will call the delete method of the
	 * Tree class to delete the object and return true, otherwise it will return
	 * false.
	 */
	public void Delete(int key) {
		int hashVal = hashFunc(key);
		Node<Student> current = hashArray[hashVal].getRoot();

		try {
			if (Search(key) == null)
				System.out.println("Student Not Found");
			else {
				hashArray[hashVal].delete(key);
				System.out.println("Student Has Been Deleted Successfully");
			}
		} catch (Exception exption) {
			System.out.println(exption.getMessage());
		}
	}

	/*
	 * Method will loop over the hashArray and call the pre-order display of each
	 * index Tree and will print the content
	 */
	public void print() {
		for (int i = 0; i < SIZE; i++) {
			Node<Student> current = hashArray[i].getRoot();
			hashArray[i].preorder(current);
		}
	}

	/*
	 * Method will receive and Object of the HashTree, and saving it in the
	 * "students.dat" file, which will be called later to retrieve data when the APP
	 * started
	 */
	public void saveData(HashTree data) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("students.dat"));
			output.writeObject(data);
			output.close();
		} catch (Exception e) {
			System.err.println("Error Saving");
		}

	}

	/*
	 * Method will read the data of the "student.dat" if it exist, and it will
	 * return it, which will be assigned to the main HashTree table when APP start
	 */
	public HashTree loadData() {
		HashTree table = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("students.dat"));
			table = (HashTree) input.readObject();
			input.close();
		} catch (Exception e) {
			System.err.println("Error Reading");
		}
		return table;
	}

}
