package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * contains data for update password request
 */
@Getter
@Setter
public class UpdatePasswordData {
    private String oldPassword;
    private String newPassword;
    private long customerId;

    /**
     * Check if the data is valid
     *
     * @return true if all values are set and valid
     */
    public boolean isValid() {
        return newPassword != null && newPassword.matches(SignUpData.REGEX_PASSWORD);
    }
}
