package ch.heigvd.smtp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmailGenerator {

    private final Group[] groups;
    private final ArrayList<Message> messages;

    private static final Random random = new Random();

    public EmailGenerator(Group[] groups, String path){
        this.groups = groups;
        messages = Message.readMessages(path);
    }

    public ArrayList<Email> generateEmails(){
        ArrayList<Email> emails = new ArrayList<Email>(groups.length);
        String from, body, subject;
        List<String> to;


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
