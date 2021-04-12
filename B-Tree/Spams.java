import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Spams {
	private static Spam[] spams;


	public void ReadSpam(String filePath) {
		// convert a file to string
		try {
			Scanner x = new Scanner(new File(filePath));
			Queue<Spam> qu = new Queue();
			while (x.hasNext()) {
				String word = x.next();
				double threShold = Double.parseDouble(x.next());
				Spam toadd = new Spam(word, threShold);
				qu.enqueue(toadd);
			}
			this.spams = new Spam[qu.size()];
			int i = 0;
			while (!qu.isEmpty()) {
				spams[i] = (Spam) qu.dequeue();
				i++;

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SpamsIterator<Spam> iterator() {
		return new SpamsIterator<Spam>(spams);
	}

}
