package ch.heigvd.smtp;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {


    public static void main(String[] args) {

        List<String> to = Arrays.asList("rdm@rdm.ch", "lol@gmail.com", "hello@hl.swiss");

        Email emailTest = new Email("celestin.piccin@heig-vd.ch", to,
                "blablablablablablab", "test1");

        SmtpClient server = new SmtpClient("localhost", 25);

        try {
            server.sendEmail(emailTest);
        }
        catch (IOException e){
            System.out.println("Error:" + e);
        }


    }

}
