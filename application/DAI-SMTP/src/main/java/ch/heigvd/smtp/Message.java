package ch.heigvd.smtp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing the message of an email (body + subject) and allowing to generate a list of said objects from
 * proper text file (see documentation for the text file format
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class Message {
	// Properties of an email
	private final String subject;
	private final String body;

	// Constants
	private static final String LINE_ENDING = "\r\n";
	private static final String MESSAGE_ENDING = ".";

	private static final Logger LOG = Logger.getLogger(Message.class.getName());

	/**
	 * Constructor of a message passing the subject and body directly.
	 * It does not need to be publicly exposed ... but it makes sense to let potential children call it.
	 *
	 * @param subject the subject of the message
	 * @param body the message body itself
	 */
	protected Message(String subject, String body){
		this.subject = subject;
		this.body = body;
	}

	/**
	 * Actual method used to create a List of Messages from a text file. The description of the text file format can be
	 * found in the readme.
	 *
	 * @param inputFile the path to the file to read Messages from
	 * @return The list of messages from file
	 * @throws IOException in case of reading error from File (or if the file is not found [FileNotFound])
	 * @throws RuntimeException in case of file content not matching the required design
	 */
	static public ArrayList<Message> readMessages(String inputFile) throws IOException {
		ArrayList<Message> messages = new ArrayList<>();

		// Opening the file
		try (BufferedReader inReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8)) ) {

			// Reading the messages from the file
			while(inReader.ready()){
				// First line of each message representation in text file
				String sub = inReader.readLine();

				if(sub.equals("") && !inReader.ready()){ // blank line at the end of the file
					continue;
				}

				// Reading the body too
				StringBuilder bod = new StringBuilder();
				String line;
				while(!(line = inReader.readLine()).equals(MESSAGE_ENDING)) {
					if (!inReader.ready()) {
						throw new RuntimeException("Malformed input file");
					}
					bod.append(line).append(LINE_ENDING);
				}

				// Creating the actual Message and adding it to the list
				messages.add(new Message(sub, bod.substring(0, bod.length() - 2)));
			}
			// no "isr.close();" : try with ressources
			return messages;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "Error while reading message file.", ex);
			throw ex;
		}
	}

	public static void main(String[] args) {
		try {
			ArrayList<Message> m = Message.readMessages("./application/DAI-SMTP/config/messages.txt");
			System.out.println(m.size());
			System.out.println(m.get(m.size()-2).getBody());
			System.out.println(m.get(m.size()-1).getBody());
		}catch (Exception e){
			// Don't care!!
		}
	}

	String getSubject(){
		return subject;
	}

	String getBody(){
		return body;
	}
}
