package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntryDto {

    public EntryDto() {
    }

    private String name;
    private String data;

}
