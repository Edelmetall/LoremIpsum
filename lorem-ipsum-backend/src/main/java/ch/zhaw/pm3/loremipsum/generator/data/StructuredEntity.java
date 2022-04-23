package ch.zhaw.pm3.loremipsum.generator.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Entity fuer die abbildung von strukturierte Daten.
 */
@Entity(name = "Structured")
public class StructuredEntity extends DataFormatEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private DataFormatEntity dataFormatEntity;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BusinessConstraintEntity> businessConstraintEntitySet;

}
