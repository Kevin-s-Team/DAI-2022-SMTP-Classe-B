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
     * @throws IOException
     * @throws RuntimeException
     */
    public EmailGenerator(Group[] groups, String path) throws IOException, RuntimeException {
        this.groups = groups;
        messages = Message.readMessages(path);
    }

    /**
     * Public method, generate the emails given the groups and the messages
     *
     * @return an array of emails for the prank
     */
    public ArrayList<Email> generateEmails(){
        ArrayList<Email> emails = new ArrayList<Email>(groups.length);
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
