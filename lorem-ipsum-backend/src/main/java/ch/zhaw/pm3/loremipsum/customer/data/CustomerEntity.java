package ch.zhaw.pm3.loremipsum.customer.data;

import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity(name = "CUSTOMER")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name="customer_gen", sequenceName="customer_seq", initialValue = 50)
    private Long id;
    private String firstName;
    private String lastName;

    @Column
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<TemplateEntity> ownedTemplate;

    protected CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Set<TemplateEntity> getOwnedTemplate() {
        if (this.ownedTemplate == null) {
            return new HashSet<>();
        }
        return this.ownedTemplate;
    }
}
