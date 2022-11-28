package ch.heigvd.smtp;

import java.util.List;

/**
 * This class represents an email.
 * An email contains a sender and a list of receptionists, it
 * also contains a subject and a message (the body)
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class Email {

    private final String from;
    private final List<String> to;
    private final String message;
    private final String subject;

    /**
     * Constructor of Email
     *
     * @param from sender
     * @param to receptionists
     * @param message body
     * @param subject subject
     */
    public Email(String from, List<String> to, String message, String subject){
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    /**
     * Getter for from
     *
     * @return sender
     */
    public String getFrom(){
        return from;
    }

    /**
     * Getter for message
     *
     * @return the body of the email
     */
    public String getMessage(){
        return message;
    }

    /**
     * Getter for subject
     *
     * @return the subject
     */
    public String getSubject(){
        return subject;
    }

    /**
     * Getter for to
     *
     * @return a list of receptionists
     */
    public List<String> getTo(){
        return to;
    }

}
