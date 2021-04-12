
public class BTreeNode {

	public int t;// minimum degree
	public String[] keys; // an array of keys
	public BTreeNode[] childs;
	public int n;// current number of keys
	public boolean leaf;

	public BTreeNode(int t, boolean leaf) {
		if(t<1)
			throw new IllegalArgumentException("ILegal degree");
		this.t = t;
		this.leaf = leaf;
		this.keys = new String[2 * t - 1];
		this.childs = new BTreeNode[2 * t];
		this.n = 0;
	}

	public String[] getKeys() {
		return this.keys;
	}

	public BTreeNode[] getChilds() {
		return this.childs;
	}

	public int getNumOfKeys() {
		return this.n;
	}

	public boolean isLeaf() {
		return this.leaf;
	}

	public int getNumOfChilds() {
		//return the number of Childs in the current node
		int count = 0;
		for (int i = 0; i < childs.length; i++) {
			if (childs[i] != null)
				count++;
		}

		return count;
	}

	
	// print all the keys in the giving node
	public String getKeysAsString() {
		int i;
		String res = "";

		for (i = 0; i < n; i++) {
			res += keys[i];

			if (i + 1 != n) {
				res += ",";
			}
		}

		return res;
	}
}
