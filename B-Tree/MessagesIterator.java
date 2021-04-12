
public class MessagesIterator<T>{

	private Message[] array;
	int i;

	public MessagesIterator(Message[] array) {
		i = 0;
		this.array = array;

	}

	public boolean hasNext() {
		return i < array.length;

	}

	public Message next() {
		Message tmp = array[i];
		i++;
		return tmp;

	}

}
