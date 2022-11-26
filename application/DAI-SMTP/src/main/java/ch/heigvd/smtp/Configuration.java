package ch.heigvd.smtp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuration {

    private int nbGroups;
    private int port;
    private String ipv4;

    public Configuration() throws Exception{
        getConf("./config/config.txt");
    }

    private void getConf(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        List<String> conf = new ArrayList<String>();
        String res;
        while ((res = reader.readLine()) != null){
            conf.add(res);
        }

        if(conf.size() != 3){
            throw new RuntimeException("Your config.txt file is not valid");
        }

        ipv4 = conf.get(0);
        port = Integer.parseInt(conf.get(1));
        nbGroups = Integer.parseInt(conf.get(2));
        /*System.out.println(ipv4);
        System.out.println(port);
        System.out.println(nbGroups);*/

    }

    public String getIpv4(){
        return ipv4;
    }

    public int getNbGroups(){
        return nbGroups;
    }

    public int getPort(){
        return port;
    }
}
