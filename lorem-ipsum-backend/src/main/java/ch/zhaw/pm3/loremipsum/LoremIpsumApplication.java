package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.RowConfigurationEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.repo.RowTemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.customer.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.Arrays;
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
            @Autowired RowTemplateRepository rowTemplateRepository,
            @Autowired DataFormatRepository dataFormatRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                CustomerEntity user = new CustomerEntity(name, name.toLowerCase() + "@domain.com");
                user.setEmail(name);
                user.setEncodedPassword(SecurityUtils.encodePassword(SecurityUtils.md5(user.getEmail()), "1234"));
                customerRepository.save(user);
            });
            customerRepository.findAll().forEach(System.out::println);

            Arrays.stream(EntryTypeEnum.values()).map(EntryTypeEnum::getDisplayName).forEach(name -> {
                DataFormatEntity dataFormatEntity = new DataFormatEntity();
                dataFormatEntity.setName(name);
                dataFormatEntity.setActive(true);
                dataFormatRepository.save(dataFormatEntity);
            });


            TemplateEntity entity = new TemplateEntity("Test");

            entity.setOwner(customerRepository.findById(50L));

            RowConfigurationEntity one = new RowConfigurationEntity();
            one.setIndex(1);
            one.setDataFormatEntity(dataFormatRepository.findById(50L).get());
            one.setName("firstName");

            RowConfigurationEntity second = new RowConfigurationEntity();
            second.setIndex(2);
            second.setDataFormatEntity(dataFormatRepository.findById(51L).get());
            second.setName("lastName");

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
