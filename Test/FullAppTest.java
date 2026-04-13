import org.junit.Test;
import static org.junit.Assert.*;

public class FullAppTest {
    //Registration test for username
    @Test
    public void testValiduserName() {// Test if username is valid
        Registration reg = new Registration();
        assertTrue(reg.checkuserName("abcdef_"));// valid <= chars and contains "_"

    }

    @Test
    public void testInvaliduserNameTooLong() {// test if the username is not too long
        Registration reg = new Registration();
        assertFalse(reg.checkuserName("abcdef_")); // too long
    }

    @Test
    public void testInvaliduserNameNoUnderscore() {//test if the username does not have an underscore
        Registration reg = new Registration();
        assertFalse(reg.checkuserName("abcd"));// missing an underscore
    }

    // Registration: Password tests
    @Test
    public void testValidPassword() {// test if password is valid
        Registration reg = new Registration();
        assertTrue(reg.checkPasword("Passw0rd!")); // password has an uppercase, a number and a special character

    }

    @Test
    public void testInvalidPasswordTooShort() {// test if password is not too short
        Registration reg = new Registration();
        assertFalse(reg.checkPasword("Pw1!")); // The password is too short
    }

    @Test
    public void testInvalidPasswordNoUppercase() {// check if password contains an uppercase
        Registration reg = new Registration();
        assertFalse(reg.checkPasword("password1")); // does not have an uppercase letter
    }

    @Test
    public void testInvalidPasswordNoDigit() {// test if password contains a digit
        Registration reg = new Registration();
        assertFalse(reg.checkPasword("Password!"));// missing digit
    }

    @Test
    public void testInvalidPasswordNoSpecialChar() {// tests if the password contains a special character
        Registration reg = new Registration();
        assertFalse(reg.checkPasword("Password1")); // missing a special character
    }

    //Registration Phone number tests
    @Test
    public void testValidPhoneNumberLocal() {// if phone number is local and starts with a 0 and contains 9 digits
        Registration reg = new Registration();
        assertTrue(reg.checkphoneNumber("0123456789")); // STARTS WITH 0 + 9 digits
    }

    @Test
    public void testValidPhoneNumberInternational() {// the phone number should contain a country code e.g +27
        Registration reg = new Registration();
        assertTrue(reg.checkphoneNumber("+27123456789")); // Starts with +27 + 9 digits
    }

    @Test
    public void testInvalidPhoneNumberTooShort() {
        Registration reg = new Registration();
        assertFalse(reg.checkphoneNumber("12345")); // phone number is too short
    }

    @Test
    public void testInvalidPhoneNumberWrongPrefix() {
        Registration reg = new Registration();
        assertFalse(reg.checkphoneNumber("9912345678")); // wrong prefix
    }

    //Login: Successful login
    @Test
    public void testSuccessfulLogin() {
        Login login = new Login("user123", "pass123", "John", "Doe");
        String result = login.loginUser("user123", "pass123");

    }

    // Login : Filed Login
    @Test
    public void testFailedLogin() {
        Login login = new Login("user123", "pass123", "John", "Does");
        String result = login.loginUser("wrongUser", "wrongPass");

    }
}
