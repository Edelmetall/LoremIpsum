package ch.zhaw.pm3.loremipsum.customer.ui.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * Contains data required for the login request
 */
@Getter
@Setter
public class LoginData {
    private String email;
    private String password;
}
