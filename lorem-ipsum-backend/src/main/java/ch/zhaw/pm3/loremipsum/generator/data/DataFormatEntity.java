package ch.zhaw.pm3.loremipsum.generator.data;

import javax.persistence.*;

@Entity(name = "DataFomat")
@Inheritance(strategy = InheritanceType.JOINED)
public class DataFormatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_format_gen")
    @SequenceGenerator(name = "data_format_gen", sequenceName = "data_format_seq", initialValue = 50)
    private Long id;

    private String name;

    private boolean active;

}
