package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * contains data for sign up request
 */
@Getter
@Setter
public class SignUpData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private static final String REGEX_EMAIL = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}$";

    public boolean isValid() {
        return firstName != null && !firstName.isBlank() &&
                lastName != null && !lastName.isBlank() &&
                email != null && email.matches(REGEX_EMAIL) &&
                password != null && password.matches(REGEX_PASSWORD);
    }
}
