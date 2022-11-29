package ch.heigvd.smtp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to generate a list of ready-to-send emails
 * for the prank
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class EmailGenerator {

    private final Group[] groups;
    private final ArrayList<Message> messages;

    //random is used for the random selection of a message (body + subject)
    private static final Random random = new Random();

    /**
     * Constructor of EmailGenerator
     *
     * @param groups an array of groups to prank
     * @param path the path to the file containing the messages (body + subject)
     * @throws IOException in case of reading error from File (or if the file is not found [FileNotFound])
     * @throws RuntimeException in case of file content not matching the required design
     */
    public EmailGenerator(Group[] groups, String path) throws IOException {
        this.groups = groups;
        messages = Message.readMessages(path);
    }

    /**
     * Public method, generate the emails given the groups and the messages
     *
     * @return an array of emails for the prank
     */
    public ArrayList<Email> generateEmails(){
        ArrayList<Email> emails = new ArrayList<>(groups.length);
        String from, body, subject;
        List<String> to;

        //for every group, an email is generated, the body and subject is chosen randomly
        for(Group group : groups){
            int index = random.nextInt(messages.size());
            from = group.getSender();
            to = group.getVictims();
            body = messages.get(index).getBody();
            subject = messages.get(index).getSubject();
            emails.add(new Email(from, to, body, subject));
        }
        return emails;
    }

}
