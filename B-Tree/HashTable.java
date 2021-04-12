import java.util.StringTokenizer;

public class HashTable {
	private HashList[] table;
	private int m;

	public HashTable(int m) {
		if(m<1)
			throw new IllegalArgumentException("ILegal length");
		this.m = m;
		this.table = new HashList[m];
		for (int i = 0; i < m; i++) {
			table[i] = new HashList();
		}
	}
	public int getm() {
		return m;
	}
	public HashTable(String m) {
		this(Integer.parseInt(m));
	}

	public HashList GetTable(int i) {
		return this.table[i];
	}

	public boolean Search(String st) {
		return table[hashFunction(st,m)].contains(st)!=null;
	}

	public int count(String st) {
		//return how many twice the element st appears in the list in the index HashFunc in the array 
		boolean ans = Search(st);
		if (ans) {
			int stIndex = table[hashFunction(st,m)].indexOf(st);
			int count = table[hashFunction(st,m)].get(stIndex).GetCount();
			return count;

		} else
			return 0;
	}

	public int hashFunction(String txt, int m) {
		//return the place of the element in the array
	
		int ans=0;
		for(int i=0; i<txt.length(); i++)
			ans=ans+txt.charAt(i);
		
		
	
		return ans% m;
	}

	public void Insert(String st) {
		//use hashfunction in order to insert the element in the hash list
		table[hashFunction(st,m)].insert(st);
	}

	
}
