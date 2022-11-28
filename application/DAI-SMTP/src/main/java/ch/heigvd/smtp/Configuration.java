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
 * Class used to read the config file
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class Configuration {

    private int nbGroups;
    private int port;
    private String ipv4;

    //regex for ipv4 verification, found here : https://mkyong.com/regular-expressions/how-to-validate-ip-address-with-regular-expression/
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");

    /**
     * Constructor of Configuration
     *
     * @param path the path to the file containing the config
     * @throws Exception
     */
    public Configuration(String path) throws Exception{
        getConf(path);
    }

    /**
     * Private method, read the config in a file
     *
     * @param path the path to the config file
     * @throws Exception
     */
    private void getConf(String path) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        List<String> conf = new ArrayList<String>();
        String res;
        while ((res = reader.readLine()) != null){ //read every line in the file
            conf.add(res);
        }

        //conf file has only 3 lines
        if(conf.size() != 3){
            throw new RuntimeException("Your config.txt file is not valid");
        }

        ipv4 = conf.get(0);
        port = Integer.parseInt(conf.get(1));
        nbGroups = Integer.parseInt(conf.get(2));

       if(port < 0){
           throw new RuntimeException("Port number is not valid");
       }

       if(nbGroups <= 0){
           throw new RuntimeException("Number of groups is not valid");
       }

       if(!checkIpv4(ipv4)){
           if(!ipv4.equals("localhost")){ //we accept 'localhost' keyword
               throw new RuntimeException("Invalid ipv4 address");
           }
       }
    }

    /**
     * private method, used to check the validity of an IPv4 address
     *
     * @param ip the string to check
     * @return bool
     */
    private static boolean checkIpv4(String ip){
        Matcher matcher = IPV4_PATTERN.matcher(ip);
        return matcher.find();
    }

    /**
     * Getter for ipv4
     *
     * @return ipv4
     */
    public String getIpv4(){
        return ipv4;
    }

    /**
     * Getter for nbGroups
     *
     * @return nbGroups
     */
    public int getNbGroups(){
        return nbGroups;
    }

    /**
     * Getter for port
     *
     * @return port
     */
    public int getPort(){
        return port;
    }
}
