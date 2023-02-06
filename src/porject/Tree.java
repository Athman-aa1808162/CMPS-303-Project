package porject;

import java.io.Serializable;

public class Tree<E> implements Serializable {

	private Node<E> root;

	public E search(int k) {
		Node<E> current = getRoot();
		while (current.key != k) {
			if (k < current.key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current.data;
	}

	public void insert(int k, E e) {
		Node<E> newNode = new Node(k, e);
		if (getRoot() == null)
			setRoot(newNode);
		else {
			Node current = getRoot();
			Node parent;
			while (true) {
				parent = current;
				if (k < current.key) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public boolean delete(int k) {
		Node current = getRoot();
		Node parent = getRoot();
		boolean isLeftChild = true;
		while (current.key != k) {
			parent = current;
			if (k < current.key) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return false;
		}
		if (current.leftChild == null && current.rightChild == null) {
			if (current == getRoot())
				setRoot(null);
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		} else if (current.rightChild == null)
			if (current == getRoot())
				setRoot(current.leftChild);
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else // right child of parent
				parent.rightChild = current.leftChild;
		else if (current.leftChild == null)
			if (current == getRoot())
				setRoot(current.rightChild);
			else if (isLeftChild) // left child of parent
				parent.leftChild = current.rightChild;
			else // right child of parent
				parent.rightChild = current.rightChild;
		else {

			Node successor = getSuccessor(current);
			if (current == getRoot())
				setRoot(successor);
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = current.leftChild;
		}
		return true;

	}

	private Node<E> getSuccessor(Node<E> delNode) {
		Node<E> successorParent = delNode;
		Node<E> successor = delNode;
		Node<E> current = delNode.rightChild; // go to right child
		while (current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if (successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			preorder(getRoot());
			break;
		case 2:
			inorder(getRoot());
			break;
		case 3:
			postorder(getRoot());
			break;
		}

	}

	public void preorder() {
		preorder(getRoot());
	}

	public void preorder(Node<E> n) {
		if (n == null) {
			return;
		} else {
			n.display();
			preorder(n.leftChild);
			preorder(n.rightChild);
		}
	}

	public void preorder1() {
		preorder(getRoot());
	}

	public void preorder1(Node<E> n, int year) {
		if (n == null) {
			return;
		} else {
			Integer key = n.key;
			int yearOnly = Integer.parseInt(key.toString().substring(0, 4));
			if (yearOnly == year) {
				n.display();
			}
			preorder1(n.leftChild, year);
			preorder1(n.rightChild, year);
		}
	}

	public void inorder(Node<E> n) {
		if (n == null)
			return;
		else {
			inorder(n.leftChild);
			n.display();
			inorder(n.rightChild);
		}

	}

	public void postorder(Node<E> n) {
		if (n == null)
			return;
		else {
			postorder(n.leftChild);
			postorder(n.rightChild);
			n.display();
		}
	}

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

}
