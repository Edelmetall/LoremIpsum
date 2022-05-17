package ch.zhaw.pm3.loremipsum.customer.ui;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.LoginData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.SignUpData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.UpdateEmailData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.UpdatePasswordData;
import ch.zhaw.pm3.loremipsum.generator.template.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.utils.EmailUtils;
import ch.zhaw.pm3.loremipsum.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Controller for customer related actions
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerRepository repository;
    private final TemplateRepository templateRepository;

    public CustomerController(@Autowired CustomerRepository repository,
                              @Autowired TemplateRepository templateRepository) {
        this.repository = repository;
        this.templateRepository = templateRepository;
    }

    /**
     * Try to get the user from the database by e-mail and password
     *
     * @param loginData contains e-mail and password
     * @return customer if valid data has been passed
     */
    @PostMapping("/api/customer/login")
    public CustomerEntity login(@RequestBody LoginData loginData) {
        String email = loginData.getEmail();
        String password = loginData.getPassword();
        byte[] encodedPassword = SecurityUtils.encodePassword(SecurityUtils.md5(email), password);

        CustomerEntity customer = repository.findByEmailAndPassword(email, encodedPassword);

        // temporary reset password is only valid for 24 hours
        if (customer != null) {
            customer.setPassword(null);
            if (customer.getPasswordResetAt() != null &&
                    customer.getPasswordResetAt().isAfter(LocalDateTime.now().plusHours(24))) {
                return null;
            }
        }

        return customer;
    }

    private static final String SIGN_UP_SUBJECT = "Welcome to LoremIpsum";
    private static final String SIGN_UP_BODY = """
            Hello %s %s!
            
            You have successfully registered for LoremIpsum!
            
            Your LoremIpsum Team
            """;

    /**
     * Create e new entry for the customer
     *
     * @param signUpData contains e-mail, first name, last name and password
     * @return created customer
     */
    @PostMapping("/api/customer/signup")
    public CustomerEntity signup(@RequestBody SignUpData signUpData) {
        if (signUpData.isValid() && !repository.existsByEmail(signUpData.getEmail())) {
            String password = signUpData.getPassword();
            byte[] encodedPassword = SecurityUtils.encodePassword(SecurityUtils.md5(signUpData.getEmail()), password);

            CustomerEntity customerEntity = new CustomerEntity(signUpData.getFirstName(), signUpData.getLastName());
            customerEntity.setEmail(signUpData.getEmail());
            customerEntity.setPassword(encodedPassword);

            customerEntity = repository.save(customerEntity);

            EmailUtils.sendEmail(customerEntity.getEmail(), SIGN_UP_SUBJECT, SIGN_UP_BODY.formatted(
                    customerEntity.getFirstName(),
                    customerEntity.getLastName()
            ));

            return customerEntity;
        }
        return null;
    }

    private static final String RESET_PASSWORD_SUBJECT = "Your new LoremIpsum password";
    private static final String RESET_PASSWORD_BODY = """
            Hello %s %s!
            
            Your new password is: %s
            
            This password is valid for 24 hours. Please change it once you log in.
            
            Your LoremIpsum Team
            """;

    /**
     * Reset the password for a customer
     *
     * @param email customer e-mail address
     */
    @GetMapping("/api/customer/resetPassword/{email}")
    public void resetPassword(@PathVariable String email) {
        CustomerEntity customerEntity = repository.findByEmail(email);
        if (customerEntity != null) {
            String password = SecurityUtils.generatePassword();
            byte[] encoded = SecurityUtils.encodePassword(SecurityUtils.md5(customerEntity.getEmail()), password);

            customerEntity.setPassword(encoded);
            customerEntity.setPasswordResetAt(LocalDateTime.now());

            repository.save(customerEntity);

            EmailUtils.sendEmail(customerEntity.getEmail(), RESET_PASSWORD_SUBJECT, RESET_PASSWORD_BODY.formatted(
                    customerEntity.getFirstName(),
                    customerEntity.getLastName(),
                    password
            ));
        }
    }

    /**
     * Change the password for a customer
     *
     * @param updatePasswordData contains the old password, new password and the customer id
     * @return updated customer entity
     */
    @PostMapping("/api/customer/updatePassword")
    public CustomerEntity updatePassword(@RequestBody UpdatePasswordData updatePasswordData) {
        if (updatePasswordData.isValid()) {
            CustomerEntity customerEntity = repository.findById(updatePasswordData.getCustomerId());
            if (customerEntity != null) {
                byte[] salt = SecurityUtils.md5(customerEntity.getEmail());
                if (Arrays.equals(customerEntity.getPassword(),
                        SecurityUtils.encodePassword(salt, updatePasswordData.getOldPassword()))) {
                    customerEntity.setPassword(SecurityUtils.encodePassword(salt, updatePasswordData.getNewPassword()));
                    customerEntity.setPasswordResetAt(null); // in case the previous password was a temporary reset password
                    return repository.save(customerEntity);
                }
            }
        }
        return null;
    }

    /**
     * Change the e-mail address for a customer
     *
     * @param updateEmailData contains the old password, new password and the customer id
     * @return updated customer entity
     */
    @PostMapping("/api/customer/updateEmail")
    public CustomerEntity updateEmail(@RequestBody UpdateEmailData updateEmailData) {
        if (updateEmailData.isValid()) {
            CustomerEntity customerEntity = repository.findById(updateEmailData.getCustomerId());
            if (customerEntity != null) {
                byte[] salt = SecurityUtils.md5(customerEntity.getEmail());
                if (Arrays.equals(customerEntity.getPassword(),
                        SecurityUtils.encodePassword(salt, updateEmailData.getPassword()))) {
                    customerEntity.setEmail(updateEmailData.getNewEmail());
                    customerEntity = repository.save(customerEntity);
                    return customerEntity;
                }
            }
        }
        return null;
    }
}
