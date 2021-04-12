
public class SpamsIterator<T> {

	private T[] array;
	int i;

	public SpamsIterator(T[] array) {
		i = 0;
		this.array = array;

	}
    //check if we have more elements
	public boolean hasNext() {
		return i < array.length;

	}
   //return the next element 
	public T next() {
		T tmp = array[i];
		i++;
		return tmp;

	}



}
