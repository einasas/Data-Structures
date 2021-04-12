//Implementation of a Node of a Doubly Linked List
public class Node {

	Page element;
	Node next;
	Node prev;

	public Node(Page element, Node next, Node prev) {
		this.element = element;
		this.next = next;
		this.prev = prev;
	}

}
