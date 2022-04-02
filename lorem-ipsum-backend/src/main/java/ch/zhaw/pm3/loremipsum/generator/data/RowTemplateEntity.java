package ch.zhaw.pm3.loremipsum.generator.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "ROW_TEMPLATE")
public class RowTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_gen")
    @SequenceGenerator(name="row_gen", sequenceName="row_seq", initialValue = 50)
    private Long id;

    private int index;

    @ManyToOne
    private DataFormatEntity dataFormatEntity;

    @ManyToOne
    @JoinColumn(name="template_id", nullable=false)
    private TemplateEntity templateEntity;


    @Override
    public String toString() {
        return String.format(
                "RowTemplateEntity[from=%d, to='%d']",
                id, index);
    }

}
