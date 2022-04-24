package ch.zhaw.pm3.loremipsum.generator.template.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;


@Getter
@Setter
@Embeddable
public class RangeConstraint {

    @Column(name = "rangeFrom")
    private BigDecimal from;

    @Column(name = "rangeTo")
    private BigDecimal to;


    @Override
    public String toString() {
        return String.format(
                "RangeConstraint[from=%s, to='%s']",
                from, to);
    }

}
