package ch.zhaw.pm3.loremipsum.generator.template.data;

import javax.persistence.*;

@Entity(name = "Type")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_typ_gen")
    @SequenceGenerator(name = "data_typ_gen", sequenceName = "data_typ_seq", initialValue = 50)
    private Long id;

    private DataTypeEnum dataType;

    private RangeConstraint rangeConstraint;

    private LengthConstraint lengthConstraint;


}
