package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class RowEntryDto {
    private final List<EntryDto> entryList = new ArrayList<>();
}
