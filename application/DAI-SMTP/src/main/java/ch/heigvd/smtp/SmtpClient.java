package ch.heigvd.smtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * Class that handles sending emails with the SMTP protocol to
 * an SMTP server
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class SmtpClient {
    private static final String END = "\r\n";
    private final int serverPort;
    private final String serverAddress;

    /**
     * Constructor of SmtpClient
     *
     * @param serverAddress address of the server
     * @param serverPort port of the server
     */
    public SmtpClient(String serverAddress, int serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Public method, sends an email using SMTP
     *
     * @param email the email to be sent
     * @throws IOException
     */
    public void sendEmail(Email email) throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        String str;

        /* STEP 1: connection */
        str = reader.readLine();
        if(!str.startsWith("220")){ //check first response
            throw new IOException(str);
        }

        /* STEP 2: EHLO */
        writer.write("EHLO smtpClient" + END);
        writer.flush();

        while ( (str = reader.readLine()) != null){ //check responses after EHLO
            if(str.startsWith("250 ")){
                break;
            }
            if(!str.startsWith("250-")){
                throw new IOException(str);
            }
        }

        /* STEP 3: MAIL FROM */
        writer.write("MAIL FROM: " + email.getFrom() + END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("250")){ //check response after MAIL FROM
            throw new IOException(str);
        }


        /* STEP 4: RCPT TO */
        for(int i = 0; i < email.getTo().size(); i++){ //send RCPT TO for every receptionist
            writer.write("RCPT TO: " + email.getTo().get(i) + END);
            writer.flush();

            if( !(str = reader.readLine()).startsWith("250")){ //check response after RCPT TO
                throw new IOException(str);
            }
        }

        /* STEP 5: DATA */
        writer.write("DATA" + END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("354")){ //check response after DATA
            throw new IOException(str);
        }

        //write content-type, from and first to
        writer.write("Content-type: text/plain; charset=utf-8" + END +
                "From: " + email.getFrom() + END + "To: " + email.getTo().get(0));

        int toSize = email.getTo().size();
        if(toSize > 1){ //if multiple receptionist => complete the to section
            for(int i = 1; i < toSize; i++){
                writer.write(", " + email.getTo().get(i));
            }
        }
        writer.write(END);

        //write the subject of the email, encoded in Base64
        writer.write("Subject: =?utf-8?B?" +
                Base64.getEncoder().encodeToString(email.getSubject().getBytes()) + "?=" + END);
        writer.write(END);

        //write the body of the message
        writer.write(email.getMessage());

        //finally terminate with a \r\n.\r\n and flush
        writer.write(END+"."+END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("250")){ //check server response
            throw new IOException(str);
        }

        /* STEP 6: QUIT */
        writer.write("quit"+END); //terminate the session
        writer.flush();


        if( !(str = reader.readLine()).startsWith("221")){ //check server response
            throw new IOException(str);
        }

        //close streams and socket
        writer.close();
        reader.close();
        socket.close();
    }
}
