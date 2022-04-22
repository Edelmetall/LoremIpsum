package ch.zhaw.pm3.loremipsum.customer.ui;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.LoginData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.UpdateEmailData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.UpdatePasswordData;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.SignUpData;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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


    @GetMapping("/api/customer/")
    public Collection<CustomerEntity> getCustomer() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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

            // todo send e-mail login successful

            return customerEntity;
        }
        return null;
    }

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

            // todo send e-mail with new password and remove print
            System.out.println("New password: " + password);
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

                    // todo send e-mail to old and new e-mail address

                    return customerEntity;
                }
            }
        }
        return null;
    }

    @PutMapping("/api/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerEntity updateCustomer(
            @PathVariable("id") final String id, @RequestBody final CustomerEntity customer) {
        return repository.save(customer);
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/customer/new/{name}/{templateId}")
    public CustomerEntity updateCustomer(
            @PathVariable("name") final String name, final Long templateId) {
        TemplateEntity templateEntity = templateRepository.findById(templateId).get();

        CustomerEntity customer = new CustomerEntity(name, "Muster");
        customer.getOwnedTemplate().add(templateEntity);
        repository.save(customer);
        templateEntity.setOwner(customer);
        templateRepository.save(templateEntity);

        return repository.findById(customer.getId()).get();
    }


    @GetMapping("/api/customer/{id}")
    public CustomerEntity findById(@PathVariable long id) {
        return repository.findById(id);
    }

}
