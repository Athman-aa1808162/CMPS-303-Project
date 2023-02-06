package porject;

import java.io.Serializable;

public class Node<E> implements Serializable {
	int key;
	E data;
	Node<E> leftChild;
	Node<E> rightChild;

	public E getData() {
		return data;
	}

	public Node(int key, E data) {
		this.key = key;
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}

	public void display() {
		System.out.print("\nStudent Information: \n" + data);
	}
}
