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
}
