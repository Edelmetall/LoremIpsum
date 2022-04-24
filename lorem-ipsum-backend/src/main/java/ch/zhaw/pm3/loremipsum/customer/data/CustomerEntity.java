package ch.zhaw.pm3.loremipsum.customer.data;

import ch.zhaw.pm3.loremipsum.generator.template.data.TemplateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity(name = "CUSTOMER")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", initialValue = 50)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private byte[] password;
    private LocalDateTime passwordResetAt;

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
