## Introduction 
	**Data Structure Course - ```CMPS 303```

This project is a combination of two commonly used data structures, Hash Table and Tree, to create a more efficient data structure for managing student information. The main goal of this project is to reduce the time complexity of common operations such as insertion, searching, and deletion to O(log n).

**Operation time complixity to be:

	1. Insert  ```O(log n)```
	2. Search ```O(log n)```
	3. Delete ```O(log n)```

## Functionality
The application has several operations that can be performed including:

1.  Adding a new student
2.  Searching for a student
3.  Searching for all students in a specific year
4.  Deleting a student
5.  Printing all students
6.  Exiting the application & save to files

## Project Classes
The project consists of several classes including:

- App:
	This is the main class responsible for the application's overall functionality and validation methods for handling user inputs. It provides a user-friendly menu for the user to perform the operations listed above.

- HashTree: 
	This class is responsible for implementing the Hash Table data structure and its method, handling the hash function used for searching and inserting, deleting operations.

- Tree: 
	This class implements the Tree data structure and is responsible for handling the tree operations such as insertion, deletion, and searching.

- Node: 
	This class represents a node in the tree and contains

- Student: 
	This class contains information about a student, including the student's `name`, `id`, and `GPA`

## Conclusion
This project combines two commonly used data structures, Hash Table and Tree, to create a more efficient data structure for managing student information. The project has several classes that work together to provide a user-friendly interface for the user to perform common operations such as insertion, searching, and deletion with a time complexity of `O(log n).
