package ch.heigvd.smtp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try{
            Configuration conf = new Configuration();
            SmtpClient client = new SmtpClient(conf.getIpv4(), conf.getPort());
            ParseEmail parsed = new ParseEmail();
            Group[] groups = Group.createGroups(parsed.getEmails(), conf.getNbGroups());
            EmailGenerator emailGenerator = new EmailGenerator(groups,"./config/messages.txt" );
            ArrayList<Email> prankEmails = emailGenerator.generateEmails();
            for(Email emailToSend : prankEmails){
                client.sendEmail(emailToSend);
            }
        }
        catch (Exception e){
            System.out.println("error: " + e);
        }

    }

}
