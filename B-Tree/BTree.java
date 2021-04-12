import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BTree {
	private int t;
	private BTreeNode root;

	public BTree(int t) {
		if(t<2)
			throw new IllegalArgumentException("ILegal degree");
		this.t = t;
		this.root = new BTreeNode(t, true);
	}
	public BTree(String t) {
			this(Integer.parseInt(t));
	}
	private boolean checkDegree(String t) {
		
		for(int i=0;i<t.length();i++) {
			if(!(t.charAt(i)>=0 & t.charAt(i)<=9))
					return false;
		}
		return true;
			
	}
	public boolean contains(String key) {
		if(key==null)
			throw new NullPointerException();
		return search(key)!=null;
	}
	

	public BTreeNode[] getChilds() {
		return root.getChilds();
	}

	public BTreeNode getRoot() {
		return this.root;
	}

	public BTreeNode search(String k) {
		if (root == null)
			return null;
		return search(root, k);
	}

	public BTreeNode search(BTreeNode x, String key) {
		if(key==null)
			throw new NullPointerException();
		if(key=="")
			throw new IllegalArgumentException();
		int i = 0;
		while (i <= x.n - 1 && key.compareTo(x.keys[i]) > 0) {
			i++;
		}
		if (i < x.n && key.equals(x.keys[i])) {
			return x;
		} else if (x.leaf) {
			return null;
		} else
			return search(x.childs[i], key);
	}

	public void insert(String key) {
		if(key==null)
			throw new NullPointerException();
		if(key=="")
			throw new IllegalArgumentException();
		BTreeNode r = root;
		if (r.n == 2 * t - 1) {
			BTreeNode x = new BTreeNode(t, false);
			root = x;
			x.n = 0;
			x.childs[0] = r;
			splitChild(0, x);
			insertNonFull(x, key);

		} else
			insertNonFull(r, key);
	}

	public void splitChild(int i, BTreeNode x) {
		BTreeNode y = x.childs[i];
		BTreeNode z = new BTreeNode(t, y.leaf);
		z.n = t - 1;
		for (int j = 0; j < t - 1; j++) {
			z.keys[j] = y.keys[j + t];
			y.keys[j + t] = null;
		}
		if (y.leaf == false) {
			for (int j = 0; j < t; j++)
				z.childs[j] = y.childs[j + t];
		}
		y.n = t - 1;
		for (int j = x.n; j >= i + 1; j--)
			x.childs[j + 1] = x.childs[j];
		x.childs[i + 1] = z;
		for (int j = x.n - 1; j >= i; j--)
			x.keys[j + 1] = x.keys[j];
		x.keys[i] = y.keys[t - 1];
		y.keys[t - 1] = null;
		x.n = x.n + 1;
	}

	public void insertNonFull(BTreeNode x, String key) {
		int i = x.n - 1;
		if (x.leaf == true) {
			while (i >= 0 && key.compareTo(x.keys[i]) < 0) {
				x.keys[i + 1] = x.keys[i];
				i--;
			}
			x.keys[i + 1] = key;
			x.n = x.n + 1;
		} else {
			while (i >= 0 && key.compareTo(x.keys[i]) < 0)
				i--;
			i++;
			if (x.childs[i].n == 2 * t - 1) {
				splitChild(i, x);
				if (key.compareTo(x.keys[i]) > 0)
					i++;
			}
			insertNonFull(x.childs[i], key);

		}

	}

	public String breadFirstSearch() {
		int i;
		boolean first = true;
		Queue<BTreeNode> q1 = new Queue<BTreeNode>();// current level(rama 1)
		Queue<BTreeNode> q2 = new Queue<BTreeNode>();// next level(rama 2)
		String ans = "";//returned value
		q1.enqueue(root);
		BTreeNode tmp;
		while (!q1.isEmpty()) {
			tmp = q1.dequeue();
			if (tmp == null) {
				// not the same parent 
				if (!q1.isEmpty() && q1.peek() == null) {
					ans += "^";
				}
			} else {//get the node keys
				ans += tmp.getKeysAsString();
				if (first) {
					first = false;
				} else {
					if (q1.peek() != null) {//moved to the next node that have the same parent in the same level 
						ans += "|";
					}
				}
				if (!tmp.leaf) {
					notLeaf(q2, tmp);
				}
			}
			if (q1.isEmpty()) {
				q1 = q2;
				if (!q2.isEmpty())//moved to the next level
					ans += "#";
				q2 = new Queue<BTreeNode>();
			}
		}
		return ans;
	}

	private void notLeaf(Queue<BTreeNode> q2, BTreeNode tmp) {
		int i;
		q2.enqueue(null);
		for (i = 0; i <= tmp.n; i++) {
			q2.enqueue(tmp.childs[i]);
		}
		q2.enqueue(null);
	}

	public void createFullTree(String string) {
		readFileString(string);
	}
	//read a file as string , and add to the btree(friends)
	private void readFileString(String filePath) {
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
				insert(line);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String toString() {
		return breadFirstSearch();
	}

}