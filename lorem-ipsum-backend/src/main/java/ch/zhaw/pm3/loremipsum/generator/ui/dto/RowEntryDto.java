package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class RowEntryDto {
    private Set<EntryDto> entryList = new HashSet<>();

}
