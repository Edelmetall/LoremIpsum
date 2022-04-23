package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;

import java.util.Set;

public abstract class AbstractEntryGenService {


    /**
     * Gibt einen Eintrag mit dem gewuenschten generat zurueck, ruft den eigentlichen Generator fur die
     * gefordeten Testdaten auf.
     * @param rowConfigurationDto konfiguration der Spalte
     * @param optionDtoSet optionen fuer die Konfiguration
     * @return Entry mit gewuenschten TestDaten
     */
    public EntryDto genEntry(RowConfigurationDto rowConfigurationDto, Set<OptionDto> optionDtoSet) {
        EntryDto entryDto = new EntryDto();
        entryDto.setName(rowConfigurationDto.getName());
        entryDto.setData(getData(rowConfigurationDto, optionDtoSet));
        return entryDto;
    }

    protected abstract String getData(RowConfigurationDto rowConfigurationDto, Set<OptionDto> optionDtoSet);

}
