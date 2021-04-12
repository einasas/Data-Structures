import java.util.NoSuchElementException;

public class Queue<T> {
	private int n;// size
	private Node first;
	private Node last;

	private class Node {
		private T item;
		private Node next;
	}

	public Queue() {
		first = null;
		last = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	public void enqueue(T item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
		n++;
	}

	public T dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		T item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null;
		return item;
	}
}