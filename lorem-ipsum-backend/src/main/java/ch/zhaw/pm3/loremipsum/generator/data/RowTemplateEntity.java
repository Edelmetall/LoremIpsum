package ch.zhaw.pm3.loremipsum.generator.data;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne()
    @JoinColumn(name="data_format_id")
    @JsonBackReference
    private DataFormatEntity dataFormatEntity;

    //private String dataType;

    private String name;
    private String example;
    private String option;
    private String regex;

    @ManyToOne
    @JoinColumn(name="template_id")
    @JsonBackReference
    private TemplateEntity templateEntity;

    @Override
    public String toString() {
        return String.format(
                "RowTemplateEntity[from=%d, to='%d']",
                id, index);
    }

}
