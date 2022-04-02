package ch.zhaw.pm3.loremipsum.generator.data;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "templateEntity")
    private Set<RowTemplateEntity> rowTemplateEntities;

    protected TemplateEntity() {
    }

    public TemplateEntity(String name) {
        this.name = name;
    }

    public Set<RowTemplateEntity> getRowTemplateEntities() {
        if (rowTemplateEntities == null) {
            this.rowTemplateEntities = new HashSet<>();
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
