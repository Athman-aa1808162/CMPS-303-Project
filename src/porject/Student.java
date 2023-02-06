package porject;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private double GPA;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public Student(int id, String name, double gpa) {
		this.id = id;
		this.name = name;
		this.GPA = gpa;
	}

	public String toString() {
		return "ID: " + getId() + "\nName: " + getName() + "\nGPA: " + getGPA() + "\n-----------------------";
	}

}
