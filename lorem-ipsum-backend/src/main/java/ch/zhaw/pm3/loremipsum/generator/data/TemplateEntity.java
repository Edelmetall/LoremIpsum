package ch.zhaw.pm3.loremipsum.generator.data;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.apache.velocity.Template;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "TEMPLATE")
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "template_gen")
    @SequenceGenerator(name = "template_gen", sequenceName = "template_seq", initialValue = 50)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "templateEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RowTemplateEntity> rowTemplateEntities;

    protected TemplateEntity() {
    }

    public TemplateEntity(String name) {
        this.name = name;
    }

    public List<RowTemplateEntity> getRowTemplateEntities() {
        if (rowTemplateEntities == null) {
            this.rowTemplateEntities = new ArrayList<>();
        }
        return rowTemplateEntities;
    }

    @Override
    public String toString() {
        return "TemplateEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", rowTemplateEntities=" + getRowTemplateEntities() +
                '}';
    }
}
