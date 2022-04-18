package ch.zhaw.pm3.loremipsum.generator.data;

import javax.persistence.*;

/**
 * Entity fuer die Abbildung von FachBedingungen.
 */
@Entity(name = "BusinessConstraint")
public class BusinessConstraintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_constraint_gen")
    @SequenceGenerator(name = "business_constraint_gen", sequenceName = "business_constraint_seq", initialValue = 50)
    private Long id;

}
