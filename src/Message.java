import java.util.ArrayList;


import java.util.Random;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    // variables used in the program
    public String recipient;
    public String messageText;
    public String messageHash;
    public long messageID;
    //counts total number of message created
    public static int totalMessage=0;
//Arrays
    public static ArrayList<String> sentMessage = new ArrayList<>();// To store sent messages
    public static ArrayList<String> disregardedMessages= new ArrayList<>();// to store disregarded messages
    public static ArrayList<String> storedMessages = new ArrayList<>();// to store stored messages
    public static ArrayList<String> messageHashes = new ArrayList<>();// to store created message hashes
    public static ArrayList<Long> messageIDs = new ArrayList<>();// to store message IDs
    public static ArrayList<String>storedRecipients = new ArrayList<>();// to store recipients


    // message class
    // constructor for creating a new message
    public Message(String recipient, String messageText){
        this.recipient= recipient;
        this.messageText= messageText;
// automatically generates a message ID and increments the total message counter.
        generateMessageID();
        totalMessage++;
    }
// checks if the message length is valid
    // The messages are required to be less than 250 words
    public boolean checkMessageLength()
    {
        if(messageText.length() <= 250){
            return true;
        }
        else    // return true is condition is true or else return false
        {
            return false;
        }
    }
    // generates a random 10 digit message ID
    public void generateMessageID()
    {// makes sure each message has a unique identity
        Random random = new Random();
        messageID= 1000000000L +
                (long) (random.nextDouble() * 9000000000L);
    }
    public boolean checkmessageID(){
        String id = String.valueOf(messageID);
        return id.length()<=10;
    }
    // checks if the recipient number meets the needed criteria
    public boolean checkRecipientCell() {
        return recipient.startsWith("+27")
                && recipient.length() <=13;
    }

    public String createMessageHash(int totalMessage)
    {
        // creates a message hash based on message ID and message content
        String idString = String.valueOf(messageID);
        String firstTwoDigits = idString.substring(0,2);
        String[] words = messageText.split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord= words[words.length-1].toUpperCase();
        return firstTwoDigits + ":" + totalMessage + ":"+" " + firstWord + lastWord;
    }
 //Allows for an output message when a message has been sent
public String sentMessage(){
        sentMessage.add(messageText);
        messageHashes.add(createMessageHash(totalMessage));
        messageIDs.add(messageID);
        return "Message Successfully Sent";
    }
    //Allows for an output message when a message has been disregarded
    public String disregardMessage(){
        disregardedMessages.add(messageText);
        return "Message disregarded";
    }
// This will display the message details like the message ID the message hash
    public String printMessage(int messageNumber){
        return
                "Message ID:" + messageID+ "\nMessage Hash:" + createMessageHash(messageNumber) + "\nRecipient:" + recipient+ "\nMessage:" + messageText;
    }
    // this returns the total number of messages
    public int returntotalMessages() {
        return totalMessage;
    }

    // stores the message for later sending
    public void storeMessage(){

       //These add message details to the arrays to store
        messageHash = createMessageHash(totalMessage);

        storedMessages.add(messageText);
        messageHashes.add(messageHash);
        messageIDs.add(messageID);
        storedRecipients.add(recipient);

//save store messages to json file
        Gson gson = new Gson();
        try{
            FileWriter writer = new FileWriter("messages.json", true);
            gson.toJson(this, writer);// uses Json to store message
            writer.write(System.lineSeparator());
            writer.close();
           // store message
        }
        catch (IOException e){
            System.out.println("Error storing message");
        }
    }


}
