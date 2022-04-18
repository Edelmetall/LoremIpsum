package ch.zhaw.pm3.loremipsum.output.template.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JavaAttribute {

    public JavaAttribute(String name, String type) {
        this.name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        this.type = type;
    }

    private String name;
    private String type;

    /**
     * Gibt den Name des Attribute zurueck damit er als infix genutz werden kann. (Camelcase)
     * Bsp: "name" -> "Name" fuer getName()
     * @return Attributname fuer Infix formatiert
     */
    public String getInfixName() {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

}
