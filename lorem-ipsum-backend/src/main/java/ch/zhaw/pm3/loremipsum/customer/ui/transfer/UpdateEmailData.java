package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * contains data for update e-mail request
 */
@Getter
@Setter
public class UpdateEmailData {
    private String newEmail;
    private String password;
    private long customerId;

    /**
     * Check if the data is valid
     *
     * @return true if all values are set and valid
     */
    public boolean isValid() {
        return newEmail != null && newEmail.matches(SignUpData.REGEX_EMAIL);
    }
}
