
/**
 * 
 * @author Imran Essa & 208953828
 * einas ashkar 207061102
 */
import java.util.Arrays;

public class MemoryManagementSystem {
	public String[] secondaryMemory;
	private boolean useLRU;
	private DoublyLinkedListImpl mainMemory;
	private boolean[] valid;
	private Node[] nodes;
	// YOU CAN ADD MORE FIELDS HERE

	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		// ADD YOUR CODE HERE
		this.useLRU = useLRU;
		mainMemory = new DoublyLinkedListImpl();
		secondaryMemory = new String[secondaryMemorySize];
		nodes = new Node[secondaryMemorySize];
		valid = new boolean[secondaryMemorySize];
		// we copy the n first elements from the secondary memory to the main memory
		// and we intilaized the n first valid array's value to true, because the n first elements exists
		// into the main memory
		for (int i = 0; i < secondaryMemory.length; i++) {
			secondaryMemory[i] = "";
			if (i < mainMemorySize) {
				nodes[i] = mainMemory.addFirst(new Page(secondaryMemory[i], i, false));
				valid[i] = true;
			}
		}
	}

	@Override
	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	//The method returns the data of the page whose index is key
	public String read(int index) {
		String data = "";
		//Check if the page is in the main memory
		if (valid[index]) {
			// save the data of the page
			data = nodes[index].element.getData();
			//if the page is in the main memory, check if the page is replaced by LRU 
			if (this.useLRU) {
				//move the current page to the beginning of the list 
				mainMemory.updateToFirst(nodes[index]);
			}
		} 
		// if the page is in the secondary memory
		else {
			// Pull the page out of the secondary memory
			data = secondaryMemory[index];
			// Remove the last node in the linkList
			Page last = mainMemory.removeLast();
			// Check if the node that we deleted has been changed
			if (last.hasChanged()) {
				secondaryMemory[last.getIndex()] = last.getData();
			}
			//Update the VAILD array to a false value because the page has been deleted from the main memory
			valid[last.getIndex()]  = false;
			//Update the Nodes array to null in the page index that we deleted
			nodes[last.getIndex()] = null;
			// adding the page to the head of the link
			nodes[index] = mainMemory.addFirst(new Page(data, index, false));
			// update the valid array to a true value 
			valid[index] = true;
		}
		return data;
	}
	// The method Add to the data of the page whose index KEY the CHAR content by concatenating to the end of the page
	public void write(int index, char c) {
		//Check if the page is in the main memory
		if (valid[index]) {
			//if the page is in the main memory, check if the page is replaced by LRU 
			if (this.useLRU) {
				//move the current page to the beginning of the list 
				mainMemory.updateToFirst(nodes[index]);
			}
		} 
		// if the page is in the secondary memory
		else {
			// Pull the page out of the secondary memory
			String data = secondaryMemory[index];
			// Remove the last node in the linkList
			Page last = mainMemory.removeLast();
			// Check if the node that we deleted has been changed
			if (last.hasChanged()) {
				secondaryMemory[last.getIndex()] = last.getData();
			}
			//Update the VAILD array to a false value because the page has been deleted from the main memory
			valid[last.getIndex()]  = false;
			//Update the Nodes array to null in the page index that we deleted
			nodes[last.getIndex()] = null;
			// adding the page to the head of the link
			nodes[index] = mainMemory.addFirst(new Page(data, index, false));
			// update the valid array to a true value 
			valid[index] = true;
		}
		// adding the char c to the data of the page using WriteData method
		nodes[index].element.WriteData(c);
	}
}
