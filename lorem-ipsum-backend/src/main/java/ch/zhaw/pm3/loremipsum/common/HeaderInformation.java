package ch.zhaw.pm3.loremipsum.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HeaderInformation {

    private String name;
    private EntryTypeEnum entryTypeEnum;

}
