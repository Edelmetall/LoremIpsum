package ch.zhaw.pm3.loremipsum.customer.ui;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/customer/")
    public CustomerEntity getCustomerPost(@RequestBody final CustomerEntity customer) {
        return repository.save(customer);
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
