import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Messages {
	private Message[] messages;
	private HashTable[] hashs;

	public MessagesIterator<Message> iterator() {
		return new MessagesIterator(messages);
	}

	public int getsize(int i) {
		return messages[i].getMesText().length();
	}

	public void generateMessages(String filePath) {

		Queue qu = new Queue<Message>();
		readFileString(filePath, qu);
		this.messages = new Message[qu.size()];
		int i = 0;
		while (!qu.isEmpty()) {
			messages[i] = (Message) qu.dequeue();
			i++;

		}

	}

	private void readFileString(String filePath, Queue<Message> qu) {

		try {

			Scanner x = new Scanner(new File(filePath));
			while (x.hasNext()) {
				String StringFrom = x.next();
				String StringTo = x.next();
				Message ToAdd = CuteMessage(StringFrom, StringTo);
				while (x.findInLine("#") == null && x.hasNext()) {
					ToAdd.setMesText(ToAdd.getMesText() + x.nextLine() + " ");
				}

				qu.enqueue(ToAdd);

			}

		}

		catch (Exception e) {

		}

	}

	private Message CuteMessage(String from, String to) {

		from = from.substring(5);
		to = to.substring(3);
		Message ans = new Message(from, to);
		return ans;

	}

	public String findSpams(String filepath, BTree btree) {
		String ans = "";
		Spams spamArray = new Spams();
		spamArray.ReadSpam(filepath);
		MessagesIterator<Message> itMessage = this.iterator();
		int i = 0;
		while (itMessage.hasNext()) {
			Message ms = itMessage.next();
			String recipient = ms.getNameOfRecipient();
			String sender = ms.getNameOfSender();

			String mesTxt = ms.getMesText();
			if (!isFriends(sender, recipient, btree)) {

				int howManyWord = ms.getNumOfWord();
				// System.out.println(howManyWord);
				if (checkSpam(spamArray, hashs[i], howManyWord)) {
					ans = Createoutput(ans, i);
				}
			}
			i++;
		}

		return ans;

	}

	public String Createoutput(String s, int i) {

		if (s.equals(""))
			return "" + i;
		s = s + "," + i;
		return s;

	}

	private boolean checkSpam(Spams spam, HashTable h, int howManyWord) {
		SpamsIterator<Spam> itSpam = spam.iterator();
		while (itSpam.hasNext()) {
			Spam tmp = itSpam.next();
			double percent = 0;
			double threShold = tmp.GetthreShold();
			if (h.Search(tmp.getSpamWord())) {
				percent = (((double) h.count(tmp.getSpamWord()) / howManyWord) * 100);
				if (threShold <= percent) {

					return true;
				}

			}
		}
		return false;
	}

	private boolean isFriends(String sender, String recipient, BTree b) {
		String friends = sender + " & " + recipient;
		if (b.search(friends) != null)
			return true;
		friends = recipient + " & " + sender;
		return b.search(friends) != null;

	}

	private void creatHashes(String m) {
		int m1 = Integer.parseInt(m);
		for (int i = 0; i < messages.length; i++)
			hashs[i] = new HashTable(m1);
	}

	public void createHashTables(String string) {
		hashs = new HashTable[messages.length];
		creatHashes(string);
		for (int i = 0; i < messages.length; i++) {
			HashTable tmp = hashs[i];
			String s = messages[i].getMesText();
			StringTokenizer st = new StringTokenizer(s);

			while (st.hasMoreTokens()) {
				String e = st.nextToken();
				tmp.Insert(e);
			}
		}

	}

}
