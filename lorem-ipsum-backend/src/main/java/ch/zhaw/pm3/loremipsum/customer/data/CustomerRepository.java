package ch.zhaw.pm3.loremipsum.customer.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByLastName(String lastName);

    boolean existsByEmail(String email);

    CustomerEntity findByEmailAndEncodedPassword(String email, byte[] encodedPassword);

    CustomerEntity findById(long id);
}
