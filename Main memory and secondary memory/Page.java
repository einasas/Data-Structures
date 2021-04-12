import java.util.NoSuchElementException;

/**
 * einas ashkar 207061102
 * 
 * @author Imran Essa & 208953828
 */

// Represents a page in memory
public class Page {
	// the data of the page
	private String data;
	// index of the page in memory
	private int index;
	//Boolean value that represents if the data of the page has been modified
	private boolean hasChanged;

	public Page(String data, int index, boolean hasChanged) {
		this.data = data;
		this.index = index;
		this.hasChanged = hasChanged;
	}
	// returns the data of the page
	public String getData() {
		return data;
	}
	// returns the index of the page
	public int getIndex() {
		return index;
	}
	// returns if the data page has changed 
	public boolean hasChanged() {
		return hasChanged;
	}
	// Chaining a char to the page
	public void WriteData(char data) {
		this.data += data;
		// update haschanged field ,because we change the data of the page
		this.hasChanged = true;
	}
}
