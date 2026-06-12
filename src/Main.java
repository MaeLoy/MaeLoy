import java.sql.SQLOutput;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {


        Registration registration = new Registration();

        // calling the registration method to validate the details entered by the user
        registration.Register();

        // Scanner method to read the input made through loging in
        Scanner scanner = new Scanner(System.in);

        // ask the user to log in after registration
        System.out.println("Please log in now");

        //Get the user's username
        System.out.println("Enter Username");
        String loginUser = scanner.nextLine();

        // Get the user's password
        System.out.println("Enter Password");
        String loginPass = scanner.nextLine();

        // Create login object using registered user details
        Login login = new Login(registration.userName, registration.Password, registration.firstName, registration.lastName);

        // Attempt to login and display the results
        System.out.println(login.loginUser(loginUser, loginPass));

// after user is successfully logged i they are welcomed to quick chat
        if (login.loginUser(loginUser, loginPass).contains("Welcome")) {
            System.out.println("Welcome to QuickChat");
            int choice = 0;
            // they are welcomed by the following options as soon as they enter quick chat
            while (choice != 3) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("4. Stored Messages");

                choice = scanner.nextInt();// allows the user to insert the option they would like
                scanner.nextLine();

                if (choice == 1) {
// if user opts for option 1 they get a prompt asking them the number of messages they wish to send
                    System.out.println("How many messages would you like to send?");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine();// allows the user to enter their desired option

                    // continuation of choosing option 1
                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("Enter recipient number:");// the user enters their recipient number
                        String recipient = scanner.nextLine();// allows the user to be able to enter the number

                        System.out.println("Enter your message:");// user prompt
                        String text = scanner.nextLine();// allows the user to enter their message

                        Message msg = new Message(recipient,text);// after entering their message the program asks them what they would like to do with their message
                        System.out.println("Choose an option: ");
                        System.out.println("1. Send message");
                        System.out.println("2. Disregard message");
                        System.out.println("3. Store Message to send later");

                        int option= scanner.nextInt();
                        scanner.nextLine();// allows the user to enter their option

                        if (msg.checkMessageLength()){// checks the message length
                            if(option == 1) {
                                System.out.println(msg.sentMessage());
                                System.out.println(msg.printMessage(i));

                            }else if (option==2){
                                System.out.println("Press 0 to delete message");// prompt for option 2
                                int delete = scanner.nextInt();
                                if (delete==0){
                                    System.out.println("Message discarded");// output after choosing to discard message
                                }
                            }else if (option == 3){
                                msg.storeMessage();
                                System.out.println("Message successfully stored.");// prompt for option 3
                                System.out.println("Message ID: " + msg.messageID);
                                System.out.println("Messsage Hash: "+ msg.messageHash);
                                System.out.println();
                            }
                        } else {
                            System.out.println("Please enter a message of less than 250 characters");

                        }
                    }
                } else if (choice == 2) {
                    System.out.println("Coming Soon.");
                } else if (choice == 3) {
                    System.out.println("Goodbye");
                    break;
                    // stored messages menu
                } else if (choice == 4) {
                    System.out.println("Stored Messages Menu");
                    System.out.println("1. Display sender and recipient");
                    System.out.println("2. Display the longest stored message");
                    System.out.println("3. Search by message ID");
                    System.out.println("4. Search by recipient");
                    System.out.println("5. Delete using message hash");
                    System.out.println("6. Display report for all stored messages");
//methods
                    int storedChoice= scanner.nextInt();
                    if(storedChoice==1){// Display all stored recipients and their messages
                        for(int i = 0; i < Message.storedMessages.size(); i++){// loop through all the stored messages and display them
                            System.out.println("Recipient: "+ Message.storedRecipients.get(i));
                            System.out.println("Message: "+ Message.storedMessages.get(i));
                            System.out.println();// blank line for better presentation

                        }
                    }
                    else if(storedChoice==2){
                        String longestMessage= "";// display the longest message stored
                        for (int i = 0; i< Message.storedMessages.size(); i++){// this will basically compare all stored message lengths
                            if(Message.storedMessages.get(i).length()> longestMessage.length()){
                                longestMessage=Message.storedMessages.get(i);
                            }
                        }
                        System.out.println("Longest stored message: ");
                        System.out.println(longestMessage);
                    }
                    else if(storedChoice==3){// search for stored message using its message ID
                        System.out.println("Enter Message ID: ");
                        long searchID = scanner.nextLong();
                        boolean found = false;// whether the message was found

                        for (int i = 0; i< Message.messageIDs.size(); i++){// search throughh all message IDs
                            if(Message.messageIDs.get(i) == searchID){// Displays message details if the entered ID matches
                                System.out.println("Message Found: ");
                                System.out.println("Recipient: "+ Message.storedRecipients.get(i));
                                System.out.println("Message: "+ Message.storedMessages.get(i));

                                found = true;
                                break;
                            }
                        }
                        if (! found){// Displays message not found if the message ID had no match
                            System.out.println("Message ID not found.");
                        }
                    }
                    else if(storedChoice==4){// search for messages using a recipient
                        System.out.println("Enter recipient: ");
                        scanner.nextLine();
                        String searchRecipient = scanner.nextLine();

                        boolean found = false;
                        for (int i = 0; i < Message.storedRecipients.size(); i++){// search through stored recipients
                            if (Message.storedRecipients.get(i).equals(searchRecipient)){// Display the message recipient and the message details
                                System.out.println("Message Found: ");
                                System.out.println("Recipient: "+ Message.storedRecipients.get(i));
                                System.out.println("Message: "+ Message.storedMessages.get(i));

                                 found = true;
                            }
                        }
                        if (! found) {// notify users if a recipient is not found matching their input
                            System.out.println("Recipient not found.");
                        }
                    }
                    else if(storedChoice==5){// Delete a stored message using its message hash
                        System.out.println("Enter message hash: ");
                        scanner.nextLine();
                        String searchHash = scanner.nextLine();

                        boolean found = false;

                        for (int i = 0; i< Message.messageHashes.size(); i++) {// search for the matching message hash

                            if (Message.messageHashes.get(i).equals(searchHash)) {// delete all message information related to that message hash found

                                Message.messageHashes.remove(i);
                                Message.storedMessages.remove(i);
                                Message.storedRecipients.remove(i);
                                Message.messageIDs.remove(i);

                                System.out.println("Message deleted successfully.");

                                found = true;
                                break;
                            }
                        }
                        if (!found){// informs the user if the hash they entered does not match any stored message
                            System.out.println("Message hash not found.");
                        }
                    }
                    else if(storedChoice==6){// display a report of all stored messages
                        for (int i = 0; i < Message.storedMessages.size(); i++) {//loop through all stored messages and display them and their details

                            System.out.println("Message Hash: "+ Message.messageHashes.get(i));
                            System.out.println("Recipient: " + Message.storedRecipients.get(i));
                            System.out.println("Message: "+ Message.storedMessages.get(i));
                            System.out.println();
                        }
                    }


                }else {
                    System.out.println("Invalid choice");
                }// End of stored messages menu
            }// closes while loop
        }// closes the if the login is successful
    }//closes the main method
}// closes class
