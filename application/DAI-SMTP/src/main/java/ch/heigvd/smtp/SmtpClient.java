package ch.heigvd.smtp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SmtpClient {
    private final String END = "\r\n";
    private final int serverPort;
    private final String serverAddress;

    public SmtpClient(String serverAddress, int serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendEmail(Email email) throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        String str;

        str = reader.readLine();
        if(!str.startsWith("220")){
            throw new IOException(str);
        }

        writer.write("EHLO smtpClient" + END);
        writer.flush();

        while ( (str = reader.readLine()) != null){
            if(str.startsWith("250 ")){
                break;
            }
            if(!str.startsWith("250-")){
                throw new IOException("Error : "+str);
            }
        }

        writer.write("MAIL FROM: " + email.getFrom() + END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("250")){
            throw new IOException(str);
        }

        for(int i = 0; i < email.getTo().size(); i++){
            writer.write("RCPT TO: " + email.getTo().get(i) + END);
            writer.flush();

            if( !(str = reader.readLine()).startsWith("250")){
                throw new IOException(str);
            }
        }

        writer.write("DATA" + END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("354")){
            throw new IOException(str);
        }

        writer.write("Content-type: text/plain; charset=utf-8" + END + "Subject: " +email.getSubject() + END + END +email.getMessage() + END + "." + END);
        writer.flush();

        if( !(str = reader.readLine()).startsWith("250")){
            throw new IOException(str);
        }

        writer.write("quit"+END);
        writer.flush();


        if( !(str = reader.readLine()).startsWith("221")){
            throw new IOException(str);
        }

        writer.close();
        reader.close();
        socket.close();
    }
}
