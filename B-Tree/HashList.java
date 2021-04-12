
public class HashList {
	private HashListElement first;

	public HashList() {
	}

	// Returns the number of elements in this list
	public int size() {
		int c = 0;
		for (HashListElement curr = first; curr != null; curr = curr.getNext())
			c = c + 1;
		return c;
	}

	// Returns true if this list contains no elements.
	public boolean isEmpty() {
		return first == null;
	}

	// Adds element to the beginning of this list
	public void addFirst(String element) {
		if (element == null)
			throw new NullPointerException();
		first = new HashListElement(element, first);
	}

	// Returns the element at the specified position in this list.
	public HashListElement get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
		HashListElement current = first;
		while (index > 0) {
			index = index - 1;
			current = current.getNext();
		}
		return current;
	}

	// Returns true if this list contains the specified element
	public HashListElement contains(Object element) {
		boolean ans = false;
		for (HashListElement curr = first; curr != null ; curr = curr.getNext())
			if(element.equals(curr.getData()))
					return curr;
		return null;
	}

	// Replaces the element at the specified position in this list with the
	// specified element
	public void set(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

		HashListElement current = first;
		while (index > 0) {
			index = index - 1;
			current = current.getNext();
		}
		String prev = current.getData();
		current.SetCount();

	}

	public void insert(String toinsert) {
		if (first == null) {
			addFirst(toinsert);
			first.SetCount();
			HashListElement toAdd = new HashListElement(toinsert, first);
		} 
		
		else {
			HashListElement element=this.contains(toinsert);
			if (element==null) {
				HashListElement  toAdd=new HashListElement(toinsert,first);
				first = toAdd;
				toAdd.SetCount();
			}
			else {
				element.SetCount();
			}
			
		}
	}


	// Returns the index of the first occurrence of the specified element in this
	// list, or -1 if this list does not contain the element.
	public int indexOf(String element) {
		int ans = -1;
		int index = 0;
		for (HashListElement curr = first; curr != null & ans == -1; curr = curr.getNext())
			if (curr.getData().equals(element))
				ans = index;
			else
				index = index + 1;
		return ans;
	}

}
