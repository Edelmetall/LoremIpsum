package ch.zhaw.pm3.loremipsum.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents fields that the user can choose to generate
 */
public enum EntryTypeEnum {
    FIRST_NAME("First name", OptionEnum.LAND_CD, OptionEnum.GENDER),
    LAST_NAME("Last name", OptionEnum.LAND_CD, OptionEnum.GENDER),
    IBANR("IBAN", OptionEnum.LAND_CD),
    PHONE_NUMBER("Phone number", OptionEnum.LAND_CD, OptionEnum.PHONE_NUMER_FORMAT),
    DATE("Date"),
    DATE_TIME("Date and Time"),
    GUID("GUID/UUID"),
    TIME("Time"),
    BOOLEAN("Boolean"),
    PAN("PAN Nr");

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

    /**
     * text to display for this enum
     *
     * @return display text
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * returns a list of available options for this enum
     *
     * @return options
     */
    public List<OptionEnum> getAvailableOptions() {
        return this.availableOptions;
    }

    /**
     * find the enum by its display name
     *
     * @param displayName display name (text that is shown to the user)
     * @return enum
     */
    public static EntryTypeEnum getEnumFromDisplayName(String displayName) {
        return displayNameToEnumMap.get(displayName);
    }
}
