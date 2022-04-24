package ch.zhaw.pm3.loremipsum.generator.firstname;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "FIRST_NAME")
@AllArgsConstructor
@Getter
public class FirstNameEntity {


    protected FirstNameEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "first_name_gen")
    @SequenceGenerator(name = "first_name_gen", sequenceName = "first_name_seq", initialValue = 50)
    private Long id;

    @Column
    private String landCategory;

    @Column
    private String gender;

    @Column
    private String value;

}
