package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RowEntryDto {
    private final List<String> entryList = new ArrayList<>();
}
