package ch.heigvd.smtp;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a group, a group is composed of victims and one sender
 *
 * @author Célestin Piccin, Kévin Jorand
 */
public class Group {

    private final String sender;
    private final List<String> victims;
    private static final int MIN_SIZE_OF_GROUP = 3;

    /**
     * Constructor of Group
     *
     * @param sender the sender
     * @param victims a list of victims
     */
    public Group(String sender, List<String> victims){
        this.sender = sender;
        this.victims = victims;
    }


    /**
     * public static method, generate groups for the prank
     *
     * @param emails a list of emails
     * @param nbGroup the number of group to be formed
     * @return an array of group
     */
    public static Group[] createGroups(List<String> emails, int nbGroup){

        int nbOfEmails = emails.size();
        if(nbOfEmails < nbGroup * MIN_SIZE_OF_GROUP){
            throw new RuntimeException("Not enough emails or number of groups too high");
        }

        Group[] res = new Group[nbGroup];
        int sizeOfGroup = nbOfEmails/nbGroup;
        int rest = nbOfEmails % nbGroup;
        int counter = 0;

        for(int i = 0; i < nbGroup; i++){ //for every group
            List<String> vict = new ArrayList<String>();
            for(int j = 0; j < sizeOfGroup-1; j++){ //we retrieve the victims
                vict.add(emails.get(counter++));
            }
            String sender = emails.get(counter++); //and then the sender

            //the last group gets the remaining emails as victims, can be 0, depends on the modulus
            if(i == nbGroup-1){
                for(int k = 0; k < rest; k++){
                    vict.add(emails.get(counter++));
                }
            }
            res[i] = new Group(sender, vict); //a new group is formed and added in the result array
        }

        return res;
    }


    /**
     * Getter for sender
     *
     * @return sender
     */
    String getSender(){
        return sender;
    }

    /**
     * Getter for victims
     *
     * @return a list of victims
     */
    List<String> getVictims(){
        return victims;
    }
}
