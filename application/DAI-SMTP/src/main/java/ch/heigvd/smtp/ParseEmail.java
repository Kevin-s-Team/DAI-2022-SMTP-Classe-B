package ch.heigvd.smtp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseEmail {

    private static final Pattern EMAIL_VERIFICATION = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
    private List<String> emails = new ArrayList<String>();

    public ParseEmail() throws Exception{
        readEmails("./config/emails.txt");
    }

    private static boolean checkEmailValidity(String email) {
        Matcher matcher = EMAIL_VERIFICATION.matcher(email);
        return matcher.find();
    }

    private void readEmails(String path) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            if(checkEmailValidity(line)){
                emails.add(line);
            }
            else {
                throw new Exception("An email is invalid");
            }
        }

        //System.out.println(emails);
    }

    public List<String> getEmails(){
        return emails;
    }
}
