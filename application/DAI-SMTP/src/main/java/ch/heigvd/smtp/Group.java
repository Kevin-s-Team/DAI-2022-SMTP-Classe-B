package ch.heigvd.smtp;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String sender;
    private List<String> victims;
    private static final int MIN_SIZE_OF_GROUP = 3;

    public Group(String sender, List<String> victims){
        this.sender = sender;
        this.victims = victims;
    }

    public static Group[] createGroups(List<String> emails, int nbGroup){

        int nbOfEmails = emails.size();
        if(nbOfEmails < nbGroup * MIN_SIZE_OF_GROUP){
            throw new RuntimeException("Not enough emails or number of groups too high");
        }

        Group[] res = new Group[nbGroup];
        int sizeOfGroup = nbOfEmails/nbGroup;
        int rest = nbOfEmails % nbGroup;
        int counter = 0;

        for(int i = 0; i < nbGroup; i++){
            List<String> vict = new ArrayList<String>();
            for(int j = 0; j < sizeOfGroup-1; j++){
                vict.add(emails.get(counter++));
            }
            String sender = emails.get(counter++);

            if(i == nbGroup-1){
                for(int k = 0; k < rest; k++){
                    vict.add(emails.get(counter++));
                }
            }
            res[i] = new Group(sender, vict);
        }

        return res;
    }
}
