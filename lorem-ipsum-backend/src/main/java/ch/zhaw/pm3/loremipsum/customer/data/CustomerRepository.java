package ch.zhaw.pm3.loremipsum.customer.data;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);

    CustomerEntity findByEmailAndPassword(String email, byte[] encodedPassword);

    CustomerEntity findById(long id);

    CustomerEntity findByEmail(String email);
}
