package ch.zhaw.pm3.loremipsum.generator.firstname;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

@Component
public class InitDataLoader implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitDataLoader.class);
    private final FirstNameRepo firstNameRepo;

    public InitDataLoader(@Autowired FirstNameRepo firstNameRepo) {
        this.firstNameRepo = firstNameRepo;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Set<FirstNameEntity> firstNameEntityList = new HashSet<>();
        for (LandEnum landEnum : LandEnum.values()) {
            try {
                if (landEnum == LandEnum.SWITZERLAND) {
                    getNameFromFile("data/firstname/firstNameMaleSwitzerland.txt")
                            .forEach(firstName -> firstNameEntityList.add(new FirstNameEntity(null, landEnum.name(), GenderEnum.MALE.name(), firstName)));
                    getNameFromFile("data/firstname/firstNameFemaleSwitzerland.txt.txt")
                            .forEach(firstName ->  firstNameEntityList.add(new FirstNameEntity(null, landEnum.name(), GenderEnum.FEMALE.name(), firstName)));
                    continue;
                }


                Faker faker = new Faker(new Locale(landEnum.getLocale()));
                IntStream.range(0, 500).forEach(i -> firstNameEntityList.add(
                        new FirstNameEntity(null, landEnum.name(), GenderEnum.DIV.name(), faker.name().firstName())));
            } catch (Exception ex) {
                logger.warn("Could not insert FirstName with {}", landEnum.getLocale());
            }
        }
        firstNameRepo.saveAll(firstNameEntityList);
    }

    private Set<String> getNameFromFile(String fileName) throws IOException {

        Set<String> stringList = new HashSet<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }

}
