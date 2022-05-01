package ch.zhaw.pm3.loremipsum.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EntryTypeEnum {
    FIRST_NAME("First name", OptionEnum.LAND_CD, OptionEnum.GENDER),
    LAST_NAME("Last name", OptionEnum.LAND_CD, OptionEnum.GENDER),
    IBANR("IBAN"),
    TELE_NR("Phone number", OptionEnum.LAND_CD, OptionEnum.TELE_NR_FORMAT),
    DATE("Date"),
    DATE_TIME("Date and Time"),
    TIME("Time"),
    BOOLEAN("Boolean");

    private static final Map<String, EntryTypeEnum> displayNameToEnumMap = new HashMap<>();

    private final String displayName;
    private final List<OptionEnum> availableOptions;

    static {
        for (EntryTypeEnum e : EntryTypeEnum.values()) {
            displayNameToEnumMap.put(e.getDisplayName(), e);
        }
    }

    EntryTypeEnum(String displayName, OptionEnum... optionEnums) {
        this.displayName = displayName;
        this.availableOptions = List.of(optionEnums);
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<OptionEnum> getAvailableOptions() {
        return this.availableOptions;
    }

    public static EntryTypeEnum getEnumFromDisplayName(String displayName) {
        return displayNameToEnumMap.get(displayName);
    }
}
