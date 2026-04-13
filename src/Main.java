public static void main() {// main method-program starts here
    // create Registration object to handle the user signing up
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
}






