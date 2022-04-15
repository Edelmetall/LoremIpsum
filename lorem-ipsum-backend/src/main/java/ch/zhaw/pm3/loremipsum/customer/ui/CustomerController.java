package ch.zhaw.pm3.loremipsum.customer.ui;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.customer.ui.transfer.LoginData;
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
        return repository.findByEmailAndEncodedPassword(email, encodedPassword);
    }

    /**
     * Create e new entry for the customer
     *
     * @param signUpData contains e-mail, first name, last name and password
     * @return created customer
     */
    @PostMapping("/api/customer/signup")
    public CustomerEntity signup(@RequestBody SignUpData signUpData) {
        String password = signUpData.getPassword();
        byte[] encodedPassword = SecurityUtils.encodePassword(SecurityUtils.md5(signUpData.getEmail()), password);

        CustomerEntity customerEntity = new CustomerEntity(signUpData.getFirstName(), signUpData.getLastName());
        customerEntity.setEmail(signUpData.getEmail());
        customerEntity.setEncodedPassword(encodedPassword);

        return repository.save(customerEntity);
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
