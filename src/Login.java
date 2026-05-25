// Class to make the login class work
public class Login {

    // variables to store registered user details
    private String userName;// stores username
    private String Password;// stores password
    private String firstName;// stores first name
    private String lastName;// stores last name

    // construction to initialise user details when object is created
    public Login(String userName, String Password,String firstName, String lastName) {
        this.userName = userName;// assign username
        this.Password = Password;// assign password
        this.firstName = firstName;// assign first name
        this.lastName = lastName;// assign last name

    }
    // method to check if login details are correct
    public String loginUser (String enteredUsername, String enteredPassword) {
        //check if entered username and password match stored values
        if(enteredUsername.equals(this.userName) && enteredPassword.equals(this.Password)) {

            //return welcome message if login is successful
            return "Welcome"+" " + firstName + " " + lastName + " " + "It is great to see you";
        }else {
            // return error message if login details are incorrect
            return "Username or Password incorrect. Please try again." ;
        }

    }

}

