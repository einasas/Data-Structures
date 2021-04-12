import java.util.NoSuchElementException;

public class DoublyLinkedListImpl {
	// First node of DoublyLinkedList
	private Node head;
	// Last node of DoublyLinkedList
	private Node tail;
	private int size;
	
	public DoublyLinkedListImpl() {
		size = 0;
	}

	

	 // returns the size of the linked list
	 
	public int size() {
		return size;
	}

	 // return whether the list is empty or not
	
	public boolean isEmpty() {
		return size == 0;
	}

	
	//To reorder existing node to head of queue
	public void updateToFirst(Node node) {
		if (node == null || node == head) {
			return;
		}
		if(node==tail) {
			tail = tail.prev;
			tail.next = null;
		}
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		if (next != null) {
			next.prev = prev;
		}
		node.prev=null;
		node.next = head;
		head.prev = node;
		head = node;
	}
	
	 //adds element at the first position (head) of the linked list 

	public Node addFirst(Page element) {
		Node tmp = new Node(element, head, null);
		if (head != null) {
			head.prev = tmp;
		}
		head = tmp;
		if (tail == null) {
			tail = tmp;
		}
		size++;
		return tmp;
	
	}

//Removes the node from last position (tail) of LinkedList
	public Page removeLast() {
		
		Page value = null;
		if (tail != null) {
			value = tail.element;

			if (tail == head) {
				head = tail = null;
			} else {
				tail = tail.prev;
				tail.next = null;
			}
		}
		return value;
	}

}
