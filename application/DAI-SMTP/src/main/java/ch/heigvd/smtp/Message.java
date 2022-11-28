package ch.heigvd.smtp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Message {
	private String subject;
	private String body;
	private static final String LINE_ENDING = "\r\n";
	private static final String MESSSAGE_ENDING = ".";
	private static final Logger LOG = Logger.getLogger(Message.class.getName());


	protected Message(String subject, String body){ // No need public exposure ... but make sense to let children call it
		this.subject = subject;
		this.body = body;
	}

	static public ArrayList<Message> readMessages(String inputFile) throws IOException, RuntimeException {
		ArrayList<Message> messages = new ArrayList<>();
		try (BufferedReader inReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));) {
			while(inReader.ready()){
				String sub = inReader.readLine();
				if(sub.equals("") && !inReader.ready()){ // blank line at the end of the file
					continue;
				}
				String bod = "";
				String line = "";
				while(!(line = inReader.readLine()).equals(MESSSAGE_ENDING)) {
					if (!inReader.ready()) {
						throw new RuntimeException("Malformed input file");
					}
					bod += line + LINE_ENDING;
				}
				bod = bod.substring(0, bod.length()-2);
				messages.add(new Message(sub, bod));
			}
			// isr.close(); => no : try with ressources
			return messages;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "Error while reading message file.", ex);
			throw ex;
		}
	}

	String getSubject(){
		return subject;
	}

	String getBody(){
		return body;
	}
}
