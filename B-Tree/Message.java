import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Message {

	private String sender;
	private String recipient;
	private String textMes;
	private int NumOfWords;

	public Message(String sender, String recipient) {
		if(sender==""||recipient=="") {
			throw new IllegalArgumentException("Illegal");
		}
		this.sender = sender;
		this.recipient = recipient;
		this.textMes = "";
		this.NumOfWords=0;
	

	}
	
	

	public int getNumOfWord() {
		return this.NumOfWords;
	}


	public String getNameOfSender() {
		return this.sender;
	}

	public String getNameOfRecipient() {
		return this.recipient;
	}

	public String getMesText() {
		return this.textMes;
	}

	public void setNameOfSender(String name) {
		this.sender = name;
	}

	public void setNameOfRecipient(String name) {
		this.recipient = name;
	}

	public void setMesText(String text) {
		this.textMes = text;
		StringTokenizer st = new StringTokenizer(textMes);
		this.NumOfWords = st.countTokens();
		

	}


}
