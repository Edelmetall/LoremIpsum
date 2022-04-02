package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.generator.data.RowTemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.RowTemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@SpringBootApplication
public class LoremIpsumApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoremIpsumApplication.class, args);
    }

    CommandLineRunner initCustomer(@Autowired CustomerRepository customerRepository) {
        return args -> {

        };
    }

    @Bean
    @Transactional
    CommandLineRunner initTemplate(
            @Autowired CustomerRepository customerRepository,
            @Autowired TemplateRepository templateRepository,
            @Autowired RowTemplateRepository rowTemplateRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                CustomerEntity user = new CustomerEntity(name, name.toLowerCase() + "@domain.com");
                customerRepository.save(user);
            });
            customerRepository.findAll().forEach(System.out::println);


            TemplateEntity entity = new TemplateEntity("Test");

            RowTemplateEntity one = new RowTemplateEntity();
            one.setIndex(1);

            RowTemplateEntity second = new RowTemplateEntity();
            second.setIndex(2);
            entity.getRowTemplateEntities().add(one);
            entity.getRowTemplateEntities().add(second);
            templateRepository.save(entity);
            entity.getRowTemplateEntities().forEach(rowTemplateEntity -> {
                        rowTemplateEntity.setTemplateEntity(entity);
                        rowTemplateRepository.save(rowTemplateEntity);
                    }
            );
        };
    }


}
