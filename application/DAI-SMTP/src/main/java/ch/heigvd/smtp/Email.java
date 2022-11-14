package ch.heigvd.smtp;

import java.util.List;

public class Email {

    private final String from;
    private final List<String> to;
    private final String message;
    private final String subject;

    public Email(String from, List<String> to, String message, String subject){
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    public String getFrom(){
        return from;
    }

    public String getMessage(){
        return message;
    }

    public String getSubject(){
        return subject;
    }

    public List<String> getTo(){
        return to;
    }

}
