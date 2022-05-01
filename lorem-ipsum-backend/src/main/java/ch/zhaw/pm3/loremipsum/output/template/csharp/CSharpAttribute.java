package ch.zhaw.pm3.loremipsum.output.template.csharp;

import lombok.Data;

@Data
public class CSharpAttribute {
    private String name;
    private String type;

    public CSharpAttribute(String name, String type) {
        this.name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        this.type = type;
    }
}
