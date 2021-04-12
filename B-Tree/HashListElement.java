
public class HashListElement {
	private int count;
	private String data;
	private HashListElement next;

	public HashListElement(String word, HashListElement next) {
		this.data = word;
		this.next = next;
		this.count = 0;
	}

	public HashListElement(String word) {
		this.data = word;
		this.next = null;
		this.count = 0;
	}

	public HashListElement getNext() {
		return next;
	}

	public void setNext(HashListElement next) {
		this.next = next;
	}

	public int GetCount() {
		return this.count;
	}

	public String getData() {
		return data;
	}

	public String setData(String data) {
		String tmp = this.data;
		this.data = data;
		return tmp;
	}

	public String toString() {
		return data.toString();
	}

	public void SetCount() {
		this.count++;
	}

}
