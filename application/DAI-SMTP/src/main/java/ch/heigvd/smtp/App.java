package ch.heigvd.smtp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {


    public static void main(String[] args) {

        /*List<String> to = Arrays.asList("rdm@rdm.ch", "lol@gmail.com", "hello@hl.swiss");

        Email emailTest = new Email("celestin.piccin@heig-vd.ch", to,
                "blablablablablablab", "test1");

        SmtpClient server = new SmtpClient("localhost", 25);

        try {
            server.sendEmail(emailTest);
        }
        catch (IOException e){
            System.out.println("Error:" + e);
        }*/

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
