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

                                System.out.println("Message successfully stored.");// prompt for option 3
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
                } else {
                    System.out.println("Invalid choice");
                }
            }// closes while loop
        }// closes the if the login is successful
    }//closes the main method
}// closes class
