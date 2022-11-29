package ch.heigvd.smtp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class to read the prank victims emails from a file
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class ParseEmail {

    //REGEX for email verification, found on the internet, I don't remember where...
    private static final Pattern EMAIL_VERIFICATION = Pattern.compile("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
    private final List<String> emails = new ArrayList<>();

    /**
     * Constructor of ParseEmail
     *
     * @param path the path to the file containing the emails to be read
     * @throws Exception In case there is a problem reading the file in path
     */
    public ParseEmail(String path) throws Exception{
        readEmails(path);
    }

    /**
     * Private method, use the regex to checks if the email is valid
     *
     * @param email the email that has to be checked
     * @return bool
     */
    private static boolean checkEmailValidity(String email) {
        Matcher matcher = EMAIL_VERIFICATION.matcher(email);
        return matcher.find();
    }

    /**
     * Private method, read the emails in a file
     *
     * @param path path to the file
     * @throws Exception In case there is a problem reading the file
     */
    private void readEmails(String path) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(path), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            if(checkEmailValidity(line)){ //if email is valid => added to the list
                emails.add(line);
            }
            else { //otherwise throw exception
                throw new RuntimeException("An email is invalid");
            }
        }
    }

    /**
     * Getter for emails
     *
     * @return a list of string containing emails
     */
    public List<String> getEmails(){
        return emails;
    }
}
