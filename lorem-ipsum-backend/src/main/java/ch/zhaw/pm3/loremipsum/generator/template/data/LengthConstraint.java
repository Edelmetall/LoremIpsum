package ch.zhaw.pm3.loremipsum.generator.template.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class LengthConstraint {

    @Column(name = "lengthFrom")
    private long from;

    @Column(name = "lengthTo")
    private long to;



    @Override
    public String toString() {
        return String.format(
                "LengthConstraint[from=%d, to='%d']",
                from, to);
    }

}
