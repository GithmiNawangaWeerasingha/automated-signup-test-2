package utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\+94\\d{9}$");
    }
    public static boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    }
}
