import javax.xml.transform.Source;
import java.util.Scanner;

public class Registration {
    // Declaring Variables to store details
    Scanner scanner = new Scanner(System.in); // insert scanner object to read input from user
    String userName; // stores Username
    String Password; // stores the password
    String phoneNumber;// stores the phone number
    String lastName;// stores last name
    String firstName; // stores first name
    public boolean checkuserName(String userName) {return userName.length()<=5 && userName.contains("_");}
    // method to check if username meets requirements also contains the rules of  what is required
    public boolean checkPasword(String Password){
        //method to check if password meets the requirements and also states the rules
        if (Password.length() < 8) return false;
        // check if password length is atleast 8 characters
        boolean hasUppercase = false;// check if password has an uppercase
        boolean hasSpecial = false;// check is password has a special character
        boolean hasDigit = false;// check if password has a digit
        // loop through characters in the password
        for (char ch : Password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;//checks if character is an uppercase letter
            if (!Character.isLetterOrDigit(ch)) hasSpecial = true;// checks if character is not a letter or digit
            if (Character.isDigit(ch)) hasDigit = true;// checks if password contains a digit
        }
        return  hasUppercase && hasSpecial && hasDigit;
    } // the password will only be valid if it has all the conditions above

    // This is now the actual registration sections
    public boolean checkphoneNumber(String phoneNumber) { return phoneNumber.matches("(\\+27|0)[0-9]{9}");}
    public void Register() { // method for registration
        System.out.println("Enter first name");//ask the user to enter their first name
        firstName = scanner.nextLine();
        System.out.println("Enter last name");// asks the user to enter their lastname
        lastName = scanner.nextLine();

        do { // loop until condition is met
            System.out.println("Enter Username");
            userName = scanner.nextLine();
            //check if username is correct
            if (checkuserName(userName)) {
                System.out.println("Username successfully captured");
            } else {// if not correct display the line below
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is more than five characters in length");

            }
        } while (!checkuserName(userName));// this code should repeat until valid

        do {// loop until a correct password is entered
            System.out.println("Enter Password");
            Password = scanner.nextLine();// read password input

            // check if the password is valid using the method for check password
            if (checkPasword(Password)) {
                System.out.println("Password successfully captured");
            } else {
                //give the error message mentioned below if the password does not meet the requirements mentioned above
                System.out.println("Password is not correctly formatted; ensure the password contains atleast eight characters, a capital letter, a number, a special character");
            }
        } while (!checkPasword(Password));// repeat until password is valid

        do {// loop until a phone number with the correct requirements and format is entered
            System.out.println("Enter phone Number");
            phoneNumber = scanner.nextLine();

            // check if the format used for the phone number is correct
            if (checkphoneNumber(phoneNumber)) {
                System.out.println("Cellphone number successfully added");
            } else {
                // show the error message below if the phone number is not correct
                System.out.println("Cellphone number incorrectly formatted or does not contain international code");
            }
        } while (!checkphoneNumber(phoneNumber));// repeat until the phone number is correct


    }


}

