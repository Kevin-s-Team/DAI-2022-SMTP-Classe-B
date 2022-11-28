package ch.heigvd.smtp;

import java.util.ArrayList;


/**
 * App to run the prank
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class App {

    public static void main(String[] args) {

        try{
            //get config
            Configuration conf = new Configuration("./config/config.txt");

            //prepare smtp client
            SmtpClient client = new SmtpClient(conf.getIpv4(), conf.getPort());

            //get emails
            ParseEmail parsed = new ParseEmail("./config/emails.txt");

            //form the groups
            Group[] groups = Group.createGroups(parsed.getEmails(), conf.getNbGroups());

            //generate the prank emails
            EmailGenerator emailGenerator = new EmailGenerator(groups,"./config/messages.txt" );
            ArrayList<Email> prankEmails = emailGenerator.generateEmails();

            //send the emails
            for(Email emailToSend : prankEmails){
                client.sendEmail(emailToSend);
            }
        }
        catch (Exception e){ //any generated exception is caught here and the error message is printed
            System.out.println("error: " + e);
        }
    }

}
